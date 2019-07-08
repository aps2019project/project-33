package View.CollectionMenu.ShowDeck;

import Controller.Client.Client;
import Controller.Client.ClientMassage;
import Controller.MenuList;
import Controller.Server.Server;
import Controller.Server.ServerMassage;
import Model.Collection;
import Model.Deck;
import View.CollectionMenu.DeleteDeck.DeleteDeckController;
import com.sun.org.apache.xalan.internal.xsltc.dom.CachedNodeListIterator;
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

public class SelectDeckController implements Initializable {
    public AnchorPane mainPane;
    public ImageView duelystImage;
    public ImageView backButton;
    public Label okButton;

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

        okButton.setOnMouseClicked(event -> {
            try {
                if(selectedDeck != null) {
                    ServerMassage serverMassage = Client.getClient().collectionMenuCommand(ClientMassage.CollectionMenuRequest.ShowDeck,
                            selectedDeck, null, null);
                    isFirstTime = true;
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

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

    public static void clearShadows(){
        for(Label label : deckLabels){
            label.getStyleClass().remove("SelectedLabel");
        }
    }
}
