package com.mahersoua.popularmovies;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.mahersoua.popularmovies.adapters.MovieCatalogAdapter;
import com.mahersoua.popularmovies.utils.JsonUtils;
import com.mahersoua.popularmovies.data.MoviesDataLoader;

import org.json.JSONArray;

public class CatalogActivity extends AppCompatActivity implements MoviesDataLoader.IMoviesCallback {

    private static final String TAG = "CatalogActivity";
    private MovieCatalogAdapter movieCatalogAdapter;
    private RecyclerView movieRecyclerView;
    private int lastSelectedItemId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.mahersoua.popularmovies.R.layout.activity_catalog);
        MoviesDataLoader.getInstance().requestMovieList(this , MoviesDataLoader.DEFAUL_URL);

        movieRecyclerView = findViewById(R.id.listContainer);
        RecyclerView.LayoutManager recyclerViewLayoutManager = new GridLayoutManager(this , 2);

        movieRecyclerView.setLayoutManager(recyclerViewLayoutManager);

        movieCatalogAdapter = new MovieCatalogAdapter(this, null);
        movieRecyclerView.setAdapter(movieCatalogAdapter);
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
        MenuItem menuItem = menu.getItem(0);
        getSupportActionBar().setSubtitle(menuItem.getTitle());
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
                MoviesDataLoader.getInstance().requestMovieList(this, MoviesDataLoader.API_URL_POPULAR);
                break;

            case R.id.highest_rated:
                MoviesDataLoader.getInstance().requestMovieList(this, MoviesDataLoader.API_URL_HIGHEST_RATED);
                break;
        }
        movieRecyclerView.smoothScrollToPosition(0);
        getSupportActionBar().setSubtitle(item.getTitle());
        lastSelectedItemId = item.getItemId();
        return super.onOptionsItemSelected(item);
    }
}
