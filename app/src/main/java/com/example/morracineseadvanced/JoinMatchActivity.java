package com.example.morracineseadvanced;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class JoinMatchActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_joinmatch);

        EditText et_code = findViewById(R.id.editTextNumber_code);
        Button btn_join = findViewById(R.id.button_join);
        TextView txt_error = findViewById(R.id.textView_codeError);
        Button btn_back = findViewById(R.id.button_backPreGame);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(JoinMatchActivity.this, PreGameActivity.class);
                startActivity(intent);
            }
        });

        btn_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(JoinMatchActivity.this, GameActivity.class);
                startActivity(intent);
            }
        });
    }
}
