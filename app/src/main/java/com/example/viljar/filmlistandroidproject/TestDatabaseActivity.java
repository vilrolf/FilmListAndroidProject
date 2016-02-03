package com.example.viljar.filmlistandroidproject;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import java.util.List;

public class TestDatabaseActivity extends ListActivity {

    private Database mydb;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_test_database);

        loadListViewFromDB();

    }

    public void loadListViewFromDB(){
        mydb = new Database(this);
        List<MovieItem> ibs = mydb.getAllMovies();

        //TODO create custom Adapter. Seems easy enough
        ArrayAdapter<MovieItem> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, ibs);
        setListAdapter(adapter);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add:
                add3Movies();
                break;
            case R.id.delete:
                mydb.restart();
                break;
        }

        loadListViewFromDB();

    }

    private void add3Movies() {

        MovieItem movieItem1 = new MovieItem("Hemmelig");
        mydb.addMovie(movieItem1);
    }
}
