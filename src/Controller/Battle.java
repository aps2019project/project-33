package Controller;

import Controller.Client.ClientMassage;
import Controller.Server.ServerMassage;
import Generator.DeckGenerator;
import Model.*;
import Model.Buffs.Buff;
import Model.CollectionItem.*;
import Model.CollectionItem.CollectionItem;
import Model.CollectionItem.Flag;
import Model.CollectionItem.LivingCard;
import Model.CollectionItem.Minion;
import Model.Enviroment.Cell;
import Model.Enviroment.Map1;

import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;


public class Battle implements Serializable {
    private Player playerOn, playerOff;
    private Map1 map = new Map1(5, 9);
    private boolean gameIsRunning;
    private int numberOfRounds;
    private int prize;
    private int numberOfFlags;
    private int remainTimeOfTurn;
    private int maximumTimeOfTurn = 50;
    private String type, kind;
    private LivingCard selectedCard;
    private Flag mainFlag;
    private Item selectedCollectibleItem;
    private Player winnerPlayer = null, loserPlayer = null;
    private ArrayList<Flag> flags = new ArrayList<>();
    private String estate = "none";
    private long turnTime = 1000 * 5;


    private String[] kinds = {"Kill_enemy's_hero", "Hold_flag", "Take_half_of_flags"};

    {
        this.selectedCard = null;
        this.selectedCollectibleItem = null;
    }

    // in be ehtemale khoobi momkene bug bokhore
    public void
    relaxCards(Player player) throws FileNotFoundException {
        Collection collection = player.getAccount().getCollection();
        Deck mainDeck = player.getAccount().getCollection().getMainDeck();
        ArrayList<CollectionItem> livingCards = mainDeck.getCards();

        for (int i = livingCards.size() - 1; i > -1; i--) {
            CollectionItem collectionItem = livingCards.get(i);

            collection.removeCollectionItemFromCollection(collectionItem.getID());
            String address = "Data/CollectionItem/";

            if (collectionItem instanceof Hero) {
                CollectionItem.getAllLivingCards().remove(collectionItem);
                address += "Hero/" + collectionItem.getName() + ".json";
                Hero hero = (Hero) Application.readJSON(Hero.class, address);
                hero.setID(collectionItem.getID());
                CollectionItem.getAllLivingCards().add(hero);
                collection.addCollectionItemToCollection(hero.getID());
                collection.addCollectionItemToDeck(hero.getID(), mainDeck.getName());
            }

            if (collectionItem instanceof Minion) {
                CollectionItem.getAllLivingCards().remove(collectionItem);
                address += "Minion/" + collectionItem.getName() + ".json";
                Minion minion = (Minion) Application.readJSON(Minion.class, address);
                minion.setID(collectionItem.getID());
                CollectionItem.getAllLivingCards().add(minion);
                collection.addCollectionItemToCollection(minion.getID());
                collection.addCollectionItemToDeck(minion.getID(), mainDeck.getName());
            }
            if (collectionItem instanceof Spell) {
                CollectionItem.getAllSpells().remove(collectionItem);
                address += "Spell/" + collectionItem.getName() + ".json";
                Spell spell = (Spell) Application.readJSON(Spell.class, address);
                spell.setID(collectionItem.getID());
                CollectionItem.getAllSpells().add(spell);
                collection.addCollectionItemToCollection(spell.getID());
                collection.addCollectionItemToDeck(spell.getID(), mainDeck.getName());
            }
            if (collectionItem instanceof Item) {
                CollectionItem.getAllItems().remove(collectionItem);
                address += "Item/" + collectionItem.getName() + ".json";
                Item item = (Item) Application.readJSON(Item.class, address);
                item.setID(collectionItem.getID());
                CollectionItem.getAllItems().add(item);
                collection.addCollectionItemToCollection(item.getID());
                collection.addCollectionItemToDeck(item.getID(), mainDeck.getName());
            }
        }
    }

    public void createHand(Player player) {
        Deck mainDeck = player.getAccount().getCollection().getMainDeck();
        mainDeck.shuffle();
        findHero(player);
        for (int i = 0; i < 5; i++)
            player.getHand().addNextCard(mainDeck);
    }

    public void setHeroPosition(Player player) {
        Random random = new Random();
        int row = random.nextInt(this.getMap().getHeight());
        int column = random.nextInt(this.getMap().getWidth());
        player.getHero().setPositionRow(row);
        player.getHero().setPositionColumn(column);
    }

    public boolean haveSamePosition(Hero hero1, Hero hero2) {
        if (hero1.getPositionColumn() != hero2.getPositionColumn())
            return false;
        if (hero1.getPositionRow() != hero2.getPositionRow())
            return false;
        return true;
    }

    public void putHero(Player player) {
        Hero hero = player.getHero();
        Cell cell = this.getMap().getCellByCoordination(hero.getPositionRow(), hero.getPositionColumn());
        cell.insertCard(hero.getID());
        player.addAliveCard(hero);
    }

    public void putHeroes() {
        this.setHeroPosition(this.playerOn);
        this.setHeroPosition(this.playerOff);
        while (haveSamePosition(playerOff.getHero(), playerOn.getHero()))
            this.setHeroPosition(this.playerOff);
        this.putHero(playerOn);
        this.putHero(playerOff);
    }

