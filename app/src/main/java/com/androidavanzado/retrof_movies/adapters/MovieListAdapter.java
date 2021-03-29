package com.androidavanzado.retrof_movies.adapters;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.androidavanzado.retrof_movies.R;
import com.androidavanzado.retrof_movies.beans.Movie;
import com.androidavanzado.retrof_movies.utils.OnMovieItemClickListener;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.ArrayList;

import static com.androidavanzado.retrof_movies.utils.Constants.IMAGE_BASE_URL;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.ViewHolder> {

    private ArrayList<Movie> movies;
    Context context;
    private ArrayList<Movie> originalList;
    private MovieListAdapter.OnItemClickListener onItemClickListener;

    public MovieListAdapter(ArrayList<Movie> movies, Context context, MovieListAdapter.OnItemClickListener onItemClickListener) {
        this.movies = movies;
        this.context = context;
        this.onItemClickListener = onItemClickListener;
    }

    public void appendMovies(ArrayList<Movie> moviesToAppend){
        movies.addAll(moviesToAppend);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view, onItemClickListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        holder.movie = movies.get(position);
        //holder.bind(movies.get(position), onItemClickListener);

        holder.tvTitle.setText(holder.movie.getTitle());
        //Obtenemos el string del double que devuelve getVoteAverage()
        holder.tvVote.setText(String.valueOf(holder.movie.getVoteAverage()));
        Glide.with(context).load(IMAGE_BASE_URL + holder.movie.getPoster_path())
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        // TODO gestionar progressbar
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        // TODO gestionar progressbar
                        return false;
                    }
                })
                // .apply(new RequestOptions().placeholder(R.drawable.ic_place_holder).error(R.drawable.ic_place_holder))
                .into(holder.ivPoster);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public final View mView;
        public final TextView tvTitle;
        public final TextView tvVote;
        public final ImageView ivPoster;
        public final CardView cardView;
        public Movie movie;
        public final MovieListAdapter.OnItemClickListener onItemClickListener;

        public ViewHolder(View view, MovieListAdapter.OnItemClickListener onItemClickListener) {
            super(view);
            mView = view;
            tvTitle = view.findViewById(R.id.tvTitle);
            tvVote = view.findViewById(R.id.tvVoteAverage);
            ivPoster = view.findViewById(R.id.ivPoster);
            cardView = view.findViewById(R.id.cardView);
            this.onItemClickListener = onItemClickListener;

            view.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            onItemClickListener.onCardClick(getAdapterPosition());
        }
    }
    public interface OnItemClickListener {
        void onCardClick(int position);
    }
}