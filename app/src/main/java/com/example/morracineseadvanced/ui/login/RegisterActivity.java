package com.example.morracineseadvanced.ui.login;

import android.content.Intent;
import android.os.AsyncTask;
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
import com.example.morracineseadvanced.IpAddress;

public class RegisterActivity extends AppCompatActivity {


    private AuthManager authManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

        IpAddress ip = new IpAddress();
        authManager = new AuthManager(ip.ipAddress);

        final EditText username = (EditText) findViewById(R.id.username);
        final EditText password = (EditText) findViewById(R.id.password);
        final EditText confirmPassword = (EditText) findViewById(R.id.confirmPassword);

        final TextView goBack = (TextView) findViewById(R.id.button_backToLogin);
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v1) {
                Intent launchActivity = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(launchActivity);
            }
        });

        final Button register = (Button) findViewById(R.id.register);
        register.setEnabled(true);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v1) {
                if (password.getText().toString().equals(confirmPassword.getText().toString())) {
                    String user = username.getText().toString();
                    String pass = password.getText().toString();
                    new RegisterTask().execute(user, pass);
                } else {
                    // Messaggio di errore
                }
            }
        });
    }

    private class RegisterTask extends AsyncTask<String, Void, Boolean> {
        @Override
        protected Boolean doInBackground(String... params) {
            String username = params[0];
            String password = params[1];
            return authManager.register(username, password);
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if (result) {
                Intent launchActivity = new Intent(RegisterActivity.this, com.example.morracineseadvanced.ui.login.LoginActivity.class);
                startActivity(launchActivity);
            } else {
                // Messaggio di errore da qualche parte
            }
        }
    }}