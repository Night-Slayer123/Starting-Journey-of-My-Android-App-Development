package com.example.apiintegration;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.userHolder> {

    List<UserModel> users;
    MainActivity context;
    public UserAdapter(MainActivity context,List<UserModel> data) {
        this.users=data;
        this.context=context;
    }

    @NonNull
    @Override
    public userHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new userHolder(LayoutInflater.from(context).inflate(R.layout.item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull userHolder holder, int position) {
        holder.text.setText(users.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    class userHolder extends RecyclerView.ViewHolder {

        TextView text;
        public userHolder(@NonNull View itemView) {
            super(itemView);
            text= itemView.findViewById(R.id.textView);
        }
    }
}
