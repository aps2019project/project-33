//Done
//Json moonde

package Controller.Menus;

import Controller.Main;
import Model.*;

public class AccountMenu extends Menu {

    //tahesh in ja bayad har koodoom az bakhsh ha beshe ye tabe

    private Account createdAccount;

    @Override
    public void inputCommandLine() {
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
        } else if (inputLine.equals("help")) {
            AccountMenu.showHelp();
        } else if(inputLine.equals("exit"))
            return;
        else
            System.out.println("Enter valid command");
        this.inputCommandLine();
    }

    public static void showHelp() {
        System.out.println("1. create account [username]");
        System.out.println("2. login [username]");
        System.out.println("3. show leader board");
        System.out.println("4. save");
        System.out.println("5. logout");
        System.out.println("6. help");
        System.out.println("7. exit");
    }

    private void createAccount(String username) {
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

        this.createdAccount = new Account(username, password);

        System.out.println("This account is created");

        new MainMenu().inputCommandLine();
    }

    private void login(String username) {
        Account account = Account.getAccountByUsername(username);
        if (account == null) {
            System.out.println("Invalid Username !!");
            return;
        }

        System.out.println("Enter password");

        String password = Main.scanner.nextLine();
        if (!account.getPassword().equals(password)) {
            System.out.println("Invalid password !!");
            return;
        }

        System.out.println("login complete !");
        Main.application.setLoggedInAccount(account);
        return;
    }

    private void save(){
        if(createdAccount == null){
            System.out.println("Please create an account");
            return;
        }

        Account.getAccounts().add(createdAccount);
        Main.application.setLoggedInAccount(createdAccount);
        //TODO
        //in ja bayad tooye file rikhte she
    }
}
