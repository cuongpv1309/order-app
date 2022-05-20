package com.example.orderapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
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

public class AdminUpdateDeleteActivity extends AppCompatActivity implements View.OnClickListener{

    private Spinner sp;
    private EditText uname, uprice, udes;
    private Button btnUp, btnCancle, btnDel;
    private SQLiteHelper db;
    private Coffee cf;
    private int[] imgs = {R.drawable.cafecotdua,R.drawable.cafetrung,R.drawable.capuchino,
            R.drawable.chon,R.drawable.culi, R.drawable.den, R.drawable.moka, R.drawable.macchiato};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_update_delete);
        initView();
        btnCancle.setOnClickListener(this);
        btnDel.setOnClickListener(this);
        btnUp.setOnClickListener(this);

        Intent intent = getIntent();
        cf =(Coffee) intent.getSerializableExtra("cf");
        uname.setText(cf.getName());
        uprice.setText(cf.getPrice());
        udes.setText(cf.getDes());
    }

    private void initView() {
        db = new SQLiteHelper(this);
        sp = findViewById(R.id.uspinner);
        SpinnerAdapter spinnerAdapter = new SpinnerAdapter(this);
        sp.setAdapter(spinnerAdapter);
        uname = findViewById(R.id.uaddcoffee_name);
        uprice = findViewById(R.id.uaddcoffee_price);
        udes = findViewById(R.id.uaddcoffee_des);
        btnUp = findViewById(R.id.ubtnUpdate);
        btnDel = findViewById(R.id.ubtnDetele);
        btnCancle = findViewById(R.id.ubtnCancle);
    }

    @Override
    public void onClick(View view) {
        if(view == btnCancle) {
            finish();
        }
        if(view == btnDel) {
            int id = cf.getId();
            AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
            builder.setTitle("Thông báo xóa");
            builder.setMessage("Bạn có muốn xóa " + cf.getName());
            builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    db.deleteCoffee(id);
                    finish();
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
        if(view == btnUp) {
            int i = Integer.parseInt(sp.getSelectedItem().toString());
            try {
                int img = imgs[i];
                String name = uname.getText().toString();
                String price = uprice.getText().toString();
                String des = udes.getText().toString();
                db.updateCoffee(new Coffee(cf.getId(),img, name, price, des));
            } catch(Exception e) {
                Toast.makeText(getApplicationContext(), "Erorr : "+e, Toast.LENGTH_LONG).show();
            }
            finish();
        }
    }
}