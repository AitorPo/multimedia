package com.androidavanzado.asynctasktestmovies_v2.movies.listMovies.view;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.androidavanzado.asynctasktestmovies_v2.R;
import com.androidavanzado.asynctasktestmovies_v2.beans.Movie;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.ViewHolder> {

    private ArrayList<Movie> movies;
    Context context;
    private OnCardClickListener cardClickListener;

    public MovieListAdapter(ArrayList<Movie> movies, Context context, OnCardClickListener cardClickListener) {
        this.movies = movies;
        this.context = context;
        this.cardClickListener = cardClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        holder.movie = movies.get(position);
        holder.bind(movies.get(position),cardClickListener);

        holder.tvTitle.setText(holder.movie.getTitle());
        //Obtenemos el string del double que devuelve getVoteAverage()
        holder.tvVote.setText(String.valueOf(holder.movie.getVoteAverage()));
        Glide.with(context).load("https://image.tmdb.org/t/p/original" + holder.movie.getPoster_path())
                .into(holder.ivPoster);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView tvTitle;
        public final TextView tvVote;
        public final ImageView ivPoster;
        public final CardView cardView;
        public Movie movie;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            tvTitle = view.findViewById(R.id.tvGenre);
            tvVote = view.findViewById(R.id.tvVote);
            ivPoster = view.findViewById(R.id.ivPoster);
            cardView = view.findViewById(R.id.cardViewGenre);
        }

        public void bind(Movie movie, final OnCardClickListener cardClickListener){
            cardView.setOnClickListener(v -> cardClickListener.onCardClick(movie.getId(), getAdapterPosition()));
        }

    }
    public interface OnCardClickListener{
        void onCardClick(int id, int position);
    }
}