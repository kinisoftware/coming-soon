package com.kinisoftware.comingsoon.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.kinisoftware.comingsoon.R;

public class ViewHolderOrdinalMovie extends RecyclerView.ViewHolder {

    private ImageView ivMovieImage;
    private TextView tvTitle;
    private TextView tvOverview;

    public ViewHolderOrdinalMovie(View itemView) {
        super(itemView);
        ivMovieImage = (ImageView) itemView.findViewById(R.id.ivMovieImage);
        tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
        tvOverview = (TextView) itemView.findViewById(R.id.tvOverview);
    }

    public ImageView getIvMovieImage() {
        return ivMovieImage;
    }

    public TextView getTvTitle() {
        return tvTitle;
    }

    public TextView getTvOverview() {
        return tvOverview;
    }
}
