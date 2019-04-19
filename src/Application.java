import java.util.ArrayList;

public class Application {
    private Shop shop;
    private Account loggedInAccount;
    private ArrayList<Account> accounts;
    private ArrayList<Battle> battles;

    public void runApplication(){
        MainMenu mainMenu = new MainMenu();
        mainMenu.inputCommandLine();
    }

    public Account createAccount(){
        Account newAccount = new Account();
        this.addAccount(newAccount);
        return newAccount;
    }

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

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
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
