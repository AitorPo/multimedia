package com.androidavanzado.asynctasktestmovies_v2.movies.listMovies.genreList.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.androidavanzado.asynctasktestmovies_v2.R;
import com.androidavanzado.asynctasktestmovies_v2.beans.Movie;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class GenreListAdapter extends RecyclerView.Adapter<GenreListAdapter.ViewHolder> {

    private ArrayList<Movie> genreMovies;
    Context context;
    private OnCardClickListener cardClickListener;

    public GenreListAdapter(ArrayList<Movie> genreMovies, Context context, OnCardClickListener cardClickListener){
        this.genreMovies = genreMovies;
        this.context = context;
        this.cardClickListener = cardClickListener;
    }

    public GenreListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(GenreListAdapter.ViewHolder holder, int position) {
        holder.movie = genreMovies.get(position);
        holder.bind(genreMovies.get(position), cardClickListener);

        holder.tvTitle.setText(holder.movie.getTitle());
        //Obtenemos el string del double que devuelve getVoteAverage()
        holder.tvVote.setText(String.valueOf(holder.movie.getVoteAverage()));
        Glide.with(context).load("https://image.tmdb.org/t/p/original" + holder.movie.getPoster_path())
                .into(holder.ivPoster);
    }

    @Override
    public int getItemCount() {
        return genreMovies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public final View mView;
        public final TextView tvTitle;
        public final TextView tvVote;
        public final ImageView ivPoster;
        public final CardView cardView;
        public Movie movie;

        public ViewHolder(View view){
            super(view);
            mView = view;
            tvTitle = view.findViewById(R.id.tvGenre);
            tvVote = view.findViewById(R.id.tvVote);
            ivPoster = view.findViewById(R.id.ivPoster);
            cardView = view.findViewById(R.id.cardViewGenre);
        }
        public void bind(Movie movie, final GenreListAdapter.OnCardClickListener cardClickListener){
            cardView.setOnClickListener(v -> cardClickListener.onCardClick(movie.getId(), getAdapterPosition()));
        }

    }
    public interface OnCardClickListener{
        void onCardClick(int id, int position);
    }
}
