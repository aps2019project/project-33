package Controller;

import Model.*;
import Model.CollectionItem.*;
import Model.Enviroment.Map;

import java.util.ArrayList;

public class Battle {
    private Player playerOn, playerOff;
    private Map map;
    private boolean gameIsRunning;
    private int numberOfRounds;
    private String type, mode;
    private LivingCard selectedCard;
    private Flag mainFlag;
    private CollectableItem selectedCollectableItem;

    private ArrayList<Flag> flags = new ArrayList<Flag>();

    private String[] modes = {"Kill_enemy's_hero", "Hold_flags", "Take_half_of_flags"};

    {
        this.selectedCard = null;
        this.selectedCollectableItem = null;
    }


    public ArrayList<Flag> getFlags(){
        return this.flags;
    }

    public void addFlag(Flag flag){
        this.flags.add(flag);
    }

    public void showGameInfo(){
        //remaining mana bayad bashe ya maximum
        System.out.println("my mana : " + playerOn.getMana().getCurrentMana());
        System.out.println("opponent mana : " + playerOff.getMana().getCurrentMana());
        if(this.mode.equals(modes[0])){
            System.out.println("my hero HP : " + playerOn.getHero().getHP());
            System.out.println("opponent HP : " + playerOff.getHero().getHP());
        }
        if(this.mode.equals(modes[1])){
            System.out.println("flag position is : " + this.mainFlag.getPositionColumn() + ", " + this.mainFlag.getPositionRow());
          //usernamesh bayad chap she?
            System.out.println("flag owner is : " + this.mainFlag.getFlagOwner().getAccount().getUsername());
        }
        if(this.mode.equals(modes[2])){
            //hamin shekli bayad bashe ? id e sarbaz bayad bede ya chi ?
            for(Flag flag : this.getFlags()){
                if(flag.getFlagOwner() != null){
                    System.out.println(flag.getFlagLivingCard().getID() + " " + flag.getFlagOwner().getAccount().getUsername());
                }
            }
        }
    }

    public void showMyMinions(){
        for(LivingCard livingCard : playerOn.getAliveCards()){
            if(livingCard instanceof Minion){
                livingCard.showCardInBattle();
            }
        }
    }

    public void showOpponentMinions(){
        for(LivingCard livingCard : playerOff.getAliveCards()){
            if(livingCard instanceof Minion){
                livingCard.showCardInBattle();
            }
        }
    }

    //faghat vase livingCard e ?asan malum nis chejurie , card asan bayad tu bazi bashe ya chi koja donbalesh begardim
    public void showCardInfo(String ID) {
        String info = "card wasnt found";
        CollectionItem thisCollectionItem = null;
        for (CollectionItem collectionItem : playerOff.getAccount().getCollection().getMainDeck().getCards()){
            if (collectionItem.getID().equals(ID)) {
                thisCollectionItem = collectionItem;
            }
        }
        for(CollectionItem collectionItem : playerOn.getAccount().getCollection().getMainDeck().getCards()){
            if(collectionItem.getID().equals(ID)){
                thisCollectionItem = collectionItem;
            }
        }
        if(thisCollectionItem != null){
            info = thisCollectionItem.getInfo();
            if(thisCollectionItem instanceof LivingCard)
                info += " HP : " + ((LivingCard) (thisCollectionItem)).getRemainingHP() + " AP : " +
                        ((LivingCard)(thisCollectionItem)).getDecreaseHPByAttack();
        }
        System.out.println(info);
    }

    //in yeki shartesh chie bayad carde khdoemun bashe koja donbalesh begardim ...
    public void selectCard(String ID){
        for(LivingCard livingCard : playerOn.getAliveCards()){
            if(livingCard.getID().equals(ID)){
                this.selectedCard = livingCard;
                return;
            }
        }
        System.out.println("Invalid card id");
    }
    //havaset bashe in seda zade she bade in ke karemun ba card tammum shod
    public void removeSelectedCard(){
        this.selectedCard = null;
    }

    public void removeSelectedCollectableItem(){
        this.selectedCollectableItem = null;
    }
//vase selected card e dige?
//por o khali budanesh nabayad check she ?
    //che cardaei bishtar az 2 ta mitunan beran? moteghayer negah darim barashun?
    //too cell ham bayad ezafe she ?

    //naghese
    public void moveCardTo(int x, int y){
        if(selectedCard == null){
            System.out.println("select a card");
            return;
        }
        int distance = Math.abs(x - selectedCard.getPositionRow()) + Math.abs(y - selectedCard.getPositionColumn());
        if(distance <= 2 || this.selectedCard.getCanMoveGreaterTwoCell()){
            Cell lastCell = Map.getCellByCoordination(selectedCard.getPositionRow(), selectedCard.getPositionColumn());
            selectedCard.setPositionColumn(y);
            selectedCard.setPositionRow(x);
            Cell cell = Map.getCellByCoordination(x, y);
            if(cell.livingCard == null){
                cell.insertCard(selectedCard.getID());
                lastCell.removeCard(selectedCard.getID());
            }
            else{
                System.out.println("this cell is full");
            }
        }
    }

    public void attackToOpponentCard(String opponentID){}

    public void comboAttackToOpponentCard(String[] input){}

    public void useSpecialPower(int x, int y){}

    public void showHand(){
        playerOn.getHand().show();
    }

    public void insertCardInMap(String cardID, int x, int y){}

    public void endTurn(){
        Player player = playerOff;
        playerOff = playerOn;
        playerOn = player;
    }

    public void showCollectables(){
        for(CollectableItem collectableItem : playerOn.getCollectableItems()){
            System.out.println(collectableItem.getInfo());
        }
    }

    public void selectItem(String collectableItemID){
        for(CollectableItem collectableItem : playerOn.getCollectableItems())
            if(collectableItem.getID().equals(collectableItemID)){
                this.selectedCollectableItem = collectableItem;
                return;
            }
        System.out.println("collectableItem wasnt found");
    }

    public void useItem(int x, int y){}

    public void nextCard(){

    }

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
