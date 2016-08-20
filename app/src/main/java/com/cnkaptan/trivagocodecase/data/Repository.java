package com.cnkaptan.trivagocodecase.data;

import com.cnkaptan.trivagocodecase.data.remote.model.Movie;
import com.cnkaptan.trivagocodecase.data.remote.model.SearchResult;

import java.util.List;

import rx.Observable;

/**
 * Created by cnkaptan on 20/08/16.
 */
public interface Repository {
    Observable<List<SearchResult>> searchMovies(String searchTerm);

    Observable<List<Movie>> getPopularMovies(int page);
}
