package View.ShopMenu;

import Controller.Client.Client;
import Controller.MenuList;
import View.Graphic;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

public class ShopShowSearchController implements Initializable {
    public AnchorPane nonBlurAnchor;
    public AnchorPane blurAnchor;
    public AnchorPane mainAnchor;
    public ImageView backButton;

    public static boolean isFirstTime = true;
    public static VBox cardsVbox = new VBox();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (isFirstTime) {
            nonBlurAnchor.getChildren().remove(cardsVbox);
            try {
                cardsVbox = Graphic.createCards(Client.getClient().getResultOfSearch());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            nonBlurAnchor.getChildren().add(cardsVbox);
            cardsVbox.setLayoutX(200);
            cardsVbox.setLayoutY(200);
        }
        isFirstTime = false;

        backButton.setOnMouseClicked(event -> {
            //todo in bayad doros she
            // Client.getClient().setCurrentMenu(MenuList.ShopMenu);
            isFirstTime = true;
        });

    }

}
