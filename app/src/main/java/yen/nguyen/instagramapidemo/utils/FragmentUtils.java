package yen.nguyen.instagramapidemo.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * Created by yen.nguyen on 3/31/17.
 */
public class FragmentUtils {

    public static void addFragmentBackStack(FragmentManager manager, int contentId, Fragment fragment) {
        FragmentTransaction transaction = beginTransaction(manager);
        transaction.add(contentId, fragment, fragment.getClass().getName());
        transaction.addToBackStack(fragment.getClass().getName());
        transaction.commit();
    }

    public static void addFragment(FragmentManager manager, int contentId, Fragment fragment) {
        FragmentTransaction transaction = beginTransaction(manager);
        transaction.add(contentId, fragment, fragment.getClass().getName());
        transaction.commit();
    }

    public static void replaceFragmentBackStack(FragmentManager manager, int contentId, Fragment fragment) {
        FragmentTransaction transaction = beginTransaction(manager);
        transaction.replace(contentId, fragment, fragment.getClass().getName());
        transaction.addToBackStack(fragment.getClass().getName());
        transaction.commit();
    }

    public static void replaceFragment(FragmentManager manager, int contentId, Fragment fragment) {
        FragmentTransaction transaction = beginTransaction(manager);
        transaction.replace(contentId, fragment, fragment.getClass().getName());
        transaction.commit();
    }


    private static FragmentTransaction beginTransaction(FragmentManager manager) {
        FragmentTransaction transaction = manager.beginTransaction();
        return transaction;
    }
}
