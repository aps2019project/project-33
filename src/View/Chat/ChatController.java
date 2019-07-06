package View.Chat;

import Controller.Client.Client;
import Controller.MenuList;
import Controller.Server.ServerMassage;
import Model.Massage;
import javafx.animation.AnimationTimer;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ChatController implements Initializable {
    public Label backButton;
    public TextArea massageField;
    public VBox chatArea;
    public Label sentButton;

    private AnimationTimer animationTimer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        backButton.setOnMouseClicked(event -> {
            animationTimer.stop();
            try {
                Client.getClient().changeCurrentMenu(MenuList.MainMenu);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        sentButton.setOnMouseClicked(event -> {
            if(massageField.getText().isEmpty()) return;
            String massageText = massageField.getText();
            try {
                Client.getClient().sendMassageInChat(massageText);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            massageField.clear();
        });

        animationTimer = new AnimationTimer() {
            long last = 0, unit = 1000000000, fps = 10;
            @Override
            public void handle(long now) {
                if(last == 0) last = now;
                if(now > last + unit / fps){
                    chatArea.getChildren().clear();
                    ArrayList<Massage> massages = null;
                    try {
                        ServerMassage serverMassage = Client.getClient().getAllMassages();
                        massages = serverMassage.getMassages();
                    } catch (IOException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    for(Massage massage : massages){
                        Label label = new Label();
                        label.setText(massage.getSpeaker().getUsername() + " : " + massage.getMassageText());
                        label.getStyleClass().add("ChatMassage");
                        chatArea.getChildren().add(label);
                    }
                }
            }
        };
        animationTimer.start();
    }
}
