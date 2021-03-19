package com.example.moviemanagerlatest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class favorite_movies extends AppCompatActivity {
    private ListView listView;
    private String TAG_FAVORITE = "favorite_movies.java";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_movies);

        listView = findViewById(R.id.favorite_moviess);
        Intent favorite_potatoe_intent = getIntent();
        String movie_tittle_from_meat_potatoes = favorite_potatoe_intent.getStringExtra(Meat_and_Potatoes.MEAT_AND_POTATOES);
        final ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add(movie_tittle_from_meat_potatoes);

        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1, arrayList);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            arrayList.remove(position);
            adapter.notifyDataSetChanged();

            }
        });



    }

}
