package org.metaute.moviesapp.org.metaute.util;

import android.text.format.Time;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.metaute.moviesapp.org.metaute.model.MovieInfo;

import java.util.ArrayList;

/**
 * Created by metaute on 1/30/17.
 */

public class MovieDataJsonParser {

    public static ArrayList<MovieInfo> getMoviesDataFromJson(String moviesJsonStr)
            throws JSONException {

        ArrayList<MovieInfo> parsedResult = new ArrayList<>();

        // These are the names of the JSON objects that need to be extracted.
        final String TITLE = "title";
        final String RESULTS = "results";
        final String PAGE = "page";
        final String VOTE_AVERAGE = "vote_average";
        final String OVERVIEW = "overview";
        final String POSTER_PATH = "poster_path";

        JSONObject moviesResultJson = new JSONObject(moviesJsonStr);
        JSONArray resultsArray = moviesResultJson.getJSONArray(RESULTS);

        for (int i = 0; i < resultsArray.length(); i++){
            JSONObject movieJsonObject = resultsArray.getJSONObject(i);
            String title = movieJsonObject.getString(TITLE);
            String overView = movieJsonObject.getString(OVERVIEW);
            String posterPath = movieJsonObject.getString(POSTER_PATH);
            MovieInfo info = new MovieInfo(title, overView, posterPath);
            parsedResult.add(info);
        }
        return parsedResult;

    }
}
