import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        GameServer gameServer = new GameServer();
        int port = 8080;
        new Thread(() -> gameServer.startServer(port)).start();
    }
}