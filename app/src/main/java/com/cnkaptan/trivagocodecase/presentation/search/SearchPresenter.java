package com.cnkaptan.trivagocodecase.presentation.search;

import com.cnkaptan.trivagocodecase.data.Repository;
import com.cnkaptan.trivagocodecase.presentation.base.BasePresenter;

import rx.Scheduler;

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
    public void takeObservableSearchList(CharSequence charSequence) {

    }
}
