package yen.nguyen.instagramapidemo.networking.request;


import android.content.Context;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import yen.nguyen.instagramapidemo.networking.api.OnNetworkCompleteListener;
import yen.nguyen.instagramapidemo.networking.api.RetrofitService;
import yen.nguyen.instagramapidemo.networking.api.UserAPI;
import yen.nguyen.instagramapidemo.networking.model.AppException;
import yen.nguyen.instagramapidemo.networking.model.ErrorType;
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
}
