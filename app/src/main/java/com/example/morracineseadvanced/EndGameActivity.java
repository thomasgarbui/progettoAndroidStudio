package com.example.morracineseadvanced;

import android.content.Intent;
import android.os.Bundle;
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

        TextView txt_player1 =  findViewById(R.id.textView_player1);
        TextView txt_player2 =  findViewById(R.id.textView_player2);
        Button btn_back = findViewById(R.id.button_backmain);
        ImageView img_player1 = findViewById(R.id.imageView_player1);
        ImageView img_player2 = findViewById(R.id.imageView_player2);
        TextView txt_winner = findViewById(R.id.textView_winner);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EndGameActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
