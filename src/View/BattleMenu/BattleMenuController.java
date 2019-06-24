package View.BattleMenu;

import  Controller.Client;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import java.net.URL;
import java.util.ResourceBundle;

//type mishe single, multi
//mode mishe custom ya story
//kind mishe in ke kill bashe o ina ...
// chaptersam ke hich

public class BattleMenuController implements Initializable {

    private ChapterList chapter = null;
    private ModeList mode = null;
    private KindList kind = null;
    private TypeList type = null;

    enum ChapterList{
        One, Two, Three
    }
    enum ModeList{
        Custom, Story
    }
    enum KindList{
        KillEnemyHero, HoldFlag, TakeHalfOfFlags
    }
    enum TypeList{
        SinglePlayer, MultiPlayer
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
        Client.getClient().getBattleMenu().createGame(type.toString(), mode.toString(), kind.toString(), 0, 0, 0, );
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
