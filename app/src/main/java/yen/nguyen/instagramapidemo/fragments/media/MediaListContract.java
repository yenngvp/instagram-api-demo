package yen.nguyen.instagramapidemo.fragments.media;

import java.util.List;

import yen.nguyen.instagramapidemo.common.BasePresenter;
import yen.nguyen.instagramapidemo.common.PaginationCriteria;
import yen.nguyen.instagramapidemo.fragments.media.model.MediaItemViewModel;

public interface MediaListContract {

    interface View {
         void showData(List<MediaItemViewModel> data, boolean isAppendMode, PaginationCriteria criteria);
    }

    interface ActionListener extends BasePresenter<View> {
        void onLoadMoreDataRequest(int offset, int size, boolean isScrollingDown, int mediaType);
        void refreshDataManually(boolean isContinuousScrolling, int mediaType);
        void downloadMedia(String entityId);
    }
}
