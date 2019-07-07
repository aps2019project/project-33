package Controller.Client;

import Controller.Server.ServerMain;
import View.View;

import java.io.IOException;

import static Controller.Client.ClientMassage.Menu.Server;

//todo, alan baraye account ye running game gozashtim, bayad havasemoon bashe

public class ClientMain {
    public static void main(String[] args) throws IOException {
        System.out.println("salam haji");
        Client.createClient();
        System.out.println("connect shod");
        View.main(args);
    }
}
