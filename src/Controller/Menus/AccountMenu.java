//Done
//Json moonde

package Controller.Menus;

import Controller.Main;
import Model.*;

import java.io.FileNotFoundException;
import java.io.IOException;

public class AccountMenu extends Menu {

    @Override
    public void inputCommandLine() throws IOException {
        System.out.println("Here is Account Menu");
        System.out.println("For help, enter : show menu");

        String inputLine = Main.scanner.nextLine();
        inputLine = inputLine.trim();
        String[] input = inputLine.split("[ ]+");
        inputLine = inputLine.toLowerCase();

        if (inputLine.matches("create account .*")) {
            createAccount(input[2]);
            return;
        } else if (inputLine.matches("login .*")) {
            String username = input[1];
            login(username);
            return;
        } else if (inputLine.equals("show leaderboard")) {
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
        System.out.println("3. show leaderboard");
        System.out.println("4. show menu");
        System.out.println("5. exit");
    }

    private void createAccount(String username) throws IOException {
        if (Account.getAccountByUsername(username) != null) {
            System.out.println("this username is used");
            this.inputCommandLine();
            return;
        }

        System.out.println("this username is ok, please enter password !");
        String password = Main.scanner.nextLine();
        if(password.contains(" ")){
            System.out.println("password mustn't have space !!");
            this.inputCommandLine();
            return;
        }

        Account account = new Account(username, password);
        Account.getAccounts().add(account);
        account.setBudget(100000);
        Main.application.setLoggedInAccount(account);

        System.out.println("This account is created");

        new MainMenu().inputCommandLine();
    }

    private void login(String username) throws IOException {
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
