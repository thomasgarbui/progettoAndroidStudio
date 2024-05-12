import java.io.IOException;
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
