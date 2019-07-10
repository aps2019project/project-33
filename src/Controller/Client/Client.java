package Controller.Client;

import Controller.MenuList;
import Controller.Server.ServerMassage;
import Model.CollectionItem.CollectionItem;
import Model.Deck;
import com.gilecode.yagson.YaGson;
import com.gilecode.yagson.YaGsonBuilder;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

//todo in auth token ha irad dashtan
public class Client {

    //network
    private int port = 8000;
    private String ip = "127.0.0.1";
    private Socket socket;
    private Scanner input;
    private PrintStream output;

    //logical details
    private static Client client = null;
    private String authToken;

    private ArrayList<CollectionItem> resultOfSearch = new ArrayList<>();
    //todo in bayad ye jaei meghdar dehi she

    private Client() throws IOException {
        socket = new Socket(ip, port);
        input = new Scanner(socket.getInputStream());
        output = new PrintStream(socket.getOutputStream());
    }

    public static void createClient() throws IOException {
        if (client != null) return;
        client = new Client();
    }

    private ServerMassage castFromJson(String serverMassageJson){
        YaGsonBuilder yaGsonBuilder = new YaGsonBuilder();
        YaGson yaGson = yaGsonBuilder.create();
        return yaGson.fromJson(serverMassageJson, ServerMassage.class);
    }

    private String castToJson(ClientMassage clientMassage){
        YaGsonBuilder yaGsonBuilder = new YaGsonBuilder();
        YaGson yaGson = yaGsonBuilder.create();
        return yaGson.toJson(clientMassage);
    }

