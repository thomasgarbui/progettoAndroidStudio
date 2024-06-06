package com.example.morracineseadvanced;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;

import android.os.Handler;
import android.os.Looper;
import android.os.StrictMode;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import androidx.viewpager2.widget.ViewPager2;

import com.example.morracineseadvanced.data.model.MatchModel;

import org.json.JSONObject;

public class GameActivity extends AppCompatActivity {

    private static final String TAG = "ClientActivity";

    private TextView messageTextView;
    private ViewPager2 viewPager;
    private ImagePagerAdapter adapter;
    private Interactions interactions;
    private TextView opponentTextView;
    private TextView txtId;
    private MatchModel match;
    private Handler handler;
    private Runnable checkPlayerTwoRunnable;
    private Runnable checkVictory;
    private Button buttonSelect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        interactions = new Interactions();
        viewPager = findViewById(R.id.viewPager_rules);

        match = new MatchModel(null, null, null, null, null, null);
        List<Integer> imageList = new ArrayList<>();
        imageList.add(R.drawable.rock);
        imageList.add(R.drawable.fire);
        imageList.add(R.drawable.scissors);
        imageList.add(R.drawable.snake);
        imageList.add(R.drawable.human);
        imageList.add(R.drawable.tree);
        imageList.add(R.drawable.wolf);
        imageList.add(R.drawable.sponge);
        imageList.add(R.drawable.paper);
        imageList.add(R.drawable.air);
        imageList.add(R.drawable.water);
        imageList.add(R.drawable.dragon);
        imageList.add(R.drawable.devil);
        imageList.add(R.drawable.lightning);
        imageList.add(R.drawable.gun);

        adapter = new ImagePagerAdapter(this, imageList);
        viewPager.setAdapter(adapter);
        buttonSelect = findViewById(R.id.button_select);

        txtId = findViewById(R.id.txt_Id);

        String playerOne = getIntent().getStringExtra("playerOneUsername");
        String userId = PreferenceManager.getDefaultSharedPreferences(com.example.morracineseadvanced.GameActivity.this).getString("userId", null);
//        if (Objects.equals(playerOne, userId)) {
//            buttonSelect.setEnabled(false);
//        }
        buttonSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentPosition = viewPager.getCurrentItem();
//                String message = "Posizione della foto: " + currentPosition;
//                showToast(message);

                viewPager.setUserInputEnabled(false);

                Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                if (vibrator != null) {
                    vibrator.vibrate(200);
                }
                buttonSelect.setEnabled(false);
                TextView txtSelectedMove = findViewById(R.id.txtSelectedMove);
                txtSelectedMove.setText("You choose " + interactions.moves[currentPosition]);
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(com.example.morracineseadvanced.GameActivity.this);
                SharedPreferences.Editor editor = preferences.edit();
                if (Objects.equals(userId, playerOne)) {

                    selectMove(userId, interactions.moves[currentPosition], "One");

                    editor.putString("playerOneMove", interactions.moves[currentPosition]);
                    editor.apply();
                } else {
                    selectMove(userId, interactions.moves[currentPosition], "Two");

                    editor.putString("playerTwoMove", interactions.moves[currentPosition]);
                    editor.apply();
                }
            }
        });

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        handler = new Handler(Looper.getMainLooper());
        getLatestMatch(userId);
