package com.mahersoua.popularmovies.data;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class MoviesDataLoader implements LoaderManager.LoaderCallbacks<JSONObject> {
    private static final String TAG = "MoviesDataLoader";
    public static final String API_URL_POPULAR = "https://api.themoviedb.org/3/movie/popular?api_key=c4cc0326a21919184ed91baf888a80a9&language=en-US&page=1";
    public static final String API_URL_HIGHEST_RATED = "https://api.themoviedb.org/3/movie/top_rated?api_key=c4cc0326a21919184ed91baf888a80a9&language=en-US&page=1";
    public static final String DEFAUL_URL = API_URL_POPULAR;

    private static String mCurrentUrl;
    private static AppCompatActivity mActivity = null;
    private static MoviesDataLoader mInstance = null;
    private boolean isLoaderInit = false;


    private final int LOADER_ID = 11;
    public static MoviesDataLoader getInstance(){
        if(mInstance == null){
            mInstance = new MoviesDataLoader();
        }
        return mInstance;
    }

    public void requestMovieList(AppCompatActivity activity, String url){
        mActivity = activity;
        mCurrentUrl = url;
        if(!isLoaderInit){
            mActivity.getSupportLoaderManager().initLoader(LOADER_ID, null , this);
            isLoaderInit = true;
        } else {
            mActivity.getSupportLoaderManager().restartLoader(LOADER_ID, null , this);
        }
        ((IMoviesCallback)mActivity).onStartLoading();
    }

    @NonNull
    @Override
    public Loader onCreateLoader(int id, @Nullable Bundle args) {
        return new AsyncTaskLoader(mActivity) {
            String mMovieResponse = null;
            @Override
            protected void onStartLoading() {
                if(mMovieResponse == null){
                    forceLoad();
                } else {
                    deliverResult(mMovieResponse);
                }
            }
            @Nullable
            @Override
            public JSONObject loadInBackground() {
                HttpsURLConnection connection = null;
                JSONObject responseObject = null;
               try {
                    URL url = new URL(mCurrentUrl);
                    connection = (HttpsURLConnection) url.openConnection();
                    connection.connect();

                    int responseCode = connection.getResponseCode();
                    if(responseCode != HttpsURLConnection.HTTP_OK){
                        throw new IOException("HTTP code error: "+responseCode);
                    }
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
                    StringBuilder stringBuilder = new StringBuilder();
                    String readLine;
                    while ((readLine = bufferedReader.readLine()) != null){
                        stringBuilder.append(readLine);
                    }

                    responseObject = new JSONObject(stringBuilder.toString());
                } catch (MalformedURLException e) {
                    Log.d(TAG, e.getMessage());
                } catch (IOException exception){
                    Log.d(TAG , exception.getMessage());
                } catch (JSONException exception){
                   Log.d(TAG , exception.getMessage());
               }
                return responseObject;
            }

            public void deliverResult(String data){
                mMovieResponse = data;
                super.deliverResult(data);
            }
        };
    }

    @Override
    public void onLoadFinished(@NonNull Loader<JSONObject> loader, JSONObject data) {
        try{
            ((IMoviesCallback)mActivity).onLoadFinished(data.getJSONArray("results"));
        } catch (JSONException exception){
            Log.d(TAG , "***>>> "+exception.getMessage());
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader loader) {

    }

    public interface IMoviesCallback {
        void onLoadFinished(JSONArray jsonArray);
        void onStartLoading();
        void onLoadError(String error);
    }
}