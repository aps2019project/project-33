package Controller.Menus;

import Controller.*;
import Generator.DeckGenerator;
import Model.*;
import Model.CollectionItem.*;

import java.io.FileNotFoundException;

//type mishe single, multi
//mode mishe custom ya story
//kind mishe in ke kill bashe o ina ...
// chaptersam ke hich
//address e story ha in goone bashad ke 3 4 ta story bashe, ke storyi esme taraf bashe

public class BattleMenu {

    private boolean isRunning = true;
    private Battle battle = new Battle();

    private String[] types = {"Single Player", "Multi Player"};
    private String[] modes = {"Story", "Custom Game"};
    private String[] kinds = {"Kill_enemy's_hero", "Hold_flag", "Take_half_of_flags"};
    private String[] chapters = {"fight with DiveSefid", "fight with Zahhak",
            "fight with Arash"};
    private int[] prizeOfChapters = {500, 1000, 1500};
    private int[] numberOfFlags = {0, 1, 5};

    private int numberOfDecksInCustomGame = 3;
    private int prizeOfCustomGame = 1000;

    //battle, player 1, player 2, prize, mode, numberOfFlag

    public void createGame(String type, String mode, String chapter, String secondPlayer) throws FileNotFoundException {

        if (!checkDeck(Main.application.getLoggedInAccount())) {
            System.out.println("selected deck is invalid");
            return;
        }


        //set players
        battle.setPlayerOn(new Player(Main.application.getLoggedInAccount()));
        chooseSecondPlayer(secondPlayer);
        if (battle.getPlayerOff() == null || !checkDeck(battle.getPlayerOff().getAccount())) {
            System.out.println("Invalid rival");
            return;
        }

        //set battle details
        chooseType(type);
        if(battle.getType("Single Player")) setSinglePlayerDetails


        battle.runGame();
    }

    private void chooseType(String type) {
        battle.setType(type);
    }

    private void chooseSecondPlayer(String secondPlayer) {
        if (battle.getType().equals("Single Player"))
            battle.setPlayerOff(new AI());
        else {
            Account account = Account.getAccountByUsername(secondPlayer);
            if (account != null)
                battle.setPlayerOff(new Player(account));
        }
    }

    private void customGame(String mode, String chapter) throws FileNotFoundException {

        if (mode.equals("Story")) {
            String address = "Data/Battle/Story/Story";
            for (int i = 0; i < chapters.length; i++) {
                if (chapters[i].equals(chapter)) {
                    battle.setPrize(prizeOfChapters[i]);
                    battle.setKind(kinds[i]);
                    battle.setNumberOfFlags(numberOfFlags[i]);

                    Deck deck = (Deck) Application.readJSON(Deck.class, address + i + ".json");
                    ((AI) battle.getPlayerOff()).selectMainDeck(deck);
                }
            }
        }

        if (mode.equals("Custom Game")) {
            battle.setPrize(prizeOfCustomGame);

        }
    }

    private void setMode(String mode, int numberOfFlags) {
        for (int i = 0; i < kinds.length; i++)
            if (mode.equals(kinds[i])) {
                battle.setKind(kinds[i]);
                if (i == 2)
                    battle.setNumberOfFlags(numberOfFlags);
            }
    }


    private boolean checkDeck(Account account) {
        if (account.getCollection().getMainDeck() == null) return false;
        return account.getCollection().getMainDeck().checkValidateDeck();
    }

    public void story() throws FileNotFoundException {
        if (!isRunning) return;
        System.out.println("Levels :");
        for (int i = 0; i < chapters.length; i++)
            System.out.println(chapters[i] + ", Prize : " + prizeOfChapters[i]);
        System.out.println("Enter number of level: ");
        System.out.println("For choosing, enter : [Number of level]");
        readInputs();
        if (inputLine.matches("[\\d]+")) {
            for (int i = 0; i < chapters.length; i++)
                if (Integer.parseInt(inputLine) == i + 1) {
                    selectAIMainDeck(i);
                    battle.setKind(kinds[i]);
                    battle.setPrize(prizeOfChapters[i]);
                    if (i == 2)
                        battle.setNumberOfFlags(5);
                    return;
                }
        }
        System.out.println("Enter valid level");
        story();
    }

    private void selectAIMainDeck(int index) throws FileNotFoundException {
        String address = "Data/Battle/Story/Story";
        Deck deck = (Deck) Application.readJSON(Deck.class, address + index + ".json");
        ((AI) battle.getPlayerOff()).selectMainDeck(deck);
    }

    public void handleDeck(Account account) throws FileNotFoundException {
        account.getCollection().createDeck(account.getUsername());
        account.getCollection().selectMainDeck(account.getUsername());
        for (int i = 0; i < 2; i++) {
            Spell spell = Spell.createSpell(DeckGenerator.spellNames[0], account.getUsername());
            account.getCollection().addCollectionItemToCollection(spell.getID());
            account.getCollection().addCollectionItemToDeck(spell.getID(), account.getUsername());
        }
        for (int i = 0; i < 3; i++) {
            Item item = Item.createItem(DeckGenerator.itemNames[0], account.getUsername());
            account.getCollection().addCollectionItemToCollection(item.getID());
            account.getCollection().addCollectionItemToDeck(item.getID(), account.getUsername());
        }
        for (int i = 0; i < 15; i++) {
            Minion minion = Minion.createMinion(DeckGenerator.minionNames[0], account.getUsername());
            account.getCollection().addCollectionItemToCollection(minion.getID());
            account.getCollection().addCollectionItemToDeck(minion.getID(), account.getUsername());
        }
        Hero hero = Hero.createHero(DeckGenerator.heroNames[0], account.getUsername());
        account.getCollection().addCollectionItemToCollection(hero.getID());
        account.getCollection().addCollectionItemToDeck(hero.getID(), account.getUsername());
    }
}

