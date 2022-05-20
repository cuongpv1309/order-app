package com.example.orderapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.orderapp.Fragment.user.EditUser;
import com.example.orderapp.Fragment.user.useradapter;
import com.example.orderapp.Model.Coffee;
import com.example.orderapp.Model.User;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

public class UserActivity extends AppCompatActivity implements View.OnClickListener{

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private FloatingActionButton fab;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        initView();
        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("user");
        fab.setOnClickListener(this);
        FragmentManager manager = getSupportFragmentManager();
        useradapter adpater = new useradapter(manager, 3);
        viewPager.setAdapter(adpater);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void initView() {
        tabLayout = findViewById(R.id.tablayout_user);
        viewPager = findViewById(R.id.viewpager_user);
        fab = findViewById(R.id.fab_user);
    }

    @Override
    public void onClick(View view) {
        if(view == fab) {
            finish();
        }
    }
}