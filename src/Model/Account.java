package Model;

import java.io.Serializable;
import java.util.ArrayList;

import Controller.Battle;
import Controller.Client.Client;
import Controller.Client.ClientMassage;
import Controller.MenuList;
import Controller.Menus.Menu;
import Model.CollectionItem.CollectionItem;
import Model.CollectionItem.Item;

public class Account implements Serializable {
    private ClientMassage multiPlayerGameInfo;

    private static ArrayList<Account> accounts = new ArrayList<>();

    private ArrayList<Match> matches = new ArrayList<>();
    private Collection collection = new Collection();
    private String username;
    private String password;
    private int budget;
    private State state;
    private MenuList currentMenu;
    private Battle runningBattle;

    public ClientMassage getMultiPlayerGameInfo() {
        return multiPlayerGameInfo;
    }

    //Constructor

    public Account(String username, String password, int budget) {
        this.username = username;
        this.password = password;
        this.budget = 100000;
    }

    //methods

    public int getNumberOfWins() {
        int countOfWins = 0;
        for (Match match : this.matches)
            if (match.getWinner() == this) {
                countOfWins++;
            }
        return countOfWins;
    }

    public int getNumberOfLooses() {
        int countOfLooses = 0;
        for (Match match : this.matches) {
            if (match.getLoser() == this)
                countOfLooses++;
        }
        return countOfLooses;
    }

    public static Account getAccountByUsername(String username) {
        for (Account account : Account.getAccounts()) {
            if (account.getUsername().equals(username))
                return account;
        }
        return null;
    }

    public void removeCollectionItem(CollectionItem collectionItem) {

    }

    public void decreaseBudget(int cost) {
        this.budget -= cost;
    }

    public void increaseBudget(int income) {
        this.budget += income;
    }

    public synchronized static void sortArraysOfAccount(ArrayList<Account> accounts) {
        int sizeOfArray = accounts.size();
        for (int i = 0; i < sizeOfArray; i++)
            for (int j = i + 1; j < sizeOfArray; j++) {
                if (accounts.get(i).getNumberOfWins() < accounts.get(j).getNumberOfWins()) {
                    System.out.println("salammm" + " " + i + " " + j);
                    swap(accounts, i, j);
                }
                if (accounts.get(i).getNumberOfWins() == accounts.get(j).getNumberOfWins())
                    if (accounts.get(i).getNumberOfLooses() > accounts.get(j).getNumberOfLooses())
                        swap(accounts, i, j);
            }
    }

    private static void swap(ArrayList<Account> account, int i, int j) {
        Account tmp = account.get(i);
        account.set(i, account.get(j));
        account.set(j, tmp);
    }

    public void save() {
    }

    public int getNumberOfItems() {
        int numberOfItems = 0;
        for (CollectionItem collectionItem : this.getCollection().getCollectionItems())
            if (collectionItem instanceof Item)
                numberOfItems++;
        return numberOfItems;
    }

    //Here is Setters && Getters

    public static ArrayList<Account> getAccounts() {
        return accounts;
    }

    public static void setAccounts(ArrayList<Account> accounts) {
        Account.accounts = accounts;
    }

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

    public Collection getCollection() {
        return collection;
    }

    public void setCollection(Collection collection) {
        this.collection = collection;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public ArrayList<Match> getMatches() {
        return matches;
    }

    public void setMatches(ArrayList<Match> matches) {
        this.matches = matches;
    }

    public void addMatch(Match match) {
        this.matches.add(match);
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
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

    public void setMultiPlayerGameInfo(ClientMassage multiPlayerGameInfo) {
        this.multiPlayerGameInfo = multiPlayerGameInfo;
    }

    public enum State {
        Online, Offline, Busy, WaitingForGame, AnsweringToGame;
    }
}