package org.metaute.moviesapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.metaute.moviesapp.org.metaute.model.MovieInfo;

public class MainActivity extends AppCompatActivity implements MovieFragment.OnListFragmentInteractionListener{

    private static final String LOG_TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.main_container, new MovieFragment())
                    .commit();
        }
    }


    @Override
    public void onListFragmentInteraction(MovieInfo item) {
        Log.v(LOG_TAG, "Clicked on movie: " + item.getTitle());
    }
}
