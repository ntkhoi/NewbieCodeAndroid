package movie.dustin.com.movie.Utils;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by dustin on 11/26/17.
 */

public class RetrofitUtils {
    public static Retrofit get() {
        return new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client())
                .build();

    }

    private static OkHttpClient client() {
        return new OkHttpClient().newBuilder()
                .addInterceptor(apiKeyInterceptor())
                .build();
    }

    private static Interceptor apiKeyInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                HttpUrl url = request.url()
                        .newBuilder()
                        .addQueryParameter("api_key", Constants.API_KEY)
                        .build();

                request = request.newBuilder()
                        .url(url)
                        .build();

                return chain.proceed(request);
            }
        };
    }
}
