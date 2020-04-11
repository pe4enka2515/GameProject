package com.samoylov.gameproject.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.samoylov.gameproject.Data;
import com.samoylov.gameproject.Equipment;
import com.samoylov.gameproject.Hero;
import com.samoylov.gameproject.HeroStat;
import com.samoylov.gameproject.R;
import com.samoylov.gameproject.Test2;
import com.samoylov.gameproject.adapters.MyProfileStatAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class    MyStatsFragment extends Fragment implements MyProfileStatAdapter.OnPorofileClicListener {

    private Hero hero;
    private ArrayList<HeroStat> newHeroStat;
    private ArrayList<HeroStat> gg;
    private ArrayList<HeroStat> buffer;
    private ArrayList<Equipment> myEq;
    private TextView getName, getLvl, getEXP, getPoint;
    private ImageView weapon, armour;
    private boolean onW = false, onAr = false;
    private RecyclerView list_My_Stat;
    private MyProfileStatAdapter myProfileStatAdapter;
    private int i = 0;
    private Button button, button2;
    private Test2 test;

    public MyStatsFragment() {
        // Required empty public constructor
    }

    public static MyStatsFragment newInstance(int i) {
        MyStatsFragment fragment = new MyStatsFragment();
        fragment.i = i;
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        hero = Data.bdHeros.get(0);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_my_profile, container, false);
        gg = hero.getHeroStats();
        newHeroStat = hero.getNewHeroStats();
        myEq = hero.getOnEquip();
        onBuildStat(v);
        onBuildListStst();


        return v;
    }

//    @Override
//    public void onAttach(@NonNull Context context) {
//        super.onAttach(context);
//        if (context instanceof Test2) {
//            test = (Test2) context;
//        } else {
//            throw new ClassCastException(context.toString() + " must implement listener");
//        }
//    }


    private void onBuildStat(View v) {
        weapon = v.findViewById(R.id.onWeapon);
        armour = v.findViewById(R.id.onSheld);
        getName = (TextView) v.findViewById(R.id.get_My_Name);
        getLvl = (TextView) v.findViewById(R.id.get_My_Lvl);
        getEXP = (TextView) v.findViewById(R.id.get_My_EXP);
        getPoint = (TextView) v.findViewById(R.id.get_My_Point);
        getName.setText(hero.getName());
        getEXP.setText("Опыт: " + Double.toString(hero.getEXP()));
        getLvl.setText("Уровень: " + Double.toString(hero.getLvl()));
        getLvl.setText("Уровень: " + Double.toString(hero.getLvl()));
        getPoint.setText(Double.toString(hero.getPoint()));

        for (int g = 0; g < myEq.size(); g++) {
            if (myEq.get(g) instanceof Equipment.Weapon) {
                weapon.setImageResource(myEq.get(g).getmImageResource());
            }
            if (myEq.get(g) instanceof Equipment.Armour) {
                armour.setImageResource(myEq.get(g).getmImageResource());
            }
        }

        list_My_Stat = v.findViewById(R.id.list_My_Stat);

        button = (Button) v.findViewById(R.id.b_My_Apply);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hero.setPoint(Double.parseDouble(getPoint.getText().toString()));
                for (int i = 0; i < 4; i++) {
                    newHeroStat.get(i).setCount(buffer.get(i).getCount());
                }
                for (int i = 0; i < 4; i++) {
                    gg.get(i).setCount(newHeroStat.get(i).getCount());
                }
            }
        });

        weapon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = 0;
                for (int g = 0; g < hero.getOnEquip().size(); g++) {

                    if (hero.getOnEquip().get(g) instanceof Equipment.Weapon) {
                        hero.removeEquip(hero.getOnEquip().get(g));

                        i = 1;
                        g = hero.getOnEquip().size();
                    }
                    weapon.setImageResource(R.drawable.ic_colorize_gray);
                    myProfileStatAdapter.notifyDataSetChanged();
                }
                if (i == 0) {
                    test.onEquipItem("Weapon");
                }

            }

        });
        armour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = 0;
                for (int g = 0; g < hero.getOnEquip().size(); g++) {

                    if (hero.getOnEquip().get(g) instanceof Equipment.Armour) {
                        hero.removeEquip(hero.getOnEquip().get(g));

                        i = 1;
                        g = hero.getOnEquip().size();
                    }
                    armour.setImageResource(R.drawable.ic_security_gray);
                    myProfileStatAdapter.notifyDataSetChanged();
                }
                if (i == 0) {
                    test.onEquipItem("Armour");
                }

            }
        });

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof Test2) {
            test = (Test2) context;
        } else {
            throw new ClassCastException(context.toString() + " must implement listener");
        }
    }

    private void onBuildListStst() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        list_My_Stat.setLayoutManager(layoutManager);
        myProfileStatAdapter = new MyProfileStatAdapter(hero, getPoint, newHeroStat, gg);
        myProfileStatAdapter.setOnPorofileClicListener(this);
        list_My_Stat.setAdapter(myProfileStatAdapter);
    }

    @Override

    public void onItemStatClick(View view, ArrayList<HeroStat> buffer) {
        this.buffer = buffer;
    }

}

