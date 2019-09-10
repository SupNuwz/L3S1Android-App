package com.example.supuni.tha2_app_154107m;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class RegisterActivity extends AppCompatActivity {
    EditText name,index,mobile,gpa,email,password,repassword;
    String Sname,Sindex,Smobile,Sgpa,Semail,Spassword,Srepassword;
    DataBasehelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name = (EditText)findViewById(R.id.name);
        index = (EditText)findViewById(R.id.index);
        mobile = (EditText)findViewById(R.id.mobile);
        gpa = (EditText)findViewById(R.id.gpa);
        email = (EditText)findViewById(R.id.email);
        password = (EditText)findViewById(R.id.password);
        repassword = (EditText)findViewById(R.id.repassword);


        db = new DataBasehelper(this);




    }


    public void submit(View view) {
        Sname = name.getText().toString();
        Sindex = index.getText().toString();
        Smobile = mobile.getText().toString();
        Sgpa = gpa.getText().toString();
        Semail = email.getText().toString();
        Spassword = password.getText().toString();
        Srepassword = repassword.getText().toString();

        if (!TextUtils.isEmpty(Sname) && !TextUtils.isEmpty(Sindex) && !TextUtils.isEmpty(Smobile)
                && !TextUtils.isEmpty(Sgpa) && !TextUtils.isEmpty(Semail) && !TextUtils.isEmpty(Spassword)) {
            if (Spassword.equals(Srepassword)) {
                if(Sindex.matches("15\\d\\d\\d\\d[a-zA-Z]")){
                    if(Semail.matches("(.*)@(.*).com")) {
                        if(Smobile.matches("07\\d\\d\\d\\d\\d\\d\\d\\d")){
                            boolean isInserted = db.insertData(Sname, Sindex, Smobile
                                    , Sgpa, Semail, Spassword);
                            if (isInserted == true) {
                                Toast.makeText(RegisterActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(RegisterActivity.this, "Data not Inserted", Toast.LENGTH_LONG).show();
                            }

                        }else{
                            Toast.makeText(RegisterActivity.this, "Mobile no is not valid(eg:07#######)", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(RegisterActivity.this, "Email is not valid", Toast.LENGTH_SHORT).show();
                        email.setError("not valid");
                    }
                }else{
                    Toast.makeText(RegisterActivity.this, "Index number is not valid(eg:154005A)", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(RegisterActivity.this, "Password is not matching", Toast.LENGTH_SHORT).show();
            }
        }else{

            Toast.makeText(RegisterActivity.this, "Fields cannot be empty", Toast.LENGTH_SHORT).show();
        }
    }

}
