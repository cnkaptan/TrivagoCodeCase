package com.cnkaptan.trivagocodecase.presentation.search;

import com.cnkaptan.trivagocodecase.data.remote.model.SearchResult;
import com.cnkaptan.trivagocodecase.presentation.base.MvpPresenter;
import com.cnkaptan.trivagocodecase.presentation.base.MvpView;

import java.util.List;

import rx.Observable;

/**
 * Created by cihankaptan on 22/08/16.
 */
public interface SearchContract {

    interface View extends MvpView{
        void showError(Throwable t);

        void showObservableSearchList(List<SearchResult> searchResults);

        void showLoading();

        void hideLoading();

        void getLatestSearchString(String latestTerm);
    }

    interface Presenter extends MvpPresenter<View>{
        void setQueries(Observable<CharSequence> queries);
    }
}
