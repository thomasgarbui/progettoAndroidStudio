package com.example.morracineseadvanced;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.morracineseadvanced.model.AuthManager;


public class LoginActivity extends AppCompatActivity {
    private AuthManager authManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        authManager = new AuthManager(new IpAddress().ipAddress);

        final EditText username = findViewById(R.id.username);
        final EditText password = findViewById(R.id.confirmPassword);
        final Button loginButton = findViewById(R.id.login);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Save user ID in SharedPreferences

                new LoginTask(username.getText().toString(), password.getText().toString()).execute();
            }
        });
    }

    @SuppressLint("StaticFieldLeak")
    private class LoginTask extends AsyncTask<Void, Void, Boolean> {
        private String username, password;

        LoginTask(String username, String password) {
            this.username = username;
            this.password = password;
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            return authManager.login(username, password);
        }

        @Override
        protected void onPostExecute(Boolean success) {
            if (success) {


                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            } else {
                // Show error message
            }
        }
    }
}
