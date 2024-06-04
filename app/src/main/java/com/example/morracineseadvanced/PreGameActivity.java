package com.example.morracineseadvanced;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.morracineseadvanced.data.model.FriendRequestModel;
import com.example.morracineseadvanced.data.model.MatchModel;
import com.example.morracineseadvanced.data.model.UserModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    //METODI NON COMPLETI!!! VEDI onPostExecute dentro ciascun metodo

    @SuppressLint("StaticFieldLeak")
    private void newMatch(String playerOneUsername) {
        new AsyncTask<Void, Void, Boolean>() {
            @Override
            protected Boolean doInBackground(Void... voids) {
                try {
                    IpAddress ip = new IpAddress();
                    String p1 = URLEncoder.encode(playerOneUsername, "UTF-8");
                    URL url = new URL("http://" + ip.ipAddress + ":8080/newMatch?playerOneUsername="+p1);
                    Log.d(TAG, "Request URL: " + url);

                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
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

            @Override //quello che deve accadere dopo doInBackground
            protected void onPostExecute(Boolean success) {
                if (success) {
                } else {
                }
            }
        }.execute();
    }

    @SuppressLint("StaticFieldLeak")
    private void selectMove(String playerUsername,String move,String playerNumber) {
        new AsyncTask<Void, Void, Boolean>() {
            @Override
            protected Boolean doInBackground(Void... voids) {
                try {
                    IpAddress ip = new IpAddress();
                    String pUsername = URLEncoder.encode(playerUsername, "UTF-8");
                    String m = URLEncoder.encode(move, "UTF-8");
                    String pNumber = URLEncoder.encode(playerNumber, "UTF-8");
                    URL url = new URL("http://" + ip.ipAddress + ":8080/selectMove?playerUsername="+pUsername+ "&move=" + m + "&playerNumber=" + pNumber);
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

            @Override //quello che deve accadere dopo doInBackground
            protected void onPostExecute(Boolean success) {
                if (success) {
                } else {
                }
            }
        }.execute();
    }
    @SuppressLint("StaticFieldLeak")
    private void joinMatch(String playerUsername,String id) {
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

            @Override //quello che deve accadere dopo doInBackground
            protected void onPostExecute(Boolean success) {
                if (success) {
                } else {
                }
            }
        }.execute();
    }

    //mettere paramentri giusti e sistemare da url in giù
    @SuppressLint("StaticFieldLeak")
    private void getFriendRequests(String username, PreGameActivity.OnRequestsFetchedListener listener) {
        new AsyncTask<String, Void, List<MatchModel>>() {
            @Override
            protected List<MatchModel> doInBackground(String... params) {
                try {
                    IpAddress ip = new IpAddress();
                    String encodedUsername = URLEncoder.encode(params[0], "UTF-8");
                    URL url = new URL("http://" + ip.ipAddress + ":8080/getMatches?receiverUsername=" + encodedUsername);
                    Log.d(TAG, "Request URL: " + url);

                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
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

                        List<MatchModel> result = new ArrayList<>();
                        JSONArray jsonArray = new JSONArray(response.toString());
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);

                            String id = jsonObject.getString("id");
                            String playerOneUsername = jsonObject.getString("playerOneUsername");
                            String playerTwoUsername = jsonObject.getString("playerTwoUsername");
                            String playerOneMove = jsonObject.getString("playerOneMove");
                            String playerTwoMove = jsonObject.getString("playerTwoMove");
                            String winnerUsername = jsonObject.getString("winnerUsername");

                            MatchModel match = new MatchModel(id,playerOneUsername,playerTwoUsername,playerOneMove,playerTwoMove,winnerUsername);
                            result.add(match);
                        }
                        return result;
                    } else {
                        return Collections.emptyList();
                    }
                } catch (Exception e) {
                    Log.e(TAG, "Exception: ", e);
                    return Collections.emptyList();
                }
            }

            @Override
            protected void onPostExecute(List<MatchModel> requests) {
                if (listener != null) {
                    listener.onRequestsFetched(requests);
                }
                else{
                    Toast.makeText(getApplicationContext(), "No friend requests found", Toast.LENGTH_SHORT).show();
                }
            }
        }.execute(username);
    }

    public interface OnRequestsFetchedListener {
        void onRequestsFetched(List<MatchModel> results);
    }
}
