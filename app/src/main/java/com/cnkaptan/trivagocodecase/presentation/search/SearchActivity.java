package com.cnkaptan.trivagocodecase.presentation.search;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.cnkaptan.trivagocodecase.R;
import com.cnkaptan.trivagocodecase.TrackTvApplication;
import com.cnkaptan.trivagocodecase.data.Repository;
import com.cnkaptan.trivagocodecase.data.remote.model.SearchResult;
import com.cnkaptan.trivagocodecase.util.OnLoadMoreListener;
import com.jakewharton.rxbinding.support.v7.widget.RxSearchView;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class SearchActivity extends AppCompatActivity implements SearchContract.View {
    private static final String TAG = SearchActivity.class.getSimpleName();
    SearchView searchView;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.recycler_view_search_result)
    RecyclerView recyclerViewSearchResult;
    @Bind(R.id.text_view_error_msg)
    TextView textViewErrorMsg;
    @Bind(R.id.progress_bar)
    ProgressBar progressBar;

    @Inject Repository repository;
    SearchContract.Presenter searchPresenter;
    private SearchResultAdapter searchResultAdapter;
    private String latestTerm;
    private OnLoadMoreListener onLoadMoreListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        ((TrackTvApplication)getApplication()).getmAppComponent().inject(this);
        searchPresenter = new SearchPresenter(repository, Schedulers.io(), AndroidSchedulers.mainThread());
        searchPresenter.attachView(this);


        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewSearchResult.setLayoutManager(manager);
        searchResultAdapter = new SearchResultAdapter(null);
        recyclerViewSearchResult.setAdapter(searchResultAdapter);

        onLoadMoreListener = new OnLoadMoreListener(manager) {
            @Override
            public void onLoadMore(int page) {
                searchPresenter.loadNextPage(page);
            }
        };
        recyclerViewSearchResult.addOnScrollListener(onLoadMoreListener);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_main, menu);
        final MenuItem searchMenuitem = menu.findItem(R.id.menu_search);
        searchView = (SearchView) searchMenuitem.getActionView();
        searchPresenter.setQueries(RxSearchView.queryTextChanges(searchView));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        searchPresenter.detachView();
    }

    @Override
    public void showError(Throwable t) {
        textViewErrorMsg.setVisibility(View.VISIBLE);
        recyclerViewSearchResult.setVisibility(View.GONE);
        textViewErrorMsg.setText(t.getMessage());
    }

    @Override
    public void showObservableSearchList(List<SearchResult> searchResults) {
        searchResultAdapter.setSearchResults(searchResults);
    }

    @Override
    public void addObservableSearchList(List<SearchResult> searchResults) {
        searchResultAdapter.addSearchResults(searchResults);
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
        recyclerViewSearchResult.setVisibility(View.GONE);
        textViewErrorMsg.setVisibility(View.GONE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
        recyclerViewSearchResult.setVisibility(View.VISIBLE);
        textViewErrorMsg.setVisibility(View.GONE);

    }

    @Override
    public void setLatestSearchString(String latestTerm) {
        this.latestTerm = latestTerm;
        Log.e(TAG,latestTerm);
        onLoadMoreListener.clearPage();
    }


}