    public void setFlagPosition(Flag flag) {
        Random random = new Random();
        int row = random.nextInt(this.getMap().getHeight());
        int column = random.nextInt(this.getMap().getWidth());
        flag.setPositionRow(row);
        flag.setPositionColumn(column);
    }

    public void createFlags() {
        for (int i = 0; i < this.numberOfFlags; i++) {
            Flag flag = new Flag();
            flags.add(flag);
        }
    }

    public void createFlagMode() {
        if (this.kind.equals(kinds[1]))
            numberOfFlags = 1;

        this.createFlags();
        for (Flag flag : this.getFlags()) {
            this.setFlagPosition(flag);
            Cell cell = this.getMap().getCellByCoordination(flag.getPositionRow(), flag.getPositionColumn());
            while (cell.isHaveFlag() || cell.getLivingCard() != null) {
                this.setFlagPosition(flag);
                cell = this.getMap().getCellByCoordination(flag.getPositionRow(), flag.getPositionColumn());
            }
            cell.setHaveFlag(true);
        }
        this.mainFlag = this.flags.get(0);
        this.mainFlag.setFlagOwner(null);
    }

    public void canLivingCards(Player player) {
        for (LivingCard livingCard : player.getAliveCards()) {
            livingCard.setCanMove(true);
            livingCard.setCanAttack(true);
            livingCard.setCanCounterAttack(true);
        }
    }


    private void swapHeroToEnd(ArrayList<CollectionItem> cards, int index) {
        int size = cards.size();
        CollectionItem collectionItem = cards.get(index);
        cards.set(index, cards.get(size - 1));
        cards.set(size - 1, collectionItem);
    }


    public void findHero(Player player) {
        int index = 0;
        for (CollectionItem collectionItem : player.getAccount().getCollection().getMainDeck().getCards()) {
            if (collectionItem instanceof Hero) {
                player.setHero((Hero) collectionItem);
                swapHeroToEnd(player.getAccount().getCollection().getMainDeck().getCards(), index);
                break;
            }
            index++;
        }
    }

    //jaye avalie flaga o hero ha o ...
    public void preProcess() throws FileNotFoundException {
        this.relaxCards(this.playerOn);
        this.relaxCards(this.playerOff);

        this.setCards(playerOff);
        this.setCards(playerOn);

        this.createHand(playerOn);
        this.createHand(playerOff);

        this.putHeroes();

        playerOn.getMana().setMaximumMana(2);
        playerOff.getMana().setMaximumMana(3);
        playerOn.getMana().setCurrentMana(playerOn.getMana().getMaximumMana());
        playerOff.getMana().setCurrentMana(playerOff.getMana().getMaximumMana());


        if (!this.getKind().equals(kinds[0]))
            this.createFlagMode();

        createUsableItem(3);

        // gitignore test
        canLivingCards(playerOn);
        canLivingCards(playerOff);

        setRemainTimeOfTurn(maximumTimeOfTurn);

        //TODO
    }

    private void createUsableItem(int numberOfUsableItem) throws FileNotFoundException {
        for (int i = 0; i < numberOfUsableItem; i++) {
            Item item = Item.createItem(DeckGenerator.itemNames[i], "admin");
            setItemLocation(item);
        }
    }

    private void setItemLocation(Item item) {
        Random random = new Random();

        int row = random.nextInt(this.getMap().getHeight());
        int column = random.nextInt(this.getMap().getWidth());
        Cell cell = map.getCellByCoordination(row, column);
        while (cell.getLivingCard() != null) {
            row = random.nextInt(this.getMap().getHeight());
            column = random.nextInt(this.getMap().getWidth());
            cell = map.getCellByCoordination(row, column);
        }
        cell.getItems().add(item);
    }


    private void setCards(Player player) {
        for (CollectionItem collectionItem : player.getAccount().getCollection().getMainDeck().getCards()) {
            if (collectionItem instanceof Item) continue;
            if (collectionItem instanceof Hero) ((Hero) collectionItem).setBattle(this);
            if (collectionItem instanceof Spell) ((Spell) collectionItem).setBattle(this);
            if (collectionItem instanceof Minion) ((Minion) collectionItem).setBattle(this);
        }
    }


    public void showGameInfo() {
        //remaining mana bayad bashe ya maximum
        System.out.println("my mana : " + playerOn.getMana().getCurrentMana());
        System.out.println("opponent mana : " + playerOff.getMana().getCurrentMana());
        if (this.kind.equals(kinds[0])) {
            System.out.println("my hero HP : " + playerOn.getHero().getHP());
            System.out.println("opponent HP : " + playerOff.getHero().getHP());
        }
        //chera sotoon o satr midi biroon ?
        if (this.kind.equals(kinds[1])) {
            System.out.println("flag position is : " + this.mainFlag.getPositionRow() + ", " + this.mainFlag.getPositionColumn());
            //usernamesh bayad chap she?
            if (this.mainFlag.getFlagOwner() != null)
                System.out.println("flag owner is : " + this.mainFlag.getFlagOwner().getAccount().getUsername());
        }
        if (this.kind.equals(kinds[2])) {
            //hamin shekli bayad bashe ? id e sarbaz bayad bede ya chi ?
            for (Flag flag : this.getFlags()) {
                if (flag.getFlagOwner() != null) {
                    System.out.println("card id : " + flag.getFlagLivingCard().getID() + " owner player : " +
                            flag.getFlagOwner().getAccount().getUsername());
                }
            }
        }
    }

