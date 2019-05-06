package Controller;

import Model.*;
import Model.CollectionItem.*;
import Model.CollectionItem.CollectionItem;
import Model.CollectionItem.Flag;
import Model.CollectionItem.LivingCard;
import Model.CollectionItem.Minion;
import Model.Enviroment.Cell;
import Model.Enviroment.Map1;

import java.util.ArrayList;

public class Battle {
    private Player playerOn, playerOff;
    private Map1 map = new Map1();
    private boolean gameIsRunning;
    private int numberOfRounds, prize;
    private String type, mode;
    private LivingCard selectedCard;
    private Flag mainFlag;
    private CollectableItem selectedCollectableItem;
    private int numberOfFlags;
    private Player winnerPlayer = null;
    private Player loserPlayer = null;

    private ArrayList<Flag> flags = new ArrayList<Flag>();

    private String[] modes = {"Kill_enemy's_hero", "Hold_flags", "Take_half_of_flags"};

    {
        this.selectedCard = null;
        this.selectedCollectableItem = null;
    }
//jaye avalie flaga o hero ha o ...
    public void preProcess(){

    }

    public void removeAliveCard(LivingCard removingLivingCard){
        playerOn.removeDeadCard(removingLivingCard);
        playerOff.removeDeadCard(removingLivingCard);
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

    public void insertCardInMap(String cardID, int x, int y){
        CollectionItem insertingCollectionItem = null;
        for(CollectionItem collectionItem : playerOn.getHand().getHandCards()){
            if(collectionItem.getID().equals(cardID))
                insertingCollectionItem = collectionItem;
        }
        if(insertingCollectionItem == null){
            System.out.println("Invalid card ID");
            return;
        }
        if(!isInMap(x, y)){
            System.out.println("Invalid coordination");
            return;
        }
        Cell cell = map.getCellByCoordination(x, y);
        if(insertingCollectionItem instanceof LivingCard){
            if(map.getCellByCoordination(x, y).getLivingCard() != null){
                System.out.println("Destination is full !");
                return;
            }
            playerOn.getHand().removeCard(cardID);
            ((LivingCard) insertingCollectionItem).setPositionColumn(y);
            ((LivingCard) insertingCollectionItem).setPositionRow(x);
            cell.insertCard(insertingCollectionItem.getID());
            playerOn.addAliveCard((LivingCard)insertingCollectionItem);
        }
        else{
            if(insertingCollectionItem instanceof Spell){
                playerOn.getHand().removeCard(cardID);
                Spell spell = (Spell)insertingCollectionItem;
                spell.impactSpell(cell);
            }
        }
    }

    public void moveCardTo(int x, int y){
        if(selectedCard == null){
            System.out.println("select a card");
            return;
        }
        if(isInMap(x, y)){
            System.out.println("Invalid coordination");
            return;
        }
        if(map.getCellByCoordination(x, y).getLivingCard() != null){
            System.out.println("Destination is full !");
            return;
        }

        int distance = getDistance(selectedCard.getPositionRow(), selectedCard.getPositionColumn(), x, y);
        int maxDistanceCanCardGo = 2;
        if(this.selectedCard.getCanMoveGreaterTwoCell()) maxDistanceCanCardGo = Integer.MAX_VALUE;

        if(distance <= maxDistanceCanCardGo){
            Cell lastCell = map.getCellByCoordination(selectedCard.getPositionRow(), selectedCard.getPositionColumn());
            lastCell.removeCard();

            selectedCard.setPositionColumn(y);
            selectedCard.setPositionRow(x);

            Cell cell = map.getCellByCoordination(x, y);
            cell.insertCard(selectedCard.getID());
        }else{
            System.out.println("This move is impossible");
        }
    }

    private int getDistance(int x1, int y1, int x2, int y2){
        int[][] dis = new int[map.getHeight()][map.getWidth()];
        for(int i = 0; i < map.getHeight(); i ++)
            for(int j = 0; j < map.getWidth(); j ++)
                dis[i][j] = Integer.MAX_VALUE;
        dis[x1][y1] = 0;
        int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};
        for(int i = 0; i < map.getHeight(); i ++)
            for(int j = 0; j < map.getWidth(); j ++)
                for(int t = 0; t < 4; t ++){
                    int newX = i + dx[t], newY = j + dy[t];
                    if(!isInMap(newX, newY))
                        continue;
                    LivingCard livingCard = map.getCellByCoordination(newX, newY).getLivingCard();
                    if(livingCard != null){
                        Deck deck = playerOn.getAccount().getCollection().getMainDeck();
                        if(deck.findCollectionItemInDeck(livingCard.getID()) == null)
                            continue;
                    }
                    dis[i][j] = Integer.min(dis[i][j], dis[newX][newY] + 1);
                }
        return dis[x2][y2];
    }

    private boolean isInMap(int x, int y){
        if(x < 0 || y < 0) return false;
        if(x >= map.getHeight() || y >= map.getWidth())
            return false;
        return true;
    }

    public void attackToOpponentCard(String opponentID){
        ArrayList<LivingCard> opponentAliveCards = playerOff.getAliveCards();
        LivingCard opponentLivingCard = null;
        for(LivingCard livingCard : opponentAliveCards)
            if(livingCard.getID().equals(opponentID))
                opponentLivingCard = livingCard;
        if(opponentLivingCard == null){
            System.out.println("Invalid card id");
            return;
        }
        Impact.attack(this.selectedCard, opponentLivingCard);
    }

