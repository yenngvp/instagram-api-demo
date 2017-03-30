package yen.nguyen.instagramapidemo.fragments.media;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import yen.nguyen.instagramapidemo.R;
import yen.nguyen.instagramapidemo.fragments.media.model.MediaItemViewModel;

public class MediaListAdapter extends RecyclerView.Adapter<MediaListAdapter.FlexViewHolder>{

    private Context context;
    private List<MediaItemViewModel> dataSource;
    private OnItemCallback itemCallback;

    public MediaListAdapter(Context context, OnItemCallback itemCallback) {
        this.context = context;
        dataSource = new ArrayList<>();
        this.itemCallback = itemCallback;
    }

    public MediaListAdapter(Context context, List<MediaItemViewModel> list) {
        if (list == null) {
            dataSource = new ArrayList<>();
        }
        this.context = context;
        dataSource = list;
    }

    public void update(List<MediaItemViewModel> list) {
        dataSource.clear();
        dataSource.addAll(list);
        notifyDataSetChanged();
    }

    public void appendData(List<MediaItemViewModel> moreList) {
        int position = this.dataSource.size() - 1;
        this.dataSource.addAll(moreList);
        notifyItemRangeChanged(position, moreList.size());
    }

    public List<? extends MediaItemViewModel> getDataSource() {
        return dataSource;
    }

    public void add(MediaItemViewModel item) {
        dataSource.add(item);
    }

    public void remove(MediaItemViewModel item) {
        dataSource.remove(item);
    }

    public void appendOrUpdate(MediaItemViewModel item) {
        int position;
        if (dataSource.contains(item)) {
            position = dataSource.indexOf(item);
            dataSource.set(position, item);
        } else {
            dataSource.add(item);
            position = dataSource.size() - 1;
        }
        notifyItemChanged(position);
    }

    public MediaItemViewModel get(int i) {
        return dataSource.get(i);
    }

    @Override
    public FlexViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.listview_item, parent, false);
        FlexViewHolder vh = new FlexViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(FlexViewHolder holder, int position) {
        MediaItemViewModel item = dataSource.get(position);

    }

    private void bindIconBasedOnMediaType(FlexViewHolder holder, MediaItemViewModel viewModel) {

    }

    @Override
    public int getItemCount() {
        return dataSource.size();
    }

    public class FlexViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.row_img) ImageView icon;
        @BindView(R.id.row_main_title) TextView title;
        @BindView(R.id.row_sub_content) TextView content;
        @BindView(R.id.row_img_status) ImageView imgStatus;
        @BindView(R.id.row_content_layout) LinearLayout contentLayout;
        @BindView(R.id.download_progressbar)ProgressBar progressBar;

        public FlexViewHolder(final View viewItem) {
            super(viewItem);

            ButterKnife.bind(this, viewItem);

            icon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (itemCallback != null) {
                        MediaItemViewModel item = dataSource.get(getAdapterPosition());
                        itemCallback.onItemClickListener(item, null);
                    }
                }
            });

            contentLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (itemCallback != null) {
                        MediaItemViewModel item = dataSource.get(getAdapterPosition());
                        itemCallback.onItemClickListener(item, null);
                    }
                }
            });

            imgStatus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (itemCallback != null) {
                        MediaItemViewModel item = dataSource.get(getAdapterPosition());
                        itemCallback.onItemDownloadClickListener(item, getAdapterPosition());
                    }
                }
            });
        }

        public ImageView getIcon() {
            return icon;
        }

        public TextView getTitle() {
            return title;
        }

        public TextView getContent() {
            return content;
        }

        public ImageView getImgStatus() {
            return imgStatus;
        }
    }

    public interface OnItemCallback {
        void onItemClickListener(MediaItemViewModel item, String name);
        void onItemDownloadClickListener(MediaItemViewModel item, int position);
        void onItemLongClickListener(String entityId, String name);
    }
}
