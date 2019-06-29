package View.ShopMenu;

import Controller.Client;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import javax.swing.event.CaretListener;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        imagePane.setOnMouseClicked(event -> {
            Client.getClient().getShopMenu().inputCommandLine("exit");
        });
        showLabel.setOnMouseClicked(event -> {
            Client.getClient().getShopMenu().inputCommandLine("show");
        });
        showCollectionLabel.setOnMouseClicked(event -> {
            Client.getClient().getShopMenu().inputCommandLine("show collection");
        });
    }
}
