/*import java.io.IOException;
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
}*/

import java.io.*;
import java.net.*;
import java.util.*;

public class GameServer {

    private List<Socket> clientConnected;

    public GameServer() {
        this.clientConnected = new List<Socket>() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(Object o) {
                return false;
            }

            @Override
            public Iterator<Socket> iterator() {
                return null;
            }

            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @Override
            public <T> T[] toArray(T[] a) {
                return null;
            }

            @Override
            public boolean add(Socket socket) {
                return false;
            }

            @Override
            public boolean remove(Object o) {
                return false;
            }

            @Override
            public boolean containsAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean addAll(Collection<? extends Socket> c) {
                return false;
            }

            @Override
            public boolean addAll(int index, Collection<? extends Socket> c) {
                return false;
            }

            @Override
            public boolean removeAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean retainAll(Collection<?> c) {
                return false;
            }

            @Override
            public void clear() {

            }

            @Override
            public Socket get(int index) {
                return null;
            }

            @Override
            public Socket set(int index, Socket element) {
                return null;
            }

            @Override
            public void add(int index, Socket element) {

            }

            @Override
            public Socket remove(int index) {
                return null;
            }

            @Override
            public int indexOf(Object o) {
                return 0;
            }

            @Override
            public int lastIndexOf(Object o) {
                return 0;
            }

            @Override
            public ListIterator<Socket> listIterator() {
                return null;
            }

            @Override
            public ListIterator<Socket> listIterator(int index) {
                return null;
            }

            @Override
            public List<Socket> subList(int fromIndex, int toIndex) {
                return null;
            }
        };
    }

    public void startServer(int port) {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Server in ascolto sulla porta " + port);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                clientConnected.add(clientSocket);
                System.out.println("Nuova connessione da " + clientSocket.getInetAddress());
                new Thread(new ClientHandler(clientSocket)).start();
            }

        } catch (IOException e) {
            System.out.println("Connessione Spenta");
        }
    }

    /*public void sendMessage(String message) {
        for (Socket neighbor : clientConnected) {
            try {
                PrintWriter out = new PrintWriter(neighbor.getOutputStream(), true);
                out.println(id + ": " + message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }*/
}
