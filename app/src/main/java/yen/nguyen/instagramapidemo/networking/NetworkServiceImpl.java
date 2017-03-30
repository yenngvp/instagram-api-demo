package yen.nguyen.instagramapidemo.networking;

import yen.nguyen.instagramapidemo.networking.api.OnNetworkCompleteListener;
import yen.nguyen.instagramapidemo.networking.model.AppException;
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
}
