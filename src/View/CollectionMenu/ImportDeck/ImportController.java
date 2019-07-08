package View.CollectionMenu.ImportDeck;

import Controller.Application;
import Controller.Client.Client;
import Controller.Client.ClientMassage;
import Controller.MenuList;
import Controller.Server.Server;
import Controller.Server.ServerMassage;
import Model.Account;
import Model.Collection;
import Model.CollectionItem.*;
import Model.Deck;
import com.sun.org.apache.xalan.internal.xsltc.dom.CachedNodeListIterator;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ImportController implements Initializable {
    public AnchorPane mainPane;
    public ImageView duelystImage;
    public ImageView backButton;
    public Label importButton;
    public TextField nameField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        backButton.setOnMouseClicked(event -> {
            try {
                Client.getClient().changeCurrentMenu(MenuList.CollectionMenu);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        importButton.setOnMouseClicked(event -> {
            ServerMassage serverMassage = null;
            try {
                serverMassage = Client.getClient().collectionMenuCommand(ClientMassage.CollectionMenuRequest.Import,
                        null, null, nameField.getText());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            if(serverMassage.getType() == ServerMassage.Type.Accept) {
                try {
                    Client.getClient().changeCurrentMenu(MenuList.CollectionMenu);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
