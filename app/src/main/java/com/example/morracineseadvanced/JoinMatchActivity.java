package com.example.morracineseadvanced;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

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
                String playerTwoUsername = PreferenceManager.getDefaultSharedPreferences(com.example.morracineseadvanced.JoinMatchActivity.this).getString("userId", null);
                joinMatch(playerTwoUsername,et_code.getText().toString());
            }
        });
    }

    @SuppressLint("StaticFieldLeak")
    private void joinMatch(String playerUsername,String id) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.CUPCAKE) {
            new AsyncTask<Void, Void, Boolean>() {
                @Override
                protected Boolean doInBackground(Void... voids) {
                    try {
                        IpAddress ip = new IpAddress();
                        String pUsername = URLEncoder.encode(playerUsername, "UTF-8");
                        String ID = URLEncoder.encode(id, "UTF-8");
                        URL url = new URL("http://" + ip.ipAddress + ":8080/joinMatch?playerUsername="+pUsername+ "&id=" + ID);
                        Log.d(TAG, "Request URL: " + url);

                        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                        conn.setRequestMethod("PUT");
                        conn.setRequestProperty("Content-Type", "application/json");

                        int responseCode = conn.getResponseCode();
                        Log.d(TAG, "Response Code: " + responseCode);

                        if (responseCode == HttpURLConnection.HTTP_OK) {
                            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                            StringBuilder response = new StringBuilder();
                            String inputLine;
                            while ((inputLine = in.readLine()) != null) {
                                response.append(inputLine);
                            }
                            in.close();
                            Log.d(TAG, "Response: " + response.toString());
                            return true;

                        } else {
                            return false;
                        }
                    } catch (Exception e) {
                        Log.e(TAG, "Exception: ", e);
                        return false;
                    }
                }

                @Override
                protected void onPostExecute(Boolean success) {
                    if (success) {
                        Intent intent = new Intent(JoinMatchActivity.this, GameActivity.class);
                        startActivity(intent);
                    } else {
                    }
                }
            }.execute();
        }
    }
}
