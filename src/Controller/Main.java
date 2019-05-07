package Controller;

import Model.CollectionItem.Spell;
import com.gilecode.yagson.YaGson;
import com.gilecode.yagson.YaGsonBuilder;

import java.io.*;
import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);
    public static Application application = new Application();

    public static void main(String[] args) throws IOException {
        application.runApplication();
    }


    public void createTotalDisarm(){
        Spell spell = new Spell();
        spell.setPrice(1000);
        spell.setMp(0);
        spell.getInformation().setEnemyImpact(true);
        spell.getInformation().setMultipleImpact(false);
        spell.getInformation().setCanDisarmBuffAdd(true);
        spell.getInformation().setDisarmBuffPermanent(true);
        spell.setName("Total Disarm");
        writeJSON(spell, "Data/CollectionItem/Spell/TotalDisarm.json");
    }

    public static Object copy(Object object, Class className) throws IOException {
        String address = "copy.json";
        writeJSON(object, address);
        Object copyObject = readJSON(className, address);
        return copyObject;
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
}


//getClass e tooye show info cherto perte
//get AP ham bayad dashte bashe