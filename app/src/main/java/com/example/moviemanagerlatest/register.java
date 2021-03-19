package com.example.moviemanagerlatest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class register extends AppCompatActivity {
    // Java variables, string, widgets,tag....
    private Button register_register;
    private Button take_me_to_login;
    private EditText register_email;
    private EditText register_password;
    private EditText register_name;
    FirebaseAuth myFireBaseAuth;
    private String REGISTER_TAG = "Register.java Tag";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

       //We set our android widgets to java variables
        take_me_to_login = findViewById(R.id.login_redirect);
        register_email = findViewById(R.id.email_register);
        register_password = findViewById(R.id.password_register);
        register_name = findViewById(R.id.name_register);
        register_register = findViewById(R.id.register);

        //Our firebase initialization
        myFireBaseAuth = FirebaseAuth.getInstance();
        Log.e(REGISTER_TAG, "Line 36: Firebase Authentication");

        //Is the user already logged in, if so then proceed...
        if(myFireBaseAuth.getCurrentUser() != null){
            FirebaseAuth.getInstance().signOut();
            finish();
        }
        Log.e(REGISTER_TAG, "Line 51: User already Registered?");







        // This sections will have all on click listeners for buttons
        // and other logic alike.

        register_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = register_email.getText().toString().trim();
                String password = register_password.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    register_email.setError("Email is required");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    register_password.setError("Password is required");
                    return;
                }
                Log.e(REGISTER_TAG,"Line 76: Made it pass email and password");

                myFireBaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(register.this, "Registered!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),Meat_and_Potatoes.class));
                            finish();
                        }
                        else {
                            Toast.makeText(register.this, "Could Not Register", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                Log.e(REGISTER_TAG,"Line 89: Email Sign up and Authentication");


            }
        });


        take_me_to_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open_login();

            }
        });



    }

    //Opens our register activity -----------------------------------------------
    public void open_login(){
        //Create a new intent and pass it to start activity function
        Intent open_login = new Intent(this, login.class);
        startActivity(open_login);
    }
}
