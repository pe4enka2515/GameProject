package com.samoylov.gameproject.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.samoylov.gameproject.Data;
import com.samoylov.gameproject.Hero;
import com.samoylov.gameproject.HeroStat;
import com.samoylov.gameproject.HeroStatAdapter;
import com.samoylov.gameproject.R;
import com.samoylov.gameproject.adapters.ProfileAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentProfile#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentProfile extends Fragment {
    private Hero hero;
    private ArrayList<HeroStat> gg;
    TextView getName, getLvl, getEXP, getPoint;
    RecyclerView profileList;
    ProfileAdapter profileAdapter;
    private int i=0;

    public static FragmentProfile newInstance(int i) {
        FragmentProfile fragment = new FragmentProfile();
        fragment.i = i;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (i != 0) {
            hero = Data.bdHeros.get(i);
        } else {
            hero = Data.bdHeros.get(0);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_profile, container, false);
//        gg=hero.heroStats;
        getName = (TextView) v.findViewById(R.id.name_Player);
        getLvl = (TextView) v.findViewById(R.id.lvl_Player);
        getName.setText(hero.getName());

        getName.setText(hero.getName());
//        getLvl.setText("Уровень: " + hero.getLocation()+" id: "+ Double.toString(hero.getLocationId()));
        getLvl.setText("Уровень: " + Double.toString(hero.getLvl()));

        profileList = v.findViewById(R.id.list_Profile_Stat);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        profileList.setLayoutManager(layoutManager);
        profileAdapter = new ProfileAdapter(hero.heroStats);
        profileList.setAdapter(profileAdapter);
        return v;
    }
//    public void setNewHeroStat(View view) {
//        for (int i=0; i<4;i++) {
//            gg.get(i).setCount(newHeroStat.get(i).getCount());
//        }
//    }
}
