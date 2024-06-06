package com.example.morracineseadvanced;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
public class EndGameActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_endgame);

        TextView txt_player1 = findViewById(R.id.textView_player1);
        TextView txt_player2 = findViewById(R.id.textView_player2);
        Button btn_back = findViewById(R.id.button_backmain);
        ImageView img_player1 = findViewById(R.id.imageView_player1);
        ImageView img_player2 = findViewById(R.id.imageView_player2);
        TextView txt_winner = findViewById(R.id.textView_winner);

        String winnerUsername = getIntent().getStringExtra("winnerUsername");
        String playerOneUsername = getIntent().getStringExtra("playerOneUsername");
        String playerTwoUsername = getIntent().getStringExtra("playerTwoUsername");

        String playerOneMove = PreferenceManager.getDefaultSharedPreferences(EndGameActivity.this).getString("playerOneMove", null);
        String playerTwoMove = PreferenceManager.getDefaultSharedPreferences(EndGameActivity.this).getString("playerTwoMove", null);
        int playerOneMoveImageResource = getResources().getIdentifier(playerOneMove, "drawable", getPackageName());
        int playerTwoMoveImageResource = getResources().getIdentifier(playerTwoMove, "drawable", getPackageName());
        if (playerOneMoveImageResource != 0) {
            img_player1.setImageResource(playerOneMoveImageResource);
        } else {
        }
        if (playerTwoMoveImageResource != 0) {
            img_player2.setImageResource(playerTwoMoveImageResource);
        } else {
        }

        txt_winner.setText("Winner: " + winnerUsername);
        txt_player1.setText(playerOneUsername);
        txt_player2.setText(playerTwoUsername);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EndGameActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
