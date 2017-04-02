package yen.nguyen.instagramapidemo.fragments.media;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import yen.nguyen.instagramapidemo.R;
import yen.nguyen.instagramapidemo.fragments.media.model.MediaItemViewModel;
import yen.nguyen.instagramapidemo.utils.DateTimeUtils;
import yen.nguyen.instagramapidemo.utils.ImageUtil;

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
                .inflate(R.layout.cardview_item, parent, false);
        FlexViewHolder vh = new FlexViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final FlexViewHolder holder, int position) {
        MediaItemViewModel item = dataSource.get(position);

        Picasso.with(context).load(item.getUser().getProfile_picture())
                .into(holder.uploadUserPhotoImageView, new Callback() {
                    @Override
                    public void onSuccess() {
                        Drawable drawable = holder.uploadUserPhotoImageView.getDrawable();
                        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
                        holder.uploadUserPhotoImageView.setImageBitmap(ImageUtil.getRoundedCornerBitmap(bitmap, 100));
                    }

                    @Override
                    public void onError() {

                    }
                });
        holder.uploadUsernameTextView.setText(item.getUser().getFull_name());
        holder.uploadLocationTextView.setText(item.getLocationName());
        holder.uploadTimeTextView.setText(DateTimeUtils.printWithHumanReadableAndTz(new DateTime(item.getCreatedTime(), DateTimeZone.UTC)));
        Picasso.with(context).load(item.getImages().getStandard_resolution().getUrl())
                .into(holder.feedCenterImageView);

        if (item.isUserHasLiked()) {
            holder.likeIconImageView.setImageResource(R.drawable.ic_heart_red);
        } else {
            holder.likeIconImageView.setImageResource(R.drawable.ic_heart);
        }
        holder.likeCountTextView.setText(String.valueOf(item.getLikeCount()) + " Likes");
        holder.commentCountTextView.setText(String.valueOf(item.getCommentCount()) + " Comments");
    }

    @Override
    public int getItemCount() {
        return dataSource.size();
    }

    public class FlexViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.ivPostUserProfilePhoto) ImageView uploadUserPhotoImageView;
        @BindView(R.id.tvUploadUser) TextView uploadUsernameTextView;
        @BindView(R.id.tvLocation) TextView uploadLocationTextView;
        @BindView(R.id.tvUploadTime) TextView uploadTimeTextView;
        @BindView(R.id.ivFeedCenter) ImageView feedCenterImageView;
        @BindView(R.id.ivLikeIcon) ImageView likeIconImageView;
        @BindView(R.id.tvLikeCount) TextView likeCountTextView;
        @BindView(R.id.tvCommentCount) TextView commentCountTextView;

        public FlexViewHolder(final View viewItem) {
            super(viewItem);

            ButterKnife.bind(this, viewItem);

            feedCenterImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (itemCallback != null) {
                        MediaItemViewModel item = dataSource.get(getAdapterPosition());
                        itemCallback.onItemClickListener(item, null);
                    }
                }
            });

            likeIconImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MediaItemViewModel item = dataSource.get(getAdapterPosition());
                    if (item.isUserHasLiked()) {
                        item.setUserHasLiked(false);
                        item.setLikeCount(item.getLikeCount() - 1);
                    } else {
                        item.setUserHasLiked(true);
                        item.setLikeCount(item.getLikeCount() + 1);
                    }
                    notifyItemChanged(getAdapterPosition());
                }
            });
        }

    }

    public interface OnItemCallback {
        void onItemClickListener(MediaItemViewModel item, String name);
        void onItemDownloadClickListener(MediaItemViewModel item, int position);
        void onItemLongClickListener(String entityId, String name);
    }
}
