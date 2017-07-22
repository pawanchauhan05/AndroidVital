package com.pawan.androidvital.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.pawan.androidvital.R;
import com.pawan.androidvital.app.Utils;
import com.pawan.androidvital.fragment.AlertDialog.AlertDialogFragment;
import com.pawan.androidvital.fragment.BottomSheet.BottomSheetFragment;
import com.pawan.androidvital.fragment.ChooseImage.ChooseImageFragment;
import com.pawan.androidvital.fragment.DatePicker.DatePickerFragment;
import com.pawan.androidvital.fragment.Gridview.GridViewFragment;
import com.pawan.androidvital.fragment.ListView.ListViewFragment;
import com.pawan.androidvital.fragment.Menu.MenuFragment;
import com.pawan.androidvital.fragment.ProgressBar.ProgressBarFragment;
import com.pawan.androidvital.fragment.RecyclerView.Fragment.RecyclerViewFragment;
import com.pawan.androidvital.fragment.ScanWifi.ScanWifiFragment;
import com.pawan.androidvital.fragment.SnackBar.SnackBarFragment;
import com.pawan.androidvital.fragment.TimePicker.TimePickerFragment;
import com.pawan.androidvital.fragment.Toast.ToastFragment;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("AndroidVital");
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.option_menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.item1) {
            Utils.generateToast(this, "Option menu item clicked", true);
            return true;
        }else if (id == R.id.item1) {
            Utils.generateToast(this, "Option menu item clicked", true);
            return true;
        }else if (id == R.id.item1) {
            Utils.generateToast(this, "Option menu item clicked", true);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment fragment = null;
        if (id == R.id.nav_toast) {
            fragment = new ToastFragment();
        } else if (id == R.id.nav_listview) {
            fragment = new ListViewFragment();
        } else if (id == R.id.nav_snackbar) {
            fragment = new SnackBarFragment();
        }else if (id == R.id.nav_progressbar) {
            fragment = new ProgressBarFragment();
        }else if (id == R.id.nav_menu) {
            fragment = new MenuFragment();
        }else if (id == R.id.nav_alert) {
            fragment = new AlertDialogFragment();
        }else if (id == R.id.nav_recycler) {
            fragment = new RecyclerViewFragment();
        }else if (id == R.id.nav_crop_image) {
            fragment = new ChooseImageFragment();
        } else if (id == R.id.nav_scan_wifi) {
            fragment = new ScanWifiFragment();
        }else if (id == R.id.nav_grid_view) {
            fragment = new GridViewFragment();
        }else if (id == R.id.nav_date_picker) {
            fragment = new DatePickerFragment();
        }else if (id == R.id.nav_time_picker) {
            fragment = new BottomSheetFragment();
        }
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_home, fragment)
                .commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
