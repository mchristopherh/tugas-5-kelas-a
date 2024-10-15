package com.example.room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.room.entity.User;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<User> userList;

    public RecyclerViewAdapter(List<User> userList) {
        this.userList = userList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        User user = userList.get(position);
        holder.textViewName.setText(user.getName());
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public void setUsers(List<User> users) {
        this.userList = users;
        notifyDataSetChanged();
    }

    public User getUserAtPosition(int position) {
        return userList.get(position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewId, textViewName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewName);
        }
    }
}
