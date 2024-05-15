package com.example.morracineseadvanced;

import android.os.Bundle;

import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import androidx.viewpager2.widget.ViewPager2;

public class GameActivity extends AppCompatActivity {

    private static final String TAG = "ClientActivity";
    private static final String SERVER_IP = "192.168.1.100"; // Inserisci l'indirizzo IP del server
    private static final int SERVER_PORT = 12345; // Inserisci la porta del server

    private TextView messageTextView;
    private ViewPager2 viewPager; // Usa ViewPager2 invece di ViewPager
    private ImagePagerAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        viewPager = findViewById(R.id.viewPager);

        List<Integer> imageList = new ArrayList<>();
        imageList.add(R.drawable.kiwi);
        imageList.add(R.drawable.georgefloyd);

        adapter = new ImagePagerAdapter(this, imageList);
        viewPager.setAdapter(adapter);
        Button buttonSelect = findViewById(R.id.button_select);
        buttonSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentPosition = viewPager.getCurrentItem();
                String message = "Posizione della foto: " + currentPosition;
                showToast(message);
            }
        });
//        messageTextView = findViewById(R.id.messageTextView);

        // Consentire le operazioni di rete sul thread principale (da evitare in produzione)
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        // Avvia la connessione al server
//        connectToServer();
    }
    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
//}
//
//
//
//public class ClientActivity extends AppCompatActivity {
//
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//
//    }
//
//    // Metodo per connettersi al server e inviare una mossa
//    private void connectToServer() {
//        try {
//            // Stabilisci la connessione al server
//            Socket socket = new Socket(SERVER_IP, SERVER_PORT);
//
//            // Crea un oggetto Move per rappresentare una mossa nel gioco
//            Move move = new Move(/* playerId /, / positionX /, / positionY */);
//
//            // Invia la mossa al server
//            sendMove(socket, move);
//
//            // Chiudi la connessione
//            socket.close();
//        } catch (IOException e) {
//            Log.e(TAG, "Errore durante la connessione al server: " + e.getMessage());
//            // Gestisci l'errore di connessione al server
//            messageTextView.setText("Errore durante la connessione al server");
//        }
//    }
//
//    // Metodo per inviare una mossa al server
//    private void sendMove(Socket socket, Move move) throws IOException {
//        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
//        out.writeObject(move);
//    }
}