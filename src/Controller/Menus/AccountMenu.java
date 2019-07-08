//done

package Controller.Menus;

import Controller.Client.Client;
import Controller.Client.ClientMassage;
import Controller.Server.ServerMain;
import Controller.MenuList;
import Controller.Server.ServerMassage;
import Model.*;

public class AccountMenu extends Menu {

    public ServerMassage inputCommandLine(String inputLine) {
        inputLine = inputLine.trim();
        String[] input = inputLine.split("[ ]+");
        inputLine = inputLine.toLowerCase();

        System.out.println(inputLine);

        if (inputLine.matches("create account [^\\s]+ [^\\s]+")) {
            return createAccount(input[2], input[3]);
        } else if (inputLine.matches("login [^\\s]+ [^\\s]+")) {
            return login(input[1], input[2]);
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
        System.out.println("3. show menu");
        System.out.println("4. exit");
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

        System.out.println("The account is created");

        account.setState(Account.State.Online);
        account.setCurrentMenu(MenuList.MainMenu);
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

        account.setState(Account.State.Online);
        account.setCurrentMenu(MenuList.MainMenu);
        return new ServerMassage(ServerMassage.Type.Accept, null);
    }

    public ServerMassage interpret(ClientMassage clientMassage) {
        ServerMassage answer;
        if(clientMassage.getAccountMenuRequest() == ClientMassage.AccountMenuRequest.LogIn)
            answer = this.inputCommandLine("login " + clientMassage.getUsername() + " " + clientMassage.getPassword());
        else
            answer = this.inputCommandLine("create account " + clientMassage.getUsername() + " " + clientMassage.getPassword());
        return answer;
    }
}
