package Controller.Menus;

import Controller.*;
import Model.*;
import Model.CollectionItem.*;

import java.io.FileNotFoundException;

public class BattleMenu extends Menu {
    private String inputLine;
    private String[] input;
    private Battle battle = new Battle();
    private String[] modes = {"Kill_enemy's_hero", "Hold_flags", "Take_half_of_flags"};
    private String[] types = {"Single Player", "Multi Player"};
    private String[] singlePlayerKinds = {"Story", "Custom Game"};
    private String[] levels = {"1. fight with DiveSeipd", "2. fight with Zahhak",
            "3. fight with Arash"};
    private int[] prizeOfLevels = {500, 1000, 1500};
    private int numberOfDecksInCustomGame = 3;
    private int prizeOfCustomGame = 1000;

    private void readInputs() {
        inputLine = Main.scanner.nextLine();
        inputLine = inputLine.trim();
        inputLine = inputLine.toLowerCase();
        input = inputLine.split("[ ]+");
    }

    @Override
    public void inputCommandLine() throws FileNotFoundException {
        if (!checkDeck(Main.application.getLoggedInAccount())) {
            System.out.println("selected deck is invalid");
            return;
        }
        battle.setPlayerOn(new Player(Main.application.getLoggedInAccount()));
        chooseType();
        chooseSecondPlayer();
        chooseMode();
        battle.runGame();
    }

    private void chooseType() {
        for (int i = 0; i < types.length; i++)
            System.out.println(i + ". " + types[i]);
        readInputs();
        for (int i = 0; i < types.length; i++) {
            if (inputLine.equals(types[i].toLowerCase())) {
                battle.setType(types[i]);
                return;
            }
        }
        System.out.println("Please enter valid type");
        chooseType();
    }

    private void chooseSecondPlayer() {
        System.out.println(battle.getType());
        if (battle.getType().equals(types[0])) {
            battle.setPlayerOff(new AI());
            return;
        }

        showAllAccount();
        while (true) {
            System.out.println("Enter username of second player :");
            readInputs();
            if (inputLine.matches("select user .*")) {
                String username = input[2];
                Account account = Account.getAccountByUsername(username);
                if (account != null) {
                    if (!checkDeck(account)) {
                        System.out.println("Selected deck for second player is invalid");
                        continue;
                    }
                    battle.setPlayerOff(new Player(account));
                    break;
                } else
                    System.out.println("Invalid username");
            } else if (inputLine.equals("exit"))
                return;
            else
                System.out.println("Enter valid command");
        }
    }

    private void chooseMode() throws FileNotFoundException {
        if (battle.getType().equals(types[0])) {
            for (int i = 0; i < singlePlayerKinds.length; i++)
                System.out.println(singlePlayerKinds[i]);
            readInputs();
            for (int i = 0; i < singlePlayerKinds.length; i++) {
                if (inputLine.equals(singlePlayerKinds[i].toLowerCase())) {
                    if (i == 0) story();
                    else customGame();
                    return;
                }
            }
            System.out.println("enter valid Kind !!");
        } else {
            customGame();
            return;
        }
        chooseMode();
    }

    private void customGame() throws FileNotFoundException {
        battle.setPrize(prizeOfCustomGame);
        System.out.println("Modes :");
        for (int i = 0; i < modes.length; i++)
            System.out.println(modes[i]);

        if (battle.getType().equals(types[0])) {

            String address = "Data/Custom/Custom";
            System.out.println("Decks are: ");
            for (int i = 0; i < numberOfDecksInCustomGame; i++) {
                Deck deck = (Deck) Application.readJSON(Deck.class, address + i + ".json");
                System.out.print(i + 1 + ". ");
                deck.showDeck(true);
            }
            readInputs();
            if (inputLine.matches("start game [^\\s]+ [^\\s]+( [\\d]+)*")) {
                String deckName = input[2], mode = input[3];
                int numberOfFlags = 0;
                if (mode.equals(modes[2])) {
                    if (input.length == 5) {
                        numberOfFlags = Integer.parseInt(input[4]);
                    } else {
                        System.out.println("Enter valid command");
                        customGame();
                        return;
                    }
                }
                boolean isValidDeck = false;
                for (int i = 0; i < numberOfDecksInCustomGame; i++) {
                    Deck deck = (Deck) Application.readJSON(Deck.class, address + i + ".json");
                    if (deck.getName().equals(deckName)) {
                        ((AI) battle.getPlayerOff()).selectMainDeck(deck);
                        isValidDeck = true;
                    }
                }
                if (!isValidDeck) {
                    System.out.println("Choose valid deck");
                    customGame();
                    return;
                }
                if (setMode(mode, numberOfFlags))
                    return;
            }
        } else {
            readInputs();
            if (inputLine.matches("start multiplayer game [^\\s]+( [\\d]+)*")) {
                String mode = input[3];
                int numberOfFlags = 0;
                if (mode.equals(modes[2].toLowerCase())) {
                    if (input.length == 5) {
                        numberOfFlags = Integer.parseInt(input[4]);
                    } else {
                        System.out.println("Enter Valid Command");
                        return;
                    }
                }
                if (setMode(mode, numberOfFlags))
                    return;
            }
        }
        System.out.println("please enter valid command ! something went wrong");
        customGame();
    }

    private boolean setMode(String mode, int numberOfFlags) {
        for (int i = 0; i < modes.length; i++) {
            if (mode.equals(modes[i].toLowerCase())) {
                battle.setMode(modes[i]);
                if (i == 2)
                    battle.setNumberOfFlags(numberOfFlags);
                return true;
            }
        }
        return false;
    }

    private boolean checkDeck(Account account) {
        if (account.getCollection().getMainDeck() == null) return false;
        return account.getCollection().getMainDeck().checkValidateDeck();
    }

    private void showAllAccount() {
        int index = 1;
        for (Account account : Account.getAccounts()) {
            System.out.println(index++ + ". " + account.getUsername());

        }
    }


    public void story() throws FileNotFoundException {
        System.out.println("Levels :");
        for (int i = 0; i < levels.length; i++)
            System.out.println(levels[i] + ", Prize : " + prizeOfLevels[i]);
        readInputs();
        for (int i = 0; i < levels.length; i++)
            if (inputLine.equals(levels[i].toLowerCase())) {
                String address = "Data/Battle/Story/Story";
                Deck deck = (Deck) Application.readJSON(Deck.class, address + i + ".json");
                ((AI) battle.getPlayerOff()).selectMainDeck(deck);
                battle.setMode(modes[i]);
                battle.setPrize(prizeOfLevels[i]);
            }
        return;
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

