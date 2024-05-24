package com.example.morracineseadvanced;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.morracineseadvanced.model.AuthManager;
import com.example.morracineseadvanced.ui.login.RegisterActivity;

public class LoginActivity extends AppCompatActivity {

    private AuthManager authManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        final EditText username = (EditText)findViewById(R.id.username);
        final EditText password = (EditText)findViewById(R.id.password);

        final TextView register= (TextView)findViewById(R.id.Register);
        register.setOnClickListener(new View.OnClickListener(){
            @Override public void onClick(View v1){
                Intent launchActivity = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(launchActivity);
            }
        });

        final Button login= (Button)findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener(){
            @Override public void onClick(View v1){
                boolean result = authManager.login(username.getText().toString(),password.getText().toString());
                if(result){
                    Intent launchActivity = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(launchActivity);
                }else{
                    //Messaggio di errore da qualche parte
                }
            }
        });


    }

}