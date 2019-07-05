//Done

package Controller.Menus;

import Controller.Application;
import Controller.Client.ClientMassage;
import Controller.MenuList;
import Controller.Server.Server;
import Controller.Server.ServerMain;
import Controller.Server.ServerMassage;
import Model.Account;
import Model.CollectionItem.CollectionItem;

import java.io.IOException;
import java.util.ArrayList;

public class MainMenu extends Menu {

    public ServerMassage inputCommandLine(String inputLine, String authToken) throws IOException {
        System.out.println("Here is ServerMain menu");
        System.out.println("For help, enter : show menu");

        inputLine = inputLine.trim();
        inputLine = inputLine.toLowerCase();
        String[] input = inputLine.split("[ ]+");

        Account account = Account.getAccountByUsername(authToken);

        switch (inputLine) {
            case "enter collection":
                account.setCurrentMenu(MenuList.CollectionMenu);
                break;
            case "enter shop menu":
                account.setCurrentMenu(MenuList.ShopMenu);
                break;
            case "enter battle":
                account.setCurrentMenu(MenuList.ChooseType);
                BattleMenu.handleDeck(account);
                break;
            case "save":
                save();
                break;
            case "logout":
                return logout(account);
            case "help":
            case "show menu":
                MainMenu.showMenu();
                break;
            default:
                System.out.println("Please enter valid command !!");
                break;
        }
        ServerMassage serverMassage = new ServerMassage(ServerMassage.Type.Accept, null);
        return serverMassage;
    }

    public static void showMenu() {
        System.out.println("1. Enter Collection");
        System.out.println("2. Enter ShopMenu Menu");
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
        //Save ShopMenu
        {
            String address = "Data/Memory/ShopMenu/shop.json";
            Application.writeJSON(ServerMain.application.getShop(), address);
        }
        System.out.println("ShopMenu saving done :)");
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
        for (int i = 0; i < arrayList.size(); i++) {
            Object object = arrayList.get(i);
            String address = "Data/Memory/" + name + "s/" + name;
            Application.writeJSON(object, address + i + ".json");
        }
    }

    private ServerMassage logout(Account account) throws IOException {
        account.setCurrentMenu(MenuList.AccountMenu);
        account.setState(Account.State.Offline);
        ServerMassage serverMassage = new ServerMassage(ServerMassage.Type.Accept, null);
        serverMassage.setCommand(ServerMassage.Command.ClearAuthToken);
        return serverMassage;
    }

    public ServerMassage interpret(ClientMassage clientMassage) throws IOException {
        String authToken = clientMassage.getAuthToken();
        if (clientMassage.getMainMenuRequest() == ClientMassage.MainMenuRequest.EnterBattleMenu)
            return inputCommandLine("enter battle", authToken);
        if (clientMassage.getMainMenuRequest() == ClientMassage.MainMenuRequest.EnterCollectionMenu)
            return inputCommandLine("enter collection", authToken);
        if (clientMassage.getMainMenuRequest() == ClientMassage.MainMenuRequest.EnterShopMenu)
            return inputCommandLine("enter shop menu", authToken);
        if (clientMassage.getMainMenuRequest() == ClientMassage.MainMenuRequest.Save)
            return inputCommandLine("save", authToken);
        if (clientMassage.getMainMenuRequest() == ClientMassage.MainMenuRequest.LogOut)
            return inputCommandLine("logout", authToken);
        return null;
    }
}