    public void comboAttackToOpponentCard(String[] input){
        String opponentID = input[0];
        LivingCard opponentLivingCard = null;
        for(LivingCard livingCard : playerOff.getAliveCards())
            if(livingCard.getID().equals(opponentID))
                opponentLivingCard = livingCard;
        if(opponentLivingCard == null){
            System.out.println("Invalid card id");
            return;
        }
        ArrayList<LivingCard> myLivingCards = new ArrayList<>();
        for(int i = 1; i < input.length; i++){
            LivingCard myLivingCard = null;
            for(LivingCard livingCard : playerOn.getAliveCards())
                if(livingCard.getID().equals(input[i]))
                    myLivingCard = livingCard;
            if(myLivingCard == null){
                System.out.println("Invalid card id");
                return;
            }
            myLivingCards.add(myLivingCard);
        }
        Impact.comboAttack(opponentLivingCard, myLivingCards);
    }


    //TODO

    public void showHand(){
        playerOn.getHand().show();
    }


    public void endTurn(){
        Player player = playerOff;
        playerOff = playerOn;
        playerOn = player;
        if(this.getMode().equals(modes[1])){
            if(this.mainFlag.getFlagOwner() != null)
                mainFlag.setNumberOfGotRounds(mainFlag.getNumberOfGotRounds() + 1);
        }
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

    public void useSpecialPower(int x, int y){
        Impact.specialPower(playerOn.getHero(), x, y);
    }

    public void useItem(int x, int y){
        if(selectedCollectableItem == null){
            System.out.println("select a collectable item");
            return;
        }
        if(!isInMap(x, y)){
            System.out.println("Invalid coordination");
            return;
        }
        Cell cell = map.getCellByCoordination(x, y);
        Impact.impactItem(selectedCollectableItem, cell);
    }

    //havaset bashe moteghayerasho update koni mese tedad cardaye estefade shode az main deck
    public void showNextCard(){
        playerOn.getHand().showNextCard(playerOn.getAccount().getCollection().getMainDeck());
    }

    //bazi jaha bayad khali shan selecteditem o card
    public void showItemInfo(){
        if(selectedCollectableItem == null){
            System.out.println("select an item");
            return;
        }
        System.out.println(selectedCollectableItem.getInfo());
    }


    //jayze barande o time e bazi o namayesh e bazi munde
    public void checkTurn(){
        if(this.getMode().equals(modes[0])){
            if(playerOn.getHero().getHP() <= 0){
                this.setLoserPlayer(playerOn);
                this.setWinnerPlayer(playerOff);
            }
            if(playerOff.getHero().getHP() <= 0){
                this.setLoserPlayer(playerOff);
                this.setWinnerPlayer(playerOn);
            }
        }
        if(this.getMode().equals(modes[1])){
            if(mainFlag.getNumberOfGotRounds() >= 6){
                Player winner = mainFlag.getFlagOwner();
                this.setWinnerPlayer(winner);
                if(playerOff.getAccount().getUsername().equals(winner.getAccount().getUsername())){
                    this.setLoserPlayer(playerOn);
                }
                else{
                    this.setLoserPlayer(playerOff);
                }
            }
        }
        if(this.getMode().equals(modes[2])){
            int numberOfFlags = flags.size();
            int numberOfPlayerOnFlags = 0, numberOfPlayerOffFlags = 0;
            for(Flag flag : flags){
                Player flagOwner = flag.getFlagOwner();
                if(flagOwner != null){
                    if(flagOwner.getAccount().getUsername().equals(playerOn.getAccount().getUsername()))
                        numberOfPlayerOnFlags++;
                    else
                        numberOfPlayerOffFlags++;
                }
            }
            if(numberOfPlayerOnFlags > numberOfFlags / 2){
                this.setWinnerPlayer(playerOn);
                this.setLoserPlayer(playerOff);
            }
            if(numberOfPlayerOffFlags > numberOfFlags / 2){
                this.setWinnerPlayer(playerOff);
                this.setLoserPlayer(playerOn);
            }
        }
        //inja bayad neshun bede barande o etela'ato
        if(this.getWinnerPlayer() != null){
            Match match = new Match();
            match.setWinner(this.winnerPlayer.getAccount());
            match.setLoser(this.loserPlayer.getAccount());
          //  match.setTime(this.);
            playerOn.getAccount().addMatch(match);
            playerOff.getAccount().addMatch(match);

        }
    }

    public void endGame(){

    }
    public void exit(){}

    public void enterGraveYard(){}

    public void help(){

    }

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
        else if(inputLine.equals("Show Next Card"))
            showNextCard();
        else if(inputLine.equals("Enter GraveYard"))
            enterGraveYard();
        else if(inputLine.equals("Help"))
            help();
        else if(inputLine.equals("End Game"))
            endGame();
        else if(inputLine.equals("Exit"))
            return;
        else if(inputLine.equals("Show menu")){

        }
        else
            System.out.println("Enter valid command");
        this.inputCommandLine();
    }

    public void addMareBozorg(){}

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

    public Map1 getMap() {
        return map;
    }

    public void setMap(Map1 map) {
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

    public int getPrize() {
        return prize;
    }

    public void setPrize(int prize) {
        this.prize = prize;
    }

    public int getNumberOfFlags() {
        return numberOfFlags;
    }

    public void setNumberOfFlags(int numberOfFlags) {
        this.numberOfFlags = numberOfFlags;
    }

    public void setLoserPlayer(Player player){
        this.loserPlayer = player;
    }

    public Player getLoserPlayer(){
        return this.loserPlayer;
    }

    public void setWinnerPlayer(Player player){
        this.winnerPlayer = player;
    }

    public Player getWinnerPlayer(){
        return this.winnerPlayer;
    }
}
