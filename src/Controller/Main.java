package Controller;

import Model.CollectionItem.Spell;

import java.io.FileNotFoundException;
import java.io.IOException;
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
    }
}


//getClass e tooye show info cherto perte
//get AP ham bayad dashte bashe