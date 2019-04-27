import java.util.ArrayList;

public class Account {

    private static ArrayList<Account> acccounts = new ArrayList<Account>();
    private ArrayList<Match> matches = new ArrayList<Match>();
    private Collection collection;
    private String username;
    private String password;
    private int budget;
    private int numberOfItems;


    //Here is Setters && Getters

    public static ArrayList<Account> getAcccounts() {
        return acccounts;
    }

    public static void setAcccounts(ArrayList<Account> acccounts) {
        Account.acccounts = acccounts;
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
        return numberOfItems;
    }

    public void setNumberOfItems(int numberOfItems) {
        this.numberOfItems = numberOfItems;
    }

    public ArrayList<Match> getMatches() {
        return matches;
    }

    public void setMatches(ArrayList<Match> matches) {
        this.matches = matches;
    }
}