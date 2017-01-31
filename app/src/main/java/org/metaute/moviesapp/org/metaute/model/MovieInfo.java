package org.metaute.moviesapp.org.metaute.model;

/**
 * Created by metaute on 1/30/17.
 */

public class MovieInfo {
    private String title;
    private String overview;
    private String posterPath;

    public MovieInfo(String title, String overview, String posterPath) {
        this.title = title;
        this.overview = overview;
        this.posterPath = posterPath;
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public String getPosterPath() {
        return posterPath;
    }

    @Override
    public String toString() {
        return "Title: " + title;
    }
}
