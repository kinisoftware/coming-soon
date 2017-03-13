package com.kinisoftware.comingsoon.adapter;

import android.content.res.Configuration;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kinisoftware.comingsoon.R;
import com.kinisoftware.comingsoon.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Movie> movies;

    public MoviesAdapter(List<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        if (viewType == Movie.MovieType.ORDINAL.ordinal()) {
            View movieView = inflater.inflate(R.layout.item_movie, parent, false);
            viewHolder = new ViewHolderOrdinalMovie(movieView);
        } else {
            View movieView = inflater.inflate(R.layout.item_popular_movie, parent, false);
            viewHolder = new ViewHolderPopularMovie(movieView);
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        Movie movie = movies.get(position);
        int orientation = viewHolder.itemView.getContext().getResources().getConfiguration().orientation;
        String movieImagePath = "";

        if (viewHolder.getItemViewType() == Movie.MovieType.ORDINAL.ordinal()) {
            ViewHolderOrdinalMovie viewHolderOrdinalMovie = (ViewHolderOrdinalMovie) viewHolder;
            viewHolderOrdinalMovie.getTvTitle().setText(movie.getOriginalTitle());
            viewHolderOrdinalMovie.getTvOverview().setText(movie.getOverview());
            if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                movieImagePath = movie.getPosterPath();
            } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                movieImagePath = movie.getBackdropPath();
            }
            Picasso.with(viewHolder.itemView.getContext())
                    .load(movieImagePath)
                    .placeholder(R.drawable.movie_placeholder)
                    .error(R.drawable.movie_placeholder)
                    .into(viewHolderOrdinalMovie.getIvMovieImage());
        } else {
            ViewHolderPopularMovie viewHolderPopularMovie = (ViewHolderPopularMovie) viewHolder;
            movieImagePath = movie.getBackdropPath();
            Picasso.with(viewHolder.itemView.getContext())
                    .load(movieImagePath)
                    .placeholder(R.drawable.movie_placeholder)
                    .error(R.drawable.movie_placeholder)
                    .into(viewHolderPopularMovie.getIvMovieImage());
        }
    }

    @Override
    public int getItemViewType(int position) {
        return movies.get(position).getType().ordinal();
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }
}
