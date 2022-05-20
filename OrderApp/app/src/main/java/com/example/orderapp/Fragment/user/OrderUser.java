package com.example.orderapp.Fragment.user;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orderapp.Adapter.OrderUserAdapter;
import com.example.orderapp.Model.Coffee;
import com.example.orderapp.Model.Order;
import com.example.orderapp.Model.Ordertmp;
import com.example.orderapp.Model.User;
import com.example.orderapp.R;
import com.example.orderapp.dal.SQLiteHelper;

import java.util.ArrayList;
import java.util.List;

public class OrderUser extends Fragment implements OrderUserAdapter.ItemListener{

    private RecyclerView recyclerView;
    private SQLiteHelper db;
    private User user;
    private OrderUserAdapter adapter;
//    private List<Ordertmp> tmp;
    public OrderUser() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.order_user, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.order_recycle);
        user =(User) getActivity().getIntent().getSerializableExtra("user");
        db = new SQLiteHelper(getContext());
        List<Order> list = db.getAllOrderById(user.getId());
        List<Coffee> listcf = db.getAllCoffee();
        List<Ordertmp> tmp = new ArrayList<>();
        for(int i = 0; i < list.size(); i++) {
            for(int j = 0; j < listcf.size(); j++) {
                if(list.get(i).getCoffee_id() == listcf.get(j).getId()) {
                    Ordertmp o = new Ordertmp();
                    o.setAddress(list.get(i).getAddress());
                    o.setCoffee_name(listcf.get(j).getName());
                    o.setQuanity(Integer.toString(list.get(i).getQuanity()));
                    o.setTotal(Integer.toString(list.get(i).getQuanity()*Integer.parseInt(listcf.get(j).getPrice())));
                    tmp.add(o);
                }
            }
        }

        adapter = new OrderUserAdapter();
        adapter.setList(tmp);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        adapter.setItemListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        List<Order> list = db.getAllOrderById(user.getId());
        List<Coffee> listcf = db.getAllCoffee();
        List<Ordertmp> tmp = new ArrayList<>();
        for(int i = 0; i < list.size(); i++) {
            for(int j = 0; j < listcf.size(); j++) {
                if(list.get(i).getCoffee_id() == listcf.get(j).getId()) {
                    Ordertmp o = new Ordertmp();
                    o.setAddress(list.get(i).getAddress());
                    o.setCoffee_name(listcf.get(j).getName());
                    o.setQuanity(Integer.toString(list.get(i).getQuanity()));
                    o.setTotal(Integer.toString(list.get(i).getQuanity()*Integer.parseInt(listcf.get(j).getPrice())));
                    tmp.add(o);
                }
            }
        }
        adapter.setList(tmp);
    }

    @Override
    public void onItemClick(View view, int position) {
        Ordertmp o = adapter.getItem(position);
        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        builder.setTitle("Thong bao");
        builder.setMessage("Bạn đã nhận được hàng ? ");
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
