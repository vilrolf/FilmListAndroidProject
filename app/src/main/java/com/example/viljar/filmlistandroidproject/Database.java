package com.example.viljar.filmlistandroidproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Viljar on 03-Feb-16.
 */
public class Database extends SQLiteOpenHelper{
    public static final String TABLE_MOVIE = "movie";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_YEAR = "year";

    private static final String DATABASE_NAME = "movie.db";
    private static final int DATABASE_VERSION = 1;

    // Database creation sql statement
    private static final String DATABASE_CREATE = "create table "
            + TABLE_MOVIE + "(" + COLUMN_ID
            + " integer primary key, "
            + COLUMN_TITLE + " TEXT NOT NULL, "
            + COLUMN_YEAR + " INTEGER);";

    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(Database.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MOVIE);
        onCreate(db);
    }

    public void restart(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MOVIE);
        onCreate(db);
    }

    public long addMovie(MovieItem movieItem){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_TITLE, movieItem.getTitle());
        contentValues.put(COLUMN_YEAR, movieItem.getYear());
        long id = db.insert(TABLE_MOVIE,null,contentValues);

        return id;
    }

    public ArrayList<MovieItem> getAllMovies(){
        ArrayList<MovieItem> movieItems = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from " + TABLE_MOVIE, null );
        res.moveToFirst();
        while(res.isAfterLast() == false){
            MovieItem movieItem = new MovieItem();

            movieItem.setId(res.getLong(res.getColumnIndex(COLUMN_ID)));
            movieItem.setTitle(res.getString(res.getColumnIndex(COLUMN_TITLE)));
            movieItem.setYear((int) res.getLong(res.getColumnIndex(COLUMN_YEAR)));

            movieItems.add(movieItem);
            res.moveToNext();
        }

        return movieItems;

    }


}
