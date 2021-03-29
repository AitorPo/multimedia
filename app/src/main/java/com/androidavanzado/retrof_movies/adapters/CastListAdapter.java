package com.androidavanzado.retrof_movies.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.androidavanzado.retrof_movies.R;
import com.androidavanzado.retrof_movies.beans.Cast;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;

import java.util.ArrayList;

import static com.androidavanzado.retrof_movies.utils.Constants.IMAGE_BASE_URL;

public class CastListAdapter extends RecyclerView.Adapter<CastListAdapter.ViewHolder> {
    private ArrayList<Cast> castArrayList;
    private Context context;

    public CastListAdapter(Context context, ArrayList<Cast> castArrayList ){
        this.context = context;
        this.castArrayList = castArrayList;
    }


    @NonNull
    @Override
    public CastListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cast_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CastListAdapter.ViewHolder holder, int position) {
        holder.cast = castArrayList.get(position);
        holder.tvName.setText(holder.cast.getName());


        Glide.with(context)
                .load(IMAGE_BASE_URL + holder.cast.getProfilePath())
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        // TODO gestionar pb de imagen
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        // TODO gestionar pb de imagen
                        return false;
                    }
                })
                .apply(RequestOptions.circleCropTransform())
                // .apply(new RequestOptions().placeholder(R.drawable.ic_place_holder).error(R.drawable.ic_place_holder))
                .into(holder.ivProfilePath);
    }

    @Override
    public int getItemCount() {
        return castArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView tvName;
        //public final TextView tvCharacter;
        public final ImageView ivProfilePath;

        public Cast cast;

        public ViewHolder(@NonNull View view) {
            super(view);
            mView = view;
            tvName = view.findViewById(R.id.tvName);
            //tvCharacter = view.findViewById(R.id.tvCharacter);
            ivProfilePath = view.findViewById(R.id.ivProfilePath);
        }
    }
}
