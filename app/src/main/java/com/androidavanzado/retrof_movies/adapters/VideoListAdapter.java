package com.androidavanzado.retrof_movies.adapters;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.androidavanzado.retrof_movies.R;
import com.androidavanzado.retrof_movies.beans.Video;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

import static com.androidavanzado.retrof_movies.utils.Constants.YOUTUBE_THUMBNAIL_URL;
import static com.androidavanzado.retrof_movies.utils.Constants.YOUTUBE_URL_VIDEO;

public class VideoListAdapter extends RecyclerView.Adapter<VideoListAdapter.ViewHolder>{
    private ArrayList<Video> videos;
    Context context;
    private OnItemClickListener onItemClickListener;

    public VideoListAdapter(Context context, ArrayList<Video> videos, OnItemClickListener onItemClickListener){
        this.context = context;
        this.videos = videos;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.video_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.video = videos.get(position);
        holder.bind(holder.video, onItemClickListener);
        holder.tvName.setText(holder.video.getName());
        Glide.with(context)
                .load(YOUTUBE_THUMBNAIL_URL + holder.video.getKey() + "/" + holder.getAdapterPosition() + ".jpg")

                .into(holder.ivThumbnail);

    }

    @Override
    public int getItemCount() {
        return videos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView tvName;
        public final ImageView ivThumbnail;
        public final ImageView ivPlayVideo;
        public final CardView cvVideo;

        public Video video;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mView = itemView;
            tvName = itemView.findViewById(R.id.tvName);
            //tvNameVideoView = itemView.findViewById(R.id.tvNameVideoView);
            ivThumbnail = itemView.findViewById(R.id.ivThumbnail);
            ivPlayVideo = itemView.findViewById(R.id.ivPlayVideo);
            cvVideo = itemView.findViewById(R.id.cvVideo);

        }
        public void bind(Video video, final VideoListAdapter.OnItemClickListener cardClickListener){
            ivPlayVideo.setOnClickListener(v -> cardClickListener.onCardClick(video.getKey(), getAdapterPosition()));
        }
    }

    public interface OnItemClickListener {
        void onCardClick(String key, int position);
    }
}
