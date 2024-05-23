/*import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private Socket clientSocket;

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());

            while (true) {
                // Leggi la mossa inviata dal client
                Object object = in.readObject();
                if (object instanceof Move) {
                    Move move = (Move) object;
                    handleMove(move);
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void handleMove(Move move) {
        // Esegui la logica per gestire la mossa ricevuta
        System.out.println("Mossa ricevuta dal giocatore " + move.getPlayerId() + ": " +
                "Posizione X=" + move.getPositionX() + ", " +
                "Posizione Y=" + move.getPositionY());
        // Aggiorna lo stato del gioco, controlla la validità della mossa, ecc.
    }
}
*/

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class ClientHandler implements Runnable {

    private Socket clientSocket;
    private ObjectInputStream in;

    public ClientHandler(Socket clientSocket) {
        try{
            this.clientSocket = clientSocket;
            in = new ObjectInputStream(clientSocket.getInputStream());
        }catch(Exception ex){

        }

    }

    @Override
    public void run() {
        try {
            while (true) {
                TEST t = (TEST) in.readObject();
                if (t == null) {
                    System.out.println("Connessione chiusa da " + clientSocket.getInetAddress());
                    break;
                }
                System.out.println(t.string);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}