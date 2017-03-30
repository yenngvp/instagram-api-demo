package yen.nguyen.instagramapidemo.storages;

import android.content.Context;
import android.content.SharedPreferences;

import yen.nguyen.instagramapidemo.utils.AppConstants;
import yen.nguyen.instagramapidemo.utils.InstagramApplication;


/**
 * Created by yen.nguyen on 3/30/17.
 */
public class AppSharedPreferences {

    private static AppSharedPreferences appSharedPreferences;
    private Context context = InstagramApplication.getContext();

    private AppSharedPreferences() {
    }

    public static AppSharedPreferences getInstance() {
        if (appSharedPreferences == null) {
            appSharedPreferences = new AppSharedPreferences();
        }

        return appSharedPreferences;
    }

    private SharedPreferences getSharedPreferences(String prefKey) {
        return context.getSharedPreferences(prefKey, Context.MODE_PRIVATE);
    }

    public void storeInt(String key, int value) {
        SharedPreferences pref = getSharedPreferences(AppConstants.APP_STORAGE_PREFERENCE);
        pref.edit().putInt(key, value).commit();
    }

    public int getIntByKey(String key, int defaultValue) {
        SharedPreferences pref = getSharedPreferences(AppConstants.APP_STORAGE_PREFERENCE);
        return pref.getInt(key, defaultValue);
    }

    public void storeBoolean(String key, boolean value) {
        SharedPreferences pref = getSharedPreferences(AppConstants.APP_STORAGE_PREFERENCE);
        pref.edit().putBoolean(key, value).commit();
    }

    public boolean getBooleanByKey(String key) {
        SharedPreferences pref = getSharedPreferences(AppConstants.APP_STORAGE_PREFERENCE);
        return pref.getBoolean(key, false);
    }

    public boolean getBooleanByKey(String key, boolean defaultValue) {
        SharedPreferences pref = getSharedPreferences(AppConstants.APP_STORAGE_PREFERENCE);
        return pref.getBoolean(key, defaultValue);
    }

    public void storeString(String key, String value) {
        SharedPreferences pref = getSharedPreferences(AppConstants.APP_STORAGE_PREFERENCE);
        pref.edit().putString(key, value).commit();
    }

    public String getStringByKey(String key) {
        SharedPreferences pref = getSharedPreferences(AppConstants.APP_STORAGE_PREFERENCE);
        return pref.getString(key, null);
    }

    public String getStringByKey(String key, String defaul) {
        SharedPreferences pref = getSharedPreferences(AppConstants.APP_STORAGE_PREFERENCE);
        return pref.getString(key, defaul);
    }
}
