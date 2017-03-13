package com.kinisoftware.comingsoon.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.kinisoftware.comingsoon.R;

public class ViewHolderPopularMovie extends RecyclerView.ViewHolder {

    private ImageView ivMovieImage;

    public ViewHolderPopularMovie(View itemView) {
        super(itemView);

        ivMovieImage = (ImageView) itemView.findViewById(R.id.ivMovieImage);
    }

    public ImageView getIvMovieImage() {
        return ivMovieImage;
    }
}
