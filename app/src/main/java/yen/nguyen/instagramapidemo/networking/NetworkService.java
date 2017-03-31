package yen.nguyen.instagramapidemo.networking;


import yen.nguyen.instagramapidemo.networking.common.OnNetworkCompleteListener;

/**
 * Created by yen.nguyen on 3/30/17.
 */

public interface NetworkService {

    /**
     * Users services
     */
    void getMyself(OnNetworkCompleteListener callback);

    void getUserRecentMedia(OnNetworkCompleteListener callback);

    /**
     * Media services
     */
    void searchMedia(double lat, double lon, OnNetworkCompleteListener callback);

    void getMedia(String mediaId, OnNetworkCompleteListener callback);
}
