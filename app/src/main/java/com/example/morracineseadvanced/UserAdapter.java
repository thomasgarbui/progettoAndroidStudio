package com.example.morracineseadvanced;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
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
import com.example.morracineseadvanced.IpAddress;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private List<UserModel> userList;
    private Context context;
    private OnUserClickListener listener;

    public interface OnUserClickListener {
        void onSendRequestClick(UserModel user);
    }

    public UserAdapter(Context context, List<UserModel> userList, OnUserClickListener listener) {
        this.context = context;
        this.userList = userList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.user_item, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        IpAddress ip = new IpAddress();
        UserModel user = userList.get(position);
        holder.username.setText(user.getUsername());
        holder.elo.setText("Elo: " + user.getElo());
        holder.sendRequestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AsyncTask<Void, Void, Void>() {
                    @SuppressLint("StaticFieldLeak")
                    @Override
                    protected Void doInBackground(Void... voids) {
                        try {
                            String apiUrl = "http://"+ip.ipAddress+":8080/newRequest";
                            String senderUsername = user.getUsername();
                            String receiverUsername = user.getUsername();

                            String params = "senderUsername=" + senderUsername + "&receiverUsername=" + receiverUsername;

                            @SuppressLint("StaticFieldLeak") URL url = new URL(apiUrl);
                            @SuppressLint("StaticFieldLeak") HttpURLConnection conn = (HttpURLConnection) url.openConnection();
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
                }.execute();
            }
        });
    }


    @Override
    public int getItemCount() {
        return userList.size();
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder {
        TextView username, elo;
        Button sendRequestButton;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.username);
            elo = itemView.findViewById(R.id.elo);
            sendRequestButton = itemView.findViewById(R.id.send_request_button);
        }
    }
}
