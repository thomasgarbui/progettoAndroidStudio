package com.example.morracineseadvanced.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.morracineseadvanced.LoginActivity;
import com.example.morracineseadvanced.R;
import com.example.morracineseadvanced.model.AuthManager;

public class RegisterActivity extends AppCompatActivity {

    private AuthManager authManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

        final EditText username = (EditText)findViewById(R.id.username);
        final EditText password = (EditText)findViewById(R.id.password);
        final EditText confirmPassword = (EditText)findViewById(R.id.confirmPassword);

        final TextView goBack= (TextView)findViewById(R.id.goBack);
        goBack.setOnClickListener(new View.OnClickListener(){
            @Override public void onClick(View v1){
                Intent launchActivity = new Intent(RegisterActivity.this, com.example.morracineseadvanced.LoginActivity.class);
                startActivity(launchActivity);
            }
        });

        final Button register= (Button)findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener(){
            @Override public void onClick(View v1){
                if(password.getText().toString().equals(confirmPassword.getText().toString())){
                    boolean result = authManager.register(username.getText().toString(),password.getText().toString());
                    if(result){
                        Intent launchActivity = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(launchActivity);
                    }else{
                        //Messaggio di errore da qualche parte
                    }
                }else{
                    //Messaggio di errore
                }

            }
        });


    }

}