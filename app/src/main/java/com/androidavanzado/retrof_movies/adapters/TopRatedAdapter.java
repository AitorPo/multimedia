package com.androidavanzado.retrof_movies.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.androidavanzado.retrof_movies.R;
import com.androidavanzado.retrof_movies.beans.Movie;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

import static com.androidavanzado.retrof_movies.utils.Constants.IMAGE_BASE_URL;

public class TopRatedAdapter extends RecyclerView.Adapter<TopRatedAdapter.ViewHolder> {

    private ArrayList<Movie> topRatedMovies;
    Context context;
    private OnItemClickListener onCardClickListener;


    public TopRatedAdapter(ArrayList<Movie> topRatedMovies, Context context, OnItemClickListener onCardClickListener){
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
        Glide.with(context).load(IMAGE_BASE_URL + holder.movie.getPoster_path())
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
            tvTitle = view.findViewById(R.id.tvTitle);
            tvVote = view.findViewById(R.id.tvVoteAverage);
            ivPoster = view.findViewById(R.id.ivPoster);
            cardView = view.findViewById(R.id.cardView);
        }

        public void bind(Movie movie, final OnItemClickListener onItemClickListener){
            cardView.setOnClickListener(v -> onItemClickListener.onCardClick(movie.getId(), getAdapterPosition()));
        }
    }
    public interface OnItemClickListener {
        void onCardClick(int id, int position);
    }

}
