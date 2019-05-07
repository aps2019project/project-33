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
        createTotalDisarm();
        application.runApplication();
    }


    public static void createTotalDisarm() throws IOException {
        Spell spell = new Spell();
        spell.setPrice(1000);
        spell.setMp(0);
        spell.getInformation().setEnemyImpact(true);
        spell.getInformation().setMultipleImpact(false);
        spell.getInformation().setCanDisarmBuffAdd(true);
        spell.getInformation().setDisarmBuffPermanent(true);
        spell.setName("Total Disarm");
        Application.writeJSON(spell, "Data/CollectionItem/Spell/TotalDisarm.json");
    }


}


//getClass e tooye show info cherto perte
//get AP ham bayad dashte bashe