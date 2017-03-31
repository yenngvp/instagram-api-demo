package yen.nguyen.instagramapidemo.networking.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import yen.nguyen.instagramapidemo.networking.model.MediaNetworkModel;
import yen.nguyen.instagramapidemo.networking.model.SearchMediaNetworkModel;
import yen.nguyen.instagramapidemo.networking.model.UserNetworkModel;

/**
 * Created by yen.nguyen on 3/30/17.
 */

public interface MediaAPI {

    @GET("/v1/media/search")
    Call<SearchMediaNetworkModel> searchMedia(
            @Query("lat") double lat,
            @Query("lng") double lng,
            @Query("access_token") String accessToken);

    @GET("/v1/media/search")
    Call<MediaNetworkModel> getMedia(
            @Path("media") String id,
            @Query("access_token") String accessToken);
}
