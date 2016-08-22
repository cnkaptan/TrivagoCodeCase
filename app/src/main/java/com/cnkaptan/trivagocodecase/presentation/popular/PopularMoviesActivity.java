package com.cnkaptan.trivagocodecase.presentation.popular;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.cnkaptan.trivagocodecase.R;
import com.cnkaptan.trivagocodecase.data.remote.model.Movie;
import com.cnkaptan.trivagocodecase.injection.Injection;
import com.cnkaptan.trivagocodecase.util.OnLoadMoreListener;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class PopularMoviesActivity extends AppCompatActivity implements PopularMovieContract.View {

    private static final String TAG = PopularMoviesActivity.class.getSimpleName();
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.recycler_view_movies)
    RecyclerView recyclerViewMovies;
    @Bind(R.id.text_view_error_msg)
    TextView textViewErrorMsg;
    @Bind(R.id.progress_bar)
    ProgressBar progressBar;
    PopularMovieContract.Presenter moviePresenter;
    PopularMovieAdapter popularMovieAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popular_movies);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        moviePresenter = new PopularMoviesPresenter(Injection.provideTrackTvRepo(), Schedulers.io(), AndroidSchedulers.mainThread());
        moviePresenter.attachView(this);
        moviePresenter.initData();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerViewMovies.setLayoutManager(linearLayoutManager);
        popularMovieAdapter = new PopularMovieAdapter(null);
        recyclerViewMovies.setAdapter(popularMovieAdapter);


        recyclerViewMovies.addOnScrollListener(new OnLoadMoreListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page) {

                moviePresenter.loadMore(page);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        moviePresenter.detachView();
    }

    @Override
    public void showError(Throwable t) {
        textViewErrorMsg.setVisibility(View.VISIBLE);
        recyclerViewMovies.setVisibility(View.GONE);
        textViewErrorMsg.setText(t.getMessage());
    }

    @Override
    public void showInitDatas(List<Movie> movies) {
        recyclerViewMovies.setVisibility(View.VISIBLE);
        textViewErrorMsg.setVisibility(View.GONE);
        popularMovieAdapter.setMovies(movies);
    }

    @Override
    public void loadMoreSuccess(List<Movie> extraMovies) {
        recyclerViewMovies.setVisibility(View.VISIBLE);
        textViewErrorMsg.setVisibility(View.GONE);
        popularMovieAdapter.addMovies(extraMovies);
    }
}
