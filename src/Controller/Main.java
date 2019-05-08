package Controller;

import Model.CollectionItem.Minion;

import java.io.*;
import java.util.Scanner;


public class Main {
    public static Scanner scanner = new Scanner(System.in);
    public static Application application = new Application();


    public static void main(String[] args) throws IOException {
//        application.runApplication();

    }

    public static void createKamandareFars() throws IOException {
        Minion minion = new Minion();
        minion.setPrice(300);
        minion.setMP(2);
        minion.setHP(6);
        minion.setDecreaseHPByAttack(4);
        minion.getInformation().setCanDoRangedAttack(true);
        minion.setRangeOfAttack(7);
        minion.setName("KamandareFars");
        minion.getInformation().setCanHolyBuffAdd(true);
        minion.getInformation().setTimeOfStunBuff(1);
        Application.writeJSON(minion, "Data/CollectionItem/Card/LivingCard/Minion/KamandareFars.json");
        minion.getInformation().setCanAttackToHeroWhenDead(true);
        minion.getInformation().setDamageToHeroWhenDead(6);
    }
}


//getClass e tooye show info cherto perte
//get AP ham bayad dashte bashe