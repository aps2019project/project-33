package View.CollectionMenu.Search;

import Controller.Client.Client;
import Controller.MenuList;
import Model.Collection;
import Model.CollectionItem.CollectibleItem;
import Model.CollectionItem.CollectionItem;
import View.Graphic;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ShowSearchController implements Initializable {
    public AnchorPane mainPane;
    public ImageView backButton;
    public ImageView duelystImage;

    public static boolean isFirstTime = true;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(isFirstTime){
            ArrayList<CollectionItem> collectionItems = SearchController.serverMassage.getCollectionItems();
            VBox vBox = null;
            try {
                vBox = Graphic.createCards(collectionItems);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            vBox.setLayoutY(100);
            vBox.setLayoutX(100);
            mainPane.getChildren().add(vBox);
        }
        isFirstTime = false;
        backButton.setOnMouseClicked(event -> {
            try {
                Client.getClient().changeCurrentMenu(MenuList.CollectionSearch);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            isFirstTime = true;
            mainPane.getChildren().clear();
        });
    }
}
