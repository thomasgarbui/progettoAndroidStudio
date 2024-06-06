package com.example.morracineseadvanced;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.morracineseadvanced.data.model.FriendRequestModel;

import java.util.List;

public class FriendRequestAdapter extends RecyclerView.Adapter<FriendRequestAdapter.ViewHolder> {
    private Context context;
    private List<FriendRequestModel> friendRequestsList;
    private OnRequestActionListener onRequestActionListener;

    public FriendRequestAdapter(Context context, List<FriendRequestModel> friendRequestsList, OnRequestActionListener onRequestActionListener) {
        this.context = context;
        this.friendRequestsList = friendRequestsList;
        this.onRequestActionListener = onRequestActionListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_friend_request, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FriendRequestModel request = friendRequestsList.get(position);
        holder.senderUsername.setText(request.getSenderUsername());

        holder.acceptButton.setOnClickListener(v -> {
            if (onRequestActionListener != null) {
                onRequestActionListener.onAccept(request);
            }
        });

        holder.denyButton.setOnClickListener(v -> {
            if (onRequestActionListener != null) {
                onRequestActionListener.onDeny(request);
            }
        });
    }

    @Override
    public int getItemCount() {
        return friendRequestsList.size();
    }

    public void updateRequests(List<FriendRequestModel> requests) {
        this.friendRequestsList = requests;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView senderUsername;
        Button acceptButton, denyButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            senderUsername = itemView.findViewById(R.id.senderUsername);
            acceptButton = itemView.findViewById(R.id.button_accept);
            denyButton = itemView.findViewById(R.id.button_deny);
        }
    }

    public interface OnRequestActionListener {
        void onAccept(FriendRequestModel request);
        void onDeny(FriendRequestModel request);
    }
}
