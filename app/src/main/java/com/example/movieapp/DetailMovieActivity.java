package com.example.movieapp;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.room.Room;

import com.bumptech.glide.Glide;
import com.example.movieapp.model.Result;
import com.example.moviedb.R;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;

public class DetailMovieActivity extends AppCompatActivity {
    public static final String BASE_URL = "https://api.themoviedb.org/3/";
    private final static String API_KEY ="8aec02431004e0f4cfe216a95d6995c4";
    ImageView imgBackdrop,imgPoster;
    TextView titleDetail,rilisDate,voteDetail,desc;
    Result result;

    Movieapp movieapp;
    ArrayList<Result> favList;

    Retrofit retrofit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);

        initView();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // setSupportActionBar(toolbar);
        //setSupportActionBar(getTitle());
        CollapsingToolbarLayout toolBarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        String poster = getIntent().getStringExtra("poster");
        String backdropPath = getIntent().getStringExtra("backdrop");
        String detail = getIntent().getStringExtra("overview");
        String title = getIntent().getStringExtra("title");
        String rilis = getIntent().getStringExtra("rilis");
        String vote = getIntent().getStringExtra("vote");

        // toolBarLayout.setTitle(title);


        Glide.with(DetailMovieActivity.this).load(poster).into(imgPoster);
        Glide.with(DetailMovieActivity.this).load(backdropPath).error(R.drawable.ic_launcher_background)
                .into(imgBackdrop);
        desc.setText(detail);
        titleDetail.setText(title);
        rilisDate.setText(rilis);
        voteDetail.setText(vote);


//        if (getSupportActionBar() != null) {
//            getSupportActionBar().setTitle(title);
//        }
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        movieapp= Room.databaseBuilder(getApplicationContext(),Movieapp.class, "movieDB").allowMainThreadQueries().build();
        getConnectApi();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Result> movie = movieapp.movieDao().getMovieData();
                for(Result movie1 : favList) {
                    if (movie1.getTitle().equals(title)) {
                        if (movie.contains(movie1)) {
                            movieapp.movieDao().addData(movie1);
                            Resources res = getResources();
                            fab.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#F44336")));

                            Toast.makeText(view.getContext(), "Movie add favorite", Toast.LENGTH_SHORT).show();
                        } else {
                            movieapp.movieDao().delete(movie1);
                            Toast.makeText(view.getContext(), "Delete from favorite", Toast.LENGTH_SHORT).show();
                        }

                        if (movie.size() != 0) {
                            boolean isExist = false;

                            for (Result rs : movie) {
                                if (rs.getTitle().equals(movie1.getTitle())) {
                                    isExist = true;
                                    break;
                                }
                            }
                            if (isExist) {
                                movieapp.movieDao().delete(movie1);
                                Toast.makeText(view.getContext(), "Movie removed from favorites", Toast.LENGTH_SHORT).show();
                            }else{
                                movieapp.movieDao().addData(movie1);
                                Toast.makeText(view.getContext(), "Movie added to favorites", Toast.LENGTH_SHORT).show();
                            }

                        } else {
                            movieapp.movieDao().addData(movie1);
                            Toast.makeText(view.getContext(), "Movie added to favorites", Toast.LENGTH_SHORT).show();
                        }

                        break;
                    }
                }

            }
        });

    }



    }

    private void initView() {
        voteDetail = findViewById(R.id.movie_vote_detail);
        imgPoster = findViewById(R.id.img_poster);
        imgBackdrop = findViewById(R.id.img_mv_detail);
        desc = findViewById(R.id.movie_deskripsi);
        titleDetail =findViewById(R.id.movie_title_detail);
        rilisDate =findViewById(R.id.movie_rilis_detail);
        voteDetail = findViewById(R.id.movie_vote_detail);

    }
}
public  void getConnectApi(){
        if(retrofit == null){
        retrofit =new Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build();
        }
        MovieApi movieApi = retrofit.create(MovieApi.class);
        Call<UpResponse> call =movieApi.getMovieUpcoming(API_KEY);
        call.enqueue(new Callback<UpResponse>() {
@Override
public void onResponse(Call<UpResponse> call, Response<UpResponse> response) {
        if (response.isSuccessful()) {
        Log.i("movie", Objects.requireNonNull(response.body()).getResults().toString());
        favList = (ArrayList<Result>) response.body().getResults();
        }
        }

@Override
public void onFailure(Call<UpResponse> call, Throwable t) {
        t.printStackTrace();

        }
        });
        }
        }