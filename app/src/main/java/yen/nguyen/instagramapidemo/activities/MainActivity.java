package yen.nguyen.instagramapidemo.activities;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import yen.nguyen.instagramapidemo.R;
import yen.nguyen.instagramapidemo.fragments.media.MediaListFragment;
import yen.nguyen.instagramapidemo.networking.common.OnNetworkCompleteListener;
import yen.nguyen.instagramapidemo.networking.model.UserNetworkModel;
import yen.nguyen.instagramapidemo.storages.AppSharedPreferences;
import yen.nguyen.instagramapidemo.utils.FragmentUtils;
import yen.nguyen.instagramapidemo.utils.Injector;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.drawer_layout) DrawerLayout drawerLayout;
    @BindView(R.id.nav_view) NavigationView navView;

    private ActionBarDrawerToggle mDrawerToggle;
    private TextView fullNameTextView;
    private TextView usernameTextView;
    private ImageView userAvatarImageView;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    FragmentUtils.addFragment(getSupportFragmentManager(), R.id.content, MediaListFragment.newInstance(1));
                    return true;
                case R.id.navigation_dashboard:
                    return true;
                case R.id.navigation_notifications:
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        mDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close) {

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                initNavView();
            }
        };
        drawerLayout.setDrawerListener(mDrawerToggle);
        initNavView();

    }

    @Override
    protected void onResume() {
        super.onResume();

        toolbar.setTitle("Instagram API Demo");

        loadData();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        if (mDrawerToggle != null)
            mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggle
        if (mDrawerToggle != null)
            mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return (mDrawerToggle != null && mDrawerToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item));
    }

    private void initNavView() {

        Menu menu = navView.getMenu();

        View headerView = navView.getHeaderView(0);
        fullNameTextView = (TextView) headerView.findViewById(R.id.full_name_textView);
        usernameTextView = (TextView) headerView.findViewById(R.id.username_textView);
        userAvatarImageView = (ImageView) headerView.findViewById(R.id.user_avatar_imageView);

        showMyself();
    }

    private void showMyself() {

        String username = AppSharedPreferences.getInstance().getStringByKey("username");
        if (username != null) {
            String fullName = AppSharedPreferences.getInstance().getStringByKey("fullName");
            String profilePicture = AppSharedPreferences.getInstance().getStringByKey("profilePicture");
            displayAvatar(username, fullName, profilePicture);
        } else {
            Injector.getNetworkService().getMyself(new OnNetworkCompleteListener() {
                @Override
                public void onSuccess(Object data) {
                    UserNetworkModel user = (UserNetworkModel) data;
                    if (data != null) {
                        String username = user.getData().getUsername();
                        String fullName = user.getData().getFull_name();
                        String profilePicture = user.getData().getProfile_picture();
                        AppSharedPreferences.getInstance().storeString("username", username);
                        AppSharedPreferences.getInstance().storeString("fullName", fullName);
                        AppSharedPreferences.getInstance().storeString("profilePicture", profilePicture);

                        displayAvatar(username, fullName, profilePicture);
                    }
                }

                @Override
                public void onFailure(String error) {

                }
            });
        }
    }

    private void displayAvatar(String username, String fullName, String profilePicture) {
        fullNameTextView.setText(fullName);
        usernameTextView.setText(username);
        if (profilePicture != null) {
            Picasso.with(MainActivity.this).load(profilePicture).into(userAvatarImageView);
        }
    }

    private void loadData() {
        FragmentUtils.addFragment(getSupportFragmentManager(), R.id.content, MediaListFragment.newInstance(1));
    }

}
