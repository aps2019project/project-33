package Controller.Menus;


import Controller.Main;
import Controller.Menus.Menu;
import Model.Player;

public class BattleMenu extends Menu {

    @Override
    public void inputCommandLine() {
        if(!Main.application.getLoggedInAccount().getCollection().getMainDeck().checkValidateDeck()){
            System.out.println("selected deck is invalid");
            return;
        }

        System.out.println("1. Single player");
        System.out.println("2. Multi Player");

        String inputLine = Main.scanner.nextLine();
        inputLine = inputLine.trim();
        if(inputLine.equals("Single  player")){
            inputCommandLineOfSinglePlayer();
            return;
        }
        else if(inputLine.equals("Multi player")){
            inputCommandLineOfMultiPlayer();
            return;
        }
        System.out.println("Enter valid command !");
        inputCommandLine();
    }

    public void inputCommandLineOfSinglePlayer(){
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
        System.out.println("Game modes :");
        System.out.println("1. Kill enemy's hero");
        System.out.println("2. Hold flags");
        System.out.println("3. Talk half of flags");

        String inputLine = Main.scanner.nextLine();
        inputLine = inputLine.trim();

        //TODO
    }

    public void customGame(){
        System.out.println("Game modes : ");
        System.out.println("1. Kill enemy's hero");
        System.out.println("2. Hold flags");
        System.out.println("3. Talk half of flags");


    }
}
