package yen.nguyen.instagramapidemo.fragments.media;

import android.os.Handler;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import yen.nguyen.instagramapidemo.common.PaginationCriteria;
import yen.nguyen.instagramapidemo.fragments.media.model.MediaItemViewModel;
import yen.nguyen.instagramapidemo.networking.common.OnNetworkCompleteListener;
import yen.nguyen.instagramapidemo.utils.AppConstants;
import yen.nguyen.instagramapidemo.utils.Injector;
import yen.nguyen.instagramapidemo.utils.LogUtil;

/**
 * Created by yennguyen on 2/22/17.
 */

public class MediaListPresenter implements MediaListContract.ActionListener {


    private MediaListContract.View view;
    private PaginationCriteria paginationCriteria;
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
        buildPaginationCriteria(offset, size, isScrollingDown);
        isContinuosScrolling = true;
        this.mediaType = mediaType;
        loadMediaFromDatabase();
    }

    @Override
    public void refreshDataManually(boolean isContinuousScrolling, int mediaType) {
        buildPaginationCriteria(0, AppConstants.PAGINATION_SIZE, true);
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
            Injector.getNetworkService().getUserRecentMedia(new OnNetworkCompleteListener() {
                @Override
                public void onSuccess(Object data) {

                    List<MediaItemViewModel> mediaList = (List) data;
                    if (view != null) {
                        view.showData(mediaList, isContinuosScrolling, paginationCriteria);
                    }
                }

                @Override
                public void onFailure(String error) {
                    LogUtil.e("SearchMedia failure", error);
                }
            });
        }
    };

    private PaginationCriteria buildPaginationCriteria(int offset, int size, boolean isScrollingDown) {
        paginationCriteria = new PaginationCriteria.Builder()
                .sortBy("metadata.entityLastModifiedDate")
                .sortAscending(false)
                .offset(offset)
                .size(size)
                .isScrolledDown(isScrollingDown)
                .build();
        return paginationCriteria;
    }

}
