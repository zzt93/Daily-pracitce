package com.example.zzt.whyfi.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TabHost;

import com.example.zzt.whyfi.R;

public class Drawer extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        assert fab != null;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jumpToEdit();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        assert drawer != null;
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        assert navigationView != null;
        navigationView.setNavigationItemSelectedListener(this);

        setMsgList();
        setTabHost();
    }

    private void setTabHost() {
        TabHost host = (TabHost)findViewById(R.id.tabHost);
        assert host != null;
        host.setup();
        //Tab 1
        TabHost.TabSpec spec = host.newTabSpec(getString(R.string.sent_tab));
        spec.setContent(R.id.sentTab);
        spec.setIndicator(getString(R.string.sent_tab));
        host.addTab(spec);

        //Tab 2
        spec = host.newTabSpec(getString(R.string.received_tab));
        spec.setContent(R.id.receivedTab);
        spec.setIndicator(getString(R.string.received_tab));
        host.addTab(spec);
    }

    private void setMsgList() {
//        LinearLayout sentTab = (LinearLayout) findViewById(R.id.sentTab);
//        MessageItemBinding binding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.message_item, sentTab, false);

        ListView received = (ListView) findViewById(R.id.receivedList);
        String[] received_msg = getResources().getStringArray(R.array.received_msg);
        assert received != null;
        received.setAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, received_msg));

        ListView sent = (ListView) findViewById(R.id.sentList);
        String[] sent_msg = getResources().getStringArray(R.array.sent_msg);
        assert sent != null;
        sent.setAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, sent_msg));
    }

    private void jumpToEdit() {
        Intent intent = new Intent(this, EditMsgActivity.class);
        startActivity(intent);
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
//        getMenuInflater().inflate(R.menu.drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        } else if (id == R.id.nav_setting) {
            Intent intent = new Intent(this, DeviceSettingsActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
