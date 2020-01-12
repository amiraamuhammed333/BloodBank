package com.example.bloodbank.View.Activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.bloodbank.R;
import com.example.bloodbank.View.Fragment.Homecycle.About_App_Fragment;
import com.example.bloodbank.View.Fragment.Homecycle.ArticlesFragment;
import com.example.bloodbank.View.Fragment.Homecycle.Contact_Us_Fragment;
import com.example.bloodbank.View.Fragment.Homecycle.EditInformtionFragment;
import com.example.bloodbank.View.Fragment.Homecycle.HomeFragment;
import com.example.bloodbank.View.Fragment.Homecycle.NotificationSettingFragment;
import com.example.bloodbank.View.Fragment.Homecycle.NotificationFragment;
import com.example.bloodbank.View.Fragment.login.LoginFragment;
import com.example.bloodbank.helper.HelperMethod;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.bloodbank.R.string.Home_Activity;

public class HomeActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.home_activity_tv_title)
    TextView homeActivityTvTitle;
    @BindView(R.id.fram)
    FrameLayout fram;
    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.home_activity_iB)
    ImageButton homeActivityIB;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        HelperMethod.ReplaceFragment(getSupportFragmentManager(), new HomeFragment(), R.id.fram, null, null); }

        @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_information) {
            HelperMethod.ReplaceFragment(this.getSupportFragmentManager(), new EditInformtionFragment(), R.id.fram, null, null);
        } else if (id == R.id.nav_notification) {
            HelperMethod.ReplaceFragment(this.getSupportFragmentManager(), new NotificationSettingFragment(), R.id.fram, null, null);
        } else if (id == R.id.nav_favourite) {
            ArticlesFragment articlesFragment2 = new ArticlesFragment();
            articlesFragment2.fivoret = true;
            HelperMethod.ReplaceFragment(this.getSupportFragmentManager(), articlesFragment2, R.id.fram, null, null);
        } else if (id == R.id.nav_home) {
            HelperMethod.ReplaceFragment(this.getSupportFragmentManager(), new HomeFragment(), R.id.fram, null, null);
        } else if (id == R.id.nav_contact) {
            HelperMethod.ReplaceFragment(this.getSupportFragmentManager(), new Contact_Us_Fragment(), R.id.fram, null, null);
        } else if (id == R.id.nav_about_app) {
            HelperMethod.ReplaceFragment(this.getSupportFragmentManager(), new About_App_Fragment(), R.id.fram, null, null);
        } else if (id == R.id.nav_log_out) {
            HelperMethod.ReplaceFragment(this.getSupportFragmentManager(), new LoginFragment(), R.id.fram, null, null); }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true; }

    public void setHomeActivityTitle(String title) {
        homeActivityTvTitle.setText(title);
    }
    public void setHomeActivityTitleVisibility(int visibility) { homeActivityTvTitle.setVisibility(visibility); }

    @OnClick(R.id.home_activity_iB)
    public void onViewClicked() {
        HelperMethod.ReplaceFragment(this.getSupportFragmentManager(), new NotificationFragment(), R.id.fram, null, null); }
}
