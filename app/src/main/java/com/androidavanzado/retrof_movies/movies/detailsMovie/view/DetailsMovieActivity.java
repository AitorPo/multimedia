package com.androidavanzado.retrof_movies.movies.detailsMovie.view;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.androidavanzado.retrof_movies.R;
import com.androidavanzado.retrof_movies.adapters.CastListAdapter;
import com.androidavanzado.retrof_movies.adapters.GenreDetailAdapter;
import com.androidavanzado.retrof_movies.adapters.VideoListAdapter;
import com.androidavanzado.retrof_movies.beans.Cast;
import com.androidavanzado.retrof_movies.beans.DetailsMovie;
import com.androidavanzado.retrof_movies.beans.Genre;
import com.androidavanzado.retrof_movies.beans.Video;
import com.androidavanzado.retrof_movies.movies.detailsMovie.contract.DetailsMovieContract;
import com.androidavanzado.retrof_movies.movies.detailsMovie.presenter.DetailsMoviePresenter;
import com.androidavanzado.retrof_movies.movies.genres.view.GenresActivity;
import com.androidavanzado.retrof_movies.movies.listMovies.topRated.view.TopRatedActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import static com.androidavanzado.retrof_movies.utils.Constants.BACKDROP_BASE_URL;
import static com.androidavanzado.retrof_movies.utils.Constants.IMAGE_BASE_URL;
import static com.androidavanzado.retrof_movies.utils.Constants.MOVIE_ID;
import static com.androidavanzado.retrof_movies.utils.Constants.YOUTUBE_URL_VIDEO;

public class DetailsMovieActivity extends AppCompatActivity implements DetailsMovieContract.View {
    private static final String TAG = "DetailsMovieActivity";
    private DetailsMoviePresenter presenter;
    private TextView tvTitle;
    private ImageView ivPoster;
    private ImageView ivPosterPath;
    private TextView tvVoteCount;
    private TextView tvOverview;
    private TextView tvVoteAverage;
    private TextView tvNameAndType;
    private TextView tvOriginalLanguage;
    private TextView tvRuntime;
    private TextView tvOriginalTitle;
    private TextView tvHomepage;
    private TextView tvDate;
    private ProgressBar pbProgressDetail;
    private ProgressBar pbCastProgress;
    private ProgressBar pbVideoProgress;
    private ArrayList<Cast> castArrayList;
    private ArrayList<Genre> genreArrayList;
    private ArrayList<Video> videoArrayList;
    private CastListAdapter castAdapter;
    private GenreDetailAdapter genreDetailAdapter;
    private VideoListAdapter videoListAdapter;
    private Toolbar toolbar;
    private CollapsingToolbarLayout toolbarLayout;
    private RecyclerView.LayoutManager stagLayoutManager;
    private LinearLayoutManager linearLayoutManager;
    private SwipeRefreshLayout refreshLayout;
    private RecyclerView rvTrailer;
    private VideoListAdapter.OnItemClickListener onItemClickListener;
    public static int idMovie;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_detail);
        toolbar = findViewById(R.id.tbDetails);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarLayout = findViewById(R.id.toolbarLayout);
        //toolbar.setTitle(tvTitle.getText());

        initComponents();

        Intent intent = getIntent();
        int movieId = intent.getIntExtra(MOVIE_ID, 0);

        presenter = new DetailsMoviePresenter(this);
        presenter.getDetails(movieId);
}

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void initComponents(){
        castArrayList = new ArrayList<>();
        genreArrayList = new ArrayList<>();
        videoArrayList = new ArrayList<>();

        refreshLayout = findViewById(R.id.refreshDetail);

        RecyclerView rvCast = this.findViewById(R.id.rvCast);
        RecyclerView rvGenre = this.findViewById(R.id.rvGenreDetail);
        rvTrailer = this.findViewById(R.id.rvTrailerDetail);

        castAdapter = new CastListAdapter(this, castArrayList);
        genreDetailAdapter = new GenreDetailAdapter(genreArrayList, this);

        stagLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);


        rvGenre.setLayoutManager(stagLayoutManager);
        rvTrailer.setLayoutManager(linearLayoutManager);

        rvCast.setAdapter(castAdapter);
        rvGenre.setAdapter(genreDetailAdapter);

        tvTitle = this.findViewById(R.id.tvGenre);
        ivPoster = this.findViewById(R.id.ivPoster);
        ivPosterPath = this.findViewById(R.id.ivPosterPath);
        tvVoteCount = this.findViewById(R.id.tvVoteCount);
        tvOverview = this.findViewById(R.id.tvOverview);
        tvVoteAverage = this.findViewById(R.id.tvVoteAverage);
        //tvNameAndType = this.findViewById(R.id.tvNameAndType);
        tvOriginalLanguage = this.findViewById(R.id.tvOriginLang);
        tvDate = this.findViewById(R.id.tvDate);
        //tvRuntime = this.findViewById(R.id.tvRuntime);
        //tvOriginalTitle = this.findViewById(R.id.tvOriginalTitle);
        //tvHomepage = this.findViewById(R.id.tvHomepage);
        pbProgressDetail = this.findViewById(R.id.pbProgressDetail);
        pbCastProgress = this.findViewById(R.id.pbCastProgress);
        pbVideoProgress = this.findViewById(R.id.pbVideoProgress);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.itemGenre){
            Intent intent = new Intent(DetailsMovieActivity.this, GenresActivity.class);
            startActivity(intent);

        } else if (id == R.id.itemMostRated){
            Intent intent = new Intent(DetailsMovieActivity.this, TopRatedActivity.class);
            startActivity(intent);

        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void showProgress() {
        pbProgressDetail.setVisibility(View.VISIBLE);
        pbCastProgress.setVisibility(View.VISIBLE);
        pbVideoProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        pbProgressDetail.setVisibility(View.GONE);
        pbCastProgress.setVisibility(View.GONE);
        pbVideoProgress.setVisibility(View.GONE);
    }

    private void setMovieData(DetailsMovie detailsMovie){

        if (detailsMovie != null) {
            tvTitle.setText(detailsMovie.getTitle());
            //toolbarLayout.setTitle(tvTitle.getText());

            tvVoteCount.setText(String.valueOf(detailsMovie.getVote_count() + " votos"));
            Glide.with(this).load(BACKDROP_BASE_URL + detailsMovie.getPoster_path())
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            // TODO gestionar pb de la imagen
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            // TODO gestionar pb de la imagen
                            return false;
                        }
                    })
                    // apply(new RequestOptions().placeholder(R.drawable.ic_place_holder).error(R.drawable.ic_place_holder))
                    .into(ivPoster);


            Glide.with(this).load(IMAGE_BASE_URL + detailsMovie.getPoster_path())
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            // TODO gestionar pb de la imagen
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            // TODO gestionar pb de la imagen
                            return false;
                        }
                    })

                    // .apply(new RequestOptions().placeholder(R.drawable.ic_place_holder).error(R.drawable.ic_place_holder))
                    .into(ivPosterPath);

            castArrayList.clear();
            castArrayList.addAll(detailsMovie.getCreditResponse().getCast());
            castAdapter.notifyDataSetChanged();

            genreArrayList.clear();
            genreArrayList.addAll(detailsMovie.getGenres());
            genreDetailAdapter.notifyDataSetChanged();



            tvOverview.setText(detailsMovie.getOverview());
            tvVoteAverage.setText(String.valueOf(detailsMovie.getVote_average()));

            tvOriginalLanguage.setText(detailsMovie.getOriginal_language().toUpperCase());
            //tvRuntime.setText(detailsMovie.getRuntime() + " minutos");
