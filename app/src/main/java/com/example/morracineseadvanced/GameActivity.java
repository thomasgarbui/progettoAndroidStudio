package com.example.morracineseadvanced;

import android.os.Bundle;

import android.os.StrictMode;
import android.util.Log;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class GameActivity extends AppCompatActivity {

    private static final String TAG = "ClientActivity";
    private static final String SERVER_IP = "192.168.1.100"; // Inserisci l'indirizzo IP del server
    private static final int SERVER_PORT = 12345; // Inserisci la porta del server

    private TextView messageTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        messageTextView = findViewById(R.id.messageTextView);

        // Consentire le operazioni di rete sul thread principale (da evitare in produzione)
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        // Avvia la connessione al server
        connectToServer();
    }
}



public class ClientActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {

    }

    // Metodo per connettersi al server e inviare una mossa
    private void connectToServer() {
        try {
            // Stabilisci la connessione al server
            Socket socket = new Socket(SERVER_IP, SERVER_PORT);

            // Crea un oggetto Move per rappresentare una mossa nel gioco
            Move move = new Move(/* playerId /, / positionX /, / positionY */);

            // Invia la mossa al server
            sendMove(socket, move);

            // Chiudi la connessione
            socket.close();
        } catch (IOException e) {
            Log.e(TAG, "Errore durante la connessione al server: " + e.getMessage());
            // Gestisci l'errore di connessione al server
            messageTextView.setText("Errore durante la connessione al server");
        }
    }

    // Metodo per inviare una mossa al server
    private void sendMove(Socket socket, Move move) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(move);
    }
}