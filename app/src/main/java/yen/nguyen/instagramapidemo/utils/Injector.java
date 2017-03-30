package yen.nguyen.instagramapidemo.utils;

import yen.nguyen.instagramapidemo.networking.NetworkService;
import yen.nguyen.instagramapidemo.networking.NetworkServiceImpl;

/**
 * Created by yen.nguyen on 3/30/17.
 */

public final class Injector {

    public static NetworkService getNetworkService() {
        return NetworkServiceImpl.getInstance();
    }
}
