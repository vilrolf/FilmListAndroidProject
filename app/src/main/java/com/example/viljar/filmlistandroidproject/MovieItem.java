package com.example.viljar.filmlistandroidproject;


/**
 * Created by Viljar on 03-Feb-16.
 */
public class MovieItem {
    private Long id;
    private	String title;
    private int year;
    private	String released;
    private	String runtime;
    private	String genre;
    private	String actors;
    private	String plot;
    private	Float imdbRating;
    private	String poster;


    public MovieItem() {

    }

    public MovieItem(String title) {
        this.title = title;
    }

    public MovieItem(String title, int year, String released, String runtime, String genre, String actors, String plot, Float imdbRating, String poster) {
        this.title = title;
        this.year = year;
        this.released = released;
        this.runtime = runtime;
        this.genre = genre;
        this.actors = actors;
        this.plot = plot;
        this.imdbRating = imdbRating;
        this.poster = poster;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReleased() {
        return released;
    }

    public void setReleased(String released) {
        this.released = released;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public Float getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(Float imdbRating) {
        this.imdbRating = imdbRating;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }
}
