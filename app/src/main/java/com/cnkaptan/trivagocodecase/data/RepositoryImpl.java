package com.cnkaptan.trivagocodecase.data;

import com.cnkaptan.trivagocodecase.data.remote.model.Movie;
import com.cnkaptan.trivagocodecase.data.remote.model.SearchResult;
import com.cnkaptan.trivagocodecase.data.remote.TrackApi;

import java.util.List;

import rx.Observable;

/**
 * Created by cnkaptan on 20/08/16.
 */
public class RepositoryImpl implements Repository {
    private TrackApi trackApi;

    public RepositoryImpl(TrackApi trackApi) {
        this.trackApi = trackApi;
    }

    @Override
    public Observable<List<SearchResult>> searchMovies(String searchTerm) {
        return null;
    }

    @Override
    public Observable<List<Movie>> getPopularMovies(int page) {
        return trackApi.getPopularMovies(1,TrackApi.EXTENDED);
    }
}
