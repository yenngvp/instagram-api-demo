package yen.nguyen.instagramapidemo.networking.api;

import android.content.Context;


/**
 * Created by yen.nguyen on 3/30/17.
 */

public class RetrofitService {

    public static <T> T create(final Context context, Class<T> aClass) {
        return RetrofitServiceFactory.getService(context, aClass);
    }
}
