package com.example.marvel_arnau;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List films = new ArrayList<>();
    FilmAdapter filmAdapter;

    private static String URL = "https://run.mocky.io/v3/39fcc41e-9d03-486c-8fb2-235e3e831a1b";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hook();

        getFilms();
    }

    private void hook(){
        recyclerView = findViewById(R.id.recyclerView);
    }

    private void getFilms(){
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                URL,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray articlesArray = response.getJSONArray("articles");
                            for (int i = 0; i < response.getJSONArray("articles").length(); i++){
                                JSONObject jsonObject = articlesArray.getJSONObject(i);
                                Film film = new Film();
                                film.setTitle(jsonObject.getString("title"));
                                film.setDescription(jsonObject.getString("description"));
                                film.setUrlToImage(jsonObject.getString("urlToImage"));
                                film.setPublishedAt(jsonObject.getString("publishedAt"));
                                film.setContent(jsonObject.getString("content"));
                                film.setAuthorname(jsonObject.getString("authorname"));
                                film.setUrl(jsonObject.getString("url"));
                                film.setAuthorlink(jsonObject.getString("authorlink"));
                                film.setContact(jsonObject.getString("contact"));
                                film.setUrlvideo(jsonObject.getString("urlvideo"));

                                JSONObject mediaObject = jsonObject.getJSONObject("media");
                                String[] mediaArray = mediaObject.toString().split(",");
                                film.setMedia(mediaArray);

                                /*for (int j = 0; j < articlesArray.getJSONObject(j).getJSONObject("media").length(); j++){
                                    JSONObject jsonObjectMedia = mediaObject.getJSONObject(j);
                                    String[] mediaS = {jsonObjectMedia.getString("urlToImage1"),
                                            jsonObjectMedia.getString("urlToImage2"),
                                            jsonObjectMedia.getString("urlToImage3")
                                    };
                                    film.setMedia(mediaS);
                                }*/
                                JSONArray commentsArray = jsonObject.getJSONArray("comments");
                                for (int j = 0; j < commentsArray.length(); j++){
                                     film.setComments(new String[]{commentsArray.getString(j)});
                                }

                                films.add(film);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        filmAdapter = new FilmAdapter(getApplicationContext(), films);
                        recyclerView.setAdapter(filmAdapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("tag", "onErrorResponse: "+ error.getMessage());
                    }
                }
        );
        queue.add(jsonObjectRequest);
    }
}