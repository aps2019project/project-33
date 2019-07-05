package Controller.Client;

import Controller.MenuList;
import Controller.Menus.*;
import Controller.Server.ServerMassage;
import Model.CollectionItem.CollectionItem;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

//todo in auth token ha irad dashtan
public class Client {
    private CollectionMenu collectionMenu = new CollectionMenu();
    private ShopMenu shopMenu = new ShopMenu();

    //network
    private int port = 8000;
    private String ip = "127.0.0.1";
    private Socket socket;
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;

    //logical details
    private static Client client = null;
    private String authToken;

    private ArrayList<CollectionItem> resultOfSearch = new ArrayList<>();
    //todo in bayad ye jaei meghdar dehi she

    private Client() throws IOException {
        socket = new Socket("127.0.0.1", 8000);
        objectInputStream = new ObjectInputStream(socket.getInputStream());
        objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
    }

    public static void createClient() throws IOException {
        if (client != null) return;
        client = new Client();
    }

    private ServerMassage sendAndReceive(ClientMassage clientMassage) throws IOException, ClassNotFoundException {
        objectOutputStream.writeUnshared(clientMassage);
        objectOutputStream.flush();
        return (ServerMassage) objectInputStream.readUnshared();
    }

    //AccountMenu Commands

    public synchronized ServerMassage accountMenuCommand(String username, String password, ClientMassage.AccountMenuRequest accountMenuRequest) throws IOException, ClassNotFoundException {
        ClientMassage clientMassage = new ClientMassage();
        clientMassage.setDestinationMenu(ClientMassage.Menu.AccountMenu);
        clientMassage.setAccountMenuRequest(accountMenuRequest);
        clientMassage.setUsername(username);
        clientMassage.setPassword(password);
        return sendAndReceive(clientMassage);
    }

    //MainMenu Commands

    public synchronized ServerMassage mainMenuCommand(ClientMassage.MainMenuRequest mainMenuRequest) throws IOException, ClassNotFoundException {
        ClientMassage clientMassage = new ClientMassage();
        clientMassage.setAuthToken(this.authToken);
        clientMassage.setDestinationMenu(ClientMassage.Menu.MainMenu);
        clientMassage.setMainMenuRequest(mainMenuRequest);
        return sendAndReceive(clientMassage);
    }

    //BattleMenu Commands

    public synchronized ServerMassage createGame(String secondPlayerUsername, String type, String mode, String chapter, String kind) throws IOException, ClassNotFoundException {
        ClientMassage clientMassage = new ClientMassage();
        clientMassage.setAuthToken(this.authToken);
        clientMassage.setDestinationMenu(ClientMassage.Menu.BattleMenu);
        clientMassage.setSecondPlayerUsername(secondPlayerUsername);
        clientMassage.setType(type);
        clientMassage.setMode(mode);
        clientMassage.setChapter(chapter);
        clientMassage.setKind(kind);
        return sendAndReceive(clientMassage);
    }

    //Battle Commands

    public synchronized ServerMassage battleCommand(ClientMassage.BattleRequest battleRequest) throws IOException, ClassNotFoundException {
        ClientMassage clientMassage = new ClientMassage();
        clientMassage.setAuthToken(this.authToken);
        clientMassage.setDestinationMenu(ClientMassage.Menu.Battle);
        clientMassage.setBattleRequest(battleRequest);
        return sendAndReceive(clientMassage);
    }

    public synchronized ServerMassage selectCardInBattle(String collectionItemID) throws IOException, ClassNotFoundException {
        return doSomeThingWithID(collectionItemID, ClientMassage.BattleRequest.Select);
    }

    public synchronized ServerMassage insertCardInBattle(String collectionItemID, int x, int y) throws IOException, ClassNotFoundException {
        ClientMassage clientMassage = new ClientMassage();
        clientMassage.setAuthToken(this.authToken);
        clientMassage.setDestinationMenu(ClientMassage.Menu.Battle);
        clientMassage.setBattleRequest(ClientMassage.BattleRequest.InsertCard);
        clientMassage.setCollectionItemID(collectionItemID);
        clientMassage.setX(x);
        clientMassage.setY(y);
        return sendAndReceive(clientMassage);
    }

