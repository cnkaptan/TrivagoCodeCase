package com.cnkaptan.trivagocodecase.presentation.search;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cnkaptan.trivagocodecase.R;
import com.cnkaptan.trivagocodecase.data.remote.model.SearchResult;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by cihankaptan on 23/08/16.
 */
public class SearchResultAdapter extends RecyclerView.Adapter<SearchResultAdapter.SearchViewHolder> {

    private List<SearchResult> results;

    public SearchResultAdapter(List<SearchResult> results) {
        this.results = results;
    }

    public void setSearchResults(List<SearchResult> results){
        this.results = results;
        notifyDataSetChanged();
    }

    public void addSearchResults(List<SearchResult> results){
        this.results.addAll(results);
        notifyDataSetChanged();
    }

    @Override
    public SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_popular_movies, parent, false);

        return new SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SearchViewHolder holder, int position) {
        holder.tvMovieTitle.setText(results.get(position).getMovie().getTitle());
    }

    @Override
    public int getItemCount() {
        if (results == null){
            return 0;
        }
        return results.size();
    }

    public static class SearchViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.tv_movie_title)
        TextView tvMovieTitle;

        public SearchViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
