package com.cnkaptan.trivagocodecase.presentation.search;

import android.text.TextUtils;

import com.cnkaptan.trivagocodecase.data.Repository;
import com.cnkaptan.trivagocodecase.data.remote.model.SearchResult;
import com.cnkaptan.trivagocodecase.presentation.base.BasePresenter;

import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;

/**
 * Created by cihankaptan on 22/08/16.
 */
public class SearchPresenter extends BasePresenter<SearchContract.View> implements SearchContract.Presenter {
    Scheduler ioScheduler, mainScheduler;
    Repository repository;

    public SearchPresenter(Repository repository, Scheduler ioScheduler, Scheduler mainScheduler) {
        this.ioScheduler = ioScheduler;
        this.mainScheduler = mainScheduler;
        this.repository = repository;
    }

    @Override
    public void setQueries(Observable<CharSequence> queries) {
        getView().showLoading();
        addSubscription(queries
                .filter(new Func1<CharSequence, Boolean>() {
                    @Override
                    public Boolean call(CharSequence charSequence) {
                        return !TextUtils.isEmpty(charSequence);
                    }
                })
                .throttleLast(100, TimeUnit.MILLISECONDS)
                .debounce(200, TimeUnit.MILLISECONDS)
                .onBackpressureLatest()
                .observeOn(mainScheduler)
                .concatMap(new Func1<CharSequence, Observable<List<SearchResult>>>() {
                    @Override
                    public Observable<List<SearchResult>> call(CharSequence charSequence) {
                        getView().getLatestSearchString(charSequence.toString());
                        return repository.searchMovies(charSequence.toString()).subscribeOn(ioScheduler);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<SearchResult>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().hideLoading();
                        getView().showError(e);
                    }

                    @Override
                    public void onNext(List<SearchResult> searchResults) {
                        getView().hideLoading();
                        getView().showObservableSearchList(searchResults);
                    }
                }));
    }
}
