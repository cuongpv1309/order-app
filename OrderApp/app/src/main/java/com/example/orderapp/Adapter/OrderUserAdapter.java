package com.example.orderapp.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orderapp.Model.Coffee;
import com.example.orderapp.Model.Order;
import com.example.orderapp.Model.Ordertmp;
import com.example.orderapp.R;

import java.util.ArrayList;
import java.util.List;

public class OrderUserAdapter extends RecyclerView.Adapter<OrderUserAdapter.ViewHolder>{


    private List<Ordertmp> list;
    private ItemListener itemListener;

    public OrderUserAdapter() {
        list = new ArrayList<>();
    }

    public void setItemListener(ItemListener itemListener) {
        this.itemListener = itemListener;
    }

    public void setList(List<Ordertmp> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public Ordertmp getItem(int position) {
        return list.get(position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Ordertmp o = list.get(position);
        holder.name.setText(o.getCoffee_name());
        holder.address.setText(o.getAddress());
        holder.price.setText(o.getQuanity());
        holder.total.setText(o.getTotal() );
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView name, address, price, total;

        public ViewHolder(@NonNull View view) {
            super(view);
            name =view.findViewById(R.id.item_name);
            address =view.findViewById(R.id.item_address);
            price =view.findViewById(R.id.item_quanity);
            total =view.findViewById(R.id.item_total);
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
