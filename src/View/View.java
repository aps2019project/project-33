package View;

import Controller.Client;
import Controller.MenuList;
import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Stack;

public class View extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Hello World");
        Parent root = FXMLLoader.load(getClass().getResource("AccountMenu/AccountMenu.fxml"));
        primaryStage.setScene(new Scene(root));


        Task task = new Task() {
            private MenuList previousMenu = MenuList.AccountMenu;
            @Override
            protected Object call() throws Exception {
                if(!Client.getClient().getCurrentMenu().equals(previousMenu)){
                    previousMenu = Client.getClient().getCurrentMenu();
                    showMenu(primaryStage, previousMenu);
                }
                Thread.sleep(200);
                return null;
            }
        };


        primaryStage.show();
    }

    private void showMenu(Stage primaryStage, MenuList newMenu) throws IOException {
        primaryStage.setTitle(newMenu.toString());
        String address = newMenu.getAddressOfFile();

        Parent root = FXMLLoader.load(getClass().getResource(address));
        primaryStage.setScene(new Scene(root));

    }

    public static void main(String[] args){
        launch(args);
    }
}