//        checkPlayerTwoInBackground(userId, buttonSelect);
        checkVictory(userId);
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void checkPlayerTwoInBackground(String userId, Button btnSelect) {
        checkPlayerTwoRunnable = new Runnable() {
            @Override
            public void run() {
                getLatestMatch(userId);
                if (match.getPlayerTwoUsername() == null || match.getPlayerTwoUsername().isEmpty()) {
                    Log.d(TAG, "Player two is not yet joined");
                    handler.postDelayed(this, 1000);
                } else {
                    Log.d(TAG, "Player two has joined: " + match.getPlayerTwoUsername());
                    btnSelect.setEnabled(true);
                }
            }
        };
        handler.post(checkPlayerTwoRunnable);
    }

    private void checkVictory(String userId) {
        checkVictory = new Runnable() {
            @Override
            public void run() {
                getLatestMatch(userId);
                if (match.getWinnerUsername() == null || match.getWinnerUsername().isEmpty()) {
                    handler.postDelayed(this, 1000);
                } else {
                    Log.d(TAG, "Winner detected: " + match.getWinnerUsername());
                    Intent intent = new Intent(GameActivity.this, EndGameActivity.class);
                    intent.putExtra("winnerUsername", match.getWinnerUsername());
                    intent.putExtra("playerOneUsername", match.getPlayerOneUsername());
                    intent.putExtra("playerTwoUsername", match.getPlayerTwoUsername());
                    startActivity(intent);
                    finish();
                }
            }
        };
        handler.post(checkVictory);
    }


    @SuppressLint("StaticFieldLeak")
    private void getLatestMatch(String username) {
        new AsyncTask<String, Void, MatchModel>() {
            @Override
            protected MatchModel doInBackground(String... params) {
                try {
                    IpAddress ip = new IpAddress();
                    String encodedUsername = URLEncoder.encode(params[0], "UTF-8");
                    URL url = new URL("http://" + ip.ipAddress + ":8080/getLatestMatch?username=" + encodedUsername);
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

                        JSONObject jsonObject = new JSONObject(response.toString());
                        String id = jsonObject.getString("id");
                        String playerOneUsername = jsonObject.getString("playerOneUsername");
                        String playerTwoUsername = jsonObject.isNull("playerTwoUsername") ? null : jsonObject.getString("playerTwoUsername");
                        String playerOneMove = jsonObject.isNull("playerOneMove") ? null : jsonObject.getString("playerOneMove");
                        String playerTwoMove = jsonObject.isNull("playerTwoMove") ? null : jsonObject.getString("playerTwoMove");
                        String winnerUsername = jsonObject.isNull("winnerUsername") ? null : jsonObject.getString("winnerUsername");

                        Log.d(TAG, "Parsed JSON: id=" + id + ", playerOneUsername=" + playerOneUsername + ", playerTwoUsername=" + playerTwoUsername);

                        return new MatchModel(id, playerOneUsername, playerTwoUsername, playerOneMove, playerTwoMove, winnerUsername);
                    } else {
                        Log.e(TAG, "Server returned non-OK status: " + responseCode);
                        return null;
                    }
                } catch (Exception e) {
                    Log.e(TAG, "Exception: ", e);
                    return null;
                }
            }

            @Override
            protected void onPostExecute(MatchModel result) {
                if (result != null) {
                    match = result;
                    Log.d(TAG, "Match updated: " + match.getPlayerTwoUsername());
                    txtId.setText(match.getId());

                    if (match.getPlayerTwoUsername() == null) {
                        Log.d(TAG, "Player two is still null.");
                    } else {
                        Log.d(TAG, "Player two is now: " + match.getPlayerTwoUsername());
                    }

                } else {
                    Log.d(TAG, "No match found or error in response");
                }
            }
        }.execute(username);
    }



    @SuppressLint("StaticFieldLeak")
    private void selectMove(String playerUsername, String move, String playerNumber) {
        new AsyncTask<Void, Void, Boolean>() {
            @Override
            protected Boolean doInBackground(Void... voids) {
                try {
                    IpAddress ip = new IpAddress();
                    String pUsername = URLEncoder.encode(playerUsername, "UTF-8");
                    String m = URLEncoder.encode(move, "UTF-8");
                    String pNumber = URLEncoder.encode(playerNumber, "UTF-8");
                    URL url = new URL("http://" + ip.ipAddress + ":8080/selectMove?playerUsername=" + pUsername + "&move=" + m + "&playerNumber=" + pNumber);
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
                        Log.e(TAG, "Server returned non-OK status: " + responseCode);
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
                    Log.d(TAG, "Move selected successfully");
                } else {
                    Log.e(TAG, "Failed to select move");
                }
            }
        }.execute();
    }
}
