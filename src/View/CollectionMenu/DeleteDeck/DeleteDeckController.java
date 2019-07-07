package View.CollectionMenu.DeleteDeck;

import Controller.Application;
import Controller.MenuList;
import Model.Account;
import Model.Collection;
import Model.Deck;
import View.Graphic;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import org.omg.CORBA.INITIALIZE;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DeleteDeckController implements Initializable {
    public AnchorPane mainAnchor;
    public ImageView backButton;
    public ImageView duelystImage;
    public Label deleteButton;

    public static Deck selectedDeck;
    public static boolean isFirstTime = true;
    public static VBox labelsVBox = new VBox();
    public static ArrayList<Label> deckLabels = new ArrayList<>();
    public static ArrayList<Deck> decks = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /*if(isFirstTime){
            selectedDeck = null;
            labelsVBox.getChildren().clear();
            mainAnchor.getChildren().remove(labelsVBox);
            deckLabels.clear();
            //TODO deckaro chejuri bayad begiram?
            //TODO nabayad az collectione account bekhunam
            Collection collection = Client.getClient().getCollection();
            decks = collection.getDecks();
            System.out.println(decks.size() + " +++++++++");

            for(Deck deck : decks){
                Label label = new Label(deck.getName());
                label.setTextFill(Color.WHITE);

                label.getStylesheets().add(DeleteDeckController.class.getResource("Label.css").toExternalForm());
                label.getStyleClass().add("DeckLabel");

                labelsVBox.getChildren().add(label);
                deckLabels.add(label);
            }
            VBox.setMargin(labelsVBox, new Insets(0, 0, 5, 0));
            labelsVBox.setLayoutY(120);
            labelsVBox.setLayoutX(150);
            mainAnchor.getChildren().add(labelsVBox);
        }
        isFirstTime = false;
        int index = 0;
        for(Label label : deckLabels){
            final int temp = index;
            label.setOnMouseClicked(event -> {
                selectedDeck = decks.get(temp);
                clearShadows();
                label.getStyleClass().add("SelectedLabel");
            });
            index++;
        }

        System.out.println(" ::::::::::::: " + index);
        deleteButton.setOnMouseClicked(event -> {
            try {
                if(selectedDeck != null) {
                    Client.getClient().getCollectionMenu().inputCommandLine("delete deck " + selectedDeck.getName());
                    Client.getClient().setCurrentMenu(MenuList.CollectionMenu);
                    isFirstTime = true;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        backButton.setOnMouseClicked(event -> {
            Client.getClient().setCurrentMenu(MenuList.CollectionMenu);
            isFirstTime = true;

        });*/
    }

    public static void clearShadows(){
        for(Label label : deckLabels){
            label.getStyleClass().remove("SelectedLabel");
        }
    }
}
