package View.BattleMenu;

import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import javax.jws.WebParam;
import javax.tools.Diagnostic;
import java.net.URL;
import java.util.ResourceBundle;

public class BattleMenuController implements Initializable {

    private String[] modes = {"Kill_enemy's_hero", "Hold_flag", "Take_half_of_flags"};

    private ChapterList chapter = null;
    private ModeList mode = null;
    private KindList kind = null;
    private TypeList type = null;

    enum ChapterList{
        One, Two, Three;
    }
    enum ModeList{
        Custom, Story;
    }
    enum KindList{
        KillEnemyHero, HoldFlag, TakeHalfOfFlags;
    }
    enum TypeList{
        SinglePlayer, MultiPlayer;
    }

    public Label backButton;

    public Label singlePlayerButton;
    public Label multiPlayerButton;

    public Label kindOneButton;
    public Label kindThreeButton;
    public Label kindTwoButton;

    public Label chapterOneButton;
    public Label chapterTwoButton;
    public Label chapterThreeButton;

    public Label storyButton;
    public Label customGameButton;

    public void startGame(){
        //TODO
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        singlePlayerButton.setOnMouseClicked(event -> {
            type = TypeList.SinglePlayer;
        });

        multiPlayerButton.setOnMouseClicked(event -> {
            type = TypeList.MultiPlayer;
        });

        kindOneButton.setOnMouseClicked(event -> {
            kind = KindList.KillEnemyHero;
            startGame();
        });

        kindTwoButton.setOnMouseClicked(event -> {
            kind = KindList.HoldFlag;
            startGame();
        });

        kindThreeButton.setOnMouseClicked(event -> {
            kind = KindList.TakeHalfOfFlags;
            startGame();
        });

        chapterOneButton.setOnMouseClicked(event -> {
            chapter = ChapterList.One;
            startGame();
        });

        chapterTwoButton.setOnMouseClicked(event -> {
            chapter = ChapterList.Two;
            startGame();
        });

        chapterThreeButton.setOnMouseClicked(event -> {
            chapter = ChapterList.Three;
            startGame();
        });

        storyButton.setOnMouseClicked(event -> {
            mode = ModeList.Story;
        });

        customGameButton.setOnMouseClicked(event -> {
            mode = ModeList.Custom;
        });
    }
}
