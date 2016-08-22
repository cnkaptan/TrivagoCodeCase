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
        void receiveObservableSearchList(Observable<List<SearchResult>> listObservable);
    }

    interface Presenter extends MvpPresenter<View>{
        void takeObservableSearchList(CharSequence charSequence);
    }
}
