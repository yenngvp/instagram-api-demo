package yen.nguyen.instagramapidemo.common;

import android.content.Context;
import android.support.v4.content.Loader;

public final class PresenterLoader<T extends BasePresenter> extends Loader<T> {

    private final PresenterFactory<T> factory;
    private final String tag;
    private T presenter;

    public PresenterLoader(Context context, PresenterFactory<T> factory, String tag) {
        super(context);
        this.factory = factory;
        this.tag = tag;
    }

    @Override
    protected void onStartLoading() {
        if (presenter != null) {
            deliverResult(presenter);
            return;
        }

        forceLoad();
    }

    @Override
    protected void onForceLoad() {
        presenter = factory.create();
        deliverResult(presenter);
    }

    @Override
    public void deliverResult(T data) {
        super.deliverResult(data);
    }

    @Override
    protected void onStopLoading() {
    }

    @Override
    protected void onReset() {
        if (presenter != null) {
            presenter.onDestroyed();
            presenter = null;
        }
    }
}