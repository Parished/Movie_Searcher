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

public class login extends AppCompatActivity {

    //Java variables...
    private EditText login_email;
    private EditText login_password;
    private Button login_login;
    FirebaseAuth myFirebaseAuth;
    private String MY_TAG_LOGIN = "login.java TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Link our widgets our java variables
        login_email = findViewById(R.id.login_email);
        login_password = findViewById(R.id.login_password);
        login_login = findViewById(R.id.login_login);
        myFirebaseAuth = FirebaseAuth.getInstance();


        //Login button when activated will validate email and password
        //Then if a match is found from firebase then we move on to the
        //Activity "meat_and_potatoes".

        login_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = login_email.getText().toString().trim();
                String password = login_password.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    login_email.setError("Email is required");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    login_password.setError("Password is required");
                    return;
                }
                Log.e(MY_TAG_LOGIN,"Line 53: Made it pass email and password");

                //Authenticate the user email and password
                myFirebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(login.this, "Logged In", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),Meat_and_Potatoes.class));
                            finish();
                        } else {
                            Toast.makeText(login.this, "Could not log you in", Toast.LENGTH_SHORT).show();
                        }
                    }
                });




            }
        });



    }
}
