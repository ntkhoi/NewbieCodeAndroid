package movie.dustin.com.movie.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

import movie.dustin.com.movie.R;
import movie.dustin.com.movie.Utils.RetrofitUtils;
import movie.dustin.com.movie.adapter.MovieAdapter;
import movie.dustin.com.movie.api.MovieApi;
import movie.dustin.com.movie.model.NowPlaying;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ListView lvMovie;
    private MovieApi mMovieApi;
    private ProgressBar mPbLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMovieApi = RetrofitUtils.get().create(MovieApi.class);
        lvMovie = (ListView) findViewById(R.id.lvMovie);
        mPbLoading = findViewById(R.id.pbLoading);
        fetchMovies();
    }

    private void fetchMovies() {
        mMovieApi.getNowPlaying().enqueue(new Callback<NowPlaying>() {
            @Override
            public void onResponse(Call<NowPlaying> call, Response<NowPlaying> response) {
                handleResponse(response);
            }

            @Override
            public void onFailure(Call<NowPlaying> call, Throwable t) {
                Log.e("Error", t.getMessage());
            }
        });
    }

    private void handleResponse(Response<NowPlaying> response) {
        lvMovie.setAdapter(new MovieAdapter(this,response.body().getMovies()));
        mPbLoading.setVisibility(View.GONE);
    }
}
