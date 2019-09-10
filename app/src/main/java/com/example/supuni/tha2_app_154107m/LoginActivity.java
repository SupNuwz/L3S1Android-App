package com.example.supuni.tha2_app_154107m;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    DataBasehelper db;
    EditText index,passwordt;
    boolean loginsuccess;
    String user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        index = (EditText)findViewById(R.id.index);
        passwordt = (EditText)findViewById(R.id.password);
        db = new DataBasehelper(this);


    }
    public void login(View view) {
        Cursor res = db.getData();
        if(!TextUtils.isEmpty(index.getText().toString()) && !TextUtils.isEmpty(passwordt.getText().toString())) {
            if (res.moveToFirst()) {

                while (res.moveToNext()) {
                    String index_no = res.getString(res.getColumnIndex("index_no"));
                    String password = res.getString(res.getColumnIndex("password"));
                    if (index.getText().toString().equals(index_no) && passwordt.getText().toString().equals(password)) {
                        loginsuccess = true;

                        user_id = res.getString(res.getColumnIndex("user_id"));
                        break;
                        //  Toast.makeText(LoginActivity.this, "Login successfully" + user_id, Toast.LENGTH_SHORT).show();
                    } else {

                    }
                }

                if (loginsuccess) {
                    Toast.makeText(LoginActivity.this, "Login successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, ProfileActivity.class);


                    intent.putExtra("user_id", user_id);
                    startActivity(intent);

                } else {
                    Toast.makeText(LoginActivity.this, "Login not successful", Toast.LENGTH_SHORT).show();
                }


            } else
                Toast.makeText(LoginActivity.this, "Data  no have", Toast.LENGTH_SHORT).show();
        }else{
            index.setError("Can't be empty");
            Toast.makeText(LoginActivity.this, "All Field Required", Toast.LENGTH_SHORT).show();
        }

    }
}

