package com.example.orderapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.orderapp.Model.User;
import com.example.orderapp.dal.SQLiteHelper;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText username, password;
    private Button btnLogin, btnSignup;
    private SQLiteHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
//        db.addUser(new User("admin", "admin", "admin", 1));
//        db.addUser(new User("mo", "mo", "1", 2));

        btnSignup.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
    }

    private void initView() {
        username = findViewById(R.id.username);
        password = findViewById(R.id.username);
        btnLogin = findViewById(R.id.btnLogin);
        btnSignup = findViewById(R.id.btnSignup);
        db = new SQLiteHelper(this.getApplicationContext());
    }

    @Override
    public void onClick(View view) {
        if(view == btnLogin) {
            String u = username.getText().toString();
            String p = password.getText().toString();
            User user = db.checkLogin(u, p);
            if(user == null) {
                Toast toast = Toast.makeText(this, "Sai tên đăng nhập/mật khẩu", Toast.LENGTH_LONG);
                toast.show();
            } else {
                if(user.role == 1) {
                    Intent intent = new Intent(MainActivity.this, AdminActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(MainActivity.this, UserActivity.class);
                    intent.putExtra("user", user);
                    startActivity(intent);
                }

            }
        }
        if(view == btnSignup) {
            Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
            startActivity(intent);
        }
    }
}