package yen.nguyen.instagramapidemo.common;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

public abstract class BasePresenterFragment<P extends BasePresenter<V>, V> extends BaseFragment {

    private static final String TAG = "BasePresenterFragment";
    private static final int LOADER_ID = 101;

    // boolean flag to avoid delivering the result twice. Calling initLoader in onActivityCreated makes
    // onLoadFinished will be called twice during configuration change.
    private boolean delivered = false;
    private BasePresenter<V> presenter;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getLoaderManager().initLoader(LOADER_ID, null, new LoaderManager.LoaderCallbacks<P>() {
            @Override
            public final Loader<P> onCreateLoader(int id, Bundle args) {
                return new PresenterLoader<>(getContext(), getPresenterFactory(), tag());
            }

            @Override
            public final void onLoadFinished(Loader<P> loader, P presenter) {
                if (!delivered) {
                    BasePresenterFragment.this.presenter = presenter;
                    delivered = true;
                    onPresenterPrepared(presenter);
                }
            }

            @Override
            public final void onLoaderReset(Loader<P> loader) {
                BasePresenterFragment.this.presenter = null;
                onPresenterDestroyed();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onViewAttached(getPresenterView());
    }

    @Override
    public void onPause() {
        presenter.onViewDetached();
        super.onPause();
    }

    protected abstract String tag();

    protected abstract PresenterFactory<P> getPresenterFactory();

    protected abstract void onPresenterPrepared(P presenter);

    protected void onPresenterDestroyed() {
        // hook for subclasses
    }

    protected V getPresenterView() {
        return (V) this;
    }
}