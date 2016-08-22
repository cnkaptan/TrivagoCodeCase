package com.cnkaptan.trivagocodecase.presentation.popular;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cnkaptan.trivagocodecase.R;
import com.cnkaptan.trivagocodecase.data.remote.model.Movie;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by cihankaptan on 22/08/16.
 */
public class PopularMovieAdapter extends RecyclerView.Adapter<PopularMovieAdapter.PopularMovieViewHolder> {
    private List<Movie> movies;

    public PopularMovieAdapter(List<Movie> movies) {
        this.movies = movies;
    }

    public void setMovies(List<Movie> movies){
        this.movies = movies;
        notifyDataSetChanged();
    }

    public void addMovies(List<Movie> movies){
        this.movies.addAll(movies);
        notifyDataSetChanged();
    }



    @Override
    public PopularMovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_popular_movies, parent, false);
        return new PopularMovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PopularMovieViewHolder holder, int position) {
        Movie movie = movies.get(position);
        holder.tvMovieTitle.setText(movie.getTitle());
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
        public PopularMovieViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

}
