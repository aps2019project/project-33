package Controller;

import Controller.Menus.*;
import Model.Account;
import Model.CollectionItem.CollectionItem;

import java.util.ArrayList;

public class Client {
    private AccountMenu accountMenu = new AccountMenu();
    private BattleMenu battleMenu = new BattleMenu();
    private CollectionMenu collectionMenu = new CollectionMenu();
    private MainMenu mainMenu = new MainMenu();
    private ShopMenu shopMenu = new ShopMenu();
    private MenuList currentMenu = MenuList.AccountMenu;
    private static Client client = null;
    private ArrayList<CollectionItem> resultOfSearch = new ArrayList<>();
    //todo in bayad ye jaei meghdar dehi she
    private Battle runningBattle;

    private Client(){

    }

    public static Client createClient(){
        if(client != null) return client;
        client = new Client();
        return client;
    }

    public static Client getClient(){
        return Client.client;
    }

    public AccountMenu getAccountMenu() {
        return accountMenu;
    }

    public BattleMenu getBattleMenu() {
        return battleMenu;
    }

    public CollectionMenu getCollectionMenu() {
        return collectionMenu;
    }

    public MainMenu getMainMenu() {
        return mainMenu;
    }

    public ShopMenu getShopMenu() {
        return shopMenu;
    }

    public MenuList getCurrentMenu() {
        return currentMenu;
    }

    public void setCurrentMenu(MenuList currentMenu) {
        this.currentMenu = currentMenu;
    }

    public Battle getRunningBattle() {
        return runningBattle;
    }

    public void setRunningBattle(Battle runningBattle) {
        this.runningBattle = runningBattle;
    }

    public ArrayList<CollectionItem> getResultOfSearch() {
        return resultOfSearch;
    }

    public void setResultOfSearch(ArrayList<CollectionItem> result){
        this.resultOfSearch = result;
    }
}
