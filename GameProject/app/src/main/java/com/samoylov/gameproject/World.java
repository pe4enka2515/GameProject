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
import com.samoylov.gameproject.fragments.StatusBarFragment;
import com.samoylov.gameproject.locations.Location;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

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
    private StatusBarFragment statusBarFragment;
    private FragmentLocation fragmentLocation;
    private FragmentProfile fragmentProfile;
    private FragmentMyProfile fragmentMyProfile;
    private MyProfileFragment myProfileFragment;
    private BattleFragment battleFragment;
    private TextView name;
    private long regen_delay = 1000L;
    private long regen_period = 1000L;

    FragmentManager fragmentManager;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;
    NavigationView navigationView;
    final boolean[] b = {true};
    final boolean[] v = {true};

//        public Timer timer_regen = new Timer();
//        public TimerTask task_regen = new TimerTask() {
//            @Override
//            public void run() {
//                if (Data.bdHeros.get(0).getHp_now() + Data.bdHeros.get(0).getHr() >= Data.bdHeros.get(0).getHp()) {
//                    Data.bdHeros.get(0).setHp_now(Data.bdHeros.get(0).getHp());
//                } else if (Data.bdHeros.get(0).getHp_now() + Data.bdHeros.get(0).getHr() < Data.bdHeros.get(0).getHp()) {
//                    Data.bdHeros.get(0).setHp_now(Data.bdHeros.get(0).getHp_now() + Data.bdHeros.get(0).getHr());
//                }
//            }
//        };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_world);

//        timer_regen.schedule(task_regen, regen_delay, regen_period);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(this);
        View headerView=navigationView.getHeaderView(0);
        name=(TextView) headerView.findViewById(R.id.textView);
        name.setText(MainActivity.prefConfig.readName());
        setActionBarDrawerToggle();

        statusBarFragment=new StatusBarFragment();
        fragmentLocation = new FragmentLocation();
        fragmentProfile = new FragmentProfile();
        myProfileFragment = new MyProfileFragment();
        battleFragment = new BattleFragment();
        fragmentManager = getSupportFragmentManager();
        fragmentLocation = FragmentLocation.newInstance(fragmentManager);
        statusBarFragment=StatusBarFragment.newInstance(Data.bdHeros.get(0));
        Thread thread_regen_vision = new Thread() {
            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                statusBarFragment.potok();
                            }
                        });
                    }
                } catch (InterruptedException e) { }
            }
        };
        thread_regen_vision.start();
        Thread thread_regen = new Thread() {
            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (Data.bdHeros.get(0).getHp_now() + Data.bdHeros.get(0).getHr() >= Data.bdHeros.get(0).getHp()) {
                                    Data.bdHeros.get(0).setHp_now(Data.bdHeros.get(0).getHp());
                                } else if (Data.bdHeros.get(0).getHp_now() + Data.bdHeros.get(0).getHr() < Data.bdHeros.get(0).getHp()) {
                                    Data.bdHeros.get(0).setHp_now(Data.bdHeros.get(0).getHp_now() + Data.bdHeros.get(0).getHr());
                                }
                            }
                        });
                    }
                } catch (InterruptedException e) { }
            }
        };
        thread_regen.start();
        fragmentManager.beginTransaction().add(R.id.containerFragments, statusBarFragment).commit();
        fragmentManager.beginTransaction().add(R.id.containerFragments, fragmentLocation).commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        drawerLayout.closeDrawer(GravityCompat.START);
        b[0] = true;
        if (menuItem.getItemId() == R.id.myProfileItem) {
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
            fragmentManager.beginTransaction().replace(R.id.containerFragments, statusBarFragment).commit();
            fragmentManager.beginTransaction().add(R.id.containerFragments, fragmentLocation).commit();
        }
        if (menuItem.getItemId() == R.id.exit) {
            MainActivity.prefConfig.writeLoginStatus(false);

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
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
        // Запрос с айди монстром////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // получил/обработал/записал//////////////////////////////////////////////////////////////////////////////////////////////////////////
        for (int i = 0; i < Data.bdMob.size(); i++) {
            if (Data.bdMob.get(i).getId() == id) {
//                timer_regen_vis.cancel();
                battleFragment = new BattleFragment();
                battleFragment = BattleFragment.newInstance(Data.bdHeros.get(0), Data.bdMob.get(i));
                fragmentManager.beginTransaction().replace(R.id.containerFragments, battleFragment).commit();

            }
        }
//        timer_regen.schedule(task_regen, regen_delay, regen_period);
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

}

//compile fileTree(dir: 'libs', include: ['*.jar'])
//    testCompile 'junit:junit:4.12'
//    compile 'com.android.support:appcompat-v7:23.1.1'
//    compile 'com.android.support:design:23.1.1'
//    compile 'com.google.code.gson:gson:2.6.1'
//    compile 'com.squareup.retrofit2:retrofit:2.0.0-beta4'
//    compile 'com.squareup.retrofit2:converter-gson:2.0.0-beta4'
//    compile 'com.squareup.okhttp3:okhttp:3.1.2'