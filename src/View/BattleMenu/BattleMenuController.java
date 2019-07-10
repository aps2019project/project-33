package View.BattleMenu;

import Controller.Client.Client;

import java.io.FileNotFoundException;
import java.io.IOException;

//type mishe single, multi
//mode mishe custom ya story
//kind mishe in ke kill bashe o ina ...
//chaptersam ke hich

public class BattleMenuController {
    protected static ChapterList chapter = null;
    protected static ModeList mode = null;
    protected static KindList kind = null;
    protected static TypeList type = null;
    protected static String secondPlayerUserName;
    protected static int maximumTimeOfTurn = 50;
    protected static int numberOfFlag;

    enum TypeList{
        SinglePlayer, MultiPlayer;

        public String toString(){
            if(this.equals(SinglePlayer)) return "Single Player";
            else return "Multi Player";
        }
    }

    public static void startGame() throws IOException, ClassNotFoundException {
        String typeToString = null, modeToString = null, chapterToString = null, kindToString = null;
        if(type != null) typeToString = type.toString();
        if(mode != null) modeToString = mode.toString();
        if(chapter != null) chapterToString = chapter.toString();
        if(kind != null) kindToString = kind.toString();

        Client.getClient().createGame(secondPlayerUserName, typeToString, modeToString , chapterToString, kindToString, numberOfFlag, maximumTimeOfTurn);
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

    public static void startMultiPlayerGame() throws IOException, ClassNotFoundException {
        String typeToString = null, modeToString = null, chapterToString = null, kindToString = null;
        if(type != null) typeToString = type.toString();
        if(mode != null) modeToString = mode.toString();
        if(chapter != null) chapterToString = chapter.toString();
        if(kind != null) kindToString = kind.toString();

        Client.getClient().startCreateMultiGame(secondPlayerUserName, typeToString, modeToString, chapterToString, kindToString, numberOfFlag, maximumTimeOfTurn);
    }

    enum ModeList{
        Custom, Story;

        public String toString(){
            if(this.equals(Custom)) return "Custom Game";
            if(this.equals(Story)) return "Story";
            return null;
        }
    }

    public static void relax(){
        chapter = null;
        mode = null;
        type = null;
        kind = null;
    }
}
