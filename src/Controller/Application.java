package Controller;

import Controller.Menus.AccountMenu;
import Controller.Menus.MainMenu;
import Controller.Menus.ShopMenu;
import Model.*;

import java.util.ArrayList;

public class Application {

    private ArrayList<Account> accounts;
    private ArrayList<Battle> battles;
    private Collection shop;
    private Account loggedInAccount;


    public void runApplication(){
        AccountMenu accountMenu = new AccountMenu();
        accountMenu.inputCommandLine();
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

    public Collection getShop() {
        return shop;
    }

    public void setShop(Collection shop) {
        this.shop = shop;
    }
}
