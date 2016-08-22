package com.cnkaptan.trivagocodecase.presentation.popular;

import com.cnkaptan.trivagocodecase.data.Repository;
import com.cnkaptan.trivagocodecase.data.remote.model.Movie;
import com.cnkaptan.trivagocodecase.presentation.base.BasePresenter;

import java.util.List;

import rx.Scheduler;
import rx.Subscriber;

/**
 * Created by cnkaptan on 21/08/16.
 */
public class PopularMoviesPresenter extends BasePresenter<PopularMovieContract.View> implements PopularMovieContract.Presenter {

    private Repository repository;
    private Scheduler ioSchedular, mainSchedular;

    public PopularMoviesPresenter(Repository repository, Scheduler ioSchedular, Scheduler mainSchedular) {
        this.repository = repository;
        this.ioSchedular = ioSchedular;
        this.mainSchedular = mainSchedular;
    }

    @Override
    public void initData() {

        addSubscription(repository.getPopularMovies(1)
                .subscribeOn(ioSchedular)
                .observeOn(mainSchedular)
                .subscribe(new Subscriber<List<Movie>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().showError(e);
                    }

                    @Override
                    public void onNext(List<Movie> movies) {
                        getView().showInitDatas(movies);
                    }
                }));
    }

    @Override
    public void loadMore(int page) {
        addSubscription(repository.getPopularMovies(page)
        .subscribeOn(ioSchedular)
        .observeOn(mainSchedular)
        .subscribe(new Subscriber<List<Movie>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                getView().showError(e);
            }

            @Override
            public void onNext(List<Movie> movies) {
                getView().loadMoreSuccess(movies);
            }
        }));
    }
}
