package yen.nguyen.instagramapidemo.networking.api;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import yen.nguyen.instagramapidemo.networking.model.StringConverter;
import yen.nguyen.instagramapidemo.utils.AppConstants;

/**
 * Created by yen.nguyen on 3/30/17.
 */

public class RetrofitServiceFactory {
    public static final long HTTP_RESPONSE_DISK_CACHE_MAX_SIZE = 10 * 1024 * 1024;
    public static final long CONNECTION_TIMEOUT = 15000;
    public static final long READ_TIMEOUT = 20000;
    public static final long WRITE_TIMEOUT = 20000;

    private static OkHttpClient httpClient;
    private static Retrofit retrofit;
    private static Map<String, Object> servicesMap = new HashMap<>();

    private static OkHttpClient getHttpClient(final Context context) {
        if (httpClient == null) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient.Builder okClientBuilder = new OkHttpClient.Builder();

            okClientBuilder.addInterceptor(interceptor)
                    .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.MILLISECONDS)
                    .readTimeout(READ_TIMEOUT, TimeUnit.MILLISECONDS)
                    .writeTimeout(WRITE_TIMEOUT, TimeUnit.MILLISECONDS)
                    .retryOnConnectionFailure(true)
                    .addInterceptor(new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                            Request request = chain.request().newBuilder().build();
                            return chain.proceed(request);
                        }
                    });
            final File baseDir = context.getCacheDir();
            if (baseDir != null) {
                final File cacheDir = new File(baseDir, "HttpResponseCache");
                okClientBuilder.cache(new Cache(cacheDir, HTTP_RESPONSE_DISK_CACHE_MAX_SIZE));
            }
            httpClient = okClientBuilder.build();
        }
        return httpClient;
    }

    private static Retrofit getRetrofit(final Context context) {
        if (retrofit == null) {
            getHttpClient(context);
            Gson gson = new GsonBuilder()
                    .create();
            retrofit = new Retrofit.Builder()
                    .baseUrl(AppConstants.INSTAGRAM_API_BASE_URL)
                    .addConverterFactory(new StringConverter())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(httpClient)
                    .build();
        }
        return retrofit;
    }

    public static <T> T getService(final Context context, Class<T> clazz) {
        try {
            synchronized (servicesMap) {
                String clazzName = clazz.getName();
                Object service = servicesMap.get(clazzName);
                if (service == null) {
                    service = getRetrofit(context).create(clazz);
                    servicesMap.put(clazzName, service);
                }
                return (T) service;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}