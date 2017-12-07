package movie.dustin.com.movie.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import movie.dustin.com.movie.R;
import movie.dustin.com.movie.Utils.RetrofitUtils;
import movie.dustin.com.movie.adapter.MovieAdapter;
import movie.dustin.com.movie.api.MovieApi;
import movie.dustin.com.movie.model.NowPlaying;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private MovieApi mMovieApi;

    @BindView(R.id.lvMovie)
    ListView lvMovie;

    @BindView(R.id.pbLoading)
    ProgressBar mPbLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mMovieApi = RetrofitUtils.get().create(MovieApi.class);
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
