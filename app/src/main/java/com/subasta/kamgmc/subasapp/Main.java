package com.subasta.kamgmc.subasapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class Main extends AppCompatActivity implements SearchView.OnQueryTextListener {
    private Toolbar toolbar;
    private SearchView searchView;
    private BottomNavigationView navigation;
    private Sesion sesion;
    private Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sesion = new Sesion(this);

        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setSelectedItemId(R.id.navigation_home);
        navigation.setItemBackgroundResource(R.color.colorPrimary);
        menu = navigation.getMenu();

        toolbar = (Toolbar)findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, HomeFragment.newInstance(),"HomeTag");
        transaction.commit();


    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;
                    String tag = null;
                    switch (item.getItemId()) {
                        case R.id.navigation_home:
                            selectedFragment = HomeFragment.newInstance();
                            item.setEnabled(false);
                            navigation.getMenu().findItem(R.id.navigation_info).setEnabled(true);
                            navigation.getMenu().findItem(R.id.navigation_dashboard).setEnabled(true);
                            navigation.getMenu().findItem(R.id.navigation_login).setEnabled(true);
                            tag = "HomeTag";
                            break;
                        case R.id.navigation_dashboard:
                            selectedFragment = AccountFragment.newInstance();
                            item.setEnabled(false);
                            navigation.getMenu().findItem(R.id.navigation_home).setEnabled(true);
                            navigation.getMenu().findItem(R.id.navigation_info).setEnabled(true);
                            navigation.getMenu().findItem(R.id.navigation_login).setEnabled(true);
                            tag = "AccountTag";
                            break;
                        case R.id.navigation_login:
                            item.setEnabled(false);
                            navigation.getMenu().findItem(R.id.navigation_home).setEnabled(true);
                            navigation.getMenu().findItem(R.id.navigation_dashboard).setEnabled(true);
                            navigation.getMenu().findItem(R.id.navigation_info).setEnabled(true);
                            searchView.setVisibility(View.GONE);
                            if(sesion.isLogged()) {
                                selectedFragment = AccountFragment.newInstance();
                                menu.findItem(R.id.navigation_login).setTitle("Cuenta");
                                tag = "AccountTag";
                            }
                            else {
                                selectedFragment = LoginFragment.newInstance();
                                menu.findItem(R.id.navigation_login).setTitle("Login");
                                tag = "LoginTag";
                            }
                            break;
                        case R.id.navigation_info:
                            selectedFragment = InfoFragment.newInstance();
                            item.setEnabled(false);
                            navigation.getMenu().findItem(R.id.navigation_home).setEnabled(true);
                            navigation.getMenu().findItem(R.id.navigation_dashboard).setEnabled(true);
                            navigation.getMenu().findItem(R.id.navigation_login).setEnabled(true);
                            break;

                    }
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.frame_layout, selectedFragment,tag);
                    transaction.commit();
                    return true;
                }

            };

    @Override
    public boolean onCreateOptionsMenu (Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);

        searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));
        searchView.setQueryHint("Buscar...");
        searchView.setOnQueryTextListener(this);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {

        HomeFragment homeFragment = HomeFragment.newInstance();
        homeFragment.setSearch(query);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout,homeFragment,"HomeTag");
        transaction.commit();
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {

        return true;
    }

    @Override
    public void onBackPressed() {

        if(navigation.getSelectedItemId() == R.id.navigation_home) {
            Intent intent = new Intent(getApplicationContext(), SplashScreen.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("EXIT", true);
            startActivity(intent);
        }
        else{
            navigation.setSelectedItemId(R.id.navigation_home);
            navigation.getMenu().findItem(R.id.navigation_home).setEnabled(false);
            navigation.getMenu().findItem(R.id.navigation_dashboard).setEnabled(true);
            navigation.getMenu().findItem(R.id.navigation_login).setEnabled(true);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frame_layout, HomeFragment.newInstance());
            transaction.commit();
        }
    }
}
