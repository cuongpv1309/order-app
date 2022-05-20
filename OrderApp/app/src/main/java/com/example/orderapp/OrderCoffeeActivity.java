package com.example.orderapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.orderapp.Model.Coffee;
import com.example.orderapp.Model.Order;
import com.example.orderapp.dal.SQLiteHelper;

public class OrderCoffeeActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText ename, eaddress, equanity;
    private SQLiteHelper db;
    private Button btnAdd, btnCan;
    private Coffee cf;
    private Order od;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_coffee);
        initView();
        btnAdd.setOnClickListener(this);
        btnCan.setOnClickListener(this);
        Intent intent = getIntent();
        od = (Order) intent.getSerializableExtra("order");
        cf = db.getCoffeeById(od.getCoffee_id());
        if(cf == null) {
            Toast.makeText(getApplicationContext(), "coffee null", Toast.LENGTH_LONG).show();
        } else {
            ename.setText(cf.getName());
        }
    }

    private void initView() {
        ename = findViewById(R.id.order_coffee_name);
        eaddress = findViewById(R.id.order_coffee_address);
        equanity = findViewById(R.id.order_coffee_quanity);
        btnAdd = findViewById(R.id.order_coffee_btnBook);
        btnCan = findViewById(R.id.order_coffee_btnCancle);
        db = new SQLiteHelper(this);
    }

    @Override
    public void onClick(View view) {
        if(view == btnCan) {
            finish();
        }
        if(view == btnAdd) {
            String address = eaddress.getText().toString();
            String quanity = equanity.getText().toString();
            if(!address.isEmpty() && quanity.matches("\\d+")) {
                od.setAddress(address);
                od.setQuanity(Integer.parseInt(quanity));
                db.addOrder(od);
                finish();
            } else {
                Toast.makeText(this, "Nhập lại", Toast.LENGTH_LONG).show();
            }
        }
    }
}