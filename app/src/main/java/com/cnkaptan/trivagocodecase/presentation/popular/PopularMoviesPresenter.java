package com.cnkaptan.trivagocodecase.presentation.popular;

import com.cnkaptan.trivagocodecase.data.Repository;
import com.cnkaptan.trivagocodecase.presentation.base.BasePresenter;

import rx.Scheduler;

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
                .subscribe(
                        movies -> getView().showInitDatas(movies),
                        throwable -> getView().showError(throwable),
                        () -> {
                        }));
    }

    @Override
    public void loadMore(int page) {
        addSubscription(repository.getPopularMovies(page)
                .subscribeOn(ioSchedular)
                .observeOn(mainSchedular)
                .subscribe(
                        movies -> getView().loadMoreSuccess(movies),
                        e -> getView().showError(e),
                        () -> {
                        }));

    }
}
