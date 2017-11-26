package movie.dustin.com.movie.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by dustin on 11/26/17.
 */

public class NowPlaying {
    @SerializedName("results")
    private List<Movie> movies;

    public List<Movie> getMovies() {
        return movies;
    }
}
