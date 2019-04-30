package Controller;

import Model.*;
import Model.Enviroment.Map;

public class Battle {
    private Player playerOn, playerOff;
    private Map map;
    private boolean gameIsRunning;
    private int numberOfRounds;
    private String type, mode;

    public void showGameInfo(){}
    public void showMyMinions(){}
    public void showOpponentMinions(){}
    public void showCardInfo(String ID){}
    public void selectCard(String ID){}
    public void moveCardTo(int x, int y){}
    public void attackToOpponentCard(String opponentID){}
    public void comboAttackToOpponentCard(String[] input){}
    public void useSpecialPower(int x, int y){}
    public void showHand(){}
    public void insertCardInMap(String cardID, int x, int y){}
    public void endTurn(){}
    public void showCollectables(){}
    public void selectItem(String collectableItemID){}
    public void showItemInfo(){}
    public void useItem(int x, int y){}
    public void nextCard(){}
    public void endGame(){}
    public void checkTurn(){}
    public void exit(){}
    public void runGame(){
        inputCommandLine();
    }

    private void inputCommandLine(){
        String inputLine = Main.scanner.nextLine();
        inputLine = inputLine.trim();
        String[] input = inputLine.split("[ ]+");

        if(inputLine.equals("GameInfo"))
            showGameInfo();
        else if(inputLine.equals("Show my minions"))
            showMyMinions();
        else if(inputLine.equals("Show opponent minions"))
            showOpponentMinions();
        else if(inputLine.matches("Show card info *."))
            showCardInfo(input[3]);
        else if(inputLine.matches("Select *.")) {
            selectCard(input[1]);
            selectItem(input[1]);
        }
        else if(inputLine.matches("Move\\([\\d]+, [\\d]+\\)")){
            input = inputLine.split("[ \\(\\),]+");
            moveCardTo(Integer.parseInt(input[2]), Integer.parseInt(input[3]));
        }
        else if(inputLine.matches("Attack *."))
            attackToOpponentCard(input[1]);
        else if(inputLine.matches("Attack combo *. *."))
            comboAttackToOpponentCard(input);
        else if(inputLine.matches("Use special power \\([\\d]+, [\\d]+\\)")){
            input = inputLine.split("[ \\(\\),]+");
            useSpecialPower(Integer.parseInt(input[3]), Integer.parseInt(input[4]));
        }
        else if(inputLine.equals("Show hand"))
            showHand();
        else if(inputLine.matches("Insert *. in \\([\\d]+, [\\d]+\\)"))
            insertCardInMap(input[1], Integer.parseInt(input[3]), Integer.parseInt(input[4]));
        else if(inputLine.equals("End turn"))
            endTurn();
        else if(inputLine.equals("Show collectables"))
            showCollectables();
        else if(inputLine.equals("Show info"))
            showItemInfo();
        else if(inputLine.matches("Use [\\d],[\\d]"))
            useItem(Integer.parseInt(input[1]), Integer.parseInt(input[2]));

    }

    //Here is Setters && Getters

    public Player getPlayerOn() {
        return playerOn;
    }

    public void setPlayerOn(Player playerOn) {
        this.playerOn = playerOn;
    }

    public Player getPlayerOff() {
        return playerOff;
    }

    public void setPlayerOff(Player playerOff) {
        this.playerOff = playerOff;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public boolean isGameIsRunning() {
        return gameIsRunning;
    }

    public void setGameIsRunning(boolean gameIsRunning) {
        this.gameIsRunning = gameIsRunning;
    }

    public int getNumberOfRounds() {
        return numberOfRounds;
    }

    public void setNumberOfRounds(int numberOfRounds) {
        this.numberOfRounds = numberOfRounds;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }
}
