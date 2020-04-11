package com.samoylov.gameproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.samoylov.gameproject.adapters.ExpListAdapter;
import com.samoylov.gameproject.fragments.BattleFragment;
import com.samoylov.gameproject.fragments.FragmentLocation;
import com.samoylov.gameproject.fragments.FragmentMyProfile;
import com.samoylov.gameproject.fragments.FragmentProfile;
import com.samoylov.gameproject.fragments.MyBagFragment;
import com.samoylov.gameproject.fragments.MyProfileFragment;
import com.samoylov.gameproject.locations.Location;

import java.util.ArrayList;

public class World extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, Test2 {

    // Отоброжение Локации
//    Название
//    Описание
//    Список переходов
//    Спойлер 4шт в кажом список
//
//
//
//
//Z
    private FragmentLocation fragmentLocation;
    private FragmentProfile fragmentProfile;
    private FragmentMyProfile fragmentMyProfile;
    private MyProfileFragment myProfileFragment;
    private BattleFragment battleFragment;
    FragmentManager fragmentManager;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;
    NavigationView navigationView;
    final boolean[] b = {true};
    final boolean[] v = {true};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_world);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(this);

        setActionBarDrawerToggle();


        fragmentLocation = new FragmentLocation();
        fragmentProfile = new FragmentProfile();
        myProfileFragment = new MyProfileFragment();
        battleFragment = new BattleFragment();
        //fragmentMyProfile=new FragmentMyProfile();
//        fragmentProfile.newInstance(0);
        fragmentManager = getSupportFragmentManager();
        fragmentLocation = FragmentLocation.newInstance(fragmentManager);
        fragmentManager.beginTransaction().replace(R.id.containerFragments, fragmentLocation).commit();

//        listView = (ExpandableListView) findViewById(R.id.expanded_menu);
//
//        lName=(TextView) findViewById(R.id.lName);
//        lDescription=(TextView) findViewById(R.id.lDescription);
//        startLoc();

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        drawerLayout.closeDrawer(GravityCompat.START);
        b[0] = true;
        if (menuItem.getItemId() == R.id.myPtofileItem) {
            if (!v[0]) {
                drawerLayout.closeDrawer(GravityCompat.START);
            } else {
                myProfileFragment = new MyProfileFragment();
                fragmentManager = getSupportFragmentManager();
                setNavigationToolbar();
                fragmentManager.beginTransaction().replace(R.id.containerFragments, myProfileFragment).addToBackStack(null).commit();
            }
        }
        if (menuItem.getItemId() == R.id.locationItem) {
            v[0] = true;
            fragmentManager = getSupportFragmentManager();
            fragmentLocation = FragmentLocation.newInstance(fragmentManager);
            b[0] = false;
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            setActionBarDrawerToggle();
//            actionBarDrawerToggle.syncState();
            fragmentManager.beginTransaction().replace(R.id.containerFragments, fragmentLocation).commit();
        }


        return true;
    }

    @Override
    public void onSelected(String tag, int i) {
        b[0] = true;
        switch (tag) {
            case "a":
                setNavigationToolbar();
                fragmentMyProfile = new FragmentMyProfile();
                fragmentManager = getSupportFragmentManager();
                fragmentMyProfile = FragmentMyProfile.newInstance(0);
                fragmentManager.beginTransaction().replace(R.id.containerFragments, fragmentMyProfile).addToBackStack(null).commit();
                break;
            case "b":
                v[0] = true;
                setNavigationToolbar();
                fragmentMyProfile = new FragmentMyProfile();
                fragmentMyProfile = FragmentMyProfile.newInstance(i);
                fragmentManager.beginTransaction().replace(R.id.containerFragments, fragmentMyProfile).addToBackStack(null).commit();
                break;
            case "null":
                setNavigationToolbar();
                fragmentProfile = new FragmentProfile();
                fragmentProfile = FragmentProfile.newInstance(i);
                fragmentManager.beginTransaction().replace(R.id.containerFragments, fragmentProfile).addToBackStack(null).commit();
                break;
            case "Mob":
                battleFragment = new BattleFragment();
                battleFragment = BattleFragment.newInstance(Data.bdHeros.get(0), Data.bdMob.get(0));
                fragmentManager.beginTransaction().replace(R.id.containerFragments, battleFragment).commit();
                break;
            default:
                break;
        }

    }

    @Override
    public void onBattle(int id) {
        for (int i = 0; i < Data.bdMob.size(); i++) {
            if (Data.bdMob.get(i).getId() == id) {
                battleFragment = new BattleFragment();
                battleFragment = BattleFragment.newInstance(Data.bdHeros.get(0), Data.bdMob.get(i));
                fragmentManager.beginTransaction().replace(R.id.containerFragments, battleFragment).commit();

            }
        }
    }

    @Override
    public void onEquipItem(String tag) {
        MyBagFragment myBagFragment = new MyBagFragment();
        myBagFragment = MyBagFragment.newInstance(tag);
        fragmentManager.beginTransaction().replace(R.id.containerFragments, myBagFragment).addToBackStack(null).commit();
    }

    @Override
    public void test(boolean i) {
        v[0] = i;
    }

    public void setActionBarDrawerToggle() {
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.open,
                R.string.close);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }

    public void setNavigationToolbar() {


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (b[0] == true) {
                    onBackPressed();
                    getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                    getSupportActionBar().setDisplayShowHomeEnabled(false);
                    b[0] = false;
                }
                b[0] = true;
                setActionBarDrawerToggle();
//                    setActionBarDrawerToggle();
////                getSupportActionBar().setDisplayShowHomeEnabled(false);
////                actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
////                drawerLayout.addDrawerListener(actionBarDrawerToggle);
//                actionBarDrawerToggle.syncState();

            }
        });
    }

    @Override
    public void onBackPressed() {
        if (b[0]) {
            b[0] = false;
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            setActionBarDrawerToggle();
        }
        super.onBackPressed();
    }


//    public void startLoc() {
//        location = Data.bdLocations.get(0);
//        Data.bdHeros.get(0).setLocation(location.getLocName());
//        location.addPlayersOnLocationList(location.getLocName());
//        location.addOnLocation();
//        lName.setText(location.getLocName());
//        lDescription.setText(location.getLocDescription());
//        //Создаем адаптер и передаем context и список с данными
//        adapter = new ExpListAdapter(getApplicationContext(), location.getOnLocation());
//        adapter.setOnCardClickListener(this);
//        listView.setAdapter(adapter);
//
//    }
}

