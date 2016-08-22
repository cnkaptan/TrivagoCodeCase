package com.cnkaptan.trivagocodecase.presentation.search;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.cnkaptan.trivagocodecase.R;
import com.cnkaptan.trivagocodecase.data.Repository;
import com.cnkaptan.trivagocodecase.data.remote.model.SearchResult;
import com.cnkaptan.trivagocodecase.injection.Injection;
import com.jakewharton.rxbinding.support.v7.widget.RxSearchView;

import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class SearchActivity extends AppCompatActivity implements SearchContract.View {
    private static final String TAG = SearchActivity.class.getSimpleName();
    SearchView searchView;
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    SearchContract.Presenter searchPresenter;
    private Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        repository = Injection.provideTrackTvRepo();
        searchPresenter = new SearchPresenter(repository, Schedulers.io(), AndroidSchedulers.mainThread());
        searchPresenter.attachView(this);

//        repository.searchMovies("nemo")
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<List<SearchResult>>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onNext(List<SearchResult> searchResults) {
//                        for (SearchResult searchResult: searchResults) {
//                            Log.e(TAG,searchResult.getMovie().getTitle());
//                        }
//                    }
//                });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_search:
                searchView = (SearchView) item.getActionView();
                RxSearchView.queryTextChanges(searchView)
                        .filter(new Func1<CharSequence, Boolean>() {
                            @Override
                            public Boolean call(CharSequence charSequence) {
                                return !TextUtils.isEmpty(charSequence);
                            }
                        })
                        .throttleLast(100, TimeUnit.MILLISECONDS)
                        .debounce(200, TimeUnit.MILLISECONDS)
                        .onBackpressureLatest()
                        .observeOn(AndroidSchedulers.mainThread())
                        .concatMap(new Func1<CharSequence, Observable<List<SearchResult>>>() {
                            @Override
                            public Observable<List<SearchResult>> call(CharSequence charSequence) {
                                return repository.searchMovies(charSequence.toString()).subscribeOn(Schedulers.io());
                            }
                        })
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<List<SearchResult>>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {
                                e.printStackTrace();
                            }

                            @Override
                            public void onNext(List<SearchResult> searchResults) {
                                for (SearchResult searchResult: searchResults) {
                                    Log.e(TAG,searchResult.getMovie().getTitle());
                                }
                            }
                        });
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void showError(Throwable t) {

    }

    @Override
    public void receiveObservableSearchList(Observable<List<SearchResult>> listObservable) {

    }
}
