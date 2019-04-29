package Controller.Menus;

import Controller.Main;

public class MainMenu extends Menu {

    public void inputCommandLine() {
        String inputLine = Main.scanner.nextLine();
        inputLine = inputLine.trim();
        String[] input = inputLine.split("[ ]+");

        if(inputLine.equals("Enter Model.Collection")){
            CollectionMenu collectionMenu = new CollectionMenu();
            collectionMenu.inputCommandLine();
        }
        else if(inputLine.equals("Enter Controller.Shop")){
            Main.application.getShop().inputCommandLine();
        }
        else if(inputLine.equals("Enter Controller.Battle")){
            BattleMenu battleMenu = new BattleMenu();
            battleMenu.inputCommandLine();
        }
        else if(inputLine.equals("Exit")){
            return;
        }
        else if(inputLine.equals("Help"))
            MainMenu.showHelp();
        else
            System.out.println("Please enter valid command !!");
        this.inputCommandLine();
    }

    public static void showHelp() {
        System.out.println("1. Model.Collection");
        System.out.println("2. Controller.Shop");
        System.out.println("3. Controller.Battle");
        System.out.println("4. Exit");
        System.out.println("5. Help");
    }
}
