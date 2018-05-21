package com.mahersoua.popularmovies;

import android.content.Context;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.mahersoua.popularmovies.adapters.MovieCatalogAdapter;
import com.mahersoua.popularmovies.utils.JsonUtils;
import com.mahersoua.popularmovies.data.MoviesDataLoader;

import org.json.JSONArray;

public class CatalogActivity extends AppCompatActivity implements MoviesDataLoader.IMoviesCallback {

    private MovieCatalogAdapter movieCatalogAdapter;
    private RecyclerView movieRecyclerView;
    private int lastSelectedItemId;
    private String mCurrentApiUrl;
    private int mCurrentItemIndex = 0;
    private final String MENU_ITEM_INDEX = "menu_item_selected_index";
    private final String API_URL = "api_url";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);
        movieRecyclerView = findViewById(R.id.listContainer);

        if(hasInternetAccess()){
            findViewById(R.id.connectionErrorTv).setVisibility(View.VISIBLE);
            findViewById(R.id.progressBar).setVisibility(View.INVISIBLE);
            return;
        } else {
            findViewById(R.id.connectionErrorTv).setVisibility(View.INVISIBLE);
        }

        int spanCount;
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            spanCount = 2;
        } else {
            spanCount = 3;
        }
        RecyclerView.LayoutManager recyclerViewLayoutManager = new GridLayoutManager(this , spanCount);

        movieRecyclerView.setLayoutManager(recyclerViewLayoutManager);
        movieCatalogAdapter = new MovieCatalogAdapter(this, null);
        movieRecyclerView.setAdapter(movieCatalogAdapter);

        mCurrentApiUrl = MoviesDataLoader.DEFAULT_URL;

        if(savedInstanceState == null){
            MoviesDataLoader.getInstance().requestMovieList(this , mCurrentApiUrl);
        } else {
            mCurrentApiUrl = savedInstanceState.getString(API_URL, MoviesDataLoader.DEFAULT_URL);
            mCurrentItemIndex = savedInstanceState.getInt(MENU_ITEM_INDEX , 0);
            MoviesDataLoader.getInstance().requestMovieList(this , mCurrentApiUrl);
            movieRecyclerView.smoothScrollToPosition(0);
        }
    }

    private boolean hasInternetAccess(){
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if(null != connectivityManager){
            NetworkInfo info  = connectivityManager.getActiveNetworkInfo();
            return null == info || !info.isConnectedOrConnecting();
        }
        return false;
    }

    @Override
    public void onLoadFinished(JSONArray jsonArray) {
        movieCatalogAdapter.updateList(JsonUtils.getMoviesArray(jsonArray));
        findViewById(R.id.progressBar).setVisibility(View.INVISIBLE);
    }

    @Override
    public void onStartLoading() {
        findViewById(R.id.progressBar).setVisibility(View.VISIBLE);
    }

    @Override
    public void onLoadError(String error) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.movie_filter, menu);
        MenuItem menuItem = menu.getItem(mCurrentItemIndex);
        if(null != getSupportActionBar()){
            getSupportActionBar().setSubtitle(menuItem.getTitle());
        }
        lastSelectedItemId = menuItem.getItemId();
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(lastSelectedItemId == item.getItemId()){
            return false;
        }
        switch (item.getItemId()){
            case R.id.most_popular:
                mCurrentApiUrl = MoviesDataLoader.API_URL_POPULAR;
                mCurrentItemIndex = 0;
                break;

            case R.id.highest_rated:
                mCurrentApiUrl = MoviesDataLoader.API_URL_HIGHEST_RATED;
                mCurrentItemIndex = 1;
                break;
        }
        MoviesDataLoader.getInstance().requestMovieList(this, mCurrentApiUrl);

        movieRecyclerView.smoothScrollToPosition(0);
        if(null != getSupportActionBar()){
            getSupportActionBar().setSubtitle(item.getTitle());
        }
        lastSelectedItemId = item.getItemId();
        return false;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(MENU_ITEM_INDEX, mCurrentItemIndex);
        outState.putString(API_URL, mCurrentApiUrl);
        super.onSaveInstanceState(outState);
    }
}
