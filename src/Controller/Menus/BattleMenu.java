package Controller.Menus;

import Controller.*;
import Generator.DeckGenerator;
import Model.*;
import Model.CollectionItem.*;

import java.io.FileNotFoundException;

//type mishe single, multi
//mode mishe custom ya story
//kind mishe in ke kill bashe o ina ...
//chaptersam ke hich
//address e story ha in goone bashad ke 3 4 ta story bashe, ke storyi esme taraf bashe

public class BattleMenu {

    private boolean isRunning = true;
    private Battle battle;

    private String[] types = {"Single Player", "Multi Player"};
    private String[] modes = {"Story", "Custom Game"};
    private String[] kinds = {"Kill_enemy's_hero", "Hold_flag", "Take_half_of_flags"};
    private String[] chapters = {"fight with DiveSefid", "fight with Zahhak",
            "fight with Arash"};
    private int[] prizeOfChapters = {500, 1000, 1500};
    private int[] numberOfFlagsOfChapters = {0, 1, 5};

    private int numberOfDecksInCustomGame = 3;
    private int prizeOfCustomGame = 1000;

    //battle, player 1, player 2, prize, mode, numberOfFlag

    public void createGame(String secondPlayer, String type, String mode, String chapter, String kind) throws FileNotFoundException {
        initialize();

        //todo in ja kollan oomadim nafarate bazio moshakhas kardim -> Type
        if (!checkDeck(Main.application.getLoggedInAccount())) {
            System.out.println("selected deck is invalid");
            return;
        }

        //set players
        chooseType(type);
        battle.setPlayerOn(new Player(Main.application.getLoggedInAccount()));
        chooseSecondPlayer(secondPlayer);
        System.out.println(battle.getPlayerOn().getAccount().getUsername());
        //set battle details

        //todo in ja bayad berim badi, chie ? -? mode -> custom ya story
        //todo number of flags felan fix e, ta bebinim badan khoda chi mikhad
        if(type.equals("Single Player")) chooseMode(mode, chapter, kind, 1);

        if (battle.getPlayerOff() == null || !checkDeck(battle.getPlayerOff().getAccount())) {
            if(!checkDeck(battle.getPlayerOff().getAccount()))
                System.out.println("invalid deck");
            System.out.println("Invalid rival");
            return;
        }

        battle.runGame();
        Client.getClient().setRunningBattle(battle);
        Client.getClient().setCurrentMenu(MenuList.Battle);
    }

    private void initialize() {
        isRunning = true;
        battle = new Battle();
    }

    private void chooseType(String type) {
        battle.setType(type);
    }

    private void chooseSecondPlayer(String secondPlayer) {
        System.out.println(battle.getType());
        if (battle.getType().equals("Single Player")) {
            battle.setPlayerOff(new AI());
        }
        else {
            Account account = Account.getAccountByUsername(secondPlayer);
            if (account != null)
                battle.setPlayerOff(new Player(account));
        }
    }

    private boolean checkDeck(Account account) {
        if (account.getCollection().getMainDeck() == null) return false;
        return account.getCollection().getMainDeck().checkValidateDeck();
    }

    private void chooseMode(String mode, String chapter, String kind, int numberOfFlags) throws FileNotFoundException {

        if (mode.equals("Story")) {
            String address = "Data/Battle/Story/Story";
            for (int i = 0; i < chapters.length; i++) {
                if (chapters[i].equals(chapter)) {
                    battle.setPrize(prizeOfChapters[i]);
                    battle.setKind(kinds[i]);
                    battle.setNumberOfFlags(numberOfFlagsOfChapters[i]);

                    Deck deck = (Deck) Application.readJSON(Deck.class, address + i + ".json");
                    ((AI) battle.getPlayerOff()).selectMainDeck(deck);
                }
            }
        }
        if (mode.equals("Custom Game")) {
            battle.setPrize(prizeOfCustomGame);
            battle.setKind(kind);
            String address = "Data/Battle/Custom/Custom";
            for(int i = 0; i < kinds.length; i++){
                if(kind.equals(kinds[i])){
                    //todo in kheili bade vali chare i nist
                    battle.setNumberOfFlags(numberOfFlagsOfChapters[i]);

                    Deck deck = (Deck) Application.readJSON(Deck.class, address + i + ".json");
                    ((AI) battle.getPlayerOff()).selectMainDeck(deck);
                }
            }
        }
    }

    public static void handleDeck(Account account) throws FileNotFoundException {
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

