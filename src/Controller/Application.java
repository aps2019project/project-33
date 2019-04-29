package Controller;

import Controller.Menus.MainMenu;
import Model.*;

import java.util.ArrayList;

public class Application {

    private ArrayList<Account> accounts;
    private ArrayList<Battle> battles;
    private ShopMenu shopMenu;
    private Account loggedInAccount;


    public void runApplication(){
        MainMenu mainMenu = new MainMenu();
        mainMenu.inputCommandLine();
    }

    //hello

    public void addAccount(Account account){
        this.accounts.add(account);
    }

    public Battle createBattle(){
        Battle newBattle = new Battle();
        this.addBattle(newBattle);
        return newBattle;
    }

    public void addBattle(Battle battle){
        this.battles.add(battle);
    }

    // Here is Setters && Getters

    public ShopMenu getShopMenu() {
        return shopMenu;
    }

    public void setShopMenu(ShopMenu shopMenu) {
        this.shopMenu = shopMenu;
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(ArrayList<Account> accounts) {
        this.accounts = accounts;
    }

    public ArrayList<Battle> getBattles() {
        return battles;
    }

    public void setBattles(ArrayList<Battle> battles) {
        this.battles = battles;
    }

    public Account getLoggedInAccount() {
        return loggedInAccount;
    }

    public void setLoggedInAccount(Account loggedInAccount) {
        this.loggedInAccount = loggedInAccount;
    }
}
