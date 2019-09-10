package com.example.supuni.tha2_app_154107m;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ProfileActivity extends AppCompatActivity {
    String Suser_id,user_id;
    DataBasehelper db;
    TextView name,index,mobile,gpa,email;
    String[] TO ;
    Cursor res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        db = new DataBasehelper(this);
        Suser_id = getIntent().getExtras().getString("user_id");
        Toast.makeText(ProfileActivity.this, Suser_id, Toast.LENGTH_SHORT).show();
        name = (TextView)findViewById(R.id.name);
        index = (TextView)findViewById(R.id.index);
        mobile = (TextView)findViewById(R.id.mobile);
        gpa = (TextView)findViewById(R.id.gpa);
        email = (TextView)findViewById(R.id.email);
        TO = new String[]{"lahirua.15@itfac.mrt.ac.lk"};

        res = db.getProfile(Suser_id);

        if(res.moveToFirst()){
            index.setText(res.getString(res.getColumnIndex("index_no")));
            name.setText(res.getString(res.getColumnIndex("name")));
            mobile.setText(res.getString(res.getColumnIndex("mobile")));
            gpa.setText(res.getString(res.getColumnIndex("GPA")));
            email.setText(res.getString(res.getColumnIndex("email")));


        }

    }

    @SuppressLint("LongLogTag")
    public void email(View view){
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");

        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "My contact details");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Name:"+res.getString(res.getColumnIndex("name"))+
                "\nIndex no:"+res.getString(res.getColumnIndex("index_no"))+
                "\nEmail:"+res.getString(res.getColumnIndex("email"))+
                "\nMobile no:"+res.getString(res.getColumnIndex("mobile"))+
                "\nGPA:"+res.getString(res.getColumnIndex("GPA"))
        );

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
            Log.i("Finished sending email...", "");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(ProfileActivity.this,
                    "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }
}
