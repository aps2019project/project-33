package Controller;

import Generator.*;

import java.io.*;
import java.util.Scanner;


public class Main {
    public static Scanner scanner = new Scanner(System.in);
    public static Application application = new Application();




    public static void main(String[] args) throws IOException {
        //Generator.createHeroes();
        //    ShopGenerator.generate();
        application.runApplication();
    //    Generator.createCards(application);
    }

}