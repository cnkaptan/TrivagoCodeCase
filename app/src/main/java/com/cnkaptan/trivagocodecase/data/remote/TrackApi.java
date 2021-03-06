package com.cnkaptan.trivagocodecase.data.remote;

import com.cnkaptan.trivagocodecase.data.remote.model.Movie;
import com.cnkaptan.trivagocodecase.data.remote.model.SearchResult;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by cnkaptan on 20/08/16.
 */
public interface TrackApi {

    String BASE_URL = "https://api.trakt.tv";
    String EXTENDED = "full,images";
    String TYPE = "movie";

    @GET("/movies/popular")
    Observable<List<Movie>> getPopularMovies(@Query("page") int page,@Query("extended")String level);

    @GET("/movies/{id}")
    Observable<Movie> getMovieById(@Path("id") int id,@Query("extended") String level);

    @GET("/search")
    Observable<List<SearchResult>> getSearchResult(@Query("query")String query,@Query("type") String type,@Query("page") int page,@Query("limit")int limit);
}
