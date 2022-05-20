package com.example.orderapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.orderapp.Adapter.SpinnerAdapter;
import com.example.orderapp.Model.Coffee;
import com.example.orderapp.dal.SQLiteHelper;

public class AdminAddCoffeeActivity extends AppCompatActivity implements View.OnClickListener {

    private Spinner sp;
    private EditText ename, eprice, edes;
    private SQLiteHelper db;
    private Button btnAdd, btnCan;
    private int[] imgs = {R.drawable.cafecotdua,R.drawable.cafetrung,R.drawable.capuchino,
            R.drawable.chon,R.drawable.culi, R.drawable.den, R.drawable.moka, R.drawable.macchiato};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_coffee);
        initView();
        btnCan.setOnClickListener(this);
        btnAdd.setOnClickListener(this);

    }

    private void initView() {
        db = new SQLiteHelper(this);
        sp = findViewById(R.id.spinner);
        SpinnerAdapter spinnerAdapter = new SpinnerAdapter(this);
        sp.setAdapter(spinnerAdapter);
        ename = findViewById(R.id.eaddcoffee_name);
        eprice = findViewById(R.id.eaddcoffee_price);
        edes = findViewById(R.id.eaddcoffee_des);
        btnAdd = findViewById(R.id.btnAddCf);
        btnCan = findViewById(R.id.btnAddCancle);
    }

    @Override
    public void onClick(View view) {
        if(view == btnCan) {
            finish();
        }
        if(view == btnAdd) {
            int i = Integer.parseInt(sp.getSelectedItem().toString());
            try {
                int img = imgs[i];
                String name = ename.getText().toString();
                String price = eprice.getText().toString();
                String des = edes.getText().toString();
                db.addCoffee(new Coffee(img, name, price, des));
            } catch(Exception e) {
                Toast.makeText(getApplicationContext(), "Erorr : " + e, Toast.LENGTH_LONG).show();
            }
            finish();
        }
    }
}