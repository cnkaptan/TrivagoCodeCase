package com.cnkaptan.trivagocodecase;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.cnkaptan.trivagocodecase.data.remote.model.Movie;
import com.cnkaptan.trivagocodecase.injection.Injection;
import com.cnkaptan.trivagocodecase.presentation.popular.PopularMovieContract;
import com.cnkaptan.trivagocodecase.presentation.popular.PopularMoviesPresenter;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class PopularMoviesActivity extends AppCompatActivity implements PopularMovieContract.View {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.recycler_view_users)
    RecyclerView recyclerViewUsers;
    @Bind(R.id.text_view_error_msg)
    TextView textViewErrorMsg;
    @Bind(R.id.progress_bar)
    ProgressBar progressBar;
    PopularMovieContract.Presenter moviePresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popular_movies);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        moviePresenter = new PopularMoviesPresenter(Injection.provideTrackTvRepo(), Schedulers.io(), AndroidSchedulers.mainThread());
        moviePresenter.attachView(this);
        moviePresenter.initData();
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

    }

    @Override
    public void showInitDatas(List<Movie> movies) {

    }
}
