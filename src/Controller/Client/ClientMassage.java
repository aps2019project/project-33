package Controller.Client;

import Controller.Battle;
import Controller.MenuList;
import Model.CollectionItem.CollectionItem;
import Model.Deck;

import java.io.Serializable;

public class ClientMassage implements Serializable {

    public ClientMassage() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ShopMenuRequest getShopMenuRequest() {
        return shopMenuRequest;
    }

    public void setShopMenuRequest(ShopMenuRequest shopMenuRequest) {
        this.shopMenuRequest = shopMenuRequest;
    }

    public CollectionItem getCreatedCollectionitem() {
        return createdCollectionitem;
    }

    public void setCreatedCollectionitem(CollectionItem createdCollectionitem) {
        this.createdCollectionitem = createdCollectionitem;
    }

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
        CreateSinglePlayerGame, AcceptGame, RejectGame, startMultiPlayerGame;
    }

    public enum BattleRequest{
        ForfeitMatch, EndTurn, Select, UseItem, InsertCard, MoveCard, Attack, UseSpecialPower, GiveGraveYard
    }

    public enum ServerRequest{
        GiveCurrentMenu, ChangeCurrentMenu, GiveRunningGame, GiveAccounts, SendMassageInChat, GiveAllMassages;
    }

    public enum CollectionMenuRequest{
        Exit, Save, Show, GiveCollection, AddCollectionItemToDeck, CreateDeck, DeleteDeck, Export, Import, RemoveFromDeck,
        Search, SelectDeck, ShowDeck, ValidateDeck;
    }

    public enum ShopMenuRequest{
        CreateCard, Search, Buy, SearchInCollection, SearchInShop, Sell, GiveCollection, ShowCollection, GiveShop;
    }

    private String authToken;
    private Menu destinationMenu;
    private ServerRequest serverRequest;
    private AccountMenuRequest accountMenuRequest;
    private MainMenuRequest mainMenuRequest;
    private BattleMenuRequest battleMenuRequest;
    private BattleRequest battleRequest;
    private CollectionMenuRequest collectionMenuRequest;
    private MenuList newMenu;
    private ShopMenuRequest shopMenuRequest;

    private Deck selectedDeck;
    private CollectionItem selectedCollectionItem;
    private String name;

    //AccountMenuRequest Instances
    private String username, password;

    //BattleMenuRequest Instances
    private String secondPlayerUsername, type, mode, chapter, kind;
    private int numberOfFlag;

    //BattleRequest Instances
    private String collectionItemID;
    private int x, y;

    //ServerRequest Instances
    private String massage;
//create card
    private CollectionItem createdCollectionitem;

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

    public void setCollectionMenuRequest(CollectionMenuRequest collectionMenuRequest){
        this.collectionMenuRequest = collectionMenuRequest;
    }

    public CollectionMenuRequest getCollectionMenuRequest(){
        return this.collectionMenuRequest;
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

    public int getNumberOfFlag() {
        return numberOfFlag;
    }

    public void setNumberOfFlag(int numberOfFlag) {
        this.numberOfFlag = numberOfFlag;
    }

    public Deck getSelectedDeck() {
        return selectedDeck;
    }

    public void setSelectedDeck(Deck selectedDeck) {
        this.selectedDeck = selectedDeck;
    }

    public CollectionItem getSelectedCollectionItem() {
        return selectedCollectionItem;
    }

    public void setSelectedCollectionItem(CollectionItem selectedCollectionItem) {
        this.selectedCollectionItem = selectedCollectionItem;
    }
}
