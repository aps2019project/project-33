package Model;

import java.util.ArrayList;

import Model.CollectionItem.CollectionItem;
import Model.CollectionItem.Item;

public class Account {

    private static ArrayList<Account> accounts = new ArrayList<>();
    private ArrayList<Match> matches = new ArrayList<>();
    private Collection collection = new Collection();
    private String username;
    private String password;
    private int budget;

    //Constructor

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }

    //methods

    public int getNumberOfWins(){
        int countOfWins = 0;
        for(Match match : this.matches)
            if(match.getWinner() == this){
                countOfWins ++;
            }
        return countOfWins;
    }

    public int getNumberOfLooses(){
        int countOfLooses = 0;
        for(Match match : this.matches){
            if(match.getLoser() == this)
                countOfLooses ++;
        }
        return countOfLooses;
    }

    public static Account getAccountByUsername(String username){
        for(Account account : Account.getAccounts()){
            if(account.getUsername().equals(username))
                return account;
        }
        return null;
    }

//    public static void createAccount(String username, String password){
//        Account account = new Account(username, password);
//        return;
//    }

    public void removeCollectionItem(CollectionItem collectionItem){

    }

    public void decreaseBudget(int cost){
        this.budget -= cost;
    }
    public void increaseBudget(int income){
        this.budget += income;
    }

    public static void showLeaderBoard(){
        ArrayList<Account> accounts = Account.getAccounts();
        sortArraysOfAccount(accounts);
        int index = 1;
        for(Account account : accounts){
            System.out.println(index ++ + ". " + account.getUsername() + " " + "W: " + account.getNumberOfWins() + " "
            + "L: " + account.getNumberOfLooses());
        }
    }

    private static void sortArraysOfAccount(ArrayList<Account> accounts){
        int sizeOfArray = accounts.size();
        for(int i = 0; i < sizeOfArray; i ++)
            for(int j = i + 1; j < sizeOfArray; j ++) {
                if (accounts.get(i).getNumberOfWins() > accounts.get(j).getNumberOfWins()) {
                    swap(accounts, i, j);
                }
                if(accounts.get(i).getNumberOfWins() == accounts.get(j).getNumberOfWins())
                    if(accounts.get(i).getNumberOfLooses() < accounts.get(j).getNumberOfLooses())
                        swap(accounts, i, j);
            }
    }

    private static void swap(ArrayList<Account> account, int i, int j){
        Account tmp = account.get(i);
        account.set(i, account.get(j));
        account.set(i, tmp);
    }

    public void save(){}

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

    public int getNumberOfItems() {
        int numberOfItems = 0;
        for(CollectionItem collectionItem : this.getCollection().getCards())
            if(collectionItem instanceof Item)
                numberOfItems ++;
        return numberOfItems;
    }


    public ArrayList<Match> getMatches() {
        return matches;
    }

    public void setMatches(ArrayList<Match> matches) {
        this.matches = matches;
    }

    public void addMatch(Match match){
        this.matches.add(match);
    }
}