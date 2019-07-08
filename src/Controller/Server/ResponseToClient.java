package Controller.Server;

import Controller.Application;
import Controller.Battle;
import Controller.Client.Client;
import Controller.Client.ClientMassage;
import Controller.MenuList;
import Controller.Menus.*;
import Model.Account;
import Model.Massage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class ResponseToClient extends Thread {
    private AccountMenu accountMenu = new AccountMenu();
    private MainMenu mainMenu = new MainMenu();
    private BattleMenu battleMenu = new BattleMenu();
    private CollectionMenu collectionMenu = new CollectionMenu();
    private ShopMenu shopMenu = new ShopMenu();

    private Socket socket;
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;

    public ResponseToClient(Socket socket) throws IOException {
        this.socket = socket;
        this.objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        this.objectInputStream = new ObjectInputStream(socket.getInputStream());
    }

    //todo in che mozakhrafi bood ? age aval output ro doros konam bad input ok mishe, vali bar aks na :| :| :| :| :|

    @Override
    public void run() {
        while (true) {
            ClientMassage clientMassage = null;
            try {
                clientMassage = (ClientMassage) objectInputStream.readUnshared();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            ServerMassage answer = null;
            try {
                answer = interpret(clientMassage);
                objectOutputStream.writeUnshared(answer);
                objectOutputStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private ServerMassage interpret(ClientMassage clientMassage) throws IOException {
        if(clientMassage.getDestinationMenu() == ClientMassage.Menu.ShopMenu)
            return shopMenu.interpret(clientMassage);
        if(clientMassage.getDestinationMenu() == ClientMassage.Menu.CollectionMenu)
            return collectionMenu.interpret(clientMassage);
        if (clientMassage.getDestinationMenu() == ClientMassage.Menu.Server)
            return this.answer(clientMassage);
        if (clientMassage.getDestinationMenu() == ClientMassage.Menu.AccountMenu)
            return accountMenu.interpret(clientMassage);
        if (clientMassage.getDestinationMenu() == ClientMassage.Menu.MainMenu)
            return mainMenu.interpret(clientMassage);
        if (clientMassage.getDestinationMenu() == ClientMassage.Menu.BattleMenu)
            return battleMenu.interpret(clientMassage);
        if (clientMassage.getDestinationMenu() == ClientMassage.Menu.Battle) {
            Battle battle = Account.getAccountByUsername(clientMassage.getAuthToken()).getRunningBattle();
            return battle.interpret(clientMassage);
        }
        return null;
    }

    private ServerMassage answer(ClientMassage clientMassage) throws IOException {
        if (clientMassage.getServerRequest() == ClientMassage.ServerRequest.GiveCurrentMenu) {
            ServerMassage serverMassage = new ServerMassage(ServerMassage.Type.Accept, null);
            //todo age in ja auth token moshkel dashte bashe null pointer exception mikhore
            if (clientMassage.getAuthToken() == null) serverMassage.setCurrentMenu(MenuList.AccountMenu);
            else
                serverMassage.setCurrentMenu(Account.getAccountByUsername(clientMassage.getAuthToken()).getCurrentMenu());
            return serverMassage;
        }
        if (clientMassage.getServerRequest() == ClientMassage.ServerRequest.ChangeCurrentMenu) {
            String authToken = clientMassage.getAuthToken();
            Account account = Account.getAccountByUsername(authToken);
            if (account == null)
                return new ServerMassage(ServerMassage.Type.Error, ServerMassage.ErrorType.InvalidAuthToken);
            account.setCurrentMenu(clientMassage.getNewMenu());
            return new ServerMassage(ServerMassage.Type.Accept, null);
        }
        if (clientMassage.getServerRequest() == ClientMassage.ServerRequest.GiveRunningGame) {
            String authToken = clientMassage.getAuthToken();
            Account account = Account.getAccountByUsername(authToken);
            if (account == null)
                return new ServerMassage(ServerMassage.Type.Error, ServerMassage.ErrorType.InvalidAuthToken);
            if (account.getRunningBattle() == null)
                return new ServerMassage(ServerMassage.Type.Error, ServerMassage.ErrorType.RunningBattleNotFound);
            Battle battle = account.getRunningBattle();
            synchronized (battle) {
                Battle newBattle = (Battle) Application.copy(battle, Battle.class);
                ServerMassage answer = new ServerMassage(ServerMassage.Type.Accept, null);
                answer.setRunningBattle(newBattle);
                return answer;
            }
        }
        if (clientMassage.getServerRequest() == ClientMassage.ServerRequest.GiveAccounts) {
            synchronized (Account.getAccounts()) {
                ArrayList<Account> accounts = new ArrayList<>();
                for (Account account : Account.getAccounts())
                    accounts.add((Account) Application.copy(account, Account.class));
                Account.sortArraysOfAccount(accounts);
                ServerMassage serverMassage = new ServerMassage(ServerMassage.Type.Accept, null);
                serverMassage.setAccounts(accounts);
                return serverMassage;
            }
        }
        if (clientMassage.getServerRequest() == ClientMassage.ServerRequest.SendMassageInChat) {
            synchronized (Massage.getMassages()) {
                String massageText = clientMassage.getMassage();
                Account account = Account.getAccountByUsername(clientMassage.getAuthToken());
                if (account == null)
                    return new ServerMassage(ServerMassage.Type.Error, ServerMassage.ErrorType.InvalidAuthToken);
                Massage.addMassage(new Massage(massageText, account));
                return new ServerMassage(ServerMassage.Type.Accept, null);
            }
        }
        if (clientMassage.getServerRequest() == ClientMassage.ServerRequest.GiveAllMassages) {
            synchronized (Massage.getMassages()) {
                ArrayList<Massage> massages = new ArrayList<>();
                for (Massage massage : Massage.getMassages())
                    massages.add(massage);
                ServerMassage serverMassage = new ServerMassage(ServerMassage.Type.Accept, null);
                serverMassage.setMassages(massages);
                return serverMassage;
            }
        }
        return null;
    }
}
