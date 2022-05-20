package com.example.orderapp.Fragment.admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orderapp.Adapter.AdminCoffeeAdapter;
import com.example.orderapp.Adapter.AdminUserAdapter;
import com.example.orderapp.AdminActivity;
import com.example.orderapp.AdminAddCoffeeActivity;
import com.example.orderapp.AdminUpdateDeleteActivity;
import com.example.orderapp.Model.Coffee;
import com.example.orderapp.Model.User;
import com.example.orderapp.R;
import com.example.orderapp.dal.SQLiteHelper;

import java.util.List;

public class fragment_coffee extends Fragment implements AdminCoffeeAdapter.ItemListener{

    private RecyclerView recyclerView;
    private AdminCoffeeAdapter adapter;
    private Button btnAdd;
    private SQLiteHelper db;

    public fragment_coffee() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.admin_coffee, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recycle_admin_coffee);
        adapter = new AdminCoffeeAdapter();
        db = new SQLiteHelper(getContext());
        btnAdd = view.findViewById(R.id.btnAddCoffee);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), AdminAddCoffeeActivity.class);
                startActivity(intent);
            }
        });
        List<Coffee> list = db.getAllCoffee();
        adapter = new AdminCoffeeAdapter();
        adapter.setList(list);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        adapter.setItemListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        List<Coffee> list = db.getAllCoffee();
        adapter.setList(list);
    }

    @Override
    public void onItemClick(View view, int position) {
        Coffee cf = adapter.getItem(position);
        Intent intent = new Intent(getActivity(), AdminUpdateDeleteActivity.class);
        intent.putExtra("cf", cf);
        startActivity(intent);
    }
}
