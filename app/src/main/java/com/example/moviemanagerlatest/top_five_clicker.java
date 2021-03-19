package com.example.moviemanagerlatest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

public class top_five_clicker extends AppCompatActivity {
    // Java variables, tag, widgets, and strings.
    public String TAG = "top_five_clicker";
    private String API_KEY ="&apikey=7b3941a8";
    private String URL_SPLICE ="http://www.omdbapi.com/?t=";
    private TextView Tittle;
    private TextView Plot;
    private TextView Genre;
    private TextView Year;
    private ImageView Image;
    private RequestQueue myQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_five_clicker);


        //Widgets to java variables
        Tittle = findViewById(R.id.movie_tittle_top_five);
        Plot = findViewById(R.id.description_top_five);
        Genre = findViewById(R.id.gnere_top_five);
        Year = findViewById(R.id.year_top_five);
        Image = findViewById(R.id.clicker_image);


        //Inititated Request queue
        myQueue = Volley.newRequestQueue(this);

        // Create a new intent to get the values of our top five tittle (from list view)
        // And we set it to our string text
        //Then we create link our widget to java and assign the tittle to display
        Log.e(TAG,"before intent");
        Intent top_five_cliker = getIntent();
        Log.e(TAG,"before string");
        String movie_tittle_from_top_five = top_five_cliker.getStringExtra(top_five.TOP_FIVE_TITTLE_CONSTANT);
        Log.e(TAG, movie_tittle_from_top_five);


        //API URL Maker
        String final_url = URL_SPLICE + movie_tittle_from_top_five.trim() + API_KEY;

        // This sets our movie tittle that carried over from the list view
        // Then sets it to the text view, movie tittle
        // We also do our parsing logic here.
        StringRequest request_queue = new StringRequest(Request.Method.GET, final_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Log.e(TAG, "Entered Response request");
                    final JSONObject movieSearch = new JSONObject(response);
                    Log.e(TAG, "Failed here 1");
                    //----------------------------------------------------------------

                    String movie_name_json = movieSearch.getString("Title");
                    Log.e(TAG, "Failed here 2");
                    String plot_json = movieSearch.getString("Plot");
                    String Genre_Json = movieSearch.getString("Genre");
                    String Year_Json = movieSearch.getString("Year");
                    String Image_URL = movieSearch.getString("Poster");

                    //-----------------------------------------------------------------
                    Tittle.setText(movie_name_json);
                    Plot.setText(plot_json);
                    Genre.setText(Genre_Json);
                    Year.setText(Year_Json);
                    Picasso.get().load(Image_URL).into(Image);


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        myQueue.add(request_queue);
    }

    @Override
    public void finish() {
        super.finish();
    }
}
