package yen.nguyen.instagramapidemo.networking.request;

import yen.nguyen.instagramapidemo.networking.api.OnNetworkCompleteListener;
import yen.nguyen.instagramapidemo.networking.model.AppException;

/**
 * Created by yen.nguyen on 3/30/17.
 */

public interface InstagramRequest {

    void getMyself(OnNetworkCompleteListener callback) throws AppException;
}
