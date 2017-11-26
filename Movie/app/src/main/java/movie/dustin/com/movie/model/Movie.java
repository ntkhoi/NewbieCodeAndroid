package movie.dustin.com.movie.model;

import com.google.gson.annotations.SerializedName;

import movie.dustin.com.movie.Utils.Constants;

/**
 * Created by dustin on 11/26/17.
 */

public class Movie {
    @SerializedName("title")
    private  String title;

    @SerializedName("overview")
    private  String overview;

    @SerializedName("poster_path")
    private String posterPath;

    @SerializedName("backdrop_path")
    private  String backdropPath;


    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return Constants.STATIC_BASE_URL + overview;
    }

    public String getPosterPath() {
        return Constants.STATIC_BASE_URL + posterPath;
    }

    public String getBackdropPath() {
        return backdropPath;
    }
}
