package com.example.moviemanagerlatest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.android.volley.RequestQueue;

public class MainActivity extends AppCompatActivity {

    //Variables outside of main activity
    private Button top_five_button;
    private Button log_in;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Set buttons to our java variables
        top_five_button = findViewById(R.id.top_five);
        log_in = findViewById(R.id.log_in);

       //In this section our buttons will link to an event
       //So we set on click listeners and start them as such.
        top_five_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open_top_five_activity();
            }
        });

        log_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open_register_activity();
            }
        });

        //

    }

    //Opens our top five activity -------------------------------------------------------------
    public void open_top_five_activity(){
        //Create a new intent and pass it to start activity function
        Intent top_five_intent = new Intent(this, top_five.class);
        startActivity(top_five_intent);
    }

    //Opens our register activity -------------------------------------------------------------
    public void open_register_activity(){
        //Create a new intent and pass it to start activity function
        Intent register_intent = new Intent(this, register.class);
        startActivity(register_intent);
    }
}
