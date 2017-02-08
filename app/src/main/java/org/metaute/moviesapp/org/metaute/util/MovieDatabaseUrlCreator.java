package org.metaute.moviesapp.org.metaute.util;

import android.net.Uri;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

/**
 * Created by metaute on 2/7/17.
 */

public class MovieDatabaseUrlCreator {

    private static final String BASE_URL = " http://image.tmdb.org/t/p/";
    public static final String STANDARD_IMAGE_SIZE = "w185";
    public static final String BIG_IMAGE_SIZE = "w342";

    public static URL getImageUrl(String size, String posterUrl) {
        URL result = null;
        Uri builtUri = Uri.parse(BASE_URL).buildUpon()
                .appendPath(size)
                .appendEncodedPath(posterUrl)
                .build();

        try {
            result = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return result;
    }
}
