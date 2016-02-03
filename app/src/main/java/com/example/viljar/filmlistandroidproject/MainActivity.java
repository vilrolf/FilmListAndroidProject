package com.example.viljar.filmlistandroidproject;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Map;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private Database mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mydb = new Database(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //EditText editText = (EditText) findViewById(R.id.editTextMainActivity);
               // String filmName = editText.getText().toString();
               // new OmdbGetAsync().execute(filmName);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {
            startActivity(new Intent(this, TestDatabaseActivity.class));

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void showSomethingWithMovie(MovieItem movieItem) {
        Toast.makeText(MainActivity.this, "The movie was made in " + movieItem.getTitle(), Toast.LENGTH_SHORT).show();
        mydb.addMovie(movieItem);
    }

    public void addMovie(View view) {
        EditText editText = (EditText) findViewById(R.id.editTextMainActivity);
        String movieTitle = editText.getText().toString();

        new OmdbGetAsync().execute(movieTitle);
    }

    private class OmdbGetAsync extends AsyncTask<String, Void, Void> {
    // http://stackoverflow.com/a/23963310/4011112

        private MovieItem movieItem;


        @Override
        protected Void doInBackground(String... mTitle) {

            try {
                Log.d(this.toString(), mTitle[0]);
                String selectedItem = mTitle[0].replaceAll("\\s", "+");
                Log.d(this.toString(),selectedItem);
                URL url = new URL("http://www.omdbapi.com/?t=" + selectedItem + "&y=&plot=short&r=json");
                Log.d(this.toString(), url.toString());
                InputStream input = (url).openStream();

                Map<String, String> map = new Gson().fromJson(new InputStreamReader(input, "UTF-8"), new TypeToken<Map<String, String>>() {
                }.getType());


                String title = map.get("Title");
                String yearS =  map.get("Year").replaceAll("\\D", "");
                int year = Integer.parseInt(yearS);
                String released = map.get("Released");
                String runtime = map.get("Runtime");
                String genre = map.get("Genre");
                String actors = map.get("Actors");
                String plot = map.get("Plot");
                float imdbRating = Float.parseFloat(map.get("imdbRating"));
                String poster = map.get("Poster");

                movieItem = new MovieItem(title,year,released,runtime,genre,actors,plot,imdbRating,poster);
                Log.d(this.toString(), movieItem.getTitle());

            } catch (JsonIOException | JsonSyntaxException | IOException e) {

                System.out.println(e);
            }
            return null;
        }

        protected void onPostExecute(Void result) {
            showSomethingWithMovie(movieItem);
        }
    }
}
