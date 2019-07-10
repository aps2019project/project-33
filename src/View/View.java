package View;

import Controller.Client.Client;
import Controller.MenuList;
import View.Battle.BattleController;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;

public class View extends Application {
    private static Stage primaryStage;
    public static MediaView mediaView;
    public static AnchorPane mainRoot;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Account Menu");
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
                    MenuList currentMenu = null;
                    try {
                        currentMenu = Client.getClient().getCurrentMenu().getCurrentMenu();
                    } catch (IOException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    if (!currentMenu.equals(previousMenu)) {
                        if(previousMenu == MenuList.Battle)
                            BattleController.getAnimationTimer().stop();

                        previousMenu = currentMenu;
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

    public static void addMusic(String address, AnchorPane root, boolean repeat){
        Media pick = new Media(new File(address).toURI().toString());
        MediaPlayer player = new MediaPlayer(pick);
        MediaView mediaView = new MediaView(player);
        root.getChildren().add(mediaView);
        if (repeat)
            player.setCycleCount(-1);
        player.play();
    }
}
