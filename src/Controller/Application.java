//done

package Controller;

import Controller.Client.Client;
import Controller.Server.Server;
import Controller.Server.ServerMain;
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

    public void runApplication() throws IOException {
        loadData();
        new Server().run();
    }

    public synchronized static Object copy(Object object, Class className) {
        YaGsonBuilder builder = new YaGsonBuilder();
        YaGson yaGson = builder.create();
        String string = yaGson.toJson(object);
        return yaGson.fromJson(string, className);
    }

    public static void loadData() throws FileNotFoundException {
        ArrayList<Object> objects;
        //Read Account
        objects = readFromFile("Data/Memory/Accounts", "Account");
        for (Object object : objects) {
            Account account = (Account) object;
            account.setState(Account.State.Offline);
            Account.getAccounts().add(account);
        }

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

        //Read ShopMenu
        File file = new File("Data/Memory/ShopMenu");

        if(Objects.requireNonNull(file.listFiles()).length > 0)
            ServerMain.application.shop = (Collection) readJSON(Collection.class, "Data/Memory/ShopMenu/ShopMenu.json");

    }

    public static ArrayList<Object> readFromFile(String address, String type) throws FileNotFoundException {
        ArrayList<Object> arrayList = new ArrayList<>();
        File file = new File(address);
        int sizeOfFolder = Objects.requireNonNull(file.listFiles()).length;
        System.out.println(sizeOfFolder + "****");
        for (int i = 0; i < sizeOfFolder; i++) {
            Object object = readJSON(Object.class, address + "/" + type + i + ".json");
            arrayList.add(object);
        }
        return arrayList;
    }

    public synchronized static Object readJSON(Class className, String address) throws FileNotFoundException {
        YaGsonBuilder builder = new YaGsonBuilder();
        YaGson yaGson = builder.create();
        BufferedReader bufferedReader = new BufferedReader(
                new FileReader(address));

        Object object = yaGson.fromJson(bufferedReader, className);
        return object;
    }

    public synchronized static void writeJSON(Object object, String address) throws IOException {
        YaGsonBuilder builder = new YaGsonBuilder();
        YaGson yaGson = builder.create();
        FileWriter writer = new FileWriter(address);
        writer.write(yaGson.toJson(object));
        writer.close();
    }

    // Here is Setters && Getters

    public Collection getShop() {
        return shop;
    }

}
