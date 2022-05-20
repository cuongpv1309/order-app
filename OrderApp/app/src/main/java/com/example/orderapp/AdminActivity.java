package com.example.orderapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;

import com.example.orderapp.Fragment.admin.adminfragment;
import com.example.orderapp.dal.SQLiteHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

public class AdminActivity extends AppCompatActivity implements View.OnClickListener{

    private TabLayout tab;
    private ViewPager viewPager;
    private FloatingActionButton fab;
    private SQLiteHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        initView();
        fab.setOnClickListener(this);
        FragmentManager manager = getSupportFragmentManager();
        adminfragment adapter = new adminfragment(manager, 2);
        viewPager.setAdapter(adapter);
        tab.setupWithViewPager(viewPager);
    }

    private void initView() {
        tab = findViewById(R.id.tablayout_admin);
        viewPager = findViewById(R.id.viewpager_admin);
        fab = findViewById(R.id.fab_admin);
        db = new SQLiteHelper(this.getApplicationContext());
    }

    @Override
    public void onClick(View view) {
        if(view == fab) {
            finish();
        }
    }
}