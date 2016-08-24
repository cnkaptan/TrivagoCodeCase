package com.cnkaptan.trivagocodecase.presentation.search;

import android.text.TextUtils;
import android.util.Log;

import com.cnkaptan.trivagocodecase.data.Repository;
import com.cnkaptan.trivagocodecase.presentation.base.BasePresenter;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by cihankaptan on 22/08/16.
 */
public class SearchPresenter extends BasePresenter<SearchContract.View> implements SearchContract.Presenter {
    private static final String TAG = SearchPresenter.class.getSimpleName();
    Scheduler ioScheduler, mainScheduler;
    Repository repository;
    private String textQuery;

    public SearchPresenter(Repository repository, Scheduler ioScheduler, Scheduler mainScheduler) {
        this.ioScheduler = ioScheduler;
        this.mainScheduler = mainScheduler;
        this.repository = repository;
    }

    @Override
    public void setQueries(Observable<CharSequence> queries) {
        addSubscription(queries
                .filter(charSequence -> {
                    if (!TextUtils.isEmpty(charSequence)){
                        getView().showLoading();
                        return true;
                    }else{
                        getView().hideLoading();
                        return false;
                    }
                })
                .throttleLast(100, TimeUnit.MILLISECONDS)
                .debounce(200, TimeUnit.MILLISECONDS)
                .onBackpressureLatest()
                .observeOn(mainScheduler)
                .concatMap(charSequence -> {
                    Log.e(TAG,charSequence.toString());
                    getView().setLatestSearchString(charSequence.toString());
                    textQuery = charSequence.toString();
                    return repository.searchMovies(charSequence.toString()).subscribeOn(ioScheduler);
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        searchResults -> {
                            getView().hideLoading();
                            getView().showObservableSearchList(searchResults);
                        },
                        e -> {
                            getView().hideLoading();
                            getView().showError(e);
                        },
                        () -> {
                        }
                ));
    }

    @Override
    public void loadNextPage(final int page) {
        Log.e(TAG, String.format("Text = %s Page = %d", textQuery, page));
        addSubscription(repository.searchMovies(textQuery, page)
                .subscribeOn(ioScheduler)
                .observeOn(mainScheduler)
                .subscribe(
                        searchResults -> getView().addObservableSearchList(searchResults),
                        e -> getView().showError(e),
                        () -> {}
                ));
    }
}
