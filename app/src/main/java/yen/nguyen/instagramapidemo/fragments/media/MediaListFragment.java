package yen.nguyen.instagramapidemo.fragments.media;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import yen.nguyen.instagramapidemo.R;
import yen.nguyen.instagramapidemo.common.BasePresenterFragment;
import yen.nguyen.instagramapidemo.common.PaginationCriteria;
import yen.nguyen.instagramapidemo.common.PresenterFactory;
import yen.nguyen.instagramapidemo.common.customview.DividerItemDecoration;
import yen.nguyen.instagramapidemo.fragments.common.FragmentEventListener;
import yen.nguyen.instagramapidemo.fragments.media.model.MediaItemViewModel;
import yen.nguyen.instagramapidemo.utils.EndlessRecyclerViewScrollListener;
import yen.nguyen.instagramapidemo.utils.LogUtil;

public class MediaListFragment extends BasePresenterFragment<MediaListContract.ActionListener, MediaListContract.View>
        implements MediaListContract.View, MediaListAdapter.OnItemCallback, SwipeRefreshLayout.OnRefreshListener,
        FragmentEventListener {

    private static final String TAG = "MediaListFragment";

    private int mediaType;
    private MediaListContract.ActionListener presenter;
    private MediaListAdapter adapter;
    private LinearLayoutManager layoutManager;
    private EndlessRecyclerViewScrollListener scrollListener;

    @BindView(R.id.main_container) View mainContainer;
    @BindView(R.id.tv_message) TextView message;
    @BindView(R.id.media_recycler_list) RecyclerView mediaList;
    @BindView(R.id.swipe_container) SwipeRefreshLayout swipeRefreshLayout;

    public static MediaListFragment newInstance(int type) {
        MediaListFragment fragment = new MediaListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("MEDIA_TYPE", type);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();
        mediaType = args.getInt("MEDIA_TYPE");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_media_list, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        LogUtil.d("onResume", "type:" + mediaType);
    }

    private void initView() {
        adapter = new MediaListAdapter(getActivity(), this);
        layoutManager = new LinearLayoutManager(getActivity());
        mediaList.setLayoutManager(layoutManager);
        mediaList.setAdapter(adapter);

        swipeRefreshLayout.setOnRefreshListener(this);
        int offsetTop = getResources().getDimensionPixelSize(R.dimen.secondary_bar_height) +
                getResources().getDimensionPixelSize(R.dimen.space_normal);
        swipeRefreshLayout.setProgressViewOffset(false, 0, offsetTop);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
                R.color.colorInstagramPurpleViolet,
                R.color.colorInstagramRedViolet,
                R.color.colorPrimaryDark);
        scrollListener = new EndlessRecyclerViewScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int offset, int size, boolean isScrolledDown, RecyclerView view) {
                LogUtil.d(TAG, "Scrolling and loading more data.... offset: " + offset + ", size:" + size);
                presenter.onLoadMoreDataRequest(offset, size, isScrolledDown, mediaType);
                showPullToRefreshView();
            }

            @Override
            public void onScrolled(int offset, int size, RecyclerView view) {
            }
        };
        mediaList.addOnScrollListener(scrollListener);
    }

    @Override
    protected String tag() {
        return null;
    }

    @Override
    protected PresenterFactory<MediaListContract.ActionListener> getPresenterFactory() {
        return new PresenterFactory<MediaListContract.ActionListener>() {
            @Override
            public MediaListContract.ActionListener create() {
                return new MediaListPresenter();
            }
        };
    }

    @Override
    protected void onPresenterPrepared(MediaListContract.ActionListener presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onItemClickListener(MediaItemViewModel item, String name) {
//        Intent intent;
//        intent = new Intent(getActivity(), OpenMediaActivity.class);
//        intent.putExtra("entityId", item.getEntityId());
//        intent.putExtra("fileName", name);
//        startActivity(intent);
    }

    @Override
    public void onItemDownloadClickListener(final MediaItemViewModel item, int position) {

    }

    @Override
    public void onItemLongClickListener(String entityId, String name) {

    }

    @Override
    public void onRefresh() {
        if (presenter != null) {
            presenter.refreshDataManually(false, mediaType);
        }
    }

    public void showPullToRefreshView() {
        swipeRefreshLayout.setRefreshing(false);
    }

    public void hidePullToRefreshView() {
        if (swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    public void showEmptyMessage() {
        message.setVisibility(View.VISIBLE);
    }

    public void hideEmptyMessage() {
        message.setVisibility(View.GONE);
    }

    @Override
    public void showData(List<MediaItemViewModel> data, boolean isAppendMode, PaginationCriteria criteria) {
        if (isAppendMode) {
            adapter.appendData(data);
        } else {
            adapter.update(data);
            mediaList.scrollToPosition(0);
        }

        if (adapter.getDataSource().size() == 0) {
            showEmptyMessage();
        } else {
            hideEmptyMessage();
        }

        onLoadMoreDone(criteria);
    }

    public void clearData() {
        if (adapter != null) {
            adapter.getDataSource().clear();
            adapter.notifyDataSetChanged();
        }
    }

    private void onLoadMoreDone(PaginationCriteria criteria) {
        LogUtil.d("onLoadMoreDone", "Loading more done!");
        scrollListener.setLoading(false);
        scrollListener.setStartingOffset(criteria.getOffset());
        hidePullToRefreshView();
    }

    @Override
    public void onNotify(Object object) {
    }
}
