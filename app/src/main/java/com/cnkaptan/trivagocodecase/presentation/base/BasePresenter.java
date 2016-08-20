package com.cnkaptan.trivagocodecase.presentation.base;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by cnkaptan on 20/08/16.
 */
public class BasePresenter<T extends MvpView> implements MvpPresenter<T> {

    private T view;

    private CompositeSubscription compositeSubscription = new CompositeSubscription();

    @Override
    public void attachView(T view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        compositeSubscription.clear();
        view = null;
    }

    public T getView(){
        return view;
    }

    private boolean isViewAttached() {
        return view != null;
    }

    protected void addSubscription(Subscription subscription) {
        this.compositeSubscription.add(subscription);
    }
}
