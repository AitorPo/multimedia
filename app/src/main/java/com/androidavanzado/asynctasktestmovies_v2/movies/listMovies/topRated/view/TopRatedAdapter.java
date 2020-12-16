package com.androidavanzado.asynctasktestmovies_v2.movies.listMovies.topRated.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.androidavanzado.asynctasktestmovies_v2.R;
import com.androidavanzado.asynctasktestmovies_v2.beans.Movie;
import com.androidavanzado.asynctasktestmovies_v2.movies.listMovies.popularList.view.MovieListAdapter;
import com.bumptech.glide.Glide;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class TopRatedAdapter extends RecyclerView.Adapter<TopRatedAdapter.ViewHolder> {

    private ArrayList<Movie> topRatedMovies;
    Context context;
    private OnCardClickListener onCardClickListener;


    public TopRatedAdapter(ArrayList<Movie> topRatedMovies, Context context, OnCardClickListener onCardClickListener){
        this.topRatedMovies = topRatedMovies;
        this.context = context;
        this.onCardClickListener = onCardClickListener;
    }
    @NonNull
    @Override
    public TopRatedAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TopRatedAdapter.ViewHolder holder, int position) {
        holder.movie = topRatedMovies.get(position);
        holder.bind(topRatedMovies.get(position),onCardClickListener);

        holder.tvTitle.setText(holder.movie.getTitle());
        //Obtenemos el string del double que devuelve getVoteAverage()
        holder.tvVote.setText(String.valueOf(holder.movie.getVoteAverage()));
        Glide.with(context).load("https://image.tmdb.org/t/p/original" + holder.movie.getPoster_path())
                .into(holder.ivPoster);
    }

    @Override
    public int getItemCount() {
        return topRatedMovies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public final TextView tvTitle;
        public final TextView tvVote;
        public final ImageView ivPoster;
        public final CardView cardView;
        public Movie movie;

        public ViewHolder(View view) {
            super(view);
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
