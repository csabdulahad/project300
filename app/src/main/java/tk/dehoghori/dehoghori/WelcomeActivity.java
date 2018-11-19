package tk.dehoghori.dehoghori;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import tk.dehoghori.dehoghori.data.Key;
import tk.dehoghori.dehoghori.fragment.HomeFragment;
import tk.dehoghori.dehoghori.fragment.VaccineFragment;
import tk.dehoghori.dehoghori.helper.FragmentHelper;

public class WelcomeActivity extends AppCompatActivity {

    private int selectedNavigationItemId = -1;

    /*
     *   String is immutable, so we don't create titles evey time navigation menu item changes.
     *   so we use string builder to do that for optimization.
     */
    private StringBuilder titleBuilder;

    // we will commit fragments inside the frame layout of the DrawerLayout of activity_welcome.xml
    private final int contentFrame = R.id.content_frame;

    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private ActionBarDrawerToggle mActionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        mToolbar = findViewById(R.id.toolbar);
        mDrawerLayout = findViewById(R.id.drawer_layout);
        mNavigationView = findViewById(R.id.navigation_view);

        setSupportActionBar(mToolbar);
        initializeActionBarDrawerToggle();
        initializeNavigation();

        mDrawerLayout.addDrawerListener(mActionBarDrawerToggle);

        if (savedInstanceState == null) {
            setToolbarTitle(getString(R.string.app_name));
            FragmentHelper.commitFragment(getSupportFragmentManager(), new HomeFragment(), "hf", contentFrame);
        } else {
            setToolbarTitle(savedInstanceState.getString(Key.KEY_APP_NAME));
        }
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // anything can happen to the activity. so we batter save the title of the current fragment
        outState.putString(Key.KEY_APP_NAME, titleBuilder.toString());
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        if (mActionBarDrawerToggle != null)
            mActionBarDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (mActionBarDrawerToggle != null)
            mActionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void onBackPressed() {
        // close the navigation, if opened
        if (isDrawerLayoutOpened()) {
            closeDrawerLayout();
            return;
        }

        // take user home, if he/she happens to be in other fragments than home fragment
        if (!FragmentHelper.isCurrentFragment(getSupportFragmentManager(), "hf")) {
            FragmentHelper.commitIfExists(getSupportFragmentManager(), "hf", contentFrame);
            mNavigationView.getMenu().getItem(0).setChecked(true);
            setToolbarTitle(getString(R.string.app_name));
            return;
        }
        finish();
    }

    private void initializeNavigation() {
        /*
         * when user selects a menu item in the navigation, we just store the id of the selected menu
         * & close the DrawerLayout. we will only process that selected menu item When the DrawerLayout
         * is closed. so that user can see a great app.
         *
         * in the onDrawerClosed method of the ActionBarDrawerToggle interface, we process that
         * selected menu item.
         * */
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                selectedNavigationItemId = item.getItemId();
                closeDrawerLayout();
                return true;
            }
        });
    }

    private void initializeActionBarDrawerToggle() {
        /*
         * this is an interface, that we can register with the DrawerLayoutView, to learn about when
         * the DrawerLayoutView is opened or closed. this interface has to be registered with the
         * DrawerLayoutView.
         * */
        mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                invalidateOptionsMenu();
                // here we process the selected menu item, as the DrawerLayout has already been closed.
                processSelectedMenuItem();
            }
        };
    }


    private void processSelectedMenuItem() {
        switch (selectedNavigationItemId) {
            case R.id.nav_home:
                changeFragment(getString(R.string.app_name), "hf", new HomeFragment());
                break;

            case R.id.nav_vaccine:
                changeFragment("Vaccine", "vf", new VaccineFragment());
                break;
        }
        // after processing selected menu item, reset it so that no future problem can happen.
        selectedNavigationItemId = -1;
    }


    private void changeFragment(String title, String tag, Fragment fragment) {
        if (!FragmentHelper.commitFragmentInSafeMode(getSupportFragmentManager(), tag, contentFrame))
            FragmentHelper.commitFragment(getSupportFragmentManager(), fragment, tag, contentFrame);
        setToolbarTitle(title);
    }

    private void setToolbarTitle(String title) {
        // this check is required as to we don't want to create StringBuilder more than once
        if (titleBuilder == null) titleBuilder = new StringBuilder();

        // first clear out or reset the builder
        int length = titleBuilder.length();
        if (length > 0)
            titleBuilder.delete(0, length);

        // now append the tittle
        titleBuilder.append(title);
        getSupportActionBar().setTitle(titleBuilder.toString());
    }

    private boolean closeDrawerLayout() {
        if (mDrawerLayout == null)
            return false;
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private boolean isDrawerLayoutOpened() {
        return mDrawerLayout.isDrawerOpen(GravityCompat.START);
    }

    //From Jobair Joty
    //Test fork

    //commit from jobair Brunch
}
