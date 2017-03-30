package yen.nguyen.instagramapidemo.networking;


import yen.nguyen.instagramapidemo.networking.api.OnNetworkCompleteListener;

/**
 * Created by yen.nguyen on 3/30/17.
 */

public interface NetworkService {

    void getMyself(OnNetworkCompleteListener callback);
}
