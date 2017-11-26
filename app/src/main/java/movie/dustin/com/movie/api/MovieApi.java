package movie.dustin.com.movie.api;

import movie.dustin.com.movie.model.NowPlaying;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by dustin on 11/26/17.
 */

public interface MovieApi {
    @GET("now_playing")
    Call<NowPlaying> getNowPlaying();
}
