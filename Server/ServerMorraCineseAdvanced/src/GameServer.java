import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class GameServer {
    private static final int PORT = 12345; // Porta su cui il server ascolta le connessioni

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server avviato. In attesa di connessioni...");

            while (true) {
                Socket clientSocket = serverSocket.accept(); // Accetta una nuova connessione dal client
                System.out.println("Nuova connessione da " + clientSocket.getInetAddress().getHostName());

                // Avvia un nuovo thread per gestire la comunicazione con il client
                Thread clientThread = new Thread(new ClientHandler(clientSocket));
                clientThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    }