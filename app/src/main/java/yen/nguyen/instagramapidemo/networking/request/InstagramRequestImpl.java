package yen.nguyen.instagramapidemo.networking.request;


import android.content.Context;

import java.util.Collections;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import yen.nguyen.instagramapidemo.networking.api.MediaAPI;
import yen.nguyen.instagramapidemo.networking.api.UserAPI;
import yen.nguyen.instagramapidemo.networking.common.AppException;
import yen.nguyen.instagramapidemo.networking.common.ErrorType;
import yen.nguyen.instagramapidemo.networking.common.OnNetworkCompleteListener;
import yen.nguyen.instagramapidemo.networking.common.RetrofitService;
import yen.nguyen.instagramapidemo.networking.model.MediaNetworkModel;
import yen.nguyen.instagramapidemo.networking.model.SearchMediaNetworkModel;
import yen.nguyen.instagramapidemo.networking.model.UserNetworkModel;
import yen.nguyen.instagramapidemo.storages.AppSharedPreferences;
import yen.nguyen.instagramapidemo.utils.AppConstants;
import yen.nguyen.instagramapidemo.utils.InstagramApplication;
import yen.nguyen.instagramapidemo.utils.LogUtil;
import yen.nguyen.instagramapidemo.utils.NetworkUtil;

/**
 * Created by yen.nguyen on 3/30/17.
 */

public class InstagramRequestImpl implements InstagramRequest {

    private static final String TAG = "InstagramRequestImpl";
    private final Context context = InstagramApplication.getContext();
    private String accessToken = AppSharedPreferences.getInstance().getStringByKey(AppConstants.SharedPreference.ACCESS_TOKEN_KEY);;

    @Override
    public void getMyself(final OnNetworkCompleteListener callback) throws AppException {
        if (NetworkUtil.internetConnectionIsNotAvailable()) {
            throw new AppException(ErrorType.NO_NETWORK);
        }

        Call<UserNetworkModel> myselfRequest = RetrofitService.create(context, UserAPI.class).getUserSelf(accessToken);

        try {
            myselfRequest.enqueue(new Callback<UserNetworkModel>() {
                @Override
                public void onResponse(Call<UserNetworkModel> call, Response<UserNetworkModel> response) {
                    UserNetworkModel data = response.body();
                    if (callback != null) {
                        callback.onSuccess(data);
                    }
                }

                @Override
                public void onFailure(Call<UserNetworkModel> call, Throwable t) {
                    if (callback != null) {
                        callback.onFailure(t.getMessage());
                    }
                }
            });

        } catch (Exception e) {
            LogUtil.e(TAG, "getMyself", e);
            throw new AppException(ErrorType.UNEXPECTED, e.getMessage());
        }
    }


    @Override
    public void getUserRecentMedia(final OnNetworkCompleteListener callback) throws AppException {
        if (NetworkUtil.internetConnectionIsNotAvailable()) {
            throw new AppException(ErrorType.NO_NETWORK);
        }

        Call<SearchMediaNetworkModel> request = RetrofitService.create(context, UserAPI.class)
                .getUserRecentMedia(accessToken);

        try {
            request.enqueue(new Callback<SearchMediaNetworkModel>() {
                @Override
                public void onResponse(Call<SearchMediaNetworkModel> call, Response<SearchMediaNetworkModel> response) {
                    if (response.errorBody() == null && callback != null) {
                        SearchMediaNetworkModel data = response.body();
                        callback.onSuccess(data.toListOfMediaItemViewModels());
                    } else {
                        callback.onFailure(response.errorBody().toString());
                    }
                }

                @Override
                public void onFailure(Call<SearchMediaNetworkModel> call, Throwable t) {
                    if (callback != null) {
                        callback.onFailure(t.getMessage());
                    }
                }
            });

        } catch (Exception e) {
            LogUtil.e(TAG, "getUserRecentMedia", e);
            throw new AppException(ErrorType.UNEXPECTED, e.getMessage());
        }
    }

    @Override
    public void searchMedia(double lat, double lon, final OnNetworkCompleteListener callback) throws AppException {
        if (NetworkUtil.internetConnectionIsNotAvailable()) {
            throw new AppException(ErrorType.NO_NETWORK);
        }

        Call<SearchMediaNetworkModel> request = RetrofitService.create(context, MediaAPI.class)
                .searchMedia(lat, lon, accessToken);

        try {
            request.enqueue(new Callback<SearchMediaNetworkModel>() {
                @Override
                public void onResponse(Call<SearchMediaNetworkModel> call, Response<SearchMediaNetworkModel> response) {
                    if (response.errorBody() == null && callback != null) {
                        SearchMediaNetworkModel data = response.body();
                        callback.onSuccess(data != null ? data.getData() : Collections.EMPTY_LIST);
                    } else {
                        callback.onFailure(response.errorBody().toString());
                    }
                }

                @Override
                public void onFailure(Call<SearchMediaNetworkModel> call, Throwable t) {
                    if (callback != null) {
                        callback.onFailure(t.getMessage());
                    }
                }
            });

        } catch (Exception e) {
            LogUtil.e(TAG, "searchMedia", e);
            throw new AppException(ErrorType.UNEXPECTED, e.getMessage());
        }
    }

    @Override
    public void getMedia(String mediaId, final OnNetworkCompleteListener callback) throws AppException {
        if (NetworkUtil.internetConnectionIsNotAvailable()) {
            throw new AppException(ErrorType.NO_NETWORK);
        }

        Call<MediaNetworkModel> request = RetrofitService.create(context, MediaAPI.class).
                getMedia(mediaId, accessToken);

        try {
            request.enqueue(new Callback<MediaNetworkModel>() {
                @Override
                public void onResponse(Call<MediaNetworkModel> call, Response<MediaNetworkModel> response) {
                    MediaNetworkModel data = response.body();
                    if (callback != null) {
                        callback.onSuccess(data);
                    }
                }

                @Override
                public void onFailure(Call<MediaNetworkModel> call, Throwable t) {
                    if (callback != null) {
                        callback.onFailure(t.getMessage());
                    }
                }
            });

        } catch (Exception e) {
            LogUtil.e(TAG, "getMedia", e);
            throw new AppException(ErrorType.UNEXPECTED, e.getMessage());
        }
    }
}
