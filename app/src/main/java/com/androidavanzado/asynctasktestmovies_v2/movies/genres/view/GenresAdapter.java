package com.androidavanzado.asynctasktestmovies_v2.movies.genres.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.androidavanzado.asynctasktestmovies_v2.R;
import com.androidavanzado.asynctasktestmovies_v2.beans.Genre;


import java.util.ArrayList;

public class GenresAdapter extends RecyclerView.Adapter<GenresAdapter.ViewHolder> {

    private ArrayList<Genre> genres;
    Context context;
    private OnCardClickListener cardClickListener;

    public GenresAdapter(ArrayList<Genre> genres, Context context, OnCardClickListener cardClickListener) {
        this.genres = genres;
        this.context = context;
        this.cardClickListener = cardClickListener;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.genre_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.genre = genres.get(position);
        holder.bind(genres.get(position), cardClickListener);
        holder.tvGenre.setText(holder.genre.getName());
    }

    @Override
    public int getItemCount() {
        return genres.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView tvGenre;
        public final CardView cardViewGenre;

        public Genre genre;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            tvGenre = view.findViewById(R.id.tvGenre);
            cardViewGenre = view.findViewById(R.id.cardViewGenre);
        }

        public void bind(Genre genre, final GenresAdapter.OnCardClickListener cardClickListener){
            cardViewGenre.setOnClickListener(v -> cardClickListener.onCardClick(genre.getId(), getAdapterPosition()));
        }

    }
    public interface OnCardClickListener{
        void onCardClick(int id, int position);
    }
}

