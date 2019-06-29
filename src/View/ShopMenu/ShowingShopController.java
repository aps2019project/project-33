package View.ShopMenu;

import Controller.Client;
import Controller.MenuList;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ShowingShopController implements Initializable {
    public AnchorPane nonBlurAnchor;
    public AnchorPane blurAnchor;
    public AnchorPane mainAnchor;
    public static boolean isFirstTime = true;
    public ImageView backButton;
    public ArrayList<VBox> cardVboxes = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(isFirstTime){
            VBox cardVbox = new VBox();
            Image image = new Image("file:1.gif");
            ImageView cardGif = new ImageView(image); //("../../../resources/codex/chapter19_background@2x.jpg");
            cardVbox.getChildren().add(cardGif);
            cardVbox.setLayoutY(10);
            cardVbox.setLayoutY(10);
            cardVboxes.add(cardVbox);
            nonBlurAnchor.getChildren().addAll(cardVboxes);
        }
        isFirstTime = false;
        backButton.setOnMouseClicked(event -> {
            Client.getClient().setCurrentMenu(MenuList.ShopMenu);
            isFirstTime = true;
        });
    }
}
