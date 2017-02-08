package org.metaute.moviesapp;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.metaute.moviesapp.MovieFragment.OnListFragmentInteractionListener;
import org.metaute.moviesapp.org.metaute.model.MovieInfo;
import org.metaute.moviesapp.org.metaute.util.MovieDatabaseUrlCreator;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link MovieInfo} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MovieViewAdapter extends RecyclerView.Adapter<MovieViewAdapter.ViewHolder> {

    private final List<MovieInfo> mValues;
    private final OnListFragmentInteractionListener mListener;

    public MovieViewAdapter(List<MovieInfo> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_movie, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        URL imageUrl = MovieDatabaseUrlCreator
                .getImageUrl(MovieDatabaseUrlCreator.BIG_IMAGE_SIZE,
                        mValues.get(position).getPosterPath());
        Picasso.with(holder.mContentView.getContext())
                .load(imageUrl.toString())
                .into(holder.mContentView);
        Log.v("MOVIE VIEW", imageUrl.toString());
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    public void updateData(ArrayList<MovieInfo> newValues) {
        mValues.clear();
        mValues.addAll(newValues);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final ImageView mContentView;
        public MovieInfo mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mContentView = (ImageView) view.findViewById(R.id.content);
        }
    }
}
