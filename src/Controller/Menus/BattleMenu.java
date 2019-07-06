package Controller.Menus;

import Controller.*;
import Controller.Client.ClientMassage;
import Controller.Server.ServerMassage;
import Generator.DeckGenerator;
import Model.*;
import Model.CollectionItem.*;

import java.io.FileNotFoundException;

//todo, in ja avale bazi darim setup mikonim, ye chiz shabihe hamin bayad anjam bedim baraye relax shodan

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

    public ServerMassage createGame(String firstPlayerUsername, String secondPlayerUsername, String type, String mode, String chapter, String kind) throws FileNotFoundException {
        initialize();

        Account firstPlayer = Account.getAccountByUsername(firstPlayerUsername);
        if (!checkDeck(firstPlayer))
            return new ServerMassage(ServerMassage.Type.Error, ServerMassage.ErrorType.InvalidDeckForFirstPlayer);

        //set players
        chooseType(type);
        battle.setPlayerOn(new Player(firstPlayer));
        chooseSecondPlayer(secondPlayerUsername);
        if(battle.getPlayerOff() == null)
            return new ServerMassage(ServerMassage.Type.Error, ServerMassage.ErrorType.InvalidSecondPlayerUsername);

        //set battle details

        //todo in ja bayad berim badi, chie ? -? mode -> custom ya story
        //todo number of flags felan fix e, ta bebinim badan khoda chi mikhad
        if (type.equals("Single Player")) chooseMode(mode, chapter, kind, 1);
        else chooseKind(kind);

        battle.runGame();

        if (!checkDeck(battle.getPlayerOff().getAccount()))
            return new ServerMassage(ServerMassage.Type.Error, ServerMassage.ErrorType.InvalidDeckForSecondPlayer);

        setupPlayersForBattle(battle.getPlayerOff());
        setupPlayersForBattle(battle.getPlayerOn());

        System.out.println("battle created");

        return new ServerMassage(ServerMassage.Type.Accept, null);
    }

    private void setupPlayersForBattle(Player player) {
        player.getAccount().setRunningBattle(battle);
        player.getAccount().setState(Account.State.Busy);
        player.getAccount().setCurrentMenu(MenuList.Battle);
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
        } else {
            Account account = Account.getAccountByUsername(secondPlayer);
            if (account != null)
                battle.setPlayerOff(new Player(account));
        }
    }

    private boolean checkDeck(Account account) {
        if (account.getCollection().getMainDeck() == null) return false;
        return account.getCollection().getMainDeck().checkValidateDeck();
    }

    private void chooseKind(String kind) {
        battle.setPrize(prizeOfCustomGame);
        battle.setKind(kind);
        if(!kind.equals("Kill_enemy's_hero"))
            battle.setNumberOfFlags(1);
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
            for (int i = 0; i < kinds.length; i++) {
                if (kind.equals(kinds[i])) {
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
        for (int i = 0; i < 5; i++) {
            Spell spell = Spell.createSpell(DeckGenerator.spellNames[4], account.getUsername());
            account.getCollection().addCollectionItemToCollection(spell.getID());
            account.getCollection().addCollectionItemToDeck(spell.getID(), account.getUsername());
        }
        for (int i = 0; i < 5; i++) {
            Item item = Item.createItem(DeckGenerator.itemNames[0], account.getUsername());
            account.getCollection().addCollectionItemToCollection(item.getID());
            account.getCollection().addCollectionItemToDeck(item.getID(), account.getUsername());
        }
        for (int i = 0; i < 10; i++) {
            Minion minion = Minion.createMinion(DeckGenerator.minionNames[9], account.getUsername());
            account.getCollection().addCollectionItemToCollection(minion.getID());
            account.getCollection().addCollectionItemToDeck(minion.getID(), account.getUsername());
        }
        Hero hero = Hero.createHero(DeckGenerator.heroNames[0], account.getUsername());
        account.getCollection().addCollectionItemToCollection(hero.getID());
        account.getCollection().addCollectionItemToDeck(hero.getID(), account.getUsername());
    }

    public ServerMassage interpret(ClientMassage clientMassage) throws FileNotFoundException {
        if (clientMassage.getBattleMenuRequest() == ClientMassage.BattleMenuRequest.CreateSinglePlayerGame)
            return createGame(clientMassage.getAuthToken(), clientMassage.getSecondPlayerUsername(), clientMassage.getType(), clientMassage.getMode(), clientMassage.getChapter(), clientMassage.getKind());
        if (clientMassage.getBattleMenuRequest() == ClientMassage.BattleMenuRequest.startMultiPlayerGame)
            return startCreateMultiPlayerGame(clientMassage);
        if (clientMassage.getBattleMenuRequest() == ClientMassage.BattleMenuRequest.RejectGame)
            return rejectMultiPlayerGame(clientMassage);
        if (clientMassage.getBattleMenuRequest() == ClientMassage.BattleMenuRequest.AcceptGame)
            return acceptMultiPlayerGame(clientMassage);
        return null;
    }

    private ServerMassage rejectMultiPlayerGame(ClientMassage clientMassage) {
        String username = clientMassage.getAuthToken();
        Account account2 = Account.getAccountByUsername(username);
        Account account1 = Account.getAccountByUsername(account2.getMultiPlayerGameInfo().getAuthToken());
        account2.setMultiPlayerGameInfo(null);
        account1.setState(Account.State.Online);
        account2.setState(Account.State.Online);
        account1.setCurrentMenu(MenuList.MainMenu);
        account2.setCurrentMenu(MenuList.MainMenu);
        return new ServerMassage(ServerMassage.Type.Accept, null);
    }

    private ServerMassage acceptMultiPlayerGame(ClientMassage clientMassage) throws FileNotFoundException {
        String username = clientMassage.getAuthToken();
        Account account2 = Account.getAccountByUsername(username);
        ClientMassage previousClientMassage = account2.getMultiPlayerGameInfo();
        account2.setMultiPlayerGameInfo(null);
        return createGame(previousClientMassage.getAuthToken(), previousClientMassage.getSecondPlayerUsername(), previousClientMassage.getType(), previousClientMassage.getMode(), previousClientMassage.getChapter(), previousClientMassage.getKind());
    }

    private ServerMassage startCreateMultiPlayerGame(ClientMassage clientMassage) {
        String username1 = clientMassage.getAuthToken();
        String username2 = clientMassage.getSecondPlayerUsername();
        Account account1 = Account.getAccountByUsername(username1), account2 = Account.getAccountByUsername(username2);
        if(account1 == null || account2 == null)
            return new ServerMassage(ServerMassage.Type.Error, ServerMassage.ErrorType.InvalidAuthToken);
        if(account1.getState() != Account.State.Online || account2.getState() != Account.State.Online)
            return new ServerMassage(ServerMassage.Type.Error, ServerMassage.ErrorType.PlayerAreNotAvailable);
        account1.setState(Account.State.Busy);
        account2.setState(Account.State.Busy);
        account1.setCurrentMenu(MenuList.WaitingForOpponent);
        account2.setCurrentMenu(MenuList.AnswerToGame);
        account2.setMultiPlayerGameInfo(clientMassage);
        return new ServerMassage(ServerMassage.Type.Accept, null);
    }
}

