package com.mahersoua.popularmovies.utils;

import android.util.Log;

import com.mahersoua.popularmovies.models.MovieModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {
    private static final String TAG = "JsonUtils";

    public static List<MovieModel> getMoviesArray(JSONArray jsonArray){
        List<MovieModel> movieModelList = new ArrayList<>();

        try {
            for(int i = 0; i < jsonArray.length(); i++){
                JSONObject jsonItem = jsonArray.getJSONObject(i);
                MovieModel movieModel = new MovieModel();
                movieModel.setBackdropPath(jsonItem.getString("backdrop_path"));
                movieModel.setVoteAverage(jsonItem.getInt("vote_average"));
                movieModel.setAdult(jsonItem.getBoolean("adult"));
                movieModel.setId(jsonItem.getInt("id"));
                movieModel.setTitle(jsonItem.getString("title"));
                movieModel.setOverview(jsonItem.getString("overview"));
                movieModel.setOriginalLanguage(jsonItem.getString("original_language"));
                movieModel.setReleaseDate(jsonItem.getString("release_date"));
                movieModel.setOriginalTitle(jsonItem.getString("original_title"));
                movieModel.setVoteCount(jsonItem.getInt("vote_count"));
                movieModel.setPosterPath(jsonItem.getString("poster_path"));
                movieModel.setVideo(jsonItem.getBoolean("video"));
                movieModel.setPopularity(jsonItem.getDouble("popularity"));

                JSONArray genreIdsObj = jsonItem.getJSONArray("genre_ids");
                int[] genreIds = new int[genreIdsObj.length()];
                for(int j = 0; j < genreIdsObj.length(); j++){
                    genreIds[j] = genreIdsObj.getInt(j);
                }
                movieModel.setGenreIds(genreIds);

                movieModelList.add(movieModel);
            }
        } catch (JSONException error){
            Log.d(TAG , ">>>>>>>>> "+error.getMessage());
        }

        Log.d(TAG , ""+movieModelList.size());
        return movieModelList;
    }
}
