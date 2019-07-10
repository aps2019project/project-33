package View.Battle;

import Controller.Battle;
import Controller.Client.Client;
import Controller.Client.ClientMassage;
import Controller.MenuList;
import Controller.Server.ServerMassage;
import Model.Buffs.Buff;
import Model.CollectionItem.*;
import Model.Enviroment.Cell;
import Model.Hand1;
import View.Graphic;
import View.View;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

//todo, kollan flag catch nemishavad
//todo, an che ke mande az battle.
//1. tarahi e bakhshe custom
//2. harekate peivaste, animationi
//3. neshan dadane item ha o flag
//4. namayeshe asare spell o buff moonde
//5. asare khane
//6. grave yard

public class BattleController implements Initializable {
    private static ImageView movingImageView = null;
    public Label sendCheatButton;

    public Label fastForwardButton;
    public Label enterGraveYard;
    public TextField cheatText;
    private int numberOfRows = 5, numberOfColumns = 9;
    public AnchorPane rootOfPage;
    public VBox cardInformationArea;
    public Label playerUsernameLabel;
    public HBox manaArea;
    public ImageView forfeitButton;
    public HBox hBox1;
    public HBox hBox2;
    public HBox hBox3;
    public HBox hBox4;
    public HBox hBox5;
    public Label endTurnButton;
    public VBox table;
    public ImageView handItemImage1;
    public Label handItemLabel1;
    public ImageView handItemImage2;
    public Label handItemLabel2;
    public ImageView handItemImage3;
    public Label handItemLabel3;
    public ImageView handItemImage4;
    public Label handItemLabel4;
    public ImageView handItemImage5;
    public Label handItemLabel5;
    public VBox vBox1;
    public VBox vBox2;
    public VBox vBox3;
    public VBox vBox4;
    public VBox vBox5;
    public Label remainTimeLabel;

    private HBox[] rows;
    private ImageView[] handImages;
    private Label[] handManaLabels;
    private HandUnit[] handUnits;
    private VBox[] handPanes;
    private SelectedCell selectedCell = null;
    private GraphicalCell[][] graphicalCells;
    private ImageView[] manaUnits;
    private VBox cardInformationVBox;
    private static AnimationTimer animationTimer;
    private int timeOfMove = 500;
    private int fps = 30;
    private int counter;

    //todo, in ke alan chand ta mana darim masalan moonde
    //todo, enter grave yard

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        View.addMusic("resources/sfx/battle.mp3", rootOfPage, true, true);

