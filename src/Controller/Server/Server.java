package Controller.Server;

import java.awt.image.AreaAveragingScaleFilter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    private ServerSocket serverSocket;
    private ArrayList<ResponseToClient> responseToClients = new ArrayList<>();

    public void run() throws IOException {
        setServerDetails();
        acceptClients();
    }

    private void acceptClients() throws IOException {
        while (true){
            Socket socket = serverSocket.accept();
            ResponseToClient responseToClient = new ResponseToClient(socket);
            responseToClient.start();
            responseToClients.add(responseToClient);
        }
    }

    private void setServerDetails() throws IOException {
        serverSocket = new ServerSocket(8000);
    }
}
