package com.androidavanzado.retrof_movies.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.androidavanzado.retrof_movies.R;
import com.androidavanzado.retrof_movies.beans.Genre;

import java.util.ArrayList;

public class GenreDetailAdapter extends RecyclerView.Adapter<GenreDetailAdapter.ViewHolder> {
    private ArrayList<Genre> genres;
    Context context;

    public GenreDetailAdapter(ArrayList<Genre> genres, Context context) {
        this.genres = genres;
        this.context = context;
    }


    @Override
    public GenreDetailAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.genre_item_detail, parent, false);
        GenreDetailAdapter.ViewHolder viewHolder = new GenreDetailAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(GenreDetailAdapter.ViewHolder holder, int position) {

        holder.genre = genres.get(position);
        holder.tvGenreDetail.setText(holder.genre.getName());
    }

    @Override
    public int getItemCount() {
        return genres.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView tvGenreDetail;
        public final CardView cardViewGenreDetail;

        public Genre genre;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            tvGenreDetail = view.findViewById(R.id.tvGenreDetail);
            cardViewGenreDetail = view.findViewById(R.id.cardViewGenreDetail);
        }

    }
}
