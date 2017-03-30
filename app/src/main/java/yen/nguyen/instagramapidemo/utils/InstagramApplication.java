package yen.nguyen.instagramapidemo.utils;

import android.app.Application;
import android.content.Context;

/**
 * Created by yen.nguyen on 3/30/17.
 */

public final class InstagramApplication extends Application {

    private static InstagramApplication instance;
    private static Context context;


    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;
        context = getApplicationContext();
    }

    public static InstagramApplication getInstance() {
         return instance;
    }

    public static Context getContext() {
        return context;
    }
}
