package com.example.orderapp.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orderapp.Model.User;
import com.example.orderapp.R;

import java.util.ArrayList;
import java.util.List;

public class AdminUserAdapter extends RecyclerView.Adapter<AdminUserAdapter.ViewHolder>{

    private List<User> list;
    private ItemListener itemListener;

    public AdminUserAdapter() {
        list = new ArrayList<>();
    }

    //lang nghe set item
    public void setItemListener(ItemListener itemListener) {
        this.itemListener = itemListener;
    }

    public void setList(List<User> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public User getItem(int position) {
        return list.get(position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_user_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        User u = list.get(position);
        holder.name.setText(u.getName());
        holder.username.setText(u.getUsername());
        holder.password.setText(u.getPassword());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    //bat su kien click chuot
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView name, username, password;

        public ViewHolder(@NonNull View view) {
            super(view);
            name =view.findViewById(R.id.admin_user_name);
            username =view.findViewById(R.id.admin_user_username);
            password =view.findViewById(R.id.admin_user_password);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(itemListener != null) {
                itemListener.onItemClick(view, getAdapterPosition());
            }
        }
    }

    public interface ItemListener{
        //truyen vao view va vi tri click
        void onItemClick(View view, int position);
    }

}
