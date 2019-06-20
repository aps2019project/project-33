package Controller;

import Controller.Menus.*;

public class Client {
    private AccountMenu accountMenu = new AccountMenu();
    private BattleMenu battleMenu = new BattleMenu();
    private CollectionMenu collectionMenu = new CollectionMenu();
    private MainMenu mainMenu = new MainMenu();
    private ShopMenu shopMenu = new ShopMenu();
    private static Client client = null;

    private Client(){

    }

    public static Client createClient(){
        if(client != null) return client;
        client = new Client();
        return client;
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

}
