package com.example.rbrazuk.moviesfrag;

/**
 * Created by rossbrazuk1 on 3/24/16.
 */
public class Movie {
    private String title;
    private int rating;
    private String yearReleased;
    private String director;
    private boolean isOnWatchList;
    private String genre;
    private String dateWatched;

    public Movie() {

    }

    public Movie(String title, int rating, String yearReleased) {
        this.title = title;
        this.rating = rating;
        this.yearReleased = yearReleased;
    }

    public Movie(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getYearReleased() {
        return yearReleased;
    }

    public void setYearReleased(String yearReleased) {
        this.yearReleased = yearReleased;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public boolean isOnWatchList() {
        return isOnWatchList;
    }

    public void setIsOnWatchList(boolean isOnWatchList) {
        this.isOnWatchList = isOnWatchList;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDateWatched() {
        return dateWatched;
    }

    public void setDateWatched(String dateWatched) {
        this.dateWatched = dateWatched;
    }
}
