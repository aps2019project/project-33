package Controller;

import Generator.*;
import View.View;

import java.io.*;
import java.util.Scanner;

import static javafx.application.Application.launch;


//nemidunam khodamam
//lanat be git
//todo, bugi ke hast ine ke ma increaase ap ro divert kardim o jash buff zadim, kari nadare faghat bayad handle konimesh

public class Main {
    public static Scanner scanner = new Scanner(System.in);
    public static Application application = new Application();

    public static void main(String[] args) throws IOException {
        Generator.createCards();
        ShopGenerator.generate();
        DeckGenerator.deckGenerator();;
        application.runApplication();
        View.main(args);
    }
}