    private ServerMassage sendAndReceive(ClientMassage clientMassage) throws IOException, ClassNotFoundException {
        String clientMassageJson = castToJson(clientMassage);
        output.println(clientMassageJson);
        output.flush();
        return castFromJson(input.nextLine());
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
//collection commands
    public synchronized ServerMassage collectionMenuCommand(ClientMassage.CollectionMenuRequest collectionMenuRequest) throws IOException, ClassNotFoundException {
        ClientMassage clientMassage = new ClientMassage();
        clientMassage.setAuthToken(this.authToken);
        clientMassage.setDestinationMenu(ClientMassage.Menu.CollectionMenu);
        clientMassage.setCollectionMenuRequest(collectionMenuRequest);
        return sendAndReceive(clientMassage);
    }

    public synchronized ServerMassage collectionMenuCommand(ClientMassage.CollectionMenuRequest collectionMenuRequest,
                                                            Deck deck, CollectionItem collectionItem, String name) throws IOException, ClassNotFoundException {
        ClientMassage clientMassage = new ClientMassage();
        clientMassage.setAuthToken(this.authToken);
        clientMassage.setDestinationMenu(ClientMassage.Menu.CollectionMenu);
        clientMassage.setCollectionMenuRequest(collectionMenuRequest);
        clientMassage.setSelectedCollectionItem(collectionItem);
        clientMassage.setSelectedDeck(deck);
        clientMassage.setName(name);
        return sendAndReceive(clientMassage);
    }
    //shop menu commands
    public synchronized ServerMassage shopMenuCommand(ClientMassage.ShopMenuRequest shopMenuRequest, CollectionItem collectionItem,
                                                      String name) throws IOException, ClassNotFoundException {
        ClientMassage clientMassage = new ClientMassage();
        clientMassage.setAuthToken(this.authToken);
        clientMassage.setDestinationMenu(ClientMassage.Menu.ShopMenu);
        clientMassage.setShopMenuRequest(shopMenuRequest);
        clientMassage.setCreatedCollectionitem(collectionItem);
        clientMassage.setName(name);
        return sendAndReceive(clientMassage);
    }
    //BattleMenu Commands

    public ServerMassage answerToGame(ClientMassage.BattleMenuRequest battleMenuRequest) throws IOException, ClassNotFoundException {
        ClientMassage clientMassage = new ClientMassage();
        clientMassage.setAuthToken(this.authToken);
        clientMassage.setDestinationMenu(ClientMassage.Menu.BattleMenu);
        clientMassage.setBattleMenuRequest(battleMenuRequest);
        return sendAndReceive(clientMassage);
    }

    public synchronized ServerMassage startCreateMultiGame(String secondPlayerUsername, String type, String mode, String chapter, String kind, int numberOfFlag, int timeOfTurn) throws IOException, ClassNotFoundException {
        return sendCreateGameCommand(secondPlayerUsername, type, mode, chapter, kind, numberOfFlag, timeOfTurn, ClientMassage.BattleMenuRequest.startMultiPlayerGame);

    }

    public synchronized ServerMassage createGame(String secondPlayerUsername, String type, String mode, String chapter, String kind, int numberOfFlag, int maximumTimeOfTurn) throws IOException, ClassNotFoundException {
        return sendCreateGameCommand(secondPlayerUsername, type, mode, chapter, kind, numberOfFlag, maximumTimeOfTurn, ClientMassage.BattleMenuRequest.CreateSinglePlayerGame);
    }

    private ServerMassage sendCreateGameCommand(String secondPlayerUsername, String type, String mode, String chapter, String kind, int numberOfFlag, int timeOfTurn, ClientMassage.BattleMenuRequest battleMenuRequest) throws IOException, ClassNotFoundException {
        ClientMassage clientMassage = new ClientMassage();
        clientMassage.setAuthToken(this.authToken);
        clientMassage.setDestinationMenu(ClientMassage.Menu.BattleMenu);
        clientMassage.setBattleMenuRequest(battleMenuRequest);
        clientMassage.setSecondPlayerUsername(secondPlayerUsername);
        clientMassage.setType(type);
        clientMassage.setMode(mode);
        clientMassage.setChapter(chapter);
        clientMassage.setKind(kind);
        clientMassage.setTimeOfTurn(timeOfTurn);
        clientMassage.setNumberOfFlag(numberOfFlag);
        return sendAndReceive(clientMassage);
    }

    //Battle Commands

    public ServerMassage sendCheatText(String string) throws IOException, ClassNotFoundException {
        ClientMassage clientMassage = new ClientMassage();
        clientMassage.setAuthToken(this.authToken);
        clientMassage.setDestinationMenu(ClientMassage.Menu.Battle);
        clientMassage.setBattleRequest(ClientMassage.BattleRequest.Cheat);
        clientMassage.setCheatText(string);
        return sendAndReceive(clientMassage);
    }


    public ServerMassage fastForward() throws IOException, ClassNotFoundException {
        return battleCommand(ClientMassage.BattleRequest.FastForward);
    }

    public synchronized ServerMassage graveYardCommand() throws IOException, ClassNotFoundException {
        ClientMassage clientMassage = new ClientMassage();
        clientMassage.setAuthToken(this.authToken);
        clientMassage.setDestinationMenu(ClientMassage.Menu.Battle);
        clientMassage.setBattleRequest(ClientMassage.BattleRequest.GiveGraveYard);
        return sendAndReceive(clientMassage);
    }

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

    public synchronized ServerMassage sendMassageInChat(String massageText) throws IOException, ClassNotFoundException {
        ClientMassage clientMassage = new ClientMassage();
        clientMassage.setAuthToken(this.authToken);
        clientMassage.setDestinationMenu(ClientMassage.Menu.Server);
        clientMassage.setServerRequest(ClientMassage.ServerRequest.SendMassageInChat);
        clientMassage.setMassage(massageText);
        return sendAndReceive(clientMassage);
    }

    public ServerMassage getAllMassages() throws IOException, ClassNotFoundException {
        ClientMassage clientMassage = new ClientMassage();
        clientMassage.setAuthToken(this.authToken);
        clientMassage.setDestinationMenu(ClientMassage.Menu.Server);
        clientMassage.setServerRequest(ClientMassage.ServerRequest.GiveAllMassages);
        return sendAndReceive(clientMassage);
    }

    public synchronized ServerMassage getCurrentMenu() throws IOException, ClassNotFoundException {
        return getServerMassage(ClientMassage.ServerRequest.GiveCurrentMenu);
    }

    public synchronized ServerMassage getRunningGame() throws IOException, ClassNotFoundException {
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
