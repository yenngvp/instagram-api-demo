package yen.nguyen.instagramapidemo.activities;

import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import icepick.Icepick;
import icepick.State;
import yen.nguyen.instagramapidemo.R;
import yen.nguyen.instagramapidemo.utils.LogUtil;

public class ViewMediaActivity extends AppCompatActivity
    implements MediaPlayer.OnPreparedListener,
        MediaPlayer.OnCompletionListener,
        MediaPlayer.OnErrorListener {

    private static final String TAG = "OpenMediaActivity";

    @BindView(R.id.message) TextView messageTextView;
    @BindView(R.id.loading_layout) RelativeLayout loadingRelativeLayout;
    @BindView(R.id.download_progressbar) ProgressBar progressBar;
    @BindView(R.id.videoView) VideoView videoView;
    @BindView(R.id.imageview) ImageView imageView;
    @BindView(R.id.media_view_relative_layout) RelativeLayout mediaViewRelativeLayout;

    @State boolean isFullScreenMode;
    @State int currentWatchingPosition;

    private String url;
    private String title;
    private String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Icepick.restoreInstanceState(this, savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_view_media);

        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        url = getIntent().getStringExtra("url");
        title = getIntent().getStringExtra("title");
        type = getIntent().getStringExtra("type");

        ButterKnife.bind(this);

        initView();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Icepick.saveInstanceState(this, outState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mediaLoader != null) {
            mediaLoader.removeCallbacks(mediaLoadRunnable);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onResume() {
        super.onResume();

        showHideFullScreenMode();
        loadData();
    }

    @Override
    public void onPause() {
        super.onPause();

        if ("video".equals(type)) {
            stopVideo();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            isFullScreenMode = true;
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            isFullScreenMode = false;
        }
        showHideFullScreenMode();
    }


    private void initView() {
    }

    private void showHideFullScreenMode() {
        if (isFullScreenMode) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        } else {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
    }

    private void showLoadingView() {
        loadingRelativeLayout.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.VISIBLE);
        messageTextView.setVisibility(View.INVISIBLE);
        mediaViewRelativeLayout.setVisibility(View.INVISIBLE);
    }

    private void hideLoadingView() {
        loadingRelativeLayout.setVisibility(View.GONE);
    }

    private void showErrorMessage(String message) {
        loadingRelativeLayout.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.INVISIBLE);
        mediaViewRelativeLayout.setVisibility(View.INVISIBLE);
        messageTextView.setVisibility(View.VISIBLE);
        messageTextView.setText(message);
    }

    private void showImageView() {
        mediaViewRelativeLayout.setVisibility(View.VISIBLE);
        imageView.setVisibility(View.VISIBLE);
        videoView.setVisibility(View.GONE);
    }

    private void showVideoView() {
        mediaViewRelativeLayout.setVisibility(View.VISIBLE);
        imageView.setVisibility(View.GONE);
        videoView.setVisibility(View.VISIBLE);
    }

    private void loadData() {
        if (TextUtils.isEmpty(url) || TextUtils.isEmpty(type)) {
            showErrorMessage("Invalid data");
        } else {
//            showLoadingView();
            mediaLoader.post(mediaLoadRunnable);
        }
    }

    Handler mediaLoader = new Handler();
    Runnable mediaLoadRunnable = new Runnable() {
        @Override
        public void run() {
            if ("image".equals(type)) {
                showImage();
            } else if ("video".equals(type)) {
                playVideo();
            }
        }
    };

    private void showImage() {
        Picasso.with(this).load(url).into(imageView, new Callback() {
            @Override
            public void onSuccess() {
                hideLoadingView();
                showImageView();
            }

            @Override
            public void onError() {
                hideLoadingView();
                showErrorMessage("Image loading error!");
            }
        });
    }

    @OnClick(R.id.close_image_button)
    public void closeAcivity() {
        finish();
    }

    private void playVideo() {
        showVideoView();
        String videoPath = url;
        if (videoPath != null) {
            videoView.setVideoURI(Uri.parse(videoPath));

            if (Build.VERSION.SDK_INT >= 21) {
                MediaController mediaController = new MediaController(this);
                mediaController.setMediaPlayer(videoView);
                mediaController.setAnchorView(videoView);
                videoView.setMediaController(mediaController);
                videoView.requestFocus();
            } else {
                LogUtil.w(TAG, "The Android OS is lower than 21. Media Controller is not supported!");
            }

            videoView.setOnPreparedListener(this);
            videoView.setOnCompletionListener(this);
            videoView.setOnErrorListener(this);

            videoView.seekTo(currentWatchingPosition);

            videoView.start();
        }
    }

    private void stopVideo() {
        if (videoView != null && videoView.isPlaying()) {
            currentWatchingPosition = videoView.getCurrentPosition();
            videoView.stopPlayback();
        }
    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
    }

    @Override
    public boolean onError(MediaPlayer mediaPlayer, int i, int i1) {
        hideLoadingView();
        showErrorMessage("Cannot play the video!");
        return true;
    }

    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {
        hideLoadingView();
        showVideoView();
    }

}
