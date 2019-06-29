package View.BattleMenu;

import  Controller.Client;
import Controller.MenuList;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

//type mishe single, multi
//mode mishe custom ya story
//kind mishe in ke kill bashe o ina ...
//chaptersam ke hich

public class BattleMenuController implements Initializable {
    private ChapterList chapter = null;
    private ModeList mode = null;
    private KindList kind = null;
    private TypeList type = null;

    enum TypeList{
        SinglePlayer, MultiPlayer
    }
    enum ModeList{
        Custom, Story
    }
    enum KindList{
        KillEnemyHero, HoldFlag, TakeHalfOfFlags;
        public String toString(){
            if(this.equals(KillEnemyHero)) return "Kill_enemy's_hero";
            if(this.equals(HoldFlag)) return "Hold_flag";
            if(this.equals(TakeHalfOfFlags)) return "Take_half_of_flags";
            return null;
        }
    }

    enum ChapterList{
        One, Two, Three;
        public String toString(){
            if(this.equals(One)) return "fight with DiveSefid";
            if(this.equals(Two)) return "fight with Zahhak";
            if(this.equals(Three)) return "fight with Arash";
            return null;
        }
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
        String typeToString = null, modeToString = null, chapterToString = null, kindToString = null;
        if(type != null) typeToString = type.toString();
        if(mode != null) modeToString = mode.toString();
        if(chapter != null) chapterToString = chapter.toString();
        if(kind != null) kindToString = kind.toString();

        try {
            Client.getClient().getBattleMenu().createGame(null, typeToString, modeToString , chapterToString, kindToString);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        singlePlayerButton.setOnMouseClicked(event -> {
            type = TypeList.SinglePlayer;
            Client.getClient().setCurrentMenu(MenuList.ChooseKind);
        });

        multiPlayerButton.setOnMouseClicked(event -> {
            type = TypeList.MultiPlayer;
            Client.getClient().setCurrentMenu(MenuList.ChooseKind);
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
            Client.getClient().setCurrentMenu(MenuList.ChooseChapter);
        });

        customGameButton.setOnMouseClicked(event -> {
            mode = ModeList.Custom;
            Client.getClient().setCurrentMenu(MenuList.ChooseKind);
        });
    }
}
