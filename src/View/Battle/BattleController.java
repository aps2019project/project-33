package View.Battle;

import Controller.Client;
import Controller.MenuList;
import Model.CollectionItem.*;
import Model.Enviroment.Cell;
import Model.Hand1;
import View.Graphic;
import javafx.animation.AnimationTimer;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class BattleController implements Initializable {
    private int numberOfRows = 5, numberOfColumns = 9;
    public AnchorPane rootOfPage;
    public ImageView forfeitButton;
    public ImageView gameInfoButton;
    public HBox hBox1;
    public HBox hBox2;
    public HBox hBox3;
    public HBox hBox4;
    public HBox hBox5;
    public Label endTurnButton;
    public VBox table;
    public ImageView helpButton;
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

    private HBox[] rows;
    private ImageView[] handImages;
    private Label[] handManaLabels;
    private HandUnit[] handUnits;
    private VBox[] handPanes;
    private SelectedCell selectedCell = null;
    private GraphicalCell[][] graphicalCells;
    private VBox cardInformationVBox;

    //todo, in ke alan ma chand ta mana darim masalan moonde
    //todo, attack e combo
    //todo, special power of something
    //todo, enter grave yard
    //todo, attack kone

    //todo, in ke roo ye nafar click kardim biad info ro neshoon bede
    //todo, in ke betoone ye chizio select kone
    //todo, move kone
    //todo, insert konim roo ye chizi
    //todo, end turn -> in ke kharab nashe ham check konim ->>> kolliatesh done
    //todo, use ?!
    //todo, end game
    //todo, exit

    //todo, aslan nemidunam che juri gharare ye item ro asar bedim !

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setter();
        update();
        handUnitActions();
        graphicalCellsActions();

        forfeitButton.setOnMouseClicked(event -> {
            Client.getClient().getRunningBattle().inputCommandLine("forfeit match", Client.getClient().getUsername());
            Client.getClient().setCurrentMenu(MenuList.MainMenu);
        });

        endTurnButton.setOnMouseClicked(event -> {
            selectedCell = null;
            Client.getClient().getRunningBattle().inputCommandLine("end turn", Client.getClient().getUsername());
        });

        helpButton.setOnMouseClicked(event -> {
            //todo showing game info in where ?
        });
    }

    private void graphicalCellsActions() {
        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfColumns; j++) {
                int finalI = i;
                int finalJ = j;
                graphicalCells[i][j].getAnchorPane().setOnMouseClicked(event -> {
                    graphicalCells[finalI][finalJ].select(this);
                });
                graphicalCells[i][j].getAnchorPane().setOnMouseEntered(event -> {
                    if(graphicalCells[finalI][finalJ].getCell().getLivingCard() == null) return;;
                    rootOfPage.getChildren().remove(cardInformationVBox);
                    cardInformationVBox = Graphic.createCard(graphicalCells[finalI][finalJ].getCell().getLivingCard(), 1);
                    rootOfPage.getChildren().add(cardInformationVBox);
                });
            }
        }
    }

    private void handUnitActions() {
        for (int i = 0; i < handUnits.length; i++) {
            int finalI = i;
            handUnits[i].getImageView().setOnMouseClicked(event -> {
                handUnits[finalI].select(this);
            });
            handUnits[i].getImageView().setOnMouseEntered(event -> {
                System.out.println("salam haji");
                if(handUnits[finalI].getCollectionItem() == null) return;
                rootOfPage.getChildren().remove(cardInformationVBox);
                cardInformationVBox = Graphic.createCard(handUnits[finalI].getCollectionItem(), 1);
                rootOfPage.getChildren().add(cardInformationVBox);

            });
        }
    }

    private void update() {
        AnimationTimer animationTimer = new AnimationTimer() {
            long last = 0, unit = 1000000000, fps = 100;

            @Override
            public void handle(long now) {
                if (last == 0) last = now;
                if (now > last + unit / fps) {
                    last = now;
                    for (int i = 0; i < handUnits.length; i++)
                        handUnits[i].update(i);
                    for (int i = 0; i < numberOfRows; i++)
                        for (int j = 0; j < numberOfColumns; j++)
                            graphicalCells[i][j].update(i, j);
                    if (selectedCell != null)
                        selectedCell.update();
                }
            }
        };
        animationTimer.start();
    }

    private void setter() {
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
                graphicalCells[i][j] = new GraphicalCell(Client.getClient().getRunningBattle().getMap().getCellByCoordination(i, j));
                rows[i].getChildren().add(graphicalCells[i][j].getRoot());
                VBox.setMargin(graphicalCells[i][j].getRoot(), new Insets(5, 5, 5, 5));
            }
        }
    }

    public void setSelectedCell(SelectedCell selectedCell) {
        this.selectedCell = selectedCell;
    }

    public SelectedCell getSelectedCell() {
        return selectedCell;
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

    public void select(BattleController battleController) {
        if (collectionItem == null) return;
        SelectedCell.Type type = null;
        if (collectionItem instanceof Item) type = SelectedCell.Type.Item;
        if (collectionItem instanceof Spell) type = SelectedCell.Type.Spell;
        if (collectionItem instanceof LivingCard) type = SelectedCell.Type.LivingCard;
        battleController.setSelectedCell(new SelectedCell(type, SelectedCell.Location.Hand, null, this));
        Client.getClient().getRunningBattle().inputCommandLine("select " + collectionItem.getID(), Client.getClient().getUsername());
    }

    public void update(int i) {
        Hand1 hand = Client.getClient().getRunningBattle().getPlayerOn().getHand();
        this.pane.getStyleClass().clear();
        this.pane.getStyleClass().add("HandUnit");
        if (hand.getHandCards().size() > i) {
            if (this.collectionItem == null || !this.collectionItem.getID().equals(hand.getCollectionItemByIndex(i).getID())) {
                this.collectionItem = hand.getCollectionItemByIndex(i);
                if (collectionItem instanceof Item) this.manaLabel.setText("0");
                else this.manaLabel.setText(Integer.toString(((Card) collectionItem).getMP()));
                imageView.setImage(new Image(BattleController.class.getResource("1.gif").toExternalForm()));
            }
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

    public void update(int i, int j) {
        LivingCard livingCard = cell.getLivingCard();
        this.cell = Client.getClient().getRunningBattle().getMap().getCellByCoordination(i, j);
        if (cell.getLivingCard() == null) {
            anchorPane.getStyleClass().clear();
            anchorPane.getStyleClass().add("CellCover");
            imageView.setImage(null);
            return;
        } else if (!cell.getLivingCard().getID().equals(livingCard.getID()) || imageView.getImage() == null) {
            imageView.setImage(new Image(this.getClass().getResource("1.gif").toExternalForm()));
        }
        if (Client.getClient().getRunningBattle().getPlayerOn().haveCard(livingCard.getID())) {
            anchorPane.getStyleClass().clear();
            anchorPane.getStyleClass().add("OurCellCover");
        } else {
            anchorPane.getStyleClass().clear();
            anchorPane.getStyleClass().add("EnemyCellCover");
        }

    }

    public void select(BattleController battleController) {
        SelectedCell selectedCell = battleController.getSelectedCell();
        if (selectedCell == null) {
            if (cell.getLivingCard() == null) return;
            System.out.println(cell.getLivingCard().getName());
            battleController.setSelectedCell(new SelectedCell(SelectedCell.Type.LivingCard, SelectedCell.Location.Map, this, null));
            Client.getClient().getRunningBattle().inputCommandLine("select " + cell.getLivingCard().getID(), Client.getClient().getUsername());
        } else {
            if (selectedCell.getLocation() == SelectedCell.Location.Hand) {
                if (selectedCell.getType() == SelectedCell.Type.Item) {
                    Client.getClient().getRunningBattle().inputCommandLine("use " + cell.getX() + " " + cell.getY(), Client.getClient().getUsername());
                    battleController.setSelectedCell(null);
                } else {
                    CollectionItem collectionItem = selectedCell.getHandUnit().getCollectionItem();
                    Client.getClient().getRunningBattle().inputCommandLine(
                            "insert " + collectionItem.getID() + " in (" + this.cell.getX() + ", " + this.cell.getY() + ")", Client.getClient().getUsername());
                    battleController.setSelectedCell(null);
                }
            }
            if (selectedCell.getLocation() == SelectedCell.Location.Map) {
                Client.getClient().getRunningBattle().inputCommandLine("move to (" + cell.getX() + ", " + cell.getY() + ")", Client.getClient().getUsername());
                battleController.setSelectedCell(null);
            }
        }
    }

    public void show() {
        //todo in ja hanooz nmd bayad che konam
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