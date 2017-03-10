package com.kinisoftware.comingsoon.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Movie {

    private String posterPath;
    private String backdropPath;
    private String overview;
    private String originalTitle;

    public Movie(JSONObject jsonObject) throws JSONException {
        this.posterPath = jsonObject.getString("poster_path");
        this.overview = jsonObject.getString("overview");
        this.originalTitle = jsonObject.getString("original_title");
        this.backdropPath = jsonObject.getString("backdrop_path");
    }

    public String getPosterPath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s", posterPath);
    }

    public String getBackdropPath() {
        return String.format("https://image.tmdb.org/t/p/w780/%s", backdropPath);
    }

    public String getOverview() {
        return overview;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public static List<Movie> fromArray(JSONArray jsonArray) throws JSONException {
        List<Movie> movies = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            movies.add(new Movie(jsonArray.getJSONObject(i)));
        }

        return movies;
    }
}
