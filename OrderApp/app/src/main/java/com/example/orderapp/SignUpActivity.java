package com.example.orderapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.orderapp.Model.User;
import com.example.orderapp.dal.SQLiteHelper;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText name, username, password;
    private Button btnSignup;
    private SQLiteHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        initView();
        btnSignup.setOnClickListener(this);
    }

    private void initView() {
        db = new SQLiteHelper(SignUpActivity.this);
        name = findViewById(R.id.signupname);
        username = findViewById(R.id.signupusername);
        password = findViewById(R.id.signuppassword);
        btnSignup = findViewById(R.id.signupbtnSignup);
    }

    @Override
    public void onClick(View view) {
        if(view == btnSignup) {
            String n = name.getText().toString();
            String u = username.getText().toString();
            String p = password.getText().toString();
            db.addUser(new User(n,u,p,2));
            finish();
        }
    }
}