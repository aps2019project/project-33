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
import java.util.Random;

public class Battle {
    private Player playerOn, playerOff;
    private Map1 map = new Map1();
    private boolean gameIsRunning;
    private int numberOfRounds, prize, numberOfFlags;
    private String type, mode;
    private LivingCard selectedCard;
    private Flag mainFlag;
    private CollectableItem selectedCollectableItem;
    private Player winnerPlayer = null, loserPlayer = null;
    private ArrayList<Flag> flags = new ArrayList<>();

    private String[] modes = {"Kill_enemy's_hero", "Hold_flags", "Take_half_of_flags"};

    {
        this.selectedCard = null;
        this.selectedCollectableItem = null;
    }

    public void createHand(Player player){
        Deck mainDeck = player.getAccount().getCollection().getMainDeck();
        mainDeck.shuffle();
        for(int i = 0; i < 5; i++)
            player.getHand().addNextCard(mainDeck);
    }

    public void setHeroPosition(Player player){
        Random random = new Random();
        int row = random.nextInt(this.getMap().getHeight());
        int column = random.nextInt(this.getMap().getWidth());
        player.getHero().setPositionRow(row);
        player.getHero().setPositionColumn(column);
    }

    public boolean haveSamePosition(Hero hero1, Hero hero2){
        if(hero1.getPositionColumn() != hero2.getPositionColumn())
            return false;
        if(hero1.getPositionRow() != hero2.getPositionRow())
            return false;
        return true;
    }

    public void putHero(Player player){
        Hero hero = player.getHero();
        Cell cell = this.getMap().getCellByCoordination(hero.getPositionRow(), hero.getPositionColumn());
        cell.insertCard(hero.getID());
        player.addAliveCard(hero);
    }

    public void putHeroes(){
        this.setHeroPosition(this.playerOn);
        this.setHeroPosition(this.playerOff);
        while(haveSamePosition(playerOff.getHero(), playerOn.getHero()))
            this.setHeroPosition(this.playerOff);
        this.putHero(playerOn);
        this.putHero(playerOff);
    }

    public void setFlagPosition(Flag flag){
        Random random = new Random();
        int row = random.nextInt(this.getMap().getHeight());
        int column = random.nextInt(this.getMap().getWidth());
        flag.setPositionRow(row);
        flag.setPositionColumn(column);
    }

    public void createFlags(){
        for(int i = 0; i < this.numberOfFlags; i++){
            Flag flag = new Flag();
            flags.add(flag);
        }
    }

    public void createFlagMode(){
        //numberOfFlags chaande tuye mode e 3
        if(this.mode.equals(modes[1]))
            numberOfFlags = 1;
        this.createFlags();
        for(Flag flag : this.getFlags()){
            this.setFlagPosition(flag);
            Cell cell = this.getMap().getCellByCoordination(flag.getPositionRow(), flag.getPositionColumn());
            while(cell.isHaveFlag()){
                this.setFlagPosition(flag);
                cell = this.getMap().getCellByCoordination(flag.getPositionRow(), flag.getPositionColumn());
            }
            cell.setHaveFlag(true);
        }
    }

//jaye avalie flaga o hero ha o ...
    public void preProcess(){
        this.createHand(playerOn);
        this.createHand(playerOff);
        this.putHeroes();
        if(!this.getMode().equals(modes[0])){
            this.createFlagMode();
        }
        //TODO
    }


    public void showGameInfo(){
        //remaining mana bayad bashe ya maximum
        System.out.println("my mana : " + playerOn.getMana().getCurrentMana());
        System.out.println("opponent mana : " + playerOff.getMana().getCurrentMana());
        if(this.mode.equals(modes[0])){
            System.out.println("my hero HP : " + playerOn.getHero().getHP());
            System.out.println("opponent HP : " + playerOff.getHero().getHP());
        }
        //chera sotoon o satr midi biroon ?
        if(this.mode.equals(modes[1])){
            System.out.println("flag position is : " + this.mainFlag.getPositionRow() + ", " + this.mainFlag.getPositionColumn());
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
        String info = "card was not found";
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
                info += " HP : " + ((LivingCard) (thisCollectionItem)).getHP() + " AP : " +
                        ((LivingCard)(thisCollectionItem)).getDecreaseHPByAttack();
        }
        System.out.println(info);
    }

