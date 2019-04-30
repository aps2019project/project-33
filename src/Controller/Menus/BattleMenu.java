package Controller.Menus;


import Controller.Battle;
import Controller.Main;
import Model.AI;
import Model.Account;
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
        if(!Main.application.getLoggedInAccount().getCollection().getMainDeck().checkValidateDeck()){
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

    public void inputCommandLineOfMultiPlayer(){
        System.out.println("Enter username of second player");
        readInputs();
        if(inputLine.matches("Select user *.")){
            String username = input[2];
            Account account = Account.getAccountByUsername(username);
            if(account != null){
                battle.setPlayerOff(new Player(account));
                customGame();
                return;
            }
            else
                System.out.println("Invalid username");
        }else
            System.out.println("Enter valid command");
        inputCommandLineOfMultiPlayer();
    }

    public void inputCommandLineOfSinglePlayer(){
        battle.setPlayerOff(new AI());

        System.out.println("1. Story");
        System.out.println("2. Custom Game");

        String inputLine = Main.scanner.nextLine();
        inputLine = inputLine.trim();

        if(inputLine.equals("Story")){
            story();
        }else if(inputLine.equals("Custom game")){
            customGame();
        }
        System.out.println("Enter valid command !");
        inputCommandLineOfSinglePlayer();
    }

    public void story(){

        return;
    }

    public void customGame(){
        if(!battle.getPlayerOff().getAccount().getCollection().getMainDeck().checkValidateDeck()){
            System.out.println("invalid deck for second player");
            return;
        }
        System.out.println("Game modes : ");
        System.out.println("1. Kill enemy's hero");
        System.out.println("2. Hold flags");
        System.out.println("3. Talk half of flags");

        String inputLine = Main.scanner.nextLine();
        inputLine = inputLine.trim();
        String[] input = inputLine.split("[ ]+");

        if(!inputLine.matches("[1|2|3]")){
            System.out.println();
        }
    }
}
