package Controller.Menus;

import Controller.Battle;
import Controller.Main;
import Model.AI;
import Model.Account;
import Model.CollectionItem.CollectionItem;
import Model.CollectionItem.Hero;
import Model.CollectionItem.Minion;
import Model.Deck;
import Model.Player;

public class BattleMenu extends Menu {
    private String inputLine;
    private String[] input;
    private Battle battle = new Battle();

    private void readInputs() {
        inputLine = Main.scanner.nextLine();
        inputLine = inputLine.trim();
        inputLine = inputLine.toLowerCase();
        input = inputLine.split("[ ]+");
    }

    @Override
    public void inputCommandLine() {
        if (!checkDeck(Main.application.getLoggedInAccount())) {
            System.out.println("selected deck is invalid");
            return;
        }

        battle.setPlayerOn(new Player(Main.application.getLoggedInAccount()));

        System.out.println("1. Single player");
        System.out.println("2. Multi Player");

        readInputs();

        if (inputLine.equals("single player")) {
            battle.setType("single player");
            inputCommandLineOfSinglePlayer();
            return;
        } else if (inputLine.equals("multi player")) {
            battle.setType("multi player");
            inputCommandLineOfMultiPlayer();
            return;
        } else if (inputLine.equals("exit"))
            return;

        System.out.println("Enter valid command !");
        inputCommandLine();
    }

    private boolean checkDeck(Account account) {
        if (account.getCollection().getMainDeck() == null) return false;
        return account.getCollection().getMainDeck().checkValidateDeck();
    }

    public void inputCommandLineOfSinglePlayer() {
        battle.setPlayerOff(new AI());

        while (true) {
            System.out.println("1. Story");
            System.out.println("2. Custom Game");

            readInputs();

            if (inputLine.equals("story")) {
                story();
                break;
            } else if (inputLine.equals("custom game")) {
                customGame("");
                break;
            } else if (inputLine.equals("exit"))
                return;
            System.out.println("Enter valid command !");
        }

        configurePlayer(battle.getPlayerOn());

        battle.runGame();
    }

    public void inputCommandLineOfMultiPlayer() {
        showAllAccount();

        while (true) {
            System.out.println("Enter username of second player :");
            readInputs();
            if (inputLine.matches("select user *.")) {
                String username = input[2];
                Account account = Account.getAccountByUsername(username);
                if (account != null) {
                    battle.setPlayerOff(new Player(account));
                    break;
                } else
                    System.out.println("Invalid username");
            } else if (inputLine.equals("exit"))
                return;
            else
                System.out.println("Enter valid command");
        }

        customGame("multiplayer");

        configurePlayer(battle.getPlayerOn());
        configurePlayer(battle.getPlayerOff());

        battle.runGame();
    }

    private void showAllAccount() {
        int index = 1;
        for (Account account : Account.getAccounts())
            System.out.println(index++ + ". " + account.getUsername());
    }


    public void story() {

        return;
    }

    public void customGame(String type) {

        if (!battle.getPlayerOff().getAccount().getCollection().getMainDeck().checkValidateDeck()) {
            System.out.println("selected deck for second player is invalid");
            return;
        }

        System.out.println("Game modes : ");
        System.out.println("1. Kill_enemy's_hero");
        System.out.println("2. Hold_flags");
        System.out.println("3. Talk_half_of_flags");

        while (true) {
            readInputs();

            if (inputLine.matches("start" + type + " game *.")) {
                if (!(input[3].equals("Kill_enemy's_hero") || input[3].equals("Hold_flags") ||
                        input[3].equals("Take_half_of_flags"))) {
                    System.out.println("Please enter valid mode");
                    continue;
                } else {
                    battle.setMode(input[3]);
                    return;
                }
            } else
                System.out.println("please enter valid command");
        }
    }

    public void configurePlayer(Player player) {
        System.out.println("All Decks of players is");
        int index = 0;
        for (Deck deck : player.getAccount().getCollection().getDecks()) {
            System.out.println(index++ + deck.getName());
        }

        while (true) {
            readInputs();
            for (Deck deck : player.getAccount().getCollection().getDecks())
                if (deck.getName().equals(inputLine)) {
                    player.getAccount().getCollection().setMainDeck(deck);
                    break;
                }
            System.out.println("Enter valid deck name");
        }
    }

    public void handleDeck(Account account) {
        account.getCollection().createDeck(account.getUsername());
        account.getCollection().selectMainDeck(account.getUsername());
        for (int i = 0; i < 20; i++) {
            Minion minion = new Minion();
            minion.setName(account.getUsername() + i);
            minion.setPrice(i * 10);
            minion.setID(Integer.toString(i));
            CollectionItem.getAllLivingCards().add(minion);
            account.getCollection().addCollectionItemToCollection(minion.getID());
            account.getCollection().addCollectionItemToDeck(minion.getID(), account.getUsername());
        }
        Hero hero = new Hero();
        hero.setName(account.getUsername());
        hero.setPrice(100000);
        hero.setID(account.getUsername());
        CollectionItem.getAllLivingCards().add(hero);
        account.getCollection().addCollectionItemToCollection(hero.getID());
        account.getCollection().addCollectionItemToDeck(hero.getID(), account.getUsername());
    }

}