        try {
            setter();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        update();
        handUnitActions();
        graphicalCellsActions();

        forfeitButton.setOnMouseClicked(event -> {
            try {
                Client.getClient().battleCommand(ClientMassage.BattleRequest.ForfeitMatch);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        endTurnButton.setOnMouseClicked(event -> {
            selectedCell = null;
            try {
                Client.getClient().battleCommand(ClientMassage.BattleRequest.EndTurn);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        fastForwardButton.setOnMouseClicked(event -> {
            try {
                Client.getClient().fastForward();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            timeOfMove /= 2;
        });

        enterGraveYard.setOnMouseClicked(event -> {
            try {
                Client.getClient().changeCurrentMenu(MenuList.GraveYard);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        sendCheatButton.setOnMouseClicked(event -> {
            String string = cheatText.getText();
            cheatText.clear();
            try {
                Client.getClient().sendCheatText(string);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

    }

    public static ImageView getMovingImageView() {
        return movingImageView;
    }

    public static void setMovingImageView(ImageView movingImageView) {
        BattleController.movingImageView = movingImageView;
    }

    private void graphicalCellsActions() {
        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfColumns; j++) {
                int finalI = i;
                int finalJ = j;
                graphicalCells[i][j].getAnchorPane().setOnDragDropped(event -> {
                    try {
                        graphicalCells[finalI][finalJ].select(this);
                    } catch (IOException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                });
                graphicalCells[i][j].getAnchorPane().setOnMouseClicked(event -> {
                    if (event.getButton() == MouseButton.PRIMARY) {
                        try {
                            graphicalCells[finalI][finalJ].select(this);
                        } catch (IOException | ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                    if (event.getButton() == MouseButton.SECONDARY) {
                        try {
                            graphicalCells[finalI][finalJ].attack(this);
                        } catch (IOException | ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                    if (event.getButton() == MouseButton.MIDDLE) {
                        try {
                            graphicalCells[finalI][finalJ].specialAttack(this);
                        } catch (IOException | ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                });
                graphicalCells[i][j].getAnchorPane().setOnMouseEntered(event -> {
                    if (graphicalCells[finalI][finalJ].getCell().getLivingCard() == null) return;
                    ;
                    cardInformationArea.getChildren().remove(cardInformationVBox);
                    try {
                        String name = graphicalCells[finalI][finalJ].getCell().getLivingCard().getName();
                        cardInformationVBox = Graphic.createCard(graphicalCells[finalI][finalJ].getCell().getLivingCard());
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    cardInformationArea.getChildren().add(cardInformationVBox);
                });
            }
        }
    }

    private void handUnitActions() {
        for (int i = 0; i < handUnits.length; i++) {
            int finalI = i;
            handUnits[i].getImageView().setOnMouseClicked(event -> {
                if (event.getButton() == MouseButton.PRIMARY) {
                    try {
                        handUnits[finalI].select(this);
                    } catch (IOException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            });
            handUnits[i].getImageView().setOnMouseDragged(event -> {
                try {
                    handUnits[finalI].select(this);
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            });
            handUnits[i].getImageView().setOnMouseEntered(event -> {
                if (handUnits[finalI].getCollectionItem() == null) return;
                cardInformationArea.getChildren().remove(cardInformationVBox);
                try {
                    String name = handUnits[finalI].getCollectionItem().getName();
                    cardInformationVBox = Graphic.createCard(handUnits[finalI].getCollectionItem());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                cardInformationArea.getChildren().add(cardInformationVBox);
            });
        }
    }

    private void update() {
        animationTimer = new AnimationTimer() {
            long last = 0, unit = 1000000000;
            boolean isMove = false;
            @Override
            public void handle(long now) {
                if (last == 0) last = now;
                if (now > last + unit / fps) {
                    if(!isMove && movingImageView != null){
                        isMove = true;
                        return;
                    }
                    if(isMove && counter > 0){
                        counter --;
                        return;
                    }
                    ServerMassage serverMassage = null;
                    try {
                        serverMassage = Client.getClient().getRunningGame();
                    } catch (IOException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    if(serverMassage.getType() == ServerMassage.Type.Error) return;
                    Battle runningBattle = serverMassage.getRunningBattle();

                    last = now;
                    for (int i = 0; i < handUnits.length; i++) {
                        try {
                            handUnits[i].update(runningBattle, i);
                        } catch (IOException | ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                    for (int i = 0; i < numberOfRows; i++)
                        for (int j = 0; j < numberOfColumns; j++) {
                            try {
                                graphicalCells[i][j].update(runningBattle, i, j);
                            } catch (IOException | ClassNotFoundException e) {
                                e.printStackTrace();
                            }
                        }
                    if (selectedCell != null)
                        selectedCell.update();
                    try {
                        updatePlayerInformation(runningBattle);
                    } catch (IOException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }

                    updateRemainTimeLabel(runningBattle);

                    isMove = false;
                    rootOfPage.getChildren().remove(movingImageView);
                    movingImageView = null;
                    counter = 0;
                }
            }
        };
        animationTimer.start();
    }

    private void setter() throws IOException, ClassNotFoundException {
        Battle battle = Client.getClient().getRunningGame().getRunningBattle();
        rows = new HBox[]{hBox1, hBox2, hBox3, hBox4, hBox5};
        handImages = new ImageView[]{handItemImage1, handItemImage2, handItemImage3, handItemImage4, handItemImage5};
        handManaLabels = new Label[]{handItemLabel1, handItemLabel2, handItemLabel3, handItemLabel4, handItemLabel5};
        handPanes = new VBox[]{vBox1, vBox2, vBox3, vBox4, vBox5};
        handUnits = new HandUnit[handImages.length];
        graphicalCells = new GraphicalCell[numberOfRows][numberOfColumns];
        for (int i = 0; i < handUnits.length; i++)
            handUnits[i] = new HandUnit(handImages[i], handManaLabels[i], handPanes[i]);
        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfColumns; j++) {
                graphicalCells[i][j] = new GraphicalCell(battle.getMap().getCellByCoordination(i, j));
                rows[i].getChildren().add(graphicalCells[i][j].getRoot());
                VBox.setMargin(graphicalCells[i][j].getRoot(), new Insets(5, 5, 5, 5));
            }
        }
    }

    private void updateRemainTimeLabel(Battle runningBattle) {
        int remainTime = runningBattle.getRemainTimeOfTurn();
        remainTimeLabel.setText(Integer.toString(remainTime));
        remainTimeLabel.getStyleClass().clear();
        if(remainTime >= runningBattle.getMaximumTimeOfTurn() * 2 / 3) remainTimeLabel.getStyleClass().add("GreenTime");
        if(remainTime < runningBattle.getMaximumTimeOfTurn() * 2 / 3 && remainTime >= runningBattle.getMaximumTimeOfTurn()/ 3) remainTimeLabel.getStyleClass().add("BlackTime");
        if(remainTime < runningBattle.getMaximumTimeOfTurn() / 3) remainTimeLabel.getStyleClass().add("RedTime");
    }

    public static AnimationTimer getAnimationTimer() {
        return animationTimer;
    }

    public void setSelectedCell(SelectedCell selectedCell) {
        this.selectedCell = selectedCell;
    }

    public SelectedCell getSelectedCell() {
        return selectedCell;
    }

    private void updatePlayerInformation(Battle runningBattle) throws IOException, ClassNotFoundException {

        //update username
        playerUsernameLabel.setText(runningBattle.getPlayerOn().getAccount().getUsername());

        //update mana units
        int maximumMana = runningBattle.getPlayerOn().getMana().getMaximumMana();
        int currentMana = runningBattle.getPlayerOn().getMana().getCurrentMana();
        manaArea.getChildren().clear();
        manaArea.getStylesheets().clear();
        manaArea.getStylesheets().add(this.getClass().getResource("Battle.css").toExternalForm());
        for (int i = 0; i < Integer.max(maximumMana, currentMana); i++) {
            Image image = null;
            try {
                FileInputStream fileInputStream;
                if (i < currentMana)
                    fileInputStream = new FileInputStream("resources/ui/icon_mana@2x.png");
                else
                    fileInputStream = new FileInputStream("resources/ui/icon_mana_inactive@2x.png");
                image = new Image(fileInputStream);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            ImageView imageView = new ImageView();
            imageView.setImage(image);
            imageView.setFitHeight(30);
            imageView.setFitWidth(30);
            manaArea.getChildren().add(imageView);
        }
        Label currentManaLabel = new Label(Integer.toString(currentMana));
        currentManaLabel.getStyleClass().add("CurrentManaLabel");
        Label separator = new Label("/");
        separator.getStyleClass().add("Separator");
        Label maximumManaLabel = new Label(Integer.toString(maximumMana));
        maximumManaLabel.getStyleClass().add("MaximumManaLabel");
        manaArea.getChildren().addAll(currentManaLabel, separator, maximumManaLabel);
    }

    public AnchorPane getRootOfPage() {
        return rootOfPage;
    }

    public int getTimeOfMove() {
        return timeOfMove;
    }

    public void setTimeOfMove(int timeOfMove) {
        this.timeOfMove = timeOfMove;
    }

    public int getFps() {
        return fps;
    }

    public void setFps(int fps) {
        this.fps = fps;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }
}

class SelectedCell {
    enum Type {
        Item, LivingCard, Spell;
    }

    enum Location {
        Hand, Map;
    }

    private GraphicalCell graphicalCell;
    private HandUnit handUnit;
    private Type type;
    private Location location;

    public SelectedCell(Type type, Location location, GraphicalCell graphicalCell, HandUnit handUnit) {
        this.type = type;
        this.location = location;
        this.graphicalCell = graphicalCell;
        this.handUnit = handUnit;
    }

    public void update() {
        if (this.location == Location.Map) {
            graphicalCell.getAnchorPane().getStyleClass().clear();
            graphicalCell.getAnchorPane().getStyleClass().add("SelectedCellCover");
        } else {
            handUnit.getPane().getStyleClass().clear();
            handUnit.getPane().getStyleClass().add("SelectedHandUnit");
        }
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public GraphicalCell getGraphicalCell() {
        return graphicalCell;
    }

    public HandUnit getHandUnit() {
        return handUnit;
    }
}

class HandUnit {
    private ImageView imageView;
    private Label manaLabel;
    private VBox pane;
    private CollectionItem collectionItem;

    public HandUnit(ImageView imageView, Label manaLabel, VBox pane) {
        this.imageView = imageView;
        this.manaLabel = manaLabel;
        this.pane = pane;
    }

    public void select(BattleController battleController) throws IOException, ClassNotFoundException {
        if (collectionItem == null) return;
        SelectedCell.Type type = null;
        if (collectionItem instanceof Item) type = SelectedCell.Type.Item;
        if (collectionItem instanceof Spell) type = SelectedCell.Type.Spell;
        if (collectionItem instanceof LivingCard) type = SelectedCell.Type.LivingCard;
        battleController.setSelectedCell(new SelectedCell(type, SelectedCell.Location.Hand, null, this));
        Client.getClient().selectCardInBattle(collectionItem.getID());
    }

    public void update(Battle runningBattle, int i) throws IOException, ClassNotFoundException {
        Hand1 hand = runningBattle.getPlayerOn().getHand();
        this.pane.getStyleClass().clear();
        this.pane.getStyleClass().add("HandUnit");
        if (hand.getHandCards().size() > i) {
            if (this.collectionItem == null || !this.collectionItem.getID().equals(hand.getCollectionItemByIndex(i).getID())) {
                this.collectionItem = hand.getCollectionItemByIndex(i);
                if (collectionItem instanceof Item) this.manaLabel.setText("0");
                else this.manaLabel.setText(Integer.toString(((Card) collectionItem).getMP()));
                imageView.setImage(Graphic.setPic(collectionItem));
            //    imageView.setImage(new Image(new FileInputStream("resources/unit_gifs/1.gif")));
            }
        }
        else {
            imageView.setImage(null);
            collectionItem = null;
            manaLabel.setText(null);
        }
    }


    public ImageView getImageView() {
        return imageView;
    }

    public CollectionItem getCollectionItem() {
        return collectionItem;
    }

    public VBox getPane() {
        return pane;
    }

    public void setPane(VBox pane) {
        this.pane = pane;
    }
}

class GraphicalCell {
    private int heightOfCell = 70, widthOfCell = 70;
    private Pane root;
    private AnchorPane anchorPane;
    private ImageView imageView;
    private Cell cell;

    public GraphicalCell(Cell cell) {
        this.root = new Pane();
        root.getStylesheets().add(getClass().getResource("Battle.css").toExternalForm());
        root.getStyleClass().add("CellPane");

        this.imageView = new ImageView();
        root.getChildren().add(imageView);
        imageView.setFitWidth(widthOfCell);
        imageView.setFitHeight(heightOfCell);

        this.anchorPane = new AnchorPane();
        root.getChildren().add(anchorPane);
        anchorPane.getStyleClass().add("CellCover");

        this.cell = cell;
    }

    public void update(Battle runningBattle, int i, int j) throws IOException, ClassNotFoundException {
        LivingCard livingCard = cell.getLivingCard();
        this.cell = runningBattle.getMap().getCellByCoordination(i, j);

        if (cell.getLivingCard() == null) {
            anchorPane.getStyleClass().clear();
            anchorPane.getStyleClass().add("CellCover");
            if (cell.isHaveFlag() && imageView.getImage() == null) {
                try {
                    FileInputStream fileInputStream = new FileInputStream("resources/ui/flag.gif");
                    imageView.setImage(new Image(fileInputStream));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            } else if (!cell.isHaveFlag())
                imageView.setImage(null);
        } else if (livingCard == null || !cell.getLivingCard().getID().equals(livingCard.getID()) || imageView.getImage() == null) {
            imageView.setImage(Graphic.setPic(cell.getLivingCard()));
        //    imageView.setImage(new Image(new FileInputStream("resources/unit_gifs/1.gif")));
        }
        if (cell.getLivingCard() != null) {
            System.out.println(cell.getX() + " " + cell.getY());
            if (runningBattle.getPlayerOn().haveCard(cell.getLivingCard().getID())) {
                anchorPane.getStyleClass().clear();
                anchorPane.getStyleClass().add("OurCellCover");
            } else {
                anchorPane.getStyleClass().clear();
                anchorPane.getStyleClass().add("EnemyCellCover");
            }
            if (cell.getLivingCard().getEffects().size() > 0) {
                System.out.println(i + " " + j);
                for (Buff buff : cell.getLivingCard().getEffects())
                    System.out.println(buff);
                anchorPane.getStyleClass().add("EffectInLivingCard");
            }
        }
        if (cell.getEffects().size() > 0 || cell.getItems().size() > 0) {
            System.out.println(i + " * " + j);
            for (Buff buff : cell.getEffects())
                System.out.println(buff);
            anchorPane.getStyleClass().add("EffectInCell");
        }
    }

    public void select(BattleController battleController) throws IOException, ClassNotFoundException {
        SelectedCell selectedCell = battleController.getSelectedCell();
        if (selectedCell == null) {
            if (cell.getLivingCard() == null) return;
            battleController.setSelectedCell(new SelectedCell(SelectedCell.Type.LivingCard, SelectedCell.Location.Map, this, null));
            Client.getClient().selectCardInBattle(cell.getLivingCard().getID());
        } else {
            if (selectedCell.getLocation() == SelectedCell.Location.Hand) {
                if (selectedCell.getType() == SelectedCell.Type.Item) {
                    Client.getClient().useItemInBattle(cell.getX(), cell.getY());
                    battleController.setSelectedCell(null);
                } else {
                    CollectionItem collectionItem = selectedCell.getHandUnit().getCollectionItem();
                    Client.getClient().insertCardInBattle(collectionItem.getID(), this.cell.getX(), this.cell.getY());
                    battleController.setSelectedCell(null);
                }
            }
            if (selectedCell.getLocation() == SelectedCell.Location.Map) {
                ServerMassage serverMassage = Client.getClient().moveCardInBattle(cell.getX(), cell.getY());
                battleController.setSelectedCell(null);
                if (serverMassage.getType() == ServerMassage.Type.Accept) {
                    View.addMusic("resources/sfx/move.m4a", battleController.getRootOfPage(), false, false);
                    showGraphicalMove(selectedCell, this, battleController);
                }
            }
        }
    }

    private void showGraphicalMove(SelectedCell selectedCell, GraphicalCell graphicalCell, BattleController battleController) {

        ImageView imageView = new ImageView();
        imageView.setFitHeight(heightOfCell);
        imageView.setFitWidth(widthOfCell);
        imageView.setImage(selectedCell.getGraphicalCell().getImageView().getImage());
        imageView.setX(selectedCell.getGraphicalCell().getImageView().localToScene(selectedCell.getGraphicalCell().imageView.getBoundsInLocal()).getMinX());
        imageView.setY(selectedCell.getGraphicalCell().getImageView().localToScene(selectedCell.getGraphicalCell().imageView.getBoundsInLocal()).getMinY());

        selectedCell.getGraphicalCell().getImageView().setImage(null);

        BattleController.setMovingImageView(imageView);
        battleController.getRootOfPage().getChildren().add(imageView);

        KeyValue xValue = new KeyValue(imageView.xProperty(), graphicalCell.getImageView().localToScene(graphicalCell.getImageView().getBoundsInLocal()).getMinX());
        KeyValue yValue = new KeyValue(imageView.yProperty(), graphicalCell.getRoot().localToScene(graphicalCell.getImageView().getBoundsInLocal()).getMinY());
        KeyFrame keyFrame = new KeyFrame(Duration.millis(battleController.getTimeOfMove()), xValue, yValue);
        Timeline timeline = new Timeline(keyFrame);
        timeline.play();

        battleController.setCounter(20);
        //battleController.getTimeOfMove() / (1000 / battleController.getFps()) + 3
    }

    public void attack(BattleController battleController) throws IOException, ClassNotFoundException {
        if (battleController.getSelectedCell() == null) return;
        if (battleController.getSelectedCell().getLocation() != SelectedCell.Location.Map) return;
        LivingCard livingCard = this.getCell().getLivingCard();
        if (livingCard != null) {
            ServerMassage serverMassage = Client.getClient().attackInBattle(livingCard.getID());
            if(serverMassage.getType() == ServerMassage.Type.Accept) {
                View.addMusic("resources/sfx/attack.m4a", battleController.getRootOfPage(), false, false);
            }
        }
        battleController.setSelectedCell(null);
    }

    public void specialAttack(BattleController battleController) throws IOException, ClassNotFoundException {
        //todo nemidunam alan masalan in ke ye nafare addi bekhad special bezane oon var check shode ya na
        if (battleController.getSelectedCell() == null) return;
        if (battleController.getSelectedCell().getLocation() != SelectedCell.Location.Map) return;
        Client.getClient().useSpecialPower(cell.getX(), cell.getY());
        battleController.setSelectedCell(null);
    }

    public Pane getRoot() {
        return root;
    }

    public void setRoot(Pane root) {
        this.root = root;
    }

    public AnchorPane getAnchorPane() {
        return anchorPane;
    }

    public void setAnchorPane(AnchorPane anchorPane) {
        this.anchorPane = anchorPane;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public Cell getCell() {
        return cell;
    }
}