    //in yeki shartesh chie bayad carde khdoemun bashe koja donbalesh begardim ...
    public boolean selectCard(String ID){
        for(LivingCard livingCard : playerOn.getAliveCards()){
            if(livingCard.getID().equals(ID)){
                this.selectedCard = livingCard;
                return true;
            }
        }
        return false;
    }

    public boolean selectItem(String collectableItemID){
        for(CollectableItem collectableItem : playerOn.getCollectableItems())
            if(collectableItem.getID().equals(collectableItemID)){
                this.selectedCollectableItem = collectableItem;
                return true;
            }
        return false;
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
            System.out.println("Invalid target");
            return;
        }

        if(insertingCollectionItem instanceof Card){
            if(playerOn.getMana().getCurrentMana() < ((Card) insertingCollectionItem).getMP()){
                System.out.println("Low mana !! ");
                return;
            }
        }

        Cell cell = map.getCellByCoordination(x, y);
        if(insertingCollectionItem instanceof LivingCard){
            if(map.getCellByCoordination(x, y).getLivingCard() != null){
                System.out.println("Destination is full !");
                return;
            }
            playerOn.getHand().removeCard(cardID);
            playerOn.getHand().addNextCard(playerOn.getAccount().getCollection().getMainDeck());
            ((LivingCard) insertingCollectionItem).setPositionColumn(y);
            ((LivingCard) insertingCollectionItem).setPositionRow(x);
            cell.insertCard(insertingCollectionItem.getID());
            playerOn.addAliveCard((LivingCard)insertingCollectionItem);
        }
        else{
            if(insertingCollectionItem instanceof Spell){
                playerOn.getHand().removeCard(cardID);
                playerOn.getHand().addNextCard(playerOn.getAccount().getCollection().getMainDeck());
                Spell spell = (Spell)insertingCollectionItem;
                spell.impactSpell(cell, this);
            }
        }
        handleFlags();
    }

    public void moveCardTo(int x, int y){
        if(selectedCard == null){
            System.out.println("select a card");
            return;
        }
        if(!isInMap(x, y)){
            System.out.println("Invalid coordination");
            return;
        }
        if(map.getCellByCoordination(x, y).getLivingCard() != null){
            System.out.println("Invalid target !");
            return;
        }

        int distance = getDistance(selectedCard.getPositionRow(), selectedCard.getPositionColumn(), x, y);
        int maxDistanceCanCardGo = 2;
        if(this.selectedCard.getCanMoveGreaterTwoCell()) maxDistanceCanCardGo = Integer.MAX_VALUE;

        if(distance <= maxDistanceCanCardGo && distance < Integer.MAX_VALUE){
            Cell lastCell = map.getCellByCoordination(selectedCard.getPositionRow(), selectedCard.getPositionColumn());
            lastCell.removeCard();

            selectedCard.setPositionColumn(y);
            selectedCard.setPositionRow(x);

            Cell cell = map.getCellByCoordination(x, y);
            cell.insertCard(selectedCard.getID());
            //tuye mode e flag bayad flago begire dastesh
        }else
            System.out.println("Invalid target !");
        handleFlags();
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
        if(selectedCard == null){
            System.out.println("Select a card");
            return;
        }
        ArrayList<LivingCard> opponentAliveCards = playerOff.getAliveCards();
        LivingCard opponentLivingCard = null;
        for(LivingCard livingCard : opponentAliveCards)
            if(livingCard.getID().equals(opponentID))
                opponentLivingCard = livingCard;
        if(opponentLivingCard == null){
            System.out.println("Invalid card id");
            return;
        }
        Impact.attack(this, this.selectedCard, opponentLivingCard);
        this.removeSelectedCard();
    }

    public void comboAttackToOpponentCard(String[] input){
        String opponentID = input[2];
        LivingCard opponentLivingCard = null;
        for(LivingCard livingCard : playerOff.getAliveCards())
            if(livingCard.getID().equals(opponentID))
                opponentLivingCard = livingCard;
        if(opponentLivingCard == null){
            System.out.println("Invalid opponent card id");
            return;
        }
        ArrayList<LivingCard> myLivingCards = new ArrayList<>();
        for(int i = 3; i < input.length; i++){
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
        playerOn.getHand().show(playerOn.getAccount().getCollection().getMainDeck());
    }

    //TODO
    //in ja bayad kheili kar ha bokonim
    //tahesh migim daghighan chia
    //buff haye passive is activeshun true she
    public void endTurn(){
        Player player = playerOff;
        playerOff = playerOn;
        playerOn = player;
        if(this.getMode().equals(modes[1])){
            if(this.mainFlag.getFlagOwner() != null)
                mainFlag.setNumberOfGotRounds(mainFlag.getNumberOfGotRounds() + 1);
        }
        playerOn.getHero().setCoolDown(Math.max(0, playerOn.getHero().getCoolDown() - 1));
        playerOff.getHero().setCoolDown(Math.max(0, playerOff.getHero().getCoolDown() - 1));
        Impact.activeBuffs(this);
    }

    public void showCollectables(){
        for(CollectableItem collectableItem : playerOn.getCollectableItems()){
            System.out.println(collectableItem.getInfo());
        }
    }


    public void useSpecialPower(int x, int y){
        Hero hero = playerOn.getHero();
        if(hero == null){
            System.out.println("select a card");
            return;
        }
        if(playerOn.getMana().getCurrentMana() < hero.getMP()){
            System.out.println("need more mana");
            return;
        }
        if(hero.getCoolDown() > 0){
            System.out.println("cool down time");
            return;
        }
        if(!isInMap(x, y)){
            System.out.println("Invalid coordination");
            return;
        }
        //anjam nashe ham cooldown mishe

        Cell cell = this.map.getCellByCoordination(x, y);
        Impact.impactSpellOfHero(this, playerOn.getHero(), cell);
        hero.setCoolDown(hero.getMaxCoolDown());
    }

    //TODO
    //in bayad az hand pak she
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
        Impact.impactItem((Item) selectedCollectableItem, cell, this);
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

    //TODO
    //masalan in ke yeki bemire bere tooye grave yard add she
    public void enterGraveYard(){
        playerOn.getGraveYard().inputCommandLine();
    }

    public void help(){

    }

    public boolean isLivingCardInList(LivingCard livingCard, ArrayList<LivingCard> livingCards){
        for(LivingCard aliveCard : livingCards){
            if(aliveCard.getID().equals(livingCard.getID()))
                return true;
        }
        return false;
    }

    public Player getOwnerOfLivingCard(LivingCard livingCard){
        if(isLivingCardInList(livingCard, playerOn.getAliveCards()))
            return playerOn;
        return playerOff;
    }

    public void handleFlags(){
        for(Flag flag : flags){
            LivingCard livingCard = flag.getFlagLivingCard();
            if(livingCard != null){
                flag.setPositionColumn(livingCard.getPositionColumn());
                flag.setPositionRow(livingCard.getPositionRow());
            }
        }
        for(Flag flag : flags){
            Cell cell = this.getMap().getCellByCoordination(flag.getPositionRow(), flag.getPositionColumn());
            LivingCard livingCard = cell.getLivingCard();
            if(livingCard == null)
                continue;
            if(flag.getFlagLivingCard() == null){
                flag.setFlagLivingCard(livingCard);
                flag.setFlagOwner(getOwnerOfLivingCard(livingCard));
            }
        }
    }

    public void showMenu(){
        System.out.println("1. Game Info");
        System.out.println("2. Show my minions");
        System.out.println("3. Show opponent minions");
        System.out.println("4. Show card info [card id]");
        System.out.println("5. Select [card id]");
        System.out.println("6. Move to ([x], [y])");
        System.out.println("7. Attack [opponent card id]");
        System.out.println("8. Attack combo [opponent id] [my card id] [my card id] [...]");
        System.out.println("9. Use special power (x, y)");
        System.out.println("10. Show hand");
        System.out.println("11. Insert [card id] in (x, y)");
        System.out.println("12. End turn");
        System.out.println("13. Show collectables");
        System.out.println("14. Select [collectable id]");
        System.out.println("15. Show info");
        System.out.println("16. Use [location x, y]");
        System.out.println("17. Show Next Card");
        System.out.println("18. Enter gravayard");
        System.out.println("19. Help");
        System.out.println("20. End Game");
        System.out.println("21. Exit");
        System.out.println("22. Show menu");
    }


    public void runGame(){
        preProcess();
        inputCommandLine();
    }

    private void inputCommandLine(){
        System.out.println("Here is Battle");;

        String inputLine = Main.scanner.nextLine();
        inputLine = inputLine.trim();
        inputLine = inputLine.toLowerCase();
        String[] input = inputLine.split("[ ]+");

        if(inputLine.equals("game info"))
            showGameInfo();
        else if(inputLine.equals("show my minions"))
            showMyMinions();
        else if(inputLine.equals("show opponent minions"))
            showOpponentMinions();
        else if(inputLine.matches("show card info .*"))
            showCardInfo(input[3]);
        else if(inputLine.matches("select .*")) {
            boolean isFound = selectCard(input[1]) || selectItem(input[1]);
            if(!isFound) System.out.println("Can't find this card");
            else System.out.println("card found !!");
        }
        else if(inputLine.matches("move to \\([\\d]+, [\\d]+\\)")){
            input = inputLine.split("[ \\(\\),]+");
            moveCardTo(Integer.parseInt(input[2]), Integer.parseInt(input[3]));
        }
        else if(inputLine.matches("attack .*"))
            attackToOpponentCard(input[1]);
        else if(inputLine.matches("attack combo [^s]+( [^s]+)+"))
            comboAttackToOpponentCard(input);
        else if(inputLine.matches("use special power \\([\\d]+, [\\d]+\\)")){
            input = inputLine.split("[ \\(\\),]+");
            useSpecialPower(Integer.parseInt(input[3]), Integer.parseInt(input[4]));
        }
        else if(inputLine.equals("show hand"))
            showHand();
        else if(inputLine.matches("insert [^\\s]+ in \\([\\d]+, [\\d]+\\)")) {
            input = inputLine.split("[ \\(\\),]+");
            insertCardInMap(input[1], Integer.parseInt(input[3]), Integer.parseInt(input[4]));
        }
        else if(inputLine.equals("end turn"))
            endTurn();
        else if(inputLine.equals("show collectables"))
            showCollectables();
        else if(inputLine.equals("show info"))
            showItemInfo();
        else if(inputLine.matches("use [\\d], [\\d]")) {
            input = inputLine.split("[ \\(\\),]+");
            useItem(Integer.parseInt(input[1]), Integer.parseInt(input[2]));
        }
        else if(inputLine.equals("show next card"))
            showNextCard();
        else if(inputLine.equals("enter graveyard"))
            enterGraveYard();
        else if(inputLine.equals("help"))
            help();
        else if(inputLine.equals("end game"))
            endGame();
        else if(inputLine.equals("exit"))
            return;
        else if(inputLine.equals("show menu")){
            this.showMenu();
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

}
