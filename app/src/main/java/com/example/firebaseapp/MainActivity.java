package com.example.firebaseapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    //views
    Button mregisterbtn,mloginbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //init view
        mregisterbtn=(Button)findViewById(R.id.register_btn);
        mloginbtn=(Button)findViewById(R.id.login_btn);

        //handling views
        


    }
}
