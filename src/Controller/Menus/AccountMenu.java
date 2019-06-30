//Done
//Json moonde

package Controller.Menus;

import Controller.Client;
import Controller.Main;
import Controller.MenuList;
import Model.*;

public class AccountMenu extends Menu {

    public ServerMassage inputCommandLine(String inputLine) {
        inputLine = inputLine.trim();
        String[] input = inputLine.split("[ ]+");
        inputLine = inputLine.toLowerCase();

        if (inputLine.matches("create account [^\\s]+ [^\\s]+")) {
            return createAccount(input[2], input[3]);
        } else if (inputLine.matches("login [^\\s]+ [^\\s]+")) {
            return login(input[1], input[2]);
        } else if (inputLine.equals("show leaderboard")) {
            Account.showLeaderBoard();
            //todo in kollan divert shode
            return null;
        } else if (inputLine.equals("show menu")) {
            AccountMenu.showMenu();
            return  null;
        } else if(inputLine.equals("exit"))
            return null;
        return null;
    }

    public static void showMenu() {
        System.out.println("1. create account [username]");
        System.out.println("2. login [username]");
        System.out.println("3. show leaderboard");
        System.out.println("4. show menu");
        System.out.println("5. exit");
    }

    private ServerMassage createAccount(String username, String password) {
        if (Account.getAccountByUsername(username) != null) {
            System.out.println("this username is used");
            return new ServerMassage(ServerMassage.Type.Error, ServerMassage.ErrorType.InvalidUsernameForSignUp);
        }

        if(password.contains(" ")){
            System.out.println("password mustn't have space !!");
            return new ServerMassage(ServerMassage.Type.Error, ServerMassage.ErrorType.InvalidPasswordForSignUp);
        }

        Account account = new Account(username, password, 100000);
        Account.getAccounts().add(account);
        Main.application.setLoggedInAccount(account);

        System.out.println("The account is created");
        Client.getClient().setCurrentMenu(MenuList.MainMenu);
        return new ServerMassage(ServerMassage.Type.Accept, null);
    }

    private ServerMassage login(String username, String password) {
        Account account = Account.getAccountByUsername(username);
        if (account == null) {
            System.out.println("Invalid Username !!");
            return new ServerMassage(ServerMassage.Type.Error, ServerMassage.ErrorType.LogInFailed);
        }

        if (!account.getPassword().equals(password)) {
            System.out.println("Invalid password !!");
            return new ServerMassage(ServerMassage.Type.Error, ServerMassage.ErrorType.LogInFailed);
        }

        System.out.println("login complete !");
        Main.application.setLoggedInAccount(account);
        Client.getClient().setCurrentMenu(MenuList.MainMenu);
        return new ServerMassage(ServerMassage.Type.Accept, null);
    }
}
