package com.example.morracineseadvanced;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
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
import com.example.morracineseadvanced.data.model.UserModel;

public class FriendsActivity extends AppCompatActivity {
    private static final String TAG = "FriendsActivity";
    private RecyclerView recyclerView;
    private UserAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_friends);

        Button btn_back = findViewById(R.id.button_backToMain);
        Button btn_search = findViewById(R.id.button_searchPlayer);
        EditText txtSearchFriend = findViewById(R.id.editTextText_playerName);
        recyclerView = findViewById(R.id.recyclerView_friends);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FriendsActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = txtSearchFriend.getText().toString();
                searchUsers(username, new OnUsersFetchedListener() {
                    @Override
                    public void onUsersFetched(List<UserModel> results) {
                        if (!results.isEmpty()) {
                            adapter = new UserAdapter(FriendsActivity.this, results, new UserAdapter.OnUserClickListener() {
                                @Override
                                public void onSendRequestClick(UserModel user) {
                                    // Handle send request click
                                    Log.d(TAG, "Send request to: " + user.getUsername());
                                }
                            });
                            recyclerView.setAdapter(adapter);
                        } else {
                            Log.d(TAG, "No users found");
                        }
                    }
                });
            }
        });
    }

    @SuppressLint("StaticFieldLeak")
    private void searchUsers(String username, OnUsersFetchedListener listener) {
        new AsyncTask<String, Void, List<UserModel>>() {
            @Override
            protected List<UserModel> doInBackground(String... params) {
                try {
                    IpAddress ip = new IpAddress();
                    String encodedUsername = URLEncoder.encode(params[0], "UTF-8");
                    URL url = new URL("http://" + ip.ipAddress + ":8080/getUsers?username=" + encodedUsername);
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

                        JSONArray jsonArray = new JSONArray(response.toString());
                        List<UserModel> userList = new ArrayList<>();
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            String userName = jsonObject.getString("username");
                            String elo = jsonObject.getString("elo");
                            String password = jsonObject.getString("password");

                            UserModel user = new UserModel(userName, password, elo);
                            userList.add(user);
                        }
                        return userList;
                    } else {
                        return Collections.emptyList();
                    }
                } catch (Exception e) {
                    Log.e(TAG, "Exception: ", e);
                    return Collections.emptyList();
                }
            }

            @Override
            protected void onPostExecute(List<UserModel> userList) {
                if (listener != null) {
                    listener.onUsersFetched(userList);
                }
            }
        }.execute(username);
    }

    public interface OnUsersFetchedListener {
        void onUsersFetched(List<UserModel> users);
    }
}
