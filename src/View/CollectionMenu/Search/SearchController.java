package View.CollectionMenu.Search;

import Controller.MenuList;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SearchController implements Initializable {
    public ImageView backButton;
    public Label searchLabel;
    public TextField nameField;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
      /*  backButton.setOnMouseClicked(event -> {
            Client.getClient().setCurrentMenu(MenuList.CollectionMenu);
        });

        searchLabel.setOnMouseClicked(event -> {
            try {
                Client.getClient().getCollectionMenu().inputCommandLine("search " + nameField.getText());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });*/
    }
}
