//Done

package Controller.Menus;

import Controller.Main;

public class MainMenu extends Menu {

    public void inputCommandLine() {
        System.out.println("Here is Main menu");

        String inputLine = Main.scanner.nextLine();
        inputLine = inputLine.trim();
        inputLine = inputLine.toLowerCase();
        String[] input = inputLine.split("[ ]+");

        if(inputLine.equals("enter collection")){
            CollectionMenu collectionMenu = new CollectionMenu();
            collectionMenu.inputCommandLine();
        }
        else if(inputLine.equals("enter shop menu")){
            ShopMenu shopMenu = new ShopMenu();
            shopMenu.inputCommandLine();
        }
        else if(inputLine.equals("enter battle")){
            BattleMenu battleMenu = new BattleMenu();
            battleMenu.inputCommandLine();
        }
        else if(inputLine.equals("enter account menu")){
            AccountMenu accountMenu = new AccountMenu();
            accountMenu.inputCommandLine();
        }
        else if(inputLine.equals("exit")){
            return;
        }
        else if(inputLine.equals("help"))
            MainMenu.showHelp();
        else if(inputLine.equals("save"))
            this.save();
        else if (inputLine.equals("logout"))
            this.logout();
        else
            System.out.println("Please enter valid command !!");
        this.inputCommandLine();
    }

    public static void showHelp() {
        System.out.println("1. Collection");
        System.out.println("2. Shop Menu");
        System.out.println("3. Battle");
        System.out.println("4. Account Menu");
        System.out.println("5. Exit");
        System.out.println("6. Help");
    }

    private void save(){

    }

    private void logout() {
        Main.application.setLoggedInAccount(null);
        new  AccountMenu().inputCommandLine();
        return;
    }
}
