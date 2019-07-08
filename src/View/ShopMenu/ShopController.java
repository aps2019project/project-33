package View.ShopMenu;
import Controller.MenuList;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class ShopController implements Initializable {
    public Label showCollectionLabel;
    public Label searchLabel;
    public Label searchCollectionLabel;
    public Label buyLabel;
    public Label sellLabel;
    public Label showLabel;
    public AnchorPane mainPane;
    public AnchorPane blurPane;
    public AnchorPane nonBlurPane;
    public ImageView imagePane;
    public VBox vBox;
    public Label createCardLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /*showCollectionLabel.setOnMouseClicked(event -> {
            Client.getClient().getShopMenu().inputCommandLine("show collection");
        });

        searchCollectionLabel.setOnMouseClicked(event -> {
            Client.getClient().setCurrentMenu(MenuList.ShopSearchCollection);
        });

        buyLabel.setOnMouseClicked(event -> {
            Client.getClient().setCurrentMenu(MenuList.BuyMenu);
        });

        sellLabel.setOnMouseClicked(event -> {
            Client.getClient().getShopMenu().inputCommandLine("show collection for sell");
        });

        imagePane.setOnMouseClicked(event -> {
            Client.getClient().getShopMenu().inputCommandLine("exit");
        });

        showLabel.setOnMouseClicked(event -> {
            Client.getClient().getShopMenu().inputCommandLine("show");
        });

        searchLabel.setOnMouseClicked(event -> {
            Client.getClient().setCurrentMenu(MenuList.SearchShop);
        });

        createCardLabel.setOnMouseClicked(event -> {
            Client.getClient().setCurrentMenu(MenuList.ShopCreateCard);
        });*/
    }
}
