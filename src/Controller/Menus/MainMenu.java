//Done

package Controller.Menus;

import Controller.Application;
import Controller.Main;
import Model.Account;
import Model.CollectionItem.CollectionItem;
import Model.CollectionItem.LivingCard;
import Model.CollectionItem.Spell;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class MainMenu extends Menu {

    public void inputCommandLine() throws IOException {
        System.out.println("Here is Main menu");
        System.out.println("For help, enter : show menu");

        String inputLine = Main.scanner.nextLine();
        inputLine = inputLine.trim();
        String[] input = inputLine.split("[ ]+");

        if (inputLine.equals("enter collection")) {
            CollectionMenu collectionMenu = new CollectionMenu();
            collectionMenu.inputCommandLine();
        } else if (inputLine.equals("enter shop menu")) {
            ShopMenu shopMenu = new ShopMenu();
//            shopMenu.generate();
//            Main.application.getLoggedInAccount().setBudget(40);
            shopMenu.inputCommandLine();
        } else if (inputLine.equals("enter battle")) {
            BattleMenu battleMenu = new BattleMenu();
        //    battleMenu.handleDeck(Main.application.getLoggedInAccount());
            battleMenu.inputCommandLine();
        } else if (inputLine.equals("save"))
            this.save();
        else if (inputLine.equals("logout")) {
            this.logout();
            return;
        } else if (inputLine.equals("help") || inputLine.equals("show menu"))
            MainMenu.showMenu();
        else if (inputLine.equals("exit"))
            return;
        else
            System.out.println("Please enter valid command !!");
        this.inputCommandLine();
    }

    public static void showMenu() {
        System.out.println("1. Enter Collection");
        System.out.println("2. Enter Shop Menu");
        System.out.println("3. Enter Battle");
        System.out.println("4. save");
        System.out.println("5. logout");
        System.out.println("6. show menu");
        System.out.println("7. Exit");
    }

    private void save() throws IOException {
        //Save Accounts
        writeInFile("Account", Account.getAccounts());
        System.out.println("Account saving done :)");
        //Save Shop
        {
            String address = "Data/Memory/Shop/shop.json";
            Application.writeJSON(Main.application.getShop(), address);
        }
        System.out.println("Shop saving done :)");
        //Save All Spells
        writeInFile("Spell", CollectionItem.getAllSpells());
        System.out.println("Spell saving done :)");
        //Save All Items
        writeInFile("Item", CollectionItem.getAllItems());
        System.out.println("Item saving done:)");
        //Save All LivingCards
        writeInFile("LivingCard", CollectionItem.getAllLivingCards());
        System.out.println("LivingCard saving done :)");
    }

    private void writeInFile(String name, ArrayList arrayList) throws IOException {
        for(int i = 0; i < arrayList.size(); i ++){
            Object object = arrayList.get(i);
            String address = "Data/Memory/" + name + "s/" + name;
            Application.writeJSON(object, address + i + ".json");
        }
    }

    private void logout() throws IOException {
        Main.application.setLoggedInAccount(null);
        new AccountMenu().inputCommandLine();
        return;
    }
}
