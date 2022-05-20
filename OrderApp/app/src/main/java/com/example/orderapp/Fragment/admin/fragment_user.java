package com.example.orderapp.Fragment.admin;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.orderapp.Adapter.AdminUserAdapter;
import com.example.orderapp.Model.User;
import com.example.orderapp.R;
import com.example.orderapp.dal.SQLiteHelper;

import java.util.List;

public class fragment_user extends Fragment implements AdminUserAdapter.ItemListener{

    private RecyclerView recyclerView;
    private SQLiteHelper db;
    private AdminUserAdapter adapter;

    public fragment_user() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.admin_user, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recycle_admin_user);
        adapter = new AdminUserAdapter();
        db = new SQLiteHelper(getContext());
//        db.addItem(new Item("cuong", "Ha Noi", "10", "co", 122));
        List<User> list = db.getAllUser();
        adapter.setList(list);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        adapter.setItemListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        List<User> list = db.getAllUser();
        adapter.setList(list);
    }

    @Override
    public void onItemClick(View view, int position) {
        User user = adapter.getItem(position);
        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        builder.setTitle("Thông báo xóa");
        builder.setMessage("Bạn có muốn xóa " + user.getName() + "?");
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast toast = Toast.makeText(getContext(), "Id : " + user.getId() +  " " + user.getUsername() + " " + user.getPassword(), Toast.LENGTH_LONG);
                toast.show();
                db.delete(user.getId());
                onResume();
            }
        });
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
//        Intent intent = new Intent(getActivity(), UpdateDeleteActivity.class);
//        intent.putExtra("item", item);
//        startActivity(intent);
    }
}
