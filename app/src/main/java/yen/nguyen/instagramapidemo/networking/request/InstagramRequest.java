package yen.nguyen.instagramapidemo.networking.request;

import yen.nguyen.instagramapidemo.networking.common.OnNetworkCompleteListener;
import yen.nguyen.instagramapidemo.networking.common.AppException;

/**
 * Created by yen.nguyen on 3/30/17.
 */

public interface InstagramRequest {

    /**
     * Users requests
     */
    void getMyself(OnNetworkCompleteListener callback) throws AppException;

    void getUserRecentMedia(OnNetworkCompleteListener callback) throws AppException;

    /**
     * Media requests
     */
    void searchMedia(double lat, double lon, OnNetworkCompleteListener callback) throws AppException;

    void getMedia(String mediaId, OnNetworkCompleteListener callback) throws AppException;
}
