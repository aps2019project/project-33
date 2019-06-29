package View.Battle;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class BattleController implements Initializable {
    public ImageView forfeitButton;
    public ImageView gameInfoButton;
    public HBox hBox1;
    public HBox hBox2;
    public HBox hBox3;
    public HBox hBox4;
    public HBox hBox5;
    public Label endTurnButton;
    public VBox table;

    private HBox[] rows;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        rows = new HBox[]{hBox1, hBox2, hBox3, hBox4, hBox5};


    }
}
