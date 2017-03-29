package yen.nguyen.instagramapidemo.utils;

/**
 * Created by yen.nguyen on 3/28/17.
 */

public final class AppConstants {

    public static final String INSTAGRAM_CLIENT_ID = "79af28778f1c4cb28e2ba2015ac7ae74";
    public static final String INSTAGRAM_CLIENT_SECRET = "fdc5edc7ff1f4b4c97e3f5c7ad1ff3c4";
    public static final String INSTAGRAM_CALLBACK_URL = "https://github.com/yenngvp";
    public static final String INSTAGRAM_AUTH_URL =
            "https://api.instagram.com/oauth/authorize/?client_id=" + INSTAGRAM_CLIENT_ID +
                    "&redirect_uri=" + INSTAGRAM_CALLBACK_URL + "&response_type=token";

}
