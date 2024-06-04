package com.example.morracineseadvanced;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.morracineseadvanced.data.model.UserModel;
import com.example.morracineseadvanced.data.model.FriendRequestModel;

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
import java.util.Objects;

public class FriendsActivity extends AppCompatActivity {
    private static final String TAG = "FriendsActivity";
    private RecyclerView recyclerView;
    private UserAdapter userAdapterSearch;
    private UserAdapter userAdapterFriends;
    private FriendRequestAdapter friendRequestAdapter;
    private List<UserModel> usersList;
    private List<FriendRequestModel> friendRequestsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_friends);

        Button btn_back = findViewById(R.id.button_backToMain);
        Button btn_search = findViewById(R.id.button_searchPlayer);
        Button btn_friendsList = findViewById(R.id.button_FriendsList);
        Button btn_friendsRequests = findViewById(R.id.button_FriendsRequests);
        EditText txtSearchFriend = findViewById(R.id.editTextText_playerName);
        recyclerView = findViewById(R.id.recyclerView_friends);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        usersList = new ArrayList<>();
        friendRequestsList = new ArrayList<>();
        userAdapterSearch = new UserAdapter(this, usersList, new UserAdapter.OnUserClickListener() {
            @Override
            public void onSendRequestClick(UserModel user) {
                Log.d(TAG, "Send request to: " + user.getUsername());
            }

            @Override
            public void onCreateMatchClick(UserModel user) {

            }
        }, false);
        userAdapterFriends = new UserAdapter(this, usersList, new UserAdapter.OnUserClickListener() {
            @Override
            public void onSendRequestClick(UserModel user) {

            }

            @Override
            public void onCreateMatchClick(UserModel user) {
                Log.d(TAG, "Create match with: " + user.getUsername());
            }
        },true);
        friendRequestAdapter = new FriendRequestAdapter(this, friendRequestsList, new FriendRequestAdapter.OnRequestActionListener() {
            @Override
            public void onAccept(FriendRequestModel request) {
                handleFriendRequest(request, "accepted");
            }

            @Override
            public void onDeny(FriendRequestModel request) {
                handleFriendRequest(request, "rejected");
            }
        });
        recyclerView.setAdapter(userAdapterSearch);

        btn_back.setOnClickListener(v -> {
            Intent intent = new Intent(FriendsActivity.this, MainActivity.class);
            startActivity(intent);
        });

        btn_friendsList.setOnClickListener(v -> {
            String username = getUsernameFromSharedPreferences();
            getFriendsList(username, results -> {
                if (results != null && !results.isEmpty()) {
                    recyclerView.setAdapter(userAdapterFriends);
                    userAdapterFriends.updateUsers(results);
                } else {
                    Log.d(TAG, "No friends found");
                }
            });
        });

        btn_friendsRequests.setOnClickListener(v -> {
            String username = getUsernameFromSharedPreferences();
            getFriendRequests(username, results -> {
                if (results != null && !results.isEmpty()) {
                    recyclerView.setAdapter(friendRequestAdapter);
                    friendRequestAdapter.updateRequests(results);
                } else {
                    Log.d(TAG, "No friend requests found");
                }
            });
        });


        btn_search.setOnClickListener(v -> {
            String username = txtSearchFriend.getText().toString();
            searchUsers(username, results -> {
                if (results != null && !results.isEmpty()) {
                    recyclerView.setAdapter(userAdapterSearch);
                    userAdapterSearch.updateUsers(results);
                } else {
                    Log.d(TAG, "No users found");
                }
            });
        });


    }

    private String getUsernameFromSharedPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(this).getString("userId", null);
    }

    @SuppressLint("StaticFieldLeak")
    private void getFriendsList(String username, OnUsersFetchedListener listener) {
        new AsyncTask<String, Void, List<UserModel>>() {
            @Override
            protected List<UserModel> doInBackground(String... params) {
                try {
                    IpAddress ip = new IpAddress();
                    String encodedUsername = URLEncoder.encode(params[0], "UTF-8");
                    URL url = new URL("http://" + ip.ipAddress + ":8080/getFriends?username=" + encodedUsername);
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

                        List<UserModel> result = new ArrayList<>();
                        JSONArray jsonArray = new JSONArray(response.toString());
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            String username = jsonObject.getString("username");
                            String password = jsonObject.getString("password");
                            String elo = jsonObject.getString("elo");
                            UserModel user = new UserModel(username, password, elo);
                            result.add(user);
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
            protected void onPostExecute(List<UserModel> users) {
                if (listener != null) {
                    listener.onUsersFetched(users);
                }
                else{
                    Toast.makeText(getApplicationContext(), "You don't have any friends", Toast.LENGTH_SHORT).show();
                }
            }
        }.execute(username);
    }

    @SuppressLint("StaticFieldLeak")
    private void searchUsers(String username, OnUsersFetchedListener listener) {
        new AsyncTask<String, Void, List<UserModel>>() {
            @Override
            protected List<UserModel> doInBackground(String... params) {
                try {
                    IpAddress ip = new IpAddress();
                    String encodedUsername = URLEncoder.encode(params[0], "UTF-8");
                    URL url = new URL("http://" + ip.ipAddress + ":8080/getUser?username=" + encodedUsername);
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

                        String userName = jsonObject.getString("username");
                        String password = jsonObject.getString("password");
                        String elo = jsonObject.getString("elo");
                        UserModel result = new UserModel(userName, password, elo);
                        List<UserModel> resultList = new ArrayList<>();
                        resultList.add(result);
                        return resultList;
                    } else {
                        return Collections.emptyList();
                    }
                } catch (Exception e) {
                    Log.e(TAG, "Exception: ", e);
                    return Collections.emptyList();
                }
            }

            @Override
            protected void onPostExecute(List<UserModel> users) {
                if (listener != null) {
                    listener.onUsersFetched(users);
                }
                else{
                    Toast.makeText(getApplicationContext(), "No user found", Toast.LENGTH_SHORT).show();
                }
            }
        }.execute(username);
    }

    @SuppressLint("StaticFieldLeak")
    private void getFriendRequests(String username, OnRequestsFetchedListener listener) {
        new AsyncTask<String, Void, List<FriendRequestModel>>() {
            @Override
            protected List<FriendRequestModel> doInBackground(String... params) {
                try {
                    IpAddress ip = new IpAddress();
                    String encodedUsername = URLEncoder.encode(params[0], "UTF-8");
                    URL url = new URL("http://" + ip.ipAddress + ":8080/getRequests?senderUsername=" + encodedUsername);
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

                        List<FriendRequestModel> result = new ArrayList<>();
                        JSONArray jsonArray = new JSONArray(response.toString());
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            String senderUsername = jsonObject.getString("senderUsername");
                            String receiverUsername = jsonObject.getString("receiverUsername");
                            String status = jsonObject.getString("status");
                            FriendRequestModel request = new FriendRequestModel(senderUsername, receiverUsername,status);
                            result.add(request);
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
            protected void onPostExecute(List<FriendRequestModel> requests) {
                if (listener != null) {
                    listener.onRequestsFetched(requests);
                }
                else{
                    Toast.makeText(getApplicationContext(), "No friend requests found", Toast.LENGTH_SHORT).show();
                }
            }
        }.execute(username);
    }

    @SuppressLint("StaticFieldLeak")
    private void handleFriendRequest(FriendRequestModel request, String action) {
        new AsyncTask<Void, Void, Boolean>() {
            @Override
            protected Boolean doInBackground(Void... voids) {
                try {
                    IpAddress ip = new IpAddress();
                    String senderUsername = URLEncoder.encode(request.getSenderUsername(), "UTF-8");
                    String receiverUsername = URLEncoder.encode(request.getReceiverUsername(), "UTF-8");
                    URL url = new URL("http://" + ip.ipAddress + ":8080/modifyRequest?senderUsername=" + senderUsername + "&receiverUsername=" + receiverUsername + "&state=" + action);
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
                    Log.d(TAG, "Request " + action + " successful for: " + request.getSenderUsername());
                    // Update the friend requests list
                    String username = getUsernameFromSharedPreferences();
                    getFriendRequests(username, results -> {
                        if (results != null && !results.isEmpty()) {
                            friendRequestAdapter.updateRequests(results);
                            Toast.makeText(getApplicationContext(), "sent!", Toast.LENGTH_SHORT).show();
                        } else {
                            Log.d(TAG, "No friend requests found");
                            Toast.makeText(getApplicationContext(), "No friend requests found", Toast.LENGTH_SHORT).show();

                        }
                    });
                } else {
                    Log.d(TAG, "Request " + action + " failed for: " + request.getSenderUsername());
                }
            }
        }.execute();
    }

    public interface OnUsersFetchedListener {
        void onUsersFetched(List<UserModel> results);
    }

    public interface OnRequestsFetchedListener {
        void onRequestsFetched(List<FriendRequestModel> results);
    }
}
