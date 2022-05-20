package com.example.orderapp.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orderapp.Model.Coffee;
import com.example.orderapp.Model.User;
import com.example.orderapp.R;

import java.util.ArrayList;
import java.util.List;

public class AdminCoffeeAdapter extends RecyclerView.Adapter<AdminCoffeeAdapter.ViewHolder>{

    private List<Coffee> list;
    private ItemListener itemListener;
    private int[] imgs = {R.drawable.cafecotdua,R.drawable.cafetrung,R.drawable.capuchino,
            R.drawable.chon,R.drawable.culi, R.drawable.den, R.drawable.moka, R.drawable.macchiato};
    public AdminCoffeeAdapter() {
        list = new ArrayList<>();
    }

    //lang nghe set item
    public void setItemListener(ItemListener itemListener) {
        this.itemListener = itemListener;
    }

    public void setList(List<Coffee> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public Coffee getItem(int position) {
        return list.get(position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_coffee_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Coffee c = list.get(position);
        holder.img.setImageResource(c.getImg());
        holder.name.setText(c.getName());
        holder.price.setText(c.getPrice());
        holder.des.setText(c.getDes());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    //bat su kien click chuot
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ImageView img;
        private TextView name, price, des;

        public ViewHolder(@NonNull View view) {
            super(view);
            img =view.findViewById(R.id.admin_coffee_img);
            name =view.findViewById(R.id.admin_coffee_name);
            price =view.findViewById(R.id.admin_coffee_price);
            des =view.findViewById(R.id.admin_coffee_des);
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
