//Done
//Json moonde

package Controller.Menus;

import Controller.Main;
import Model.*;

import java.io.FileNotFoundException;
import java.io.IOException;

public class AccountMenu extends Menu {
    @Override
    public void inputCommandLine(String inputLine) {
        inputLine = inputLine.trim();
        String[] input = inputLine.split("[ ]+");
        inputLine = inputLine.toLowerCase();

        if (inputLine.matches("create account [^\\s]+ [^\\s]+")) {
            createAccount(input[2], input[3]);
            return;
        } else if (inputLine.matches("login [^\\s]+ [^\\s]+")) {
            login(input[1], input[2]);
            return;
        } else if (inputLine.equals("show leaderboard")) {
            Account.showLeaderBoard();
        } else if (inputLine.equals("show menu")) {
            AccountMenu.showMenu();
        } else if(inputLine.equals("exit"))
            return;
        else
            System.out.println("Enter valid command");
    }

    public static void showMenu() {
        System.out.println("1. create account [username]");
        System.out.println("2. login [username]");
        System.out.println("3. show leaderboard");
        System.out.println("4. show menu");
        System.out.println("5. exit");
    }

    private void createAccount(String username, String password) {
        if (Account.getAccountByUsername(username) != null) {
            System.out.println("this username is used");
            return;
        }

        if(password.contains(" ")){
            System.out.println("password mustn't have space !!");
            return;
        }

        Account account = new Account(username, password, 100000);
        Account.getAccounts().add(account);
        Main.application.setLoggedInAccount(account);

        System.out.println("The account is created");
    }

    private void login(String username, String password) {
        Account account = Account.getAccountByUsername(username);
        if (account == null) {
            System.out.println("Invalid Username !!");
            return;
        }

        if (!account.getPassword().equals(password)) {
            System.out.println("Invalid password !!");
            return;
        }

        System.out.println("login complete !");
        Main.application.setLoggedInAccount(account);
        return;
    }

}
