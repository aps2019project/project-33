package Controller;

import Generator.Generator;
import Model.CollectionItem.Item;
import Model.CollectionItem.Minion;
import Model.CollectionItem.Spell;
import Model.CollectionItem.Hero;
import com.gilecode.yagson.YaGson;
import com.gilecode.yagson.YaGsonBuilder;
import jdk.jshell.spi.SPIResolutionException;

import java.io.*;
import java.util.Scanner;


public class Main {
    public static Scanner scanner = new Scanner(System.in);
    public static Application application = new Application();




    public static void main(String[] args) throws IOException {
        application.runApplication();
     //   Generator.createCards(application);
    }

}


//getClass e tooye show info cherto perte
//get AP ham bayad dashte bashe