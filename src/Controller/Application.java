package Controller;

import Controller.Menus.AccountMenu;
import Model.*;
import Model.CollectionItem.CollectionItem;
import Model.CollectionItem.Item;
import Model.CollectionItem.LivingCard;
import Model.CollectionItem.Spell;
import com.gilecode.yagson.YaGson;
import com.gilecode.yagson.YaGsonBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

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

    public static void loadData() throws FileNotFoundException {
        ArrayList<Object> objects;
        //Read Account
        objects = readFromFile("Data/Memory/Accounts", "Account");
        for (Object object : objects)
            Account.getAccounts().add((Account) object);

        //Read Spells
        objects = readFromFile("Data/Memory/Spells", "Spell");
        for (Object object : objects)
            CollectionItem.getAllSpells().add((Spell) object);

        //Read LivingCards
        objects = readFromFile("Data/Memory/LivingCards", "LivingCard");
        for (Object object : objects)
            CollectionItem.getAllLivingCards().add((LivingCard) object);

        //Read Items
        objects = readFromFile("Data/Memory/Items", "Item");
        for (Object object : objects)
            CollectionItem.getAllItems().add((Item) object);

        //Read Shop
        File file = new File("Data/Memory/Shop");
        if(Objects.requireNonNull(file.listFiles()).length > 0)
            Main.application.shop = (Collection) readJSON(Collection.class, "Data/Memory/Shop/Shop.json");

    }

    public static ArrayList<Object> readFromFile(String address, String type) throws FileNotFoundException {
        ArrayList<Object> arrayList = new ArrayList<>();
        File file = new File(address);
        int sizeOfFolder = Objects.requireNonNull(file.listFiles()).length;
        for (int i = 0; i < sizeOfFolder; i++) {
            Object object = readJSON(Object.class, address + "/" + type + i + ".json");
            arrayList.add(object);
        }
        return arrayList;
    }

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

    // Here is Setters && Getters

    public Account getLoggedInAccount() {
        return loggedInAccount;
    }

    public void setLoggedInAccount(Account loggedInAccount) {
        this.loggedInAccount = loggedInAccount;
    }

    public Collection getShop() {
        return shop;
    }

}
