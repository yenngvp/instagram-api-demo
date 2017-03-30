package yen.nguyen.instagramapidemo.common;

public interface BasePresenter<V> {
    void onViewAttached(V view);

    void onViewDetached();

    void onDestroyed();
}