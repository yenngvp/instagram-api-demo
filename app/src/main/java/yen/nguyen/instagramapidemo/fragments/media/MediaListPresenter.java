package yen.nguyen.instagramapidemo.fragments.media;

import android.os.Handler;

/**
 * Created by yennguyen on 2/22/17.
 */

public class MediaListPresenter implements MediaListContract.ActionListener {

    private MediaListContract.View view;
    private boolean isContinuosScrolling;
    private int mediaType = 1;

    @Override
    public void onViewAttached(MediaListContract.View view) {
        this.view = view;
        refreshDataManually(false, mediaType);
    }

    @Override
    public void onDestroyed() {
        if (databaseLoadHandler != null) {
            databaseLoadHandler.removeCallbacks(databaseLoadRunnable);
        }
    }

    @Override
    public void onViewDetached() {

    }

    @Override
    public void onLoadMoreDataRequest(int offset, int size, boolean isScrollingDown, int mediaType) {
        isContinuosScrolling = true;
        this.mediaType = mediaType;
        loadMediaFromDatabase();
    }

    @Override
    public void refreshDataManually(boolean isContinuousScrolling, int mediaType) {
        isContinuosScrolling = false;
        this.mediaType = mediaType;
        loadMediaFromDatabase();
    }

    private void loadMediaFromDatabase() {
        databaseLoadHandler.post(databaseLoadRunnable);
    }

    private Handler databaseLoadHandler = new Handler();
    private Runnable databaseLoadRunnable = new Runnable() {
        @Override
        public void run() {
//            List<MediaItemViewModel> medias = databaseService.loadMediaByCriteriaAndTag(paginationCriteria, getTagString());
//            if (view != null) {
//                view.showData(medias, isContinuosScrolling, paginationCriteria);
//            }
        }
    };

    @Override
    @Deprecated
    public void downloadMedia(String entityId) {
    }

}
