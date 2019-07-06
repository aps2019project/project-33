package View.CollectionMenu.ExportDeck;

import Controller.Application;
import Controller.Client;
import Controller.MenuList;
import Model.Collection;
import Model.Deck;
import View.CollectionMenu.DeleteDeck.DeleteDeckController;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ExportController implements Initializable {
    public AnchorPane mainPane;
    public ImageView duelystImage;
    public ImageView backButton;
    public Label exportButton;

    public static Deck selectedDeck;
    public static boolean isFirstTime = true;
    public static VBox labelsVBox = new VBox();
    public static ArrayList<Label> deckLabels = new ArrayList<>();
    public static ArrayList<Deck> decks = new ArrayList<>();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(isFirstTime){
            selectedDeck = null;
            labelsVBox.getChildren().clear();
            mainPane.getChildren().remove(labelsVBox);
            deckLabels.clear();
            //TODO deckaro chejuri bayad begiram?
            //TODO nabayad az collectione account bekhunam
            Collection collection = Client.getClient().getCollection();
            decks = collection.getDecks();

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
            labelsVBox.setLayoutX(170);
            mainPane.getChildren().add(labelsVBox);
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

        exportButton.setOnMouseClicked(event -> {
            try {
                if(selectedDeck != null) {
                    //todo neveshtan too file json
                    Application.writeJSON(selectedDeck,  "../../../../Data/ExportedDecks/" + selectedDeck.getName() + ".json");
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
        });
    }

    public static void clearShadows(){
        for(Label label : deckLabels){
            label.getStyleClass().remove("SelectedLabel");
        }
    }
}
