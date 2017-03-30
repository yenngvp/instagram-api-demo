package yen.nguyen.instagramapidemo.networking.api;

/**
 * Created by yen.nguyen on 3/30/17.
 */
public interface OnNetworkCompleteListener<T> {

    void onSuccess(T data);
    void onFailure(String error);
}
