package yen.nguyen.instagramapidemo.utils;

import yen.nguyen.instagramapidemo.BuildConfig;

/**
 * Created by yen.nguyen on 3/28/17.
 */

public final class AppConstants {
    public static final String APP_STORAGE_PREFERENCE = "INSTAGRAM_API_DEMO";

    public static final String INSTAGRAM_CLIENT_ID = BuildConfig.INSTAGRAM_CLIENT_ID;
    public static final String INSTAGRAM_CLIENT_SECRET = BuildConfig.INSTAGRAM_CLIENT_SECRET;
    public static final String INSTAGRAM_CALLBACK_URL = BuildConfig.INSTAGRAM_CALLBACK_URL;
    public static final String INSTAGRAM_AUTH_URL = "https://api.instagram.com/oauth/authorize/?client_id=" + INSTAGRAM_CLIENT_ID +
                    "&redirect_uri=" + INSTAGRAM_CALLBACK_URL + "&response_type=token";
    public static final String INSTAGRAM_API_BASE_URL = "https://api.instagram.com/";

    public static final int PAGINATION_SIZE = 20;

    public class SharedPreference {
        public static final String ACCESS_TOKEN_KEY = "ACCESS_TOKEN";
    }
}
