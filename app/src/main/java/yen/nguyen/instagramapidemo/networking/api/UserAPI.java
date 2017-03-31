package yen.nguyen.instagramapidemo.networking.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import yen.nguyen.instagramapidemo.networking.model.SearchMediaNetworkModel;
import yen.nguyen.instagramapidemo.networking.model.UserNetworkModel;

/**
 * Created by yen.nguyen on 3/30/17.
 */

public interface UserAPI {

    @GET("/v1/users/self/")
    Call<UserNetworkModel> getUserSelf(@Query("access_token") String accessToken);

    @GET("/v1/users/self/media/recent")
    Call<SearchMediaNetworkModel> getUserRecentMedia(@Query("access_token") String accessToken);
}
