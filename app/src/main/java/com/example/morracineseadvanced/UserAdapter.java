package com.example.morracineseadvanced;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.morracineseadvanced.data.model.UserModel;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private List<UserModel> users;
    private Context context;
    private OnUserClickListener listener;
    private boolean isFriendList;

    public interface OnUserClickListener {
        void onSendRequestClick(UserModel user);
        void onCreateMatchClick(UserModel user);

    }

    public UserAdapter(Context context, List<UserModel> users, OnUserClickListener listener,boolean isFriendList) {
        this.context = context;
        this.users = users;
        this.listener = listener;
        this.isFriendList = isFriendList;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.user_item, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        UserModel user = users.get(position);
        holder.username.setText(user.getUsername());
        holder.elo.setText("Elo: " + user.getElo());
        if (isFriendList) {
            holder.sendRequestButton.setText("Create Match");
            holder.sendRequestButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onCreateMatchClick(user);
                    }
                }
            });
        } else {
            holder.sendRequestButton.setText("Send Request");
            holder.sendRequestButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onSendRequestClick(user);
                        new SendRequestTask(context, user.getUsername()).execute();
                    }
                }
            });
        }
    }


    @Override
    public int getItemCount() {
        return users.size();
    }

    public void updateUsers(List<UserModel> newUsers) {
        this.users = newUsers;
        notifyDataSetChanged();
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder {
        TextView username, elo;
        Button sendRequestButton;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.username);
            elo = itemView.findViewById(R.id.elo);
            sendRequestButton = itemView.findViewById(R.id.button_create_match);
        }
    }

    private static class SendRequestTask extends AsyncTask<Void, Void, Void> {
        @SuppressLint("StaticFieldLeak")
        private Context context;
        private String senderUsername;

        SendRequestTask(Context context, String senderUsername) {
            this.context = context;
            this.senderUsername = senderUsername;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                IpAddress ip = new IpAddress();
                String apiUrl = "http://" + ip.ipAddress + ":8080/newRequest";

                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
                String receiverUsername = preferences.getString("userId", null);

                String params = "senderUsername=" + receiverUsername + "&receiverUsername=" + senderUsername;

                URL url = new URL(apiUrl);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
                conn.getOutputStream().write(params.getBytes());

                int responseCode = conn.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                } else {
                }

                conn.disconnect();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
