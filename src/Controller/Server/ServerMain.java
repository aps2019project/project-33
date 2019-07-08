package Controller.Server;

import Controller.Application;
import Generator.*;
import View.Server.ServerView;

import java.io.*;
import java.util.Scanner;

import static javafx.application.Application.launch;


//nemidunam khodamam
//todo, bugi ke hast ine ke ma increaase ap ro divert kardim o jash buff zadim, kari nadare faghat bayad handle konimesh

public class ServerMain {
    public static Scanner scanner = new Scanner(System.in);
    public static Application application = new Application();

    public static void main(String[] args) throws IOException {
        Generator.createCards();
        ShopGenerator.generate();
        DeckGenerator.deckGenerator();
        new Thread(() -> {
            try {
                application.runApplication();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        ServerView.main(args);
    }
}