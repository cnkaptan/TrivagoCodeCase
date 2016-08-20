package com.cnkaptan.trivagocodecase.presentation.base;

/**
 * Created by cnkaptan on 20/08/16.
 */
public interface MvpPresenter<V extends MvpView> {

    void attachView(V view);

    void detachView();
}
