package com.cnkaptan.trivagocodecase.presentation.popular;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cnkaptan.trivagocodecase.R;
import com.cnkaptan.trivagocodecase.data.remote.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by cihankaptan on 22/08/16.
 */
public class PopularMovieAdapter extends RecyclerView.Adapter<PopularMovieAdapter.PopularMovieViewHolder> {
    private List<Movie> movies;
    private Context context;

    public PopularMovieAdapter(List<Movie> movies) {
        this.movies = movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
        notifyDataSetChanged();
    }

    public void addMovies(List<Movie> movies) {
        this.movies.addAll(movies);
        notifyDataSetChanged();
    }


    @Override
    public PopularMovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_popular_movies, parent, false);
        this.context = parent.getContext();
        return new PopularMovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PopularMovieViewHolder holder, int position) {
        Movie movie = movies.get(position);

        holder.tvMovieTitle.setText(movie.getTitle());

        if (movie.getImages() != null &&
                movie.getImages().getPoster() != null &&
                movie.getImages().getPoster().getThumb() != null) {
            Picasso.with(context).load(movie.getImages().getPoster().getThumb()).into(holder.ivMovieImage);
        } else if(movie.getImages() != null &&
                movie.getImages().getThumb() != null &&
                movie.getImages().getThumb().getFull() != null){
            Picasso.with(context).load(movie.getImages().getThumb().getFull()).into(holder.ivMovieImage);
        }
        if (!TextUtils.isEmpty(movie.getOverview())){
            holder.tvMoviewOverview.setText(movie.getOverview());
        }else{
            holder.tvMoviewOverview.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        if (movies == null) {
            return 0;
        }
        return movies.size();
    }

    public static class PopularMovieViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.tv_movie_title)
        TextView tvMovieTitle;
        @Bind(R.id.tv_movie_overview)
        TextView tvMoviewOverview;
        @Bind(R.id.iv_movie_image)
        ImageView ivMovieImage;

        public PopularMovieViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
