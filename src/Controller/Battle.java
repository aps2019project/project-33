package Controller;

import Model.*;
import Model.Enviroment.Map;

public class Battle {
    private Player playerOn, playerOff;
    private Map map;
    private boolean gameIsRunning;
    private int numberOfRounds;
    private String type;

    public void showGameInfo(){}
    public void showMyMinions(){}
    public void showOpponentMinions(){}
    public void showCardInfo(String ID){}
    public void selectCard(String ID){}
    public void moveCardTo(int x, int y){}
    public void attackToOpponentCard(int x, int y){}
    public void comboAttackToOpponentCard(String opponentCardID, String... myCardsID){}
    public void useSpecialPower(int x, int y){}
    public void showHand(){}
    public void insertCardInMap(String cardID, int x, int y){}
    public void endTurn(){}
    public void showCollectables(){}
    public void selectItem(String collectableItemID)P{}
    public void showItemInfo(){}
    public void nextCard(){}
    public void endGame(){}
    public void checkTurn(){}
    public void exit(){}
    public void runGame(){}

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
}