    public void showMinions(Player player) {
        for (LivingCard livingCard : player.getAliveCards()) {
            if (livingCard instanceof Minion) {
                String info = livingCard.getID() + " : " + livingCard.getName() + ", health : " + livingCard.getHP();
                info += ", location : (" + livingCard.getPositionRow() + ", " + livingCard.getPositionColumn() + "), power : ";
                info += livingCard.getAP();
                System.out.println(info);
            }
        }
    }

    public void showMyMinions() {
        showMinions(playerOn);
    }

    public void showOpponentMinions() {
        showMinions(playerOff);
    }

    public CollectionItem getCollectionItemInList(ArrayList<CollectionItem> collectionItems, String ID) {
        for (CollectionItem collectionItem : collectionItems) {
            if (collectionItem.getID().equals(ID))
                return collectionItem;
        }
        return null;
    }

    //faghat vase livingCard e ?asan malum nis chejurie , card asan bayad tu bazi bashe ya chi koja donbalesh begardim
    public void showCardInfo(String ID) {
        String info = "card was not found";
        ArrayList<CollectionItem> onCollectionItems = playerOn.getAccount().getCollection().getMainDeck().getCards();
        ArrayList<CollectionItem> offCollectionItems = playerOff.getAccount().getCollection().getMainDeck().getCards();
        CollectionItem collectionItem = getCollectionItemInList(onCollectionItems, ID);
        if (collectionItem == null)
            collectionItem = getCollectionItemInList(offCollectionItems, ID);
        if (collectionItem != null) {
            ((Card) collectionItem).showCardInBattle();
        }
    }

    public boolean selectCard(String ID) {
        for (LivingCard livingCard : playerOn.getAliveCards()) {
            if (livingCard.getID().equals(ID)) {
                this.selectedCard = livingCard;
                return true;
            }
        }
        return false;
    }

    //todo in ke kollan age az collectible estefade mikhaim bokonim ya na ro moshkakhas konim
    public boolean selectItem(String collectibleItemID) {
        for (CollectionItem collectionItem : playerOn.getHand().getHandCards())
            if (collectionItem instanceof Item && collectionItem.getID().equals(collectibleItemID)) {
                System.out.println(collectibleItemID);
                this.selectedCollectibleItem = (Item) collectionItem;
                return true;
            }
        return false;
    }

    public void removeSelectedCard() {
        this.selectedCard = null;
    }

    public void removeSelectedcollectibleItem() {
        this.selectedCollectibleItem = null;
    }

    public ArrayList<Minion> getOurMinionsOFCells(ArrayList<Cell> impactCells) {
        ArrayList<Minion> minions = new ArrayList<>();
        for (Cell cell : impactCells) {
            LivingCard livingCard = cell.getLivingCard();
            if (livingCard == null)
                continue;
            if (livingCard instanceof Minion) {
                boolean isOurs = playerOn.getAliveCards().contains(livingCard);
                if (isOurs) {
                    minions.add((Minion) livingCard);
                }
            }
        }
        return minions;
    }

    public void minionOnSpawn(Minion minion) {
        if (minion.getInformation().isOnSpawn())
            Impact.specialAttackOfMinion(this, minion, null);
    }

    public void endTurnMinion(Minion minion) {
        if (minion.getInformation().isOnTurn())
            Impact.specialAttackOfMinion(this, minion, null);
    }

    public void insertCardInMap(String cardID, int x, int y) {
        CollectionItem insertingCollectionItem = null;
        for (CollectionItem collectionItem : playerOn.getHand().getHandCards()) {
            if (collectionItem.getID().equals(cardID))
                insertingCollectionItem = collectionItem;
        }
        if (insertingCollectionItem == null) {
            System.out.println("Invalid card ID");
            return;
        }
        if (!isInMap(x, y)) {
            System.out.println("Invalid target");
            return;
        }

        if (insertingCollectionItem instanceof Card) {
            if (playerOn.getMana().getCurrentMana() < ((Card) insertingCollectionItem).getMP()) {
                System.out.println("Low mana !! ");
                return;
            }
        }

        Cell cell = map.getCellByCoordination(x, y);
        if (insertingCollectionItem instanceof LivingCard) {
            if (cell.getLivingCard() != null) {
                System.out.println("Destination is full !");
                return;
            }
            if (insertingCollectionItem instanceof Minion) {
                minionOnSpawn((Minion) insertingCollectionItem);
            }
            playerOn.getHand().removeCard(cardID);
            playerOn.getHand().addNextCard(playerOn.getAccount().getCollection().getMainDeck());
            ((LivingCard) insertingCollectionItem).setPositionColumn(y);
            ((LivingCard) insertingCollectionItem).setPositionRow(x);
            cell.insertCard(insertingCollectionItem.getID());
            playerOn.addAliveCard((LivingCard) insertingCollectionItem);
            if (playerOn.isHaveGhosleTamid()) {
                Impact.addHolyToCard(2, false, false, 1,
                        ((LivingCard) insertingCollectionItem));
            }
        } else {
            if (insertingCollectionItem instanceof Spell) {
                playerOn.getHand().removeCard(cardID);
                playerOn.getHand().addNextCard(playerOn.getAccount().getCollection().getMainDeck());
                Spell spell = (Spell) insertingCollectionItem;
                Impact.impactSpell(this, spell, cell);
            }
        }
        if (insertingCollectionItem instanceof Card)
            playerOn.getMana().decreaseMana(((Card) insertingCollectionItem).getMP());
        handleFlags();
    }