    public synchronized ServerMassage useItemInBattle(int x, int y) throws IOException, ClassNotFoundException {
        return doSomeThingWithCoordination(x, y, ClientMassage.BattleRequest.UseItem);
    }

    public synchronized ServerMassage moveCardInBattle(int x, int y) throws IOException, ClassNotFoundException {
        return doSomeThingWithCoordination(x, y, ClientMassage.BattleRequest.MoveCard);
    }

    private synchronized ServerMassage doSomeThingWithCoordination(int x, int y, ClientMassage.BattleRequest battleRequest) throws IOException, ClassNotFoundException {
        ClientMassage clientMassage = new ClientMassage();
        clientMassage.setAuthToken(this.authToken);
        clientMassage.setDestinationMenu(ClientMassage.Menu.Battle);
        clientMassage.setBattleRequest(battleRequest);
        clientMassage.setX(x);
        clientMassage.setY(y);
        return sendAndReceive(clientMassage);
    }

    public synchronized ServerMassage attackInBattle(String enemyCollectionItemID) throws IOException, ClassNotFoundException {
        return doSomeThingWithID(enemyCollectionItemID, ClientMassage.BattleRequest.Attack);
    }

    private synchronized ServerMassage doSomeThingWithID(String enemyCollectionItemID, ClientMassage.BattleRequest battleRequest) throws IOException, ClassNotFoundException {
        ClientMassage clientMassage = new ClientMassage();
        clientMassage.setAuthToken(this.authToken);
        clientMassage.setDestinationMenu(ClientMassage.Menu.Battle);
        clientMassage.setBattleRequest(battleRequest);
        clientMassage.setCollectionItemID(enemyCollectionItemID);
        return sendAndReceive(clientMassage);
    }

    public synchronized ServerMassage useSpecialPower(int x, int y) throws IOException, ClassNotFoundException {
        return doSomeThingWithCoordination(x, y, ClientMassage.BattleRequest.UseSpecialPower);
    }

    //Server Commands

    public synchronized ServerMassage getCurrentMenu() throws IOException, ClassNotFoundException {
        return getServerMassage(ClientMassage.ServerRequest.GiveCurrentMenu);
    }

    public synchronized ServerMassage getRunningGame() throws IOException, ClassNotFoundException {
        System.out.println("are miad inja ro");
        return getServerMassage(ClientMassage.ServerRequest.GiveRunningGame);
    }

    public synchronized ServerMassage changeCurrentMenu(MenuList newMenu) throws IOException, ClassNotFoundException {
        ClientMassage clientMassage = new ClientMassage();
        clientMassage.setAuthToken(this.authToken);
        clientMassage.setDestinationMenu(ClientMassage.Menu.Server);
        clientMassage.setServerRequest(ClientMassage.ServerRequest.ChangeCurrentMenu);
        clientMassage.setNewMenu(newMenu);
        return sendAndReceive(clientMassage);
    }

    private synchronized ServerMassage getServerMassage(ClientMassage.ServerRequest serverRequest) throws IOException, ClassNotFoundException {
        ClientMassage clientMassage = new ClientMassage();
        clientMassage.setAuthToken(this.authToken);
        clientMassage.setDestinationMenu(ClientMassage.Menu.Server);
        clientMassage.setServerRequest(serverRequest);
        return sendAndReceive(clientMassage);
    }

    public synchronized ServerMassage getAccounts() throws IOException, ClassNotFoundException {
        ClientMassage clientMassage = new ClientMassage();
        clientMassage.setDestinationMenu(ClientMassage.Menu.Server);
        clientMassage.setServerRequest(ClientMassage.ServerRequest.GiveAccounts);
        return sendAndReceive(clientMassage);
    }

    public static Client getClient() {
        return Client.client;
    }

    public CollectionMenu getCollectionMenu() {
        return collectionMenu;
    }

    public ShopMenu getShopMenu() {
        return shopMenu;
    }

    public ArrayList<CollectionItem> getResultOfSearch() {
        return resultOfSearch;
    }

    public void setResultOfSearch(ArrayList<CollectionItem> result) {
        this.resultOfSearch = result;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public Socket getSocket() {
        return socket;
    }
}
