package com.example.moviemanagerlatest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class top_five extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    //Create list view for top_five
    private ListView top_five;

    public static final String TOP_FIVE_TITTLE_CONSTANT = "top_five.java TAG";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_five);

        //Create Array and add string as such
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("The Godfather"); //0
        arrayList.add("The Godfather: Part II"); //1
        arrayList.add("One Flew Over the Cuckoo's Nest"); //2
        arrayList.add("Psycho"); //3
        arrayList.add("The Birds"); //4
        Log.d(TAG, "Finished creating array");


        //Link our widget to our java variables and log oncreate starting
        top_five = findViewById(R.id.list_view);
        Log.d(TAG,"Linked list_view to java variable top_five");

        //Create Array adapter and set it
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1, arrayList);
        top_five.setAdapter(adapter);
        Log.d(TAG,"Array adpater set");

        //Creating the on-click listener for the item from which will call the search function
        //That has not been created yet [COMEBACK HERE]
        top_five.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            //Positioning logic
            String itemPostion = (String) parent.getItemAtPosition(position);
            String item_position_string = itemPostion;
            Log.d(TAG,"on item click working");
            Log.e(TAG,item_position_string); //Shows position in log for debugging

            //Positioning logic and top_five_clicker activity
                open_top_five_clicker_activity(item_position_string);

            }
        });

    }

    //Opens our top_five_clicker activity -------------------------------------------------------------
    public void open_top_five_clicker_activity(String item_position_string){

        //Create a new intent and pass it to start activity function
        Intent top_five_cliker = new Intent(this, top_five_clicker.class );

        //Pass the tittle of the movie from which the list was clicked on into our new activity
        //Activity top_five_clicker
        top_five_cliker.putExtra(TOP_FIVE_TITTLE_CONSTANT, item_position_string);
        Log.e(TAG, item_position_string);

        startActivity(top_five_cliker);


    }

}
