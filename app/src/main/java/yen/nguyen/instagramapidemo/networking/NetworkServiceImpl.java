package yen.nguyen.instagramapidemo.networking;

import yen.nguyen.instagramapidemo.networking.common.OnNetworkCompleteListener;
import yen.nguyen.instagramapidemo.networking.common.AppException;
import yen.nguyen.instagramapidemo.networking.request.InstagramRequest;
import yen.nguyen.instagramapidemo.networking.request.InstagramRequestImpl;
import yen.nguyen.instagramapidemo.utils.LogUtil;

/**
 * Created by yen.nguyen on 3/30/17.
 */

public class NetworkServiceImpl implements NetworkService {

    private static final String TAG = "NetworkServiceImpl";

    private static NetworkServiceImpl instance = null;
    private final InstagramRequest instagramRequest;

    private NetworkServiceImpl() {
        instagramRequest = new InstagramRequestImpl();
    }

    public static NetworkServiceImpl getInstance() {
        if (instance == null) {
            instance = new NetworkServiceImpl();
        }

        return instance;
    }

    @Override
    public void getMyself(OnNetworkCompleteListener callback) {
        try {
            instagramRequest.getMyself(callback);
        } catch (AppException e) {
            LogUtil.e(TAG, e.getErrorMessage(), e);
        }
    }

    @Override
    public void getUserRecentMedia(OnNetworkCompleteListener callback) {
        try {
            instagramRequest.getUserRecentMedia(callback);
        } catch (AppException e) {
            LogUtil.e(TAG, e.getErrorMessage(), e);
        }
    }

    @Override
    public void searchMedia(double lat, double lon, OnNetworkCompleteListener callback) {
        try {
            instagramRequest.searchMedia(lat, lon, callback);
        } catch (AppException e) {
            LogUtil.e(TAG, e.getErrorMessage(), e);
        }
    }

    @Override
    public void getMedia(String mediaId, OnNetworkCompleteListener callback) {
        try {
            instagramRequest.getMedia(mediaId, callback);
        } catch (AppException e) {
            LogUtil.e(TAG, e.getErrorMessage(), e);
        }
    }
}
