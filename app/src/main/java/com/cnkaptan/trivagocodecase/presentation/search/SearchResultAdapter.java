package com.cnkaptan.trivagocodecase.presentation.search;

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
import com.cnkaptan.trivagocodecase.data.remote.model.SearchResult;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by cihankaptan on 23/08/16.
 */
public class SearchResultAdapter extends RecyclerView.Adapter<SearchResultAdapter.SearchViewHolder> {

    private List<SearchResult> results;
    private Context context;

    public SearchResultAdapter(List<SearchResult> results) {
        this.results = results;
    }

    public void setSearchResults(List<SearchResult> results) {
        this.results = results;
        notifyDataSetChanged();
    }

    public void addSearchResults(List<SearchResult> results) {
        this.results.addAll(results);
        notifyDataSetChanged();
    }

    @Override
    public SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_popular_movies, parent, false);
        this.context = parent.getContext();
        return new SearchViewHolder(view);
    }


    @Override
    public void onBindViewHolder(SearchViewHolder holder, int position) {
            Movie movie = results.get(position).getMovie();
            holder.tvMovieTitle.setText(movie.getTitle());
            Picasso.with(context).load(movie.getImages().getPoster().getThumb()).into(holder.ivMovieImage);

            if (!TextUtils.isEmpty(movie.getOverview()))
                holder.tvMoviewOverview.setText(movie.getOverview());


    }

    @Override
    public int getItemCount() {
        if (results == null) {
            return 0;
        }
        return results.size();
    }

    public static class SearchViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.tv_movie_title)
        TextView tvMovieTitle;
        @Bind(R.id.tv_movie_overview)
        TextView tvMoviewOverview;
        @Bind(R.id.iv_movie_image)
        ImageView ivMovieImage;

        public SearchViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
