package View.ShopMenu;
import Controller.Client.Client;
import Controller.Client.ClientMassage;
import Controller.MenuList;
import Controller.Server.ServerMassage;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.io.IOException;
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

    public static ServerMassage serverMassage;
    public static boolean isFirstTime = true;
    public Label budgetLabel;
    public Label budgetNameLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(isFirstTime){
            ServerMassage tempServerMassage = null;
            try {
                tempServerMassage = Client.getClient().shopMenuCommand(ClientMassage.ShopMenuRequest.GiveBudget, null,
                        null);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            budgetLabel.setTextFill(Color.WHITE);
            budgetNameLabel.setTextFill(Color.WHITE);
            budgetLabel.setText(Integer.toString(tempServerMassage.getBudget()));
        }
        isFirstTime = false;
        showCollectionLabel.setOnMouseClicked(event -> {
            try {
                serverMassage = Client.getClient().shopMenuCommand(ClientMassage.ShopMenuRequest.GiveCollection, null, null);
                System.out.println("HEEEEEEEEEEIIIIIIIII im here!");
                isFirstTime = true;
                Client.getClient().changeCurrentMenu(MenuList.ShopShowCollection);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        searchCollectionLabel.setOnMouseClicked(event -> {
            try {
                Client.getClient().changeCurrentMenu(MenuList.ShopSearchCollection);
                isFirstTime = true;
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        buyLabel.setOnMouseClicked(event -> {
            try {
                isFirstTime = true;
                Client.getClient().changeCurrentMenu(MenuList.BuyMenu);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        sellLabel.setOnMouseClicked(event -> {
            try {
                isFirstTime = true;
                Client.getClient().changeCurrentMenu(MenuList.SellMenu);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        imagePane.setOnMouseClicked(event -> {
            try {
                isFirstTime = true;
                Client.getClient().changeCurrentMenu(MenuList.MainMenu);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        showLabel.setOnMouseClicked(event -> {
            try {
                isFirstTime = true;
                Client.getClient().changeCurrentMenu(MenuList.ShowShop);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        searchLabel.setOnMouseClicked(event -> {
            try {
                isFirstTime = true;
                Client.getClient().changeCurrentMenu(MenuList.SearchShop);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        createCardLabel.setOnMouseClicked(event -> {
            try {
                isFirstTime = true;
                Client.getClient().changeCurrentMenu(MenuList.ShopCreateCard);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
    }
}
