package com.example.orderapp.Fragment.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orderapp.Adapter.MainUserAdapter;
import com.example.orderapp.AdminUpdateDeleteActivity;
import com.example.orderapp.Model.Coffee;
import com.example.orderapp.Model.Order;
import com.example.orderapp.Model.User;
import com.example.orderapp.OrderCoffeeActivity;
import com.example.orderapp.R;
import com.example.orderapp.dal.SQLiteHelper;

import java.util.ArrayList;
import java.util.List;

public class MainUser extends Fragment implements MainUserAdapter.ItemListener{

    private EditText search;
    private Button btnSearch, btnCancle;
    private RecyclerView recycler;
    private MainUserAdapter adapter;
    private SQLiteHelper db;
    private User user;

    public MainUser() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_user, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        search = view.findViewById(R.id.main_search);
        btnSearch = view.findViewById(R.id.btnMainSearch);
        btnCancle = view.findViewById(R.id.btnMainCancle);
        recycler = view.findViewById(R.id.main_recycle);
        db = new SQLiteHelper(getContext());
        user =(User) getActivity().getIntent().getSerializableExtra("user");
        List<Coffee> list = db.getAllCoffee();
        adapter = new MainUserAdapter();
        adapter.setList(list);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recycler.setLayoutManager(manager);
        recycler.setAdapter(adapter);
        adapter.setItemListener(this);
        btnCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onResume();
            }
        });
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String key = search.getText().toString();
                List<Coffee> tmp = new ArrayList<>();
                for(int i = 0; i < list.size(); i++) {
                    if(list.get(i).getName().contains(key)) {
                        tmp.add(list.get(i));
                    }
                }
                adapter.setList(tmp);
            }
        });
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
        Toast.makeText(getContext(), "Đã chọn", Toast.LENGTH_LONG).show();
        Order order = new Order(user.getId(), cf.getId(), "", 1);
        Intent intent = new Intent(getActivity(), OrderCoffeeActivity.class);
        intent.putExtra("order", order);
        startActivity(intent);
    }
}
