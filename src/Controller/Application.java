package Controller;

import Controller.Menus.AccountMenu;
import Controller.Menus.MainMenu;
import Controller.Menus.ShopMenu;
import Model.*;
import com.gilecode.yagson.YaGson;
import com.gilecode.yagson.YaGsonBuilder;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.ArrayList;

public class Application {

    private ArrayList<Account> accounts;
    private Collection shop = new Collection();
    private Account loggedInAccount;


    public void runApplication() throws IOException {
        loadData();
        AccountMenu accountMenu = new AccountMenu();
        accountMenu.inputCommandLine();
    }

    public static Object copy(Object object, Class className) throws IOException {
        String address = "copy.json";
        writeJSON(object, address);
        Object copyObject = readJSON(className, address);
        return copyObject;
    }

//    public static void loadData(){
//        String address = "Data/Memory/Accounts";
//        int numberOfAddress = new File(address).listFiles().length;
//            for(int i = 0; i < numberOfAddress; i ++){
//
//        }
//    }

    public static Object readJSON(Class className, String address) throws FileNotFoundException {
        YaGsonBuilder builder = new YaGsonBuilder();
        YaGson yaGson = builder.create();
        BufferedReader bufferedReader = new BufferedReader(
                new FileReader(address));

        Object object = yaGson.fromJson(bufferedReader, className);
        return object;
    }

    public static void writeJSON(Object object, String address) throws IOException {
        YaGsonBuilder builder = new YaGsonBuilder();
        YaGson yaGson = builder.create();
        FileWriter writer = new FileWriter(address);
        writer.write(yaGson.toJson(object));
        writer.close();
    }

    public void addAccount(Account account){
        this.accounts.add(account);
    }

    // Here is Setters && Getters

    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(ArrayList<Account> accounts) {
        this.accounts = accounts;
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
