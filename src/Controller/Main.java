package Controller;

import Generator.*;
import View.View;

import java.io.*;
import java.util.Scanner;

import static javafx.application.Application.launch;


//nemidunam khodamam

public class Main {
    public static Scanner scanner = new Scanner(System.in);
    public static Application application = new Application();

    public static void main(String[] args) throws IOException {
    //    Generator.createCards(application);
    //    ShopGenerator.generate();

        application.runApplication();
        View.main(args);
    }
}