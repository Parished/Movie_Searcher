package com.example.moviemanagerlatest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Meat_and_Potatoes extends AppCompatActivity {


    private RequestQueue myQueue;
    //API VARIABLES ---------------------------------------------
    private String API_KEY ="&apikey=7b3941a8";
    private String URL_SPLICE ="http://www.omdbapi.com/?t=";
    private TextView Tittle;
    private TextView Plot;
    private TextView Genre;
    private EditText Search_Tittle;
    private Button Search_API;
    private Button Favorite_Button;
    private ImageView Image;
    private String MY_TAG_POTATOES = "Meat_and_Potatoes.java TAG";
    public static final String MEAT_AND_POTATOES = "MEAT_AND_POTATOES.java TAG";
    public String name_outside;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meat_and__potatoes);

        //Link our android widgets to our java variables
        Tittle = findViewById(R.id.tittle_potatoe);
        Plot = findViewById(R.id.plot_potatoe);
        Search_Tittle = findViewById(R.id.enter_movie_potatoe);
        Search_API = findViewById(R.id.search_button_potatoe);
        Genre = findViewById(R.id.genrel_potatoe);
        Image = findViewById(R.id.image_potatoes);
        Favorite_Button = findViewById(R.id.favorite_potatoe);



        //Volley and on click stuff
        myQueue = Volley.newRequestQueue(this);
        Search_API.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String movie_title = Search_Tittle.getText().toString().trim();
                String url_completed = URL_SPLICE+movie_title+API_KEY;
                //name_outside = movie_title;
                Log.e(MY_TAG_POTATOES, url_completed);

                StringRequest request_queue = new StringRequest(Request.Method.GET, url_completed, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Log.e(MY_TAG_POTATOES, "Entered Response request");
                            final JSONObject movieSearch = new JSONObject(response);
                            Log.e(MY_TAG_POTATOES, "Failed here 1");
                            //----------------------------------------------------------------

                            String movie_name_json = movieSearch.getString("Title");
                            Log.e(MY_TAG_POTATOES, "Failed here 2");
                            String plot_json = movieSearch.getString("Plot");
                            String Genre_Json = movieSearch.getString("Genre");
                            String Image_Json = movieSearch.getString("Poster");
                            name_outside = movie_name_json;


                            //-----------------------------------------------------------------
                            Tittle.setText(movie_name_json);
                            Plot.setText(plot_json);
                            Genre.setText(Genre_Json);
                            Picasso.get().load(Image_Json).into(Image);


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
        });



        Favorite_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open_favorite_poatoe(name_outside);
            }
        });


    }
    //Opens our top_five_clicker activity -------------------------------------------------------------
    public void open_favorite_poatoe(String movie_name_json){

        //Create a new intent and pass it to start activity function
        Intent favorite_potatoe_intent = new Intent(this, favorite_movies.class );

        //Pass the tittle of the movie from which the list was clicked on into our new activity
        //Activity favorite_movies
        String movie_name = movie_name_json.toString();

        favorite_potatoe_intent.putExtra(MEAT_AND_POTATOES, movie_name );
        startActivity(favorite_potatoe_intent);

    }
}
