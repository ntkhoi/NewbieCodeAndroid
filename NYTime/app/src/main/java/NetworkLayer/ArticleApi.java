package NetworkLayer;

import java.util.Map;

import model.ArticleSearchResult;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by dustin on 12/3/17.
 */

public interface ArticleApi {
    @GET("articlesearch.json")
    Call<ArticleSearchResult> getSearch(@QueryMap(encoded = true) Map<String, String> options);
}
