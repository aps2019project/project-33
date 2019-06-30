package View;

import Controller.Client;
import Controller.MenuList;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class View extends Application {
    private static Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Hello World");
        Parent root = FXMLLoader.load(getClass().getResource("AccountMenu/AccountMenu.fxml"));
        primaryStage.setScene(new Scene(root));
        View.primaryStage = primaryStage;


        AnimationTimer animationTimer = new AnimationTimer() {
            private MenuList previousMenu = MenuList.AccountMenu;
            long unit = 1000000000, last = 0, fps = 10;

            @Override
            public void handle(long now) {
                if (last == 0) last = now;
                if (now > last + unit / fps) {
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

        System.out.println(newMenu + " " + address);

        Parent root = FXMLLoader.load(getClass().getResource(address));
        primaryStage.setScene(new Scene(root));

    }

    public static void main(String[] args) {
        launch(args);
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void setPrimaryStage(Stage primaryStage) {
        View.primaryStage = primaryStage;
    }
}
