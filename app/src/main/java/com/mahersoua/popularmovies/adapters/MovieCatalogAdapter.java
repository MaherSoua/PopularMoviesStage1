package com.mahersoua.popularmovies.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.mahersoua.popularmovies.DetailsActivity;
import com.mahersoua.popularmovies.R;
import com.mahersoua.popularmovies.models.MovieModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MovieCatalogAdapter extends RecyclerView.Adapter<MovieCatalogAdapter.CatalogMovieHolder> {

    public final static String MOVIE_EXTRAS = "MovieExtras";
    Context mContext;
    List<MovieModel> mMovieList = new ArrayList<>();

    String URL = "http://image.tmdb.org/t/p/w500/";

    private final String TAG = getClass().getSimpleName();

    public MovieCatalogAdapter(Context context, List<MovieModel> list){
        mContext = context;
        if(list != null){
            mMovieList = list;
        }
    }

    @NonNull
    @Override
    public CatalogMovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.catalog_movie_list_item, parent, false);
        return new CatalogMovieHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CatalogMovieHolder holder, int position) {
        MovieModel movieModel = mMovieList.get(position);
        Picasso.get().load(URL+movieModel.getPosterPath()).resize(540, 900).centerCrop().into(holder.posterContainer);
        holder.mItemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return mMovieList.size();
    }


    public void updateList(List<MovieModel> list){
        if(list != null){
            mMovieList = list;
        }
        notifyDataSetChanged();
    }

    class CatalogMovieHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView posterContainer;
        public View mItemView;
        public CatalogMovieHolder(View itemView) {
            super(itemView);
            posterContainer = itemView.findViewById(R.id.poserContainer);
            mItemView = itemView;
            mItemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(mContext, DetailsActivity.class);
            intent.putExtra(MOVIE_EXTRAS, mMovieList.get(Integer.valueOf(v.getTag().toString())));
            mContext.startActivity(intent);
        }
    }
}
