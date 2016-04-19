package com.shinr3x.termodinamica;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import layout.CapitolulDoiFragment;
import layout.CapitolulPatruFragment;
import layout.CapitolulSapteFragment;
import layout.CapitolulSaseFragment;
import layout.CapitolulTreiFragment;
import layout.CapitolulUnuFragment;
import layout.CapitoulCinciFragment;
import layout.MainFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    int id, oldId = 0;

    private MainFragment.OnFragmentInteractionListener mListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Set the initial fragment
        MainFragment mainFragment = new MainFragment();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, mainFragment);
        fragmentTransaction.commit();



    }

    public void onClick(View view) {

        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        final Intent intent = new Intent(this, MainActivity.class);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(intent);
            }
        }, 250);

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

    public void fragmentSetup(android.app.Fragment fragmentInput,int id,int oldId) {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        if (id > oldId)fragmentTransaction.setCustomAnimations(R.animator.exit_anim1, R.animator.enter_anim1);
        if (id < oldId)fragmentTransaction.setCustomAnimations(R.animator.exit_anim2, R.animator.enter_anim2);
        fragmentTransaction.replace(R.id.fragment_container, fragmentInput);
        fragmentTransaction.commit();
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        id = item.getItemId();

        if (id == R.id.capitolul_unu) {
            CapitolulUnuFragment capitolulUnuFragment = new CapitolulUnuFragment();
            fragmentSetup(capitolulUnuFragment, id, oldId);
            getSupportActionBar().setTitle(R.string.capitolul_unu);
            oldId = id;
        } else if (id == R.id.capitolul_doi) {
            CapitolulDoiFragment capitolulDoiFragment = new CapitolulDoiFragment();
            fragmentSetup(capitolulDoiFragment, id, oldId);
            getSupportActionBar().setTitle(R.string.capitolul_doi);
            oldId = id;
        } else if (id == R.id.capitolul_trei) {
            CapitolulTreiFragment capitolulTreiFragment = new CapitolulTreiFragment();
            fragmentSetup(capitolulTreiFragment, id, oldId);
            getSupportActionBar().setTitle(R.string.capitolul_trei);
            oldId = id;
        } else if (id == R.id.capitolul_patru) {
            CapitolulPatruFragment capitolulPatruFragment = new CapitolulPatruFragment();
            fragmentSetup(capitolulPatruFragment, id, oldId);
            getSupportActionBar().setTitle(R.string.capitolul_patru);
            oldId = id;
        } else if (id == R.id.capitolul_cinci) {
            CapitoulCinciFragment capitolulCinciFragment = new CapitoulCinciFragment();
            fragmentSetup(capitolulCinciFragment, id, oldId);
            getSupportActionBar().setTitle(R.string.capitolul_cinci);
            oldId = id;
        } else if (id == R.id.capitolul_sase) {
            CapitolulSaseFragment capitolulSaseFragment = new CapitolulSaseFragment();
            fragmentSetup(capitolulSaseFragment, id, oldId);
            getSupportActionBar().setTitle(R.string.capitolul_sase);
            oldId = id;
        } else if (id == R.id.capitolul_sapte) {
            CapitolulSapteFragment capitolulSapteFragment = new CapitolulSapteFragment();
            fragmentSetup(capitolulSapteFragment, id, oldId);
            getSupportActionBar().setTitle(R.string.capitolul_sapte);
            oldId = id;
        } else if (id == R.id.despre) {
            AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.myDialog));
            builder.setMessage("     Această aplicație a fost dezvoltată de \n                        Dragoș Cocîrlea"+
                                    "\n\n\n                           Bibliografie: \n  Manual Fizică clasa a XI-a, editura Știința");
            AlertDialog dialog = builder.create();
            dialog.show();
        } else if (id == R.id.feedback) {
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:dragos.cocirlea@gmail.com")); // only email apps should handle this
            intent.putExtra(Intent.EXTRA_SUBJECT, "Feedback <<Termodinamică>>");
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
        }

        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                drawer.closeDrawer(GravityCompat.START);
            }
        }, 100);
        return true;
    }
}
