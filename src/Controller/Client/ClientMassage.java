package Controller.Client;

import Controller.Battle;
import Controller.MenuList;

import java.io.Serializable;

public class ClientMassage implements Serializable {
    public enum Menu {
        AccountMenu, BattleMenu, ShopMenu, MainMenu, CollectionMenu, Server, Battle;
    }

    public enum AccountMenuRequest {
        LogIn, SignUp;
    }

    public enum MainMenuRequest {
        EnterCollectionMenu, EnterShopMenu, EnterBattleMenu, Save, LogOut;
    }

    public enum BattleMenuRequest {
        CreateGame;
    }

    public enum BattleRequest{
        ForfeitMatch, EndTurn, Select, UseItem, InsertCard, MoveCard, Attack, UseSpecialPower
    }

    public enum ServerRequest{
        GiveCurrentMenu, ChangeCurrentMenu, GiveRunningGame, GiveAccounts, SendMassageInChat, GiveAllMassages;
    }

    private String authToken;
    private Menu destinationMenu;
    private ServerRequest serverRequest;
    private AccountMenuRequest accountMenuRequest;
    private MainMenuRequest mainMenuRequest;
    private BattleMenuRequest battleMenuRequest;
    private BattleRequest battleRequest;
    private MenuList newMenu;

    //AccountMenuRequest Instances
    private String username, password;

    //BattleMenuRequest Instances
    private String secondPlayerUsername, type, mode, chapter, kind;

    //BattleRequest Instances
    private String collectionItemID;
    private int x, y;

    //ServerRequest Instances
    private String massage;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Menu getDestinationMenu() {
        return destinationMenu;
    }

    public void setDestinationMenu(Menu destinationMenu) {
        this.destinationMenu = destinationMenu;
    }

    public AccountMenuRequest getAccountMenuRequest() {
        return accountMenuRequest;
    }

    public void setAccountMenuRequest(AccountMenuRequest accountMenuRequest) {
        this.accountMenuRequest = accountMenuRequest;
    }

    public ServerRequest getServerRequest() {
        return serverRequest;
    }

    public void setServerRequest(ServerRequest serverRequest) {
        this.serverRequest = serverRequest;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public MainMenuRequest getMainMenuRequest() {
        return mainMenuRequest;
    }

    public void setMainMenuRequest(MainMenuRequest mainMenuRequest) {
        this.mainMenuRequest = mainMenuRequest;
    }

    public MenuList getNewMenu() {
        return newMenu;
    }

    public void setNewMenu(MenuList newMenu) {
        this.newMenu = newMenu;
    }

    public BattleMenuRequest getBattleMenuRequest() {
        return battleMenuRequest;
    }

    public void setBattleMenuRequest(BattleMenuRequest battleMenuRequest) {
        this.battleMenuRequest = battleMenuRequest;
    }

    public String getSecondPlayerUsername() {
        return secondPlayerUsername;
    }

    public void setSecondPlayerUsername(String secondPlayerUsername) {
        this.secondPlayerUsername = secondPlayerUsername;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getChapter() {
        return chapter;
    }

    public void setChapter(String chapter) {
        this.chapter = chapter;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public BattleRequest getBattleRequest() {
        return battleRequest;
    }

    public void setBattleRequest(BattleRequest battleRequest) {
        this.battleRequest = battleRequest;
    }

    public String getCollectionItemID() {
        return collectionItemID;
    }

    public void setCollectionItemID(String collectionItemID) {
        this.collectionItemID = collectionItemID;
    }
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }
}