//            tvOriginalTitle.setText(detailsMovie.getOriginal_title());
//            tvHomepage.setText(detailsMovie.getHomepage());

            SimpleDateFormat euFormat = new SimpleDateFormat("dd-MM-yyyy");
            SimpleDateFormat usFormat = new SimpleDateFormat("yyyy-MM-dd");

            try {
                String reformattedStr = euFormat.format(usFormat.parse(detailsMovie.getReleaseDate()));
                tvDate.setText(reformattedStr);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            refreshLayout.setOnRefreshListener(() -> {
                setMovieData(detailsMovie);
                Log.d(TAG, "Detalle refrescado");
                refreshLayout.setRefreshing(false);
            });

        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onSuccess(DetailsMovie detailsMovie) {

        setMovieData(detailsMovie);

    }
    private void showTrailer(String url){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }

    @Override
    public void onSuccessTrailers(ArrayList<Video> videos) {
        onItemClickListener = new VideoListAdapter.OnItemClickListener() {
            @Override
            public void onCardClick(String key, int position) {
                Log.d(TAG, "click");
                showTrailer(YOUTUBE_URL_VIDEO + videos.get(position).getKey());
            }
        };



        videoListAdapter = new VideoListAdapter(this, videos, onItemClickListener);

        rvTrailer.setAdapter(videoListAdapter);


        if (videos != null) {
            videoArrayList.clear();
            videoArrayList.addAll(videos);
            videoListAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onFailure(Throwable throwable) {
        Log.e(TAG, throwable.getMessage());
        Toast.makeText(this, "Error al recibir los datos de la API", Toast.LENGTH_LONG).show();
    }
}