    //todo in ja masalan vaghti mire aya flag o chizaye dg behesh dade mishe ya diverte mese bazia ?
    public ServerMassage moveCardTo(int x, int y) {
        if (selectedCard == null) {
            System.out.println("select a card");
            return new ServerMassage(ServerMassage.Type.Error, null);
        }
        if (!isInMap(x, y)) {
            System.out.println("Invalid coordination");
            return new ServerMassage(ServerMassage.Type.Error, null);
        }
        if (map.getCellByCoordination(x, y).getLivingCard() != null) {
            System.out.println("Invalid target !");
            return new ServerMassage(ServerMassage.Type.Error, null);
        }
        if (!selectedCard.isCanMove()) {
            System.out.println("This card can't move");
            return new ServerMassage(ServerMassage.Type.Error, null);
        }

        int distance = getDistance(selectedCard.getPositionRow(), selectedCard.getPositionColumn(), x, y);
        int maxDistanceCanCardGo = 2;
        if (this.selectedCard.getCanMoveGreaterTwoCell()) maxDistanceCanCardGo = Integer.MAX_VALUE;

        System.out.println(distance + " " + maxDistanceCanCardGo);

        if (distance <= maxDistanceCanCardGo && distance < Integer.MAX_VALUE) {
            Cell lastCell = map.getCellByCoordination(selectedCard.getPositionRow(), selectedCard.getPositionColumn());
            lastCell.removeCard();

            selectedCard.setPositionColumn(y);
            selectedCard.setPositionRow(x);

            Cell cell = map.getCellByCoordination(x, y);
            cell.insertCard(selectedCard.getID());
            this.selectedCard.setCanMove(false);
        } else
            return new ServerMassage(ServerMassage.Type.Error, null);
        handleFlags();
        checkTurn();
        return new ServerMassage(ServerMassage.Type.Accept, null);
    }

