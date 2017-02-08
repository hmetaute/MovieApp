package org.metaute.moviesapp;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.metaute.moviesapp.org.metaute.model.MovieInfo;
import org.metaute.moviesapp.org.metaute.util.MovieDataJsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by metaute on 1/5/17.
 */

public class FetchMoviesTask extends AsyncTask<String, Void, ArrayList<MovieInfo>> {
    private final String LOG_TAG = FetchMoviesTask.class.getSimpleName();
    private MoviesInfoConsumer consumer;

    public FetchMoviesTask(MoviesInfoConsumer consumer) {
        this.consumer = consumer;
    }

    @Override
    protected ArrayList<MovieInfo> doInBackground(String... strings) {
        ArrayList<String> results = new ArrayList<String>();
        // so that they can be closed in the finally block.
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String moviesDataJson = "";

        try {
            final String FORECAST_BASE_URL =
                    "http://api.themoviedb.org/3/movie/popular?api_key=b492c849d5a3acc408f971e6f80e1d97";
            final String API_KEY_PARAm = "api_key";

            Uri builtUri = Uri.parse(FORECAST_BASE_URL)
                    .buildUpon().build();
            URL url = new URL(builtUri.toString());

            // Create the request to OpenWeatherMap, and open the connection
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            // Read the input stream into a String
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line + "\n");
            }

            if (buffer.length() == 0) {
                return null;
            }
            moviesDataJson = buffer.toString();
            Log.v(LOG_TAG, moviesDataJson);
        } catch (ProtocolException pe) {
            Log.e(LOG_TAG, "Protocol Error: ", pe);
            return null;
        } catch (IOException e) {
            Log.e(LOG_TAG, "I/O Exception: ", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e(LOG_TAG, "Error closing stream", e);
                }
            }
        }
        try {
            return MovieDataJsonParser.getMoviesDataFromJson(moviesDataJson);
        } catch (JSONException e) {
            Log.e(LOG_TAG, e.getMessage(), e);
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(ArrayList<MovieInfo> moviesResponse) {
        consumer.onFetchFinished(moviesResponse);
    }

    public interface MoviesInfoConsumer {
        void onFetchFinished(ArrayList<MovieInfo> results);
    }
}
