package View.Battle;

import Controller.Client;
import Model.CollectionItem.CollectionItem;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class BattleController implements Initializable {
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
    private HandItem[] handItems;
    private VBox[] handPanes;

    //todo, in ke roo ye nafar click kardim biad info ro neshoon bede
    //todo, in ke betoone ye chizio select kone
    //todo, move kone
    //todo, attack kone
    //todo, attack e combo
    //todo, special power o f something
    //todo, show hand chie dg
    //todo, insert konim roo ye chizi
    //todo, end turn -> in ke kharab nashe ham check konim ->>> kolliatesh done
    //todo, show collectibles
    //todo, show info
    //todo, use ?!
    //todo, show next card
    //todo, enter grave yard
    //todo, help

    //todo, end game
    //todo, exit


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        rows = new HBox[]{hBox1, hBox2, hBox3, hBox4, hBox5};
        handImages = new ImageView[]{handItemImage1, handItemImage2, handItemImage3, handItemImage4, handItemImage5};
        handManaLabels = new Label[]{handItemLabel1, handItemLabel2, handItemLabel3, handItemLabel4, handItemLabel5};
        handPanes = new VBox[]{vBox1, vBox2, vBox3, vBox4, vBox5};
        handItems = new HandItem[handImages.length];

        for (int i = 0; i < handItems.length; i++) {
            handItems[i] = new HandItem(handImages[i], handManaLabels[i], handPanes[i]);
        }

        forfeitButton.setOnMouseClicked(event -> {
            Client.getClient().getRunningBattle().inputCommandLine("forfeit match");
        });

        endTurnButton.setOnMouseClicked(event -> {
            Client.getClient().getRunningBattle().inputCommandLine("end turn");
        });

        helpButton.setOnMouseClicked(event -> {
            //todo showing game info in where ?
        });

    }
}

class HandItem {
    private ImageView imageView;
    private Label manaLabel;
    private VBox pane;
    private CollectionItem collectionItem;

    public HandItem(ImageView imageView, Label manaLabel, VBox pane) {
        this.imageView = imageView;
        this.manaLabel = manaLabel;
        this.pane = pane;
    }
}

class Cell{
    private VBox pane;
    private ImageView imageView;

}