    private int getDistance(int x1, int y1, int x2, int y2) {
        int[][] dis = new int[map.getHeight()][map.getWidth()];
        for (int i = 0; i < map.getHeight(); i++)
            for (int j = 0; j < map.getWidth(); j++)
                dis[i][j] = Integer.MAX_VALUE / 10;
        dis[x1][y1] = 0;
        int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};
        for (int k = 0; k < map.getWidth() * map.getWidth(); k++) {
            for (int i = 0; i < map.getHeight(); i++)
                for (int j = 0; j < map.getWidth(); j++)
                    for (int t = 0; t < 4; t++) {
                        int newX = i + dx[t], newY = j + dy[t];
                        if (!isInMap(newX, newY))
                            continue;
                        LivingCard livingCard = map.getCellByCoordination(newX, newY).getLivingCard();
                        if (livingCard != null) {
                            Deck deck = playerOn.getAccount().getCollection().getMainDeck();
                            if (deck.findCollectionItemInDeck(livingCard.getID()) == null)
                                continue;
                        }
                        dis[i][j] = Integer.min(dis[i][j], dis[newX][newY] + 1);
                    }
        }
        return dis[x2][y2];
    }

    private boolean isInMap(int x, int y) {
        if (x < 0 || y < 0) return false;
        if (x >= map.getHeight() || y >= map.getWidth())
            return false;
        return true;
    }

    public ServerMassage attackToOpponentCard(String opponentID) {
        if (selectedCard == null) {
            System.out.println("Select a card");
            return new ServerMassage(ServerMassage.Type.Error, null);
        }
        ArrayList<LivingCard> opponentAliveCards = playerOff.getAliveCards();
        LivingCard opponentLivingCard = null;
        for (LivingCard livingCard : opponentAliveCards)
            if (livingCard.getID().equals(opponentID))
                opponentLivingCard = livingCard;
        if (opponentLivingCard == null) {
            System.out.println("Invalid card id");
            return new ServerMassage(ServerMassage.Type.Error, null);
        }
        Impact.attack(this, this.selectedCard, opponentLivingCard);
        checkTurn();
        return new ServerMassage(ServerMassage.Type.Accept, null);
    }

    public void comboAttackToOpponentCard(String[] input) {
        String opponentID = input[2];
        LivingCard opponentLivingCard = null;
        for (LivingCard livingCard : playerOff.getAliveCards())
            if (livingCard.getID().equals(opponentID))
                opponentLivingCard = livingCard;
        if (opponentLivingCard == null) {
            System.out.println("Invalid opponent card id");
            return;
        }
        ArrayList<LivingCard> myLivingCards = new ArrayList<>();
        for (int i = 3; i < input.length; i++) {
            LivingCard myLivingCard = null;
            for (LivingCard livingCard : playerOn.getAliveCards())
                if (livingCard.getID().equals(input[i]))
                    myLivingCard = livingCard;
            if (myLivingCard == null) {
                System.out.println("Invalid card id");
                return;
            }
            myLivingCards.add(myLivingCard);
            if (!myLivingCard.getInformation().isCombo()) {
                System.out.println("Cards can not do combo attack");
                return;
            }
        }
        Impact.comboAttack(this, opponentLivingCard, myLivingCards);
        checkTurn();
    }

    //TODO

    public void showHand() {
        playerOn.getHand().show(playerOn.getAccount().getCollection().getMainDeck());
    }

    //TODO
    //in ja bayad kheili kar ha bokonim
    //tahesh migim daghighan chia
    //buff haye passive is activeshun true she
    public void handleEndTurnMinions(Player player) {
        for (LivingCard livingCard : player.getAliveCards()) {
            if (livingCard instanceof Minion)
                endTurnMinion((Minion) livingCard);
        }
        checkTurn();
    }

    public void checkAliveCards(Player player) {
        for (int i = player.getAliveCards().size() - 1; i > -1; i--)
            player.getAliveCards().get(i).checkAlive(this);
        checkTurn();
    }

    public void endTurn() {
        setRemainTimeOfTurn(maximumTimeOfTurn);

        playerOn.getMana().configureMana();

        canLivingCards(playerOn);
        canLivingCards(playerOff);

        checkThings(playerOff);
        checkThings(playerOn);

        handleBuffs(playerOff);
        handleBuffs(playerOn);


        handleEndTurnMinions(playerOn);
        handleEndTurnMinions(playerOff);

        checkAliveCards(playerOn);
        checkAliveCards(playerOff);

        Player player = playerOff;
        playerOff = playerOn;
        playerOn = player;

        if (this.getKind().equals(kinds[1])) {
            if (this.mainFlag.getFlagOwner() != null)
                mainFlag.setNumberOfGotRounds(mainFlag.getNumberOfGotRounds() + 1);
            else
                mainFlag.setNumberOfGotRounds(0);
        }

        playerOn.getHero().setCoolDown(Math.max(0, playerOn.getHero().getCoolDown() - 1));
        playerOff.getHero().setCoolDown(Math.max(0, playerOff.getHero().getCoolDown() - 1));

        Impact.activeBuffs(this);

        numberOfRounds++;
        if (playerOn instanceof AI)
            readInput();

        selectedCard = null;
        selectedCollectibleItem = null;
    }

    public void showCollectibles() {
        for (CollectibleItem collectibleItem : playerOn.getCollectibleItems()) {
            System.out.println(collectibleItem.getInfo());
        }
    }


    public void useSpecialPower(int x, int y) {
        Hero hero = playerOn.getHero();
        if (hero == null) {
            System.out.println("select a card");
            return;
        }
        if (playerOn.getMana().getCurrentMana() < hero.getMP()) {
            System.out.println("need more mana");
            return;
        }
        if (hero.getCoolDown() > 0) {
            System.out.println("cool down time");
            return;
        }
        if (!isInMap(x, y)) {
            System.out.println("Invalid coordination");
            return;
        }
        //anjam nashe ham cooldown mishe

        Cell cell = this.map.getCellByCoordination(x, y);
        Impact.impactSpellOfHero(this, playerOn.getHero(), cell);
        hero.setCoolDown(hero.getMaxCoolDown());
        playerOn.getMana().decreaseMana(hero.getMP());
        checkTurn();
    }

    //TODO
    //in bayad az hand pak she
    public void useItem(int x, int y) {
        if (selectedCollectibleItem == null) {
            System.out.println("select a collectible item");
            return;
        }
        if (!isInMap(x, y)) {
            System.out.println("Invalid coordination");
            return;
        }
        playerOn.getHand().removeCard(selectedCollectibleItem.getID());
        playerOn.getHand().addNextCard(playerOn.getAccount().getCollection().getMainDeck());
        Cell cell = map.getCellByCoordination(x, y);
        Impact.impactItem((Item) selectedCollectibleItem, cell, this);

        checkTurn();
    }

    //havaset bashe moteghayerasho update koni mese tedad cardaye estefade shode az main deck
    public void showNextCard() {
        playerOn.getHand().showNextCard(playerOn.getAccount().getCollection().getMainDeck());
    }

    //bazi jaha bayad khali shan selecteditem o card
    public void showItemInfo() {
        if (selectedCollectibleItem == null) {
            System.out.println("select an item");
            return;
        }
        System.out.println(selectedCollectibleItem.getInfo());
    }

    //-----------------------------------------------
    //ta injaro khundam

    //todo, momkene bazi mosavi she khob
    public void checkTurn() {
        if (this.getKind().equals(kinds[0])) {
            if (playerOn.getHero().getHP() <= 0) {
                this.setLoserPlayer(playerOn);
                this.setWinnerPlayer(playerOff);
            }
            if (playerOff.getHero().getHP() <= 0) {
                this.setLoserPlayer(playerOff);
                this.setWinnerPlayer(playerOn);
            }
        }
        if (this.getKind().equals(kinds[1])) {
            if (mainFlag.getNumberOfGotRounds() >= 6) {
                Player winner = mainFlag.getFlagOwner();
                this.setWinnerPlayer(winner);
                if (playerOff.getAccount().getUsername().equals(winner.getAccount().getUsername())) {
                    this.setLoserPlayer(playerOn);
                } else {
                    this.setLoserPlayer(playerOff);
                }
            }
        }
        if (this.getKind().equals(kinds[2])) {
            int numberOfPlayerOnFlags = 0, numberOfPlayerOffFlags = 0;
            for (Flag flag : flags) {
                Player flagOwner = flag.getFlagOwner();
                if (flagOwner != null) {
                    if (flagOwner.getAccount().getUsername().equals(playerOn.getAccount().getUsername()))
                        numberOfPlayerOnFlags++;
                    else
                        numberOfPlayerOffFlags++;
                }
            }
            if (numberOfPlayerOnFlags > numberOfFlags / 2) {
                this.setWinnerPlayer(playerOn);
                this.setLoserPlayer(playerOff);
            }
            if (numberOfPlayerOffFlags > numberOfFlags / 2) {
                this.setWinnerPlayer(playerOff);
                this.setLoserPlayer(playerOn);
            }
        }
        //inja bayad neshun bede barande o etela'ato
        if (this.getWinnerPlayer() != null && this.estate.equals("none")) {
            finishMatch();
        }
    }

    public void finishMatch() {
        estate = "finished";
        Match match = new Match();
        match.setWinner(this.winnerPlayer.getAccount());
        match.setLoser(this.loserPlayer.getAccount());
        match.setTime(this.numberOfRounds);
        playerOn.getAccount().addMatch(match);
        playerOff.getAccount().addMatch(match);
        System.out.println("Game finished! the winner is : " + winnerPlayer.getAccount().getUsername());
        System.out.println("the number of rounds is :" + numberOfRounds);
        Account winnerAccount = winnerPlayer.getAccount();
        winnerAccount.setBudget(winnerAccount.getBudget() + this.prize);
        winnerAccount.setCurrentMenu(MenuList.WinnerPage);
        loserPlayer.getAccount().setCurrentMenu(MenuList.LoserPage);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        setupFree(playerOn);
        setupFree(playerOff);
    }

    private void setupFree(Player player) {
        player.getAccount().setRunningBattle(null);
        player.getAccount().setState(Account.State.Online);
    }

    public void endGame() {
        return;
    }

    public void exit() {
        return;
    }

    //TODO
    //masalan in ke yeki bemire bere tooye grave yard add she
    public void enterGraveYard() {
        playerOn.getGraveYard().inputCommandLine();
    }

    public String coordinationString(LivingCard livingCard) {
        String coordination = " Coordination : (" + livingCard.getPositionRow() + ", " + livingCard.getPositionColumn() + ") ";
        return coordination;
    }

    public void help() {
        System.out.println("you can insert these cards :");
        for (CollectionItem collectionItem : playerOn.getHand().getHandCards()) {
            if (collectionItem instanceof Card) {
                if (((Card) collectionItem).getMP() <= playerOn.getMana().getCurrentMana()) {
                    System.out.println("name: " + collectionItem.getName() + " id: " + collectionItem.getID() + " mana: " +
                            ((Card) collectionItem).getMP());
                }
            }
        }
        System.out.println("you can move these cards:");
        for (LivingCard livingCard : playerOn.getAliveCards()) {
            if (livingCard.isCanMove()) {
                System.out.println("name: " + livingCard.getName() + " id: " + livingCard.getID() +
                        this.coordinationString(livingCard));
            }
        }
        System.out.println("you can attack with these cards:");
        for (LivingCard livingCard : playerOn.getAliveCards()) {
            if (livingCard.isCanAttack()) {
                System.out.println("name: " + livingCard.getName() + " id: " + livingCard.getID() +
                        this.coordinationString(livingCard));
            }
        }
        System.out.println("you can attack to these cards:");
        for (LivingCard livingCard : playerOff.getAliveCards())
            System.out.println("name: " + livingCard.getName() + " id: " + livingCard.getID() +
                    this.coordinationString(livingCard));
        if (playerOn.getMana().getCurrentMana() >= playerOn.getHero().getMP()) {
            if (playerOn.getHero().getCoolDown() <= 0) {
                System.out.println("also you can use special power of your hero:");
                System.out.println("name: " + playerOn.getHero().getName() + " id: " + playerOn.getHero().getID() +
                        " mana: " + playerOn.getHero().getMP() + this.coordinationString(playerOn.getHero()));
            }
        }
    }

    public Player getOwnerOfLivingCard(LivingCard livingCard) {
        if (playerOn.getAliveCards().contains(livingCard))
            return playerOn;
        return playerOff;
    }

    public void handleFlags() {
        //in ja oonaei ke mordan ro doros mikonim
        for (Flag flag : flags) {
            if (flag.getFlagLivingCard() == null)
                continue;
            if (flag.getFlagLivingCard().getHP() <= 0) {
                flag.setFlagOwner(null);
                flag.setFlagLivingCard(null);
                flag.setNumberOfGotRounds(0);
            }
        }


        for (Flag flag : flags) {
            LivingCard livingCard = flag.getFlagLivingCard();
            if (livingCard != null) {
                flag.setPositionColumn(livingCard.getPositionColumn());
                flag.setPositionRow(livingCard.getPositionRow());
                Cell cell = this.getMap().getCellByCoordination(flag.getPositionRow(), flag.getPositionColumn());
                cell.setHaveFlag(false);
            }
        }

        for (Flag flag : flags) {
            Cell cell = this.getMap().getCellByCoordination(flag.getPositionRow(), flag.getPositionColumn());
            LivingCard livingCard = cell.getLivingCard();
            if (livingCard == null) {
                cell.setHaveFlag(true);
                continue;
            }
            if (flag.getFlagLivingCard() == null) {
                flag.setFlagLivingCard(livingCard);
                flag.setFlagOwner(getOwnerOfLivingCard(livingCard));
                cell.setHaveFlag(false);
            }
        }
    }

    public void showMenu() {
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
        System.out.println("13. Show collectibles");
        System.out.println("14. Select [collectible id]");
        System.out.println("15. Show info");
        System.out.println("16. Use [location x, y]");
        System.out.println("17. Show Next Card");
        System.out.println("18. Enter graveyard");
        System.out.println("19. Help");
        System.out.println("20. End Game");
        System.out.println("21. Exit");
        System.out.println("22. Show menu");
        System.out.println("23. Forfeit match");
    }


    public void runGame() throws FileNotFoundException {
        preProcess();
    }

    public void forfeitMatch(String authToken) {
        if(playerOn.getAccount().getUsername().equals(authToken)) {
            this.setWinnerPlayer(playerOff);
            this.setLoserPlayer(playerOn);
        }
        else{
            this.setWinnerPlayer(playerOn);
            this.setLoserPlayer(playerOff);
        }
        finishMatch();
    }

    public synchronized ServerMassage inputCommandLine(String inputLine, String clientUsername) {
        synchronized (this) {
            inputLine = inputLine.trim();
            String inputLineOriginal = inputLine;
            inputLine = inputLine.toLowerCase();
            String[] input = inputLineOriginal.split("[ ]+");

            System.out.println(inputLine + " by " + clientUsername);

/*
        if (!clientUsername.equals("admin")) {
            System.out.println("player on : " + playerOn.getAccount().getUsername());
            System.out.println("Here is Battle");
            System.out.println("For help, enter : show menu");
            System.out.println(kind);
        }
*/
            if(inputLine.equals("give grave yard")){
                ServerMassage serverMassage = new ServerMassage(ServerMassage.Type.Accept, null);
                if(playerOn.getAccount().getUsername().equals(clientUsername))
                    serverMassage.setGraveYard(playerOn.getGraveYard().getCards());
                else
                    serverMassage.setGraveYard(playerOff.getGraveYard().getCards());
                return serverMassage;
            }
            if (inputLine.equals("forfeit match"))
                forfeitMatch(clientUsername);
            if (inputLine.equals("enter graveyard"))
                enterGraveYard();

            if (!clientUsername.equals("sudo") && !clientUsername.equals(playerOn.getAccount().getUsername()))
                return new ServerMassage(ServerMassage.Type.Error, null);

            if (inputLine.equals("game info"))
                showGameInfo();
            else if (inputLine.equals("show my minions"))
                showMyMinions();
            else if (inputLine.equals("show opponent minions"))
                showOpponentMinions();
            else if (inputLine.matches("show card info .*"))
                showCardInfo(input[3]);
            else if (inputLine.matches("select .*")) {
                boolean isFound = selectCard(input[1]) || selectItem(input[1]);
                if (!isFound) System.out.println("Can't find this card");
                else System.out.println("card found !!");
            } else if (inputLine.matches("move to \\([\\d]+, [\\d]+\\)")) {
                input = inputLine.split("[ \\(\\),]+");
                return moveCardTo(Integer.parseInt(input[2]), Integer.parseInt(input[3]));
            } else if (inputLine.matches("attack .*"))
                return attackToOpponentCard(input[1]);
            else if (inputLine.matches("attack combo [^\\s]+( [^\\s]+)+"))
                comboAttackToOpponentCard(input);
            else if (inputLine.matches("use special power \\([\\d]+, [\\d]+\\)")) {
                input = inputLine.split("[ \\(\\),]+");
                useSpecialPower(Integer.parseInt(input[3]), Integer.parseInt(input[4]));
            } else if (inputLine.equals("show hand"))
                showHand();
            else if (inputLine.matches("insert [^\\s]+ in \\([\\d]+, [\\d]+\\)")) {
                input = inputLineOriginal.split("[ \\(\\),]+");
                insertCardInMap(input[1], Integer.parseInt(input[3]), Integer.parseInt(input[4]));
            } else if (inputLine.equals("end turn"))
                endTurn();
            else if (inputLine.equals("show collectibles"))
                showCollectibles();
            else if (inputLine.equals("show info"))
                showItemInfo();
            else if (inputLine.matches("use [\\d], [\\d]")) {
                input = inputLine.split("[ \\(\\),]+");
                useItem(Integer.parseInt(input[1]), Integer.parseInt(input[2]));
            } else if (inputLine.equals("show next card"))
                showNextCard();
            else if (inputLine.equals("help"))
                help();
            else if (inputLine.equals("end game"))
                endGame();
            else if (inputLine.equals("exit"))
                return null;
            else if (inputLine.equals("show menu")) {
                this.showMenu();
            }

            for (Flag flag : this.flags) {
                System.out.println(flag.getPositionRow() + " " + flag.getPositionColumn() + " " + flag.getFlagOwner());
            }

            return null;
        }
    }

    private void handleBuffs(Player player) {
        for (LivingCard livingCard : player.getAliveCards())
            handleBuffsOfCard(livingCard);

        //decrease remain time
        for (LivingCard livingCard : player.getAliveCards())
            decreaseTimeOfBuff(livingCard.getEffects());

        for (int i = 0; i < this.map.getHeight(); i++)
            for (int j = 0; j < this.map.getWidth(); j++) {
                Cell cell = this.map.getCellByCoordination(i, j);
                decreaseTimeOfBuff(cell.getEffects());
            }

        //relax
    }

    public void decreaseTimeOfBuff(ArrayList<Buff> buffs) {
        for (int i = buffs.size() - 1; i > -1; i--) {
            Buff buff = buffs.get(i);
            buff.setIsActive(true);
            buff.setRemainTime(buff.getRemainTime() - 1);
            if (buff.isPermanent()) continue;
            if (buff.getRemainTime() < 0) {
                buffs.remove(buff);
            }
        }
    }

    public void handleBuffsOfCard(LivingCard livingCard) {
        //reset
        livingCard.setShield(0);
        livingCard.setExtraAP(0);
        livingCard.setExtraHP(0);
        livingCard.setCanCounterAttack(true);
        livingCard.setCanMove(true);
        livingCard.setCanAttack(true);
        //set
        for (int i = livingCard.getEffects().size() - 1; i > -1; i--) {
            Buff buff = livingCard.getEffects().get(i);
            Impact.impactBuffInLivingCard(buff, livingCard);

        }
        for (int i = livingCard.getCell().getEffects().size() - 1; i > -1; i--) {
            Buff buff = livingCard.getCell().getEffects().get(i);
            Impact.impactBuffInCell(buff, livingCard.getCell());
        }
        for (int i = livingCard.getCell().getItems().size() - 1; i > -1; i--) {
            Item item = livingCard.getCell().getItems().get(i);
            Impact.impactItem(item, livingCard.getCell(), this);
            livingCard.getCell().getItems().remove(item);
        }
    }

    private void checkThings(Player player) {
//        player.getHero().checkPareSimorgh();
//        if (player.isHaveTerrorHood()) Impact.impactTerrorHood(player.getHeroPosition(), this);
//        if (player.getHero().isHaveKingKiller()) {
//            if (player.getHero().getDeadAfterRounds() == 0) player.getHero().kill();
//            else player.getHero().setDeadAfterRounds(player.getHero().getDeadAfterRounds() - 1);
//        }
    }

    private void readInput() {
        ((AI) playerOn).outputSomeRandomOrder(this);
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

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
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

    public void setLoserPlayer(Player player) {
        this.loserPlayer = player;
    }

    public Player getLoserPlayer() {
        return this.loserPlayer;
    }

    public void setWinnerPlayer(Player player) {
        this.winnerPlayer = player;
    }

    public Player getWinnerPlayer() {
        return this.winnerPlayer;
    }

    public void removeAliveCard(LivingCard removingLivingCard) {
        playerOn.removeDeadCard(removingLivingCard);
        playerOff.removeDeadCard(removingLivingCard);
    }

    public ArrayList<Flag> getFlags() {
        return this.flags;
    }

    public void addFlag(Flag flag) {
        this.flags.add(flag);
    }

    public LivingCard getSelectedCard() {
        return this.selectedCard;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public synchronized int getRemainTimeOfTurn() {
        return remainTimeOfTurn;
    }

    public synchronized void setRemainTimeOfTurn(int remainTimeOfTurn) {
        this.remainTimeOfTurn = remainTimeOfTurn;
    }

    public String getEstate() {
        return estate;
    }

    public ServerMassage interpret(ClientMassage clientMassage) {

        if(clientMassage.getBattleRequest() == ClientMassage.BattleRequest.GiveGraveYard){
            return inputCommandLine("give grave yard", clientMassage.getAuthToken());
        }
        if (clientMassage.getBattleRequest() == ClientMassage.BattleRequest.ForfeitMatch)
            return inputCommandLine("forfeit match", clientMassage.getAuthToken());
        if (clientMassage.getBattleRequest() == ClientMassage.BattleRequest.EndTurn)
            return inputCommandLine("end turn", clientMassage.getAuthToken());
        if (clientMassage.getBattleRequest() == ClientMassage.BattleRequest.Select)
            return inputCommandLine("select " + clientMassage.getCollectionItemID(), clientMassage.getAuthToken());
        if (clientMassage.getBattleRequest() == ClientMassage.BattleRequest.UseItem)
            return inputCommandLine("use " + clientMassage.getX() + ", " + clientMassage.getY(), clientMassage.getAuthToken());
        if (clientMassage.getBattleRequest() == ClientMassage.BattleRequest.InsertCard)
            return inputCommandLine("insert " + clientMassage.getCollectionItemID() + " in (" + clientMassage.getX() + ", " + clientMassage.getY() + ")", clientMassage.getAuthToken());
        if (clientMassage.getBattleRequest() == ClientMassage.BattleRequest.MoveCard)
            return inputCommandLine("move to (" + clientMassage.getX() + ", " + clientMassage.getY() + ")", clientMassage.getAuthToken());
        if (clientMassage.getBattleRequest() == ClientMassage.BattleRequest.Attack)
            return inputCommandLine("attack " + clientMassage.getCollectionItemID(), clientMassage.getAuthToken());
        if (clientMassage.getBattleRequest() == ClientMassage.BattleRequest.UseSpecialPower)
            return inputCommandLine("use special power (" + clientMassage.getX() + ", " + clientMassage.getY() + ")", clientMassage.getAuthToken());
        return null;
    }

    public int getMaximumTimeOfTurn() {
        return maximumTimeOfTurn;
    }
}
