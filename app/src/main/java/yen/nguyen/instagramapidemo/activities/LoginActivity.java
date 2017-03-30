package yen.nguyen.instagramapidemo.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import butterknife.ButterKnife;
import yen.nguyen.instagramapidemo.storages.AppSharedPreferences;
import yen.nguyen.instagramapidemo.utils.AppConstants;
import yen.nguyen.instagramapidemo.utils.LogUtil;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WebView webView = new WebView(this);
        setContentView(webView);

        ButterKnife.bind(this);
        webView.setVerticalScrollBarEnabled(false);
        webView.setHorizontalScrollBarEnabled(false);
        webView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                LogUtil.d("LoginActivity", url);
                if (url.contains("#access_token")) {
                    String parts[] = url.split("=");
                    String accessToken = parts[1];
                    saveAccessToken(accessToken);
                    redirectToHomeActivity();
                    return true;
                }

                return false;
            }
        });
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(AppConstants.INSTAGRAM_AUTH_URL);
    }

    private void saveAccessToken(String accessToken) {
        AppSharedPreferences.getInstance().storeString(AppConstants.SharedPreference.ACCESS_TOKEN_KEY, accessToken);
    }

    private void redirectToHomeActivity() {
        startActivity(new Intent(this, MainActivity.class));
    }
}
