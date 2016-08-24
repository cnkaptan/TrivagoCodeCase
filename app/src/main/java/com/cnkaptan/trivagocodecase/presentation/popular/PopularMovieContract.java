package com.cnkaptan.trivagocodecase.presentation.popular;

import com.cnkaptan.trivagocodecase.data.remote.model.Movie;
import com.cnkaptan.trivagocodecase.presentation.base.MvpPresenter;
import com.cnkaptan.trivagocodecase.presentation.base.MvpView;

import java.util.List;

/**
 * Created by cnkaptan on 21/08/16.
 */
public interface PopularMovieContract {

    interface View extends MvpView{
        void showError(Throwable t);
        void showInitDatas(List<Movie> movies);
        void loadMoreSuccess(List<Movie> extraMovies);
        void showLoading();
        void hideLoading();
    }

    interface Presenter extends MvpPresenter<View>{
        void initData();
        void loadMore(int page);
    }
}
