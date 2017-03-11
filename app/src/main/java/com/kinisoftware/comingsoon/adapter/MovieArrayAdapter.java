package com.kinisoftware.comingsoon.adapter;

import android.content.Context;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.kinisoftware.comingsoon.R;
import com.kinisoftware.comingsoon.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

import static com.kinisoftware.comingsoon.R.id.ivMovieImage;
import static com.kinisoftware.comingsoon.R.id.tvOverview;
import static com.kinisoftware.comingsoon.R.id.tvTitle;

public class MovieArrayAdapter extends ArrayAdapter<Movie> {

    private static class ViewHolderOrdinalMovie {
        ImageView ivMovieImage;
        TextView tvTitle;
        TextView tvOverview;
    }

    private static class ViewHolderPopularMovie {
        ImageView ivMovieImage;
    }

    public MovieArrayAdapter(@NonNull Context context, @NonNull List<Movie> movies) {
        super(context, android.R.layout.simple_list_item_1, movies);
    }

    @Override
    public int getViewTypeCount() {
        return Movie.MovieType.values().length;
    }

    @Override
    public int getItemViewType(int position) {
        return getItem(position).getType().ordinal();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        int viewType = getItemViewType(position);
        if (viewType == Movie.MovieType.ORDINAL.ordinal()) {
            return buildOrdinalMovieView(position, convertView, parent);
        } else {
            return buildPopularMovieView(position, convertView, parent);
        }
    }

    private View buildOrdinalMovieView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Movie movie = getItem(position);

        ViewHolderOrdinalMovie viewHolder;
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_movie, parent, false);
            viewHolder = new ViewHolderOrdinalMovie();
            viewHolder.ivMovieImage = (ImageView) convertView.findViewById(ivMovieImage);
            viewHolder.tvTitle = (TextView) convertView.findViewById(tvTitle);
            viewHolder.tvOverview = (TextView) convertView.findViewById(tvOverview);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolderOrdinalMovie) convertView.getTag();
        }

        viewHolder.tvTitle.setText(movie.getOriginalTitle());
        viewHolder.tvOverview.setText(movie.getOverview());

        int orientation = getContext().getResources().getConfiguration().orientation;
        String movieImagePath = "";
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            movieImagePath = movie.getPosterPath();
        } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            movieImagePath = movie.getBackdropPath();
        }
        Picasso.with(getContext())
                .load(movieImagePath)
                .placeholder(R.drawable.movie_placeholder)
                .error(R.drawable.movie_placeholder)
                .into(viewHolder.ivMovieImage);

        return convertView;
    }

    private View buildPopularMovieView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Movie movie = getItem(position);

        ViewHolderPopularMovie viewHolder;
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_popular_movie, parent, false);
            viewHolder = new ViewHolderPopularMovie();
            viewHolder.ivMovieImage = (ImageView) convertView.findViewById(ivMovieImage);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolderPopularMovie) convertView.getTag();
        }

        String movieImagePath = movie.getBackdropPath();
        Picasso.with(getContext())
                .load(movieImagePath)
                .placeholder(R.drawable.movie_placeholder)
                .error(R.drawable.movie_placeholder)
                .into(viewHolder.ivMovieImage);

        return convertView;
    }
}
