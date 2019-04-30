package Controller.Menus;

import Controller.Battle;
import Controller.Main;
import Model.AI;
import Model.Account;
import Model.Deck;
import Model.Player;

public class BattleMenu extends Menu {
    private String inputLine;
    private String[] input;
    private Battle battle = new Battle;

    private void readInputs(){
        inputLine = Main.scanner.nextLine();
        inputLine = inputLine.trim();
        input = inputLine.split("[ ]+");
    }

    @Override
    public void inputCommandLine() {
        if(!checkDeck(Main.application.getLoggedInAccount())){
            System.out.println("selected deck is invalid");
            return;
        }

        battle.setPlayerOn(new Player(Main.application.getLoggedInAccount()));

        System.out.println("1. Single player");
        System.out.println("2. Multi Player");

        readInputs();

        if(inputLine.equals("Single  player")){
            battle.setType("Single Player");
            inputCommandLineOfSinglePlayer();
            return;
        }
        else if(inputLine.equals("Multi player")){
            battle.setType("Multi Player");
            inputCommandLineOfMultiPlayer();
            return;
        }

        System.out.println("Enter valid command !");
        inputCommandLine();
    }

    private boolean checkDeck(Account account){
        return account.getCollection().getMainDeck().checkValidateDeck();
    }

    public void inputCommandLineOfSinglePlayer(){
        battle.setPlayerOff(new AI());

        while(true) {
            System.out.println("1. Story");
            System.out.println("2. Custom Game");

            String inputLine = Main.scanner.nextLine();
            inputLine = inputLine.trim();

            if (inputLine.equals("Story")) {
                story();
                break;
            } else if (inputLine.equals("Custom game")) {
                customGame("");
                break;
            }
            System.out.println("Enter valid command !");
        }

        configurePlayer(battle.getPlayerOn());

        battle.runGame();
    }

    public void inputCommandLineOfMultiPlayer(){
        showAllAccount();

        while(true) {
            System.out.println("Enter username of second player :");
            readInputs();
            if (inputLine.matches("Select user *.")) {
                String username = input[2];
                Account account = Account.getAccountByUsername(username);
                if (account != null) {
                    battle.setPlayerOff(new Player(account));
                    break;
                } else
                    System.out.println("Invalid username");
            } else
                System.out.println("Enter valid command");
        }

        customGame("multiplayer");

        configurePlayer(battle.getPlayerOn());
        configurePlayer(battle.getPlayerOff());

        battle.runGame();
    }

    private void showAllAccount(){
        int index = 1;
        for(Account account : Account.getAccounts())
            System.out.println(index++ + ". " + account.getUsername());
    }


    public void story(){

        return;
    }

    public void customGame(String type){
        if(!battle.getPlayerOff().getAccount().getCollection().getMainDeck().checkValidateDeck()){
            System.out.println("invalid deck for second player");
            return;
        }
        System.out.println("Game modes : ");
        System.out.println("1. Kill enemy's hero");
        System.out.println("2. Hold flags");
        System.out.println("3. Talk half of flags");

        readInputs();

        if(!inputLine.matches("start game ]")){
            System.out.println();
        }
    }

    public void configurePlayer(Player player){
        System.out.println("All Decks of players is");
        int index = 0;
        for(Deck deck : player.getAccount().getCollection().getDecks()){
            System.out.println(index++ + deck.getName());
        }

        while(true) {
            readInputs();
            for(Deck deck : player.getAccount().getCollection().getDecks())
                if(deck.getName().equals(inputLine)){
                    player.getAccount().getCollection().setMainDeck(deck);
                    break;
                }
            System.out.println("Enter valid deck name");
        }
    }

}

