package View.CollectionMenu.CreateDeck;

import Controller.MenuList;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CreateDeckController implements Initializable {
    public AnchorPane mainPane;
    public TextField nameField;
    public Label createLabel;
    public ImageView duelystImage;
    public ImageView backButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
      /*  backButton.setOnMouseClicked(event -> {
            Client.getClient().setCurrentMenu(MenuList.CollectionMenu);
        });

        createLabel.setOnMouseClicked(event -> {
            try {
                Client.getClient().getCollectionMenu().inputCommandLine("create deck " + nameField.getText());
                nameField.setText("");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });*/
    }
}
