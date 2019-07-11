package com.example.firebaseapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    //views
    private EditText emailet;
    private EditText passwordet;
    private Button loginbtn;
    private TextView nothaveac;

    private FirebaseAuth mAuth;

    //progress Dialog
    ProgressDialog progressDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        //actionbar and its title
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Login");


        //enable back button

        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);



        //view init
        emailet=(EditText)findViewById(R.id.emailet);
        passwordet=(EditText)findViewById(R.id.passwordet);
        loginbtn=(Button)findViewById(R.id.loginbtn);
        nothaveac=(TextView)findViewById(R.id.nothaveactv);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        //progress dialog
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Logging In ...");

        //not have account
        nothaveac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });


        //login button setonclick listener

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = emailet.getText().toString().trim();
                String password = passwordet.getText().toString().trim();

                if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
                {
                    //error in email
                    emailet.setError("Invalid Email");
                    emailet.setFocusable(true);
                }
                else if(password.length() < 6)
                {
                    passwordet.setError("Password length must be 6 character");
                    passwordet.setFocusable(true);
                }
                else
                {
                    loginUser(email,password);
                }

            }
        });
    }



    private void loginUser(String email , String password)
    {
        progressDialog.show();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information

                            progressDialog.dismiss();
                            FirebaseUser user = mAuth.getCurrentUser();
                            startActivity(new Intent(LoginActivity.this,ProfileActivity.class));
                            finish();

                        } else {
                            // If sign in fails, display a message to the user.
                            progressDialog.dismiss();
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.dismiss();
                Toast.makeText(LoginActivity.this,""+e.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public boolean onSupportNavigateUp()
    {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
