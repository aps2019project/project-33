//Done
//Json moonde

package Controller.Menus;

import Controller.Main;
import Model.*;

import java.io.FileNotFoundException;
import java.io.IOException;

public class AccountMenu extends Menu {

    //tahesh in ja bayad har koodoom az bakhsh ha beshe ye tabe


    @Override
    public void inputCommandLine() throws FileNotFoundException {
        System.out.println("Here is Account Menu");

        String inputLine = Main.scanner.nextLine();
        inputLine = inputLine.trim();
        inputLine = inputLine.toLowerCase();
        String[] input = inputLine.split("[ ]+");

        if (inputLine.matches("create account .*")) {
            createAccount(input[2]);
            return;
        } else if (inputLine.matches("login .*")) {
            String username = input[1];
            login(username);
            return;
        } else if (inputLine.equals("show leader board")) {
            Account.showLeaderBoard();
        } else if (inputLine.equals("show menu")) {
            AccountMenu.showMenu();
        } else if(inputLine.equals("exit"))
            return;
        else
            System.out.println("Enter valid command");
        this.inputCommandLine();
    }

    public static void showMenu() {
        System.out.println("1. create account [username]");
        System.out.println("2. login [username]");
        System.out.println("3. show leader board");
        System.out.println("4. show menu");
        System.out.println("5. exit");
    }

    private void createAccount(String username) throws IOException {
        if (Account.getAccountByUsername(username) != null) {
            System.out.println("this username is used");
            this.inputCommandLine();
            return;
        }

        System.out.println("this username is ok, please enter username !");
        String password = Main.scanner.nextLine();
        if(password.contains(" ")){
            System.out.println("password mustn't have space !!");
            this.inputCommandLine();
            return;
        }

        Account account = new Account(username, password);
        Account.getAccounts().add(account);
        Main.application.setLoggedInAccount(account);

        System.out.println("This account is created");

        new MainMenu().inputCommandLine();
    }

    private void login(String username) throws FileNotFoundException {
        Account account = Account.getAccountByUsername(username);
        if (account == null) {
            System.out.println("Invalid Username !!");
            this.inputCommandLine();
            return;
        }

        System.out.println("Enter password");

        String password = Main.scanner.nextLine();
        if (!account.getPassword().equals(password)) {
            System.out.println("Invalid password !!");
            this.inputCommandLine();
            return;
        }

        System.out.println("login complete !");
        Main.application.setLoggedInAccount(account);
        new MainMenu().inputCommandLine();

        return;
    }
}
