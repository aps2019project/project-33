package View.AccountMenu;

import View.View;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class Sample implements Initializable
{
    public AnchorPane pane;
    public VBox vvvv;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Label label = new Label("salam");
        Image image = new Image(Sample.class.getResourceAsStream("1.jpg"));
        ImageView imageView = new ImageView(image);
        imageView.setCache(true);
        imageView.setX(10);
        imageView.setY(10);
        imageView.setFitHeight(100);
        imageView.setFitWidth(100);
        System.out.println(imageView);
        pane.getChildren().addAll(imageView, label);
    }
}
