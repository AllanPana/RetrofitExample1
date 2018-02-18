package com.example.cristina.retrofitexample1.main;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.cristina.retrofitexample1.R;
import com.example.cristina.retrofitexample1.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by cristina on 18/02/18.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolderMovie>{

    private List<Movie> movieList;
    private ItemClickListener itemClickListener;
    public static final String IMAGE_URL_BASE_PATH="http://image.tmdb.org/t/p/w185//";

    public MovieAdapter(List<Movie> movieList, ItemClickListener itemClickListener) {
        this.movieList = movieList;
        this.itemClickListener = itemClickListener;
    }

    @Override
    public ViewHolderMovie onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        return new ViewHolderMovie(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderMovie holder, int position) {

        Movie movie = movieList.get(position);
        String imageUrl = IMAGE_URL_BASE_PATH + movie.getPosterPath();

        Picasso.with(holder.itemView.getContext()).load(imageUrl)
                .placeholder(android.R.drawable.sym_def_app_icon)
                .error(android.R.drawable.sym_def_app_icon)
                .into(holder.imageView);


    }

    @Override
    public int getItemCount() {
        return movieList.size() == 0 ? 0 : movieList.size();
    }

    class ViewHolderMovie extends RecyclerView.ViewHolder{
        ImageView imageView;

        public ViewHolderMovie(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.tv_title);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickListener.onItemClick(getAdapterPosition());
                }
            });
        }



    }

    public interface ItemClickListener{
        void onItemClick(int position);
    }
}
