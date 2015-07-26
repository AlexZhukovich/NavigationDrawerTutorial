package com.alexzh.navigationdrawertutorial;

import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,
        NavigationView.OnNavigationItemSelectedListener {

    private final static String STATE_SELECTED_POSITION = "position";

    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private int mCurrentSelectedPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);

        setUpToolbar();

        mNavigationView = (NavigationView)findViewById(R.id.navigation);
        mNavigationView.setNavigationItemSelectedListener(this);

        if (savedInstanceState != null) {
            mCurrentSelectedPosition =
                    savedInstanceState.getInt(STATE_SELECTED_POSITION);
        } else {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, new MainFragment()).commit();
        }
    }

    private void setUpToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            mToolbar.setNavigationIcon(R.drawable.ic_menu_white_24dp);
            mToolbar.setNavigationOnClickListener(this);
        }
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(STATE_SELECTED_POSITION, mCurrentSelectedPosition);
    }

    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mCurrentSelectedPosition = savedInstanceState.getInt(STATE_SELECTED_POSITION, 0);
        Menu menu = mNavigationView.getMenu();
        menu.getItem(mCurrentSelectedPosition).setChecked(true);
    }

    @Override
    public void onClick(View v) {
        mDrawerLayout.openDrawer(GravityCompat.START);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        menuItem.setChecked(true);

        switch (menuItem.getItemId()) {
            case R.id.item_germany:
                mCurrentSelectedPosition = 0;
                getSupportFragmentManager().beginTransaction().replace(R.id.container, CountryFragment.newInstance("Germany")).commit();
                mDrawerLayout.closeDrawers();
                return true;
            case R.id.item_canada:
                mCurrentSelectedPosition = 1;
                getSupportFragmentManager().beginTransaction().replace(R.id.container, CountryFragment.newInstance("Canada")).commit();
                mDrawerLayout.closeDrawers();
                return true;
            case R.id.item_australia:
                mCurrentSelectedPosition = 2;
                getSupportFragmentManager().beginTransaction().replace(R.id.container, CountryFragment.newInstance("Australia")).commit();
                mDrawerLayout.closeDrawers();
                return true;
            case R.id.item_usa:
                mCurrentSelectedPosition = 3;
                getSupportFragmentManager().beginTransaction().replace(R.id.container, CountryFragment.newInstance("USA")).commit();
                mDrawerLayout.closeDrawers();
                return true;
            case R.id.item_settings:
                mCurrentSelectedPosition = 4;
                mDrawerLayout.closeDrawers();
                Snackbar.make(findViewById(R.id.container), "Settings", Snackbar.LENGTH_SHORT).show();
                return true;
        }

        return false;
    }
}
