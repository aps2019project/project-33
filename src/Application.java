import java.util.ArrayList;

public class Application {
    private Shop shop;
    private Player logedInPlayer;
    private ArrayList<Account> accounts;
    private ArrayList<Battle> battles;

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
}
