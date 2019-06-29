package View;

import Controller.Client;
import Controller.MenuList;
import javafx.animation.AnimationTimer;
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


        AnimationTimer animationTimer = new AnimationTimer() {
            private MenuList previousMenu = MenuList.AccountMenu;
            long unit = 1000000000, last = 0, fps = 10;
            @Override
            public void handle(long now) {
                if(last == 0) last = now;
                if(now > last + unit / fps) {
                    last = now;

                    if (!Client.getClient().getCurrentMenu().equals(previousMenu)) {
                        previousMenu = Client.getClient().getCurrentMenu();
                        try {
                            showMenu(primaryStage, previousMenu);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
        };
        animationTimer.start();


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
