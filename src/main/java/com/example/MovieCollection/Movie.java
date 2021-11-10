package com.example.MovieCollection;

public class Movie {

    private String id;
    private String title;
    private String category;
    private String year;

    public Movie(String id, String title, String category, String year) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.year = year;
    }

    public Movie() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }




}
