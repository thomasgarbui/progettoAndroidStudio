package com.example.morracineseadvanced;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PreGameActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pregame);

        Button btn_back = findViewById(R.id.button_backMain);
        Button btn_newMatch = findViewById(R.id.button_newMatch);
        Button btn_joinMatch = findViewById(R.id.button_joinMatch);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PreGameActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btn_newMatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PreGameActivity.this, GameActivity.class);
                startActivity(intent);
            }
        });

        btn_joinMatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PreGameActivity.this, JoinMatchActivity.class);
                startActivity(intent);
            }
        });
    };
}
