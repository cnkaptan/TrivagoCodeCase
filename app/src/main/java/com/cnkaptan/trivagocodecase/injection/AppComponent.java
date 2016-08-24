package com.cnkaptan.trivagocodecase.injection;

import com.cnkaptan.trivagocodecase.presentation.popular.PopularMoviesActivity;
import com.cnkaptan.trivagocodecase.presentation.search.SearchActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by cihankaptan on 24/08/16.
 */
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    void inject(SearchActivity searchActivity);
    void inject(PopularMoviesActivity popularMoviesActivity);
}
