package com.example.orderapp.Fragment.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.orderapp.Model.User;
import com.example.orderapp.R;
import com.example.orderapp.UserActivity;
import com.example.orderapp.dal.SQLiteHelper;

import java.net.URISyntaxException;

public class EditUser extends Fragment{

    private EditText ename, eusername, epassword;
    private Button btnOk;
    private SQLiteHelper db;
    private User user;

    public EditUser() {
    }

    public EditUser(User user) {
        this.user = user;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.edit_user, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ename = view.findViewById(R.id.edit_name);
        eusername = view.findViewById(R.id.edit_username);
        epassword = view.findViewById(R.id.edit_password);
        btnOk = view.findViewById(R.id.edit_btnOk);
        db = new SQLiteHelper(getContext());
        user =(User) getActivity().getIntent().getSerializableExtra("user");

        if(user == null) {

        } else {
            ename.setText(user.getName());
            eusername.setText(user.getUsername());
            epassword.setText(user.getPassword());
        }

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newname = ename.getText().toString();
                String newusername = eusername.getText().toString();
                String newpassword = epassword.getText().toString();
                db.editUser(new User(user.getId(), newname, newusername, newpassword, 2));
            }
        });
    }


}
