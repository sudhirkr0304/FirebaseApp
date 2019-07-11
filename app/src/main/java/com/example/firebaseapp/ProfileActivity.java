package com.example.firebaseapp;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.zip.Inflater;

public class ProfileActivity extends AppCompatActivity {

    private TextView useremailtv;
    FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Profile");


        //init
        firebaseAuth = FirebaseAuth.getInstance();

        //init views;
        useremailtv=(TextView)findViewById(R.id.profiletv);




    }


    private void checkuserstatus()
    {
        FirebaseUser user  = firebaseAuth.getCurrentUser();
        if(user  != null)
        {
            useremailtv.setText(user.getEmail().toString());
            //user is signed in stay here
        }
        else
        {
            //user not signed in
            startActivity(new Intent(ProfileActivity.this,MainActivity.class));
            finish();
        }
    }

    protected  void onStart()
    {
        checkuserstatus();
        super.onStart();
    }

    //inflate option menu


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        //inflating menu
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }


    //handle menu item onclicks

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //get item id
        int id= item.getItemId();

        if(id== R.id.action_logout)
        {
            firebaseAuth.signOut();
            checkuserstatus();
        }
        return super.onOptionsItemSelected(item);
    }
}
