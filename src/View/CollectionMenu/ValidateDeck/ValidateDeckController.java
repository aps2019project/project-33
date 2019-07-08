package View.CollectionMenu.ValidateDeck;

import Controller.Client.Client;
import Controller.Client.ClientMassage;
import Controller.MenuList;
import Controller.Server.ServerMassage;
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
import sun.font.CoreMetrics;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ValidateDeckController implements Initializable {
    public AnchorPane mainPane;
    public ImageView duelystImage;
    public ImageView backButton;
    public Label validateLabel;

    public static Deck selectedDeck;
    public static boolean isFirstTime = true;
    public static VBox labelsVBox = new VBox();
    public static ArrayList<Label> deckLabels = new ArrayList<>();
    public static ArrayList<Deck> decks = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(isFirstTime){
            validateLabel.setText("");
            selectedDeck = null;
            labelsVBox.getChildren().clear();
            mainPane.getChildren().remove(labelsVBox);
            deckLabels.clear();
            //TODO deckaro chejuri bayad begiram?
            //TODO nabayad az collectione account bekhunam
            ServerMassage serverMassage = null;
            try {
                serverMassage = Client.getClient().collectionMenuCommand(ClientMassage.CollectionMenuRequest.GiveCollection);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            Collection collection = serverMassage.getCollection();
            decks = collection.getDecks();

            for (Deck deck : decks) {
                Label label = new Label(deck.getName());
                label.setTextFill(Color.WHITE);

                label.getStylesheets().add(DeleteDeckController.class.getResource("Label.css").toExternalForm());
                label.getStyleClass().add("DeckLabel");

                labelsVBox.getChildren().add(label);
                deckLabels.add(label);
            }
            VBox.setMargin(labelsVBox, new Insets(0, 0, 5, 0));
            labelsVBox.setLayoutY(90);
            labelsVBox.setLayoutX(170);
            mainPane.getChildren().add(labelsVBox);
        }
        isFirstTime = false;
        int index = 0;
        for (Label label : deckLabels) {
            final int temp = index;
            label.setOnMouseClicked(event -> {
                selectedDeck = decks.get(temp);
                //todo in ro tu shabake bayad doros konam
                ServerMassage serverMassage = null;
                try {
                    serverMassage = Client.getClient().collectionMenuCommand(ClientMassage.CollectionMenuRequest.ValidateDeck,
                            selectedDeck, null, null);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                boolean validity = serverMassage.getValidateDeck();
                String showString = "";
                if(validity)
                    showString = "VALID";
                else
                    showString = "INVALID";
                validateLabel.setText(showString);
                validateLabel.setTextFill(Color.DARKRED);

                clearShadows();
                label.getStyleClass().add("SelectedLabel");
            });
            index++;
        }

        backButton.setOnMouseClicked(event -> {
            try {
                Client.getClient().changeCurrentMenu(MenuList.CollectionMenu);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            isFirstTime = true;
        });
    }

    public static void clearShadows() {
        for (Label label : deckLabels) {
            label.getStyleClass().remove("SelectedLabel");
        }
    }
}
