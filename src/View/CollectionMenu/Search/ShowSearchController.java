package View.CollectionMenu.Search;

import Controller.Client;
import Controller.MenuList;
import View.Graphic;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class ShowSearchController implements Initializable {
    public AnchorPane mainPane;
    public ImageView backButton;
    public ImageView duelystImage;

    public static boolean isFirstTime = true;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(isFirstTime){
            VBox vBox = Graphic.createCards(Client.getClient().getResultOfSearch());
            vBox.setLayoutY(100);
            vBox.setLayoutX(100);
            mainPane.getChildren().add(vBox);
        }
        isFirstTime = false;
        backButton.setOnMouseClicked(event -> {
            Client.getClient().setCurrentMenu(MenuList.CollectionSearch);
            isFirstTime = true;
            mainPane.getChildren().clear();
        });
    }
}
