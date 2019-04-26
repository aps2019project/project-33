import java.util.ArrayList;

public class Account {
    private static ArrayList<Account> acccounts = new ArrayList<Account>();
    private String username;
    private String password;
    private Collection collection;
    private int budget;
    private int numberOfItems;
    private ArrayList<Match> matches = new ArrayList<Match>();
}