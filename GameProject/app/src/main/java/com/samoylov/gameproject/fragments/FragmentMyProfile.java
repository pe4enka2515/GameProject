package com.samoylov.gameproject.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.samoylov.gameproject.Data;
import com.samoylov.gameproject.Hero;
import com.samoylov.gameproject.HeroStat;
import com.samoylov.gameproject.R;
import com.samoylov.gameproject.Test2;
import com.samoylov.gameproject.adapters.MyProfileStatAdapter;
import com.samoylov.gameproject.adapters.ProfileAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentMyProfile extends Fragment implements MyProfileStatAdapter.OnPorofileClicListener {


    private Hero hero;
    private ArrayList<HeroStat> newHeroStat;
    private ArrayList<HeroStat> gg;
    private ArrayList<HeroStat> buffer;
    private TextView getName, getLvl, getEXP, getPoint;
    private RecyclerView list_My_Stat;
    private MyProfileStatAdapter myProfileStatAdapter;
    private int i = 0;
    private Button button;

    public FragmentMyProfile() {
        // Required empty public constructor
    }

    public static FragmentMyProfile newInstance(int i) {
        FragmentMyProfile fragment = new FragmentMyProfile();
        fragment.i = i;
        return fragment;
    }
//    public interface Test {
//        public void test(boolean i);
//    }
    Test2 test;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        test.test(false);
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
        View v = inflater.inflate(R.layout.fragment_my_profile, container, false);
        gg = hero.getHeroStats();
        newHeroStat=hero.getNewHeroStats();
        getName = (TextView) v.findViewById(R.id.get_My_Name);
        getLvl = (TextView) v.findViewById(R.id.get_My_Lvl);
        getEXP = (TextView) v.findViewById(R.id.get_My_EXP);
        getPoint = (TextView) v.findViewById(R.id.get_My_Point);
        getName.setText(hero.getName());
        getEXP.setText("Опыт: " + Double.toString(hero.getEXP()));
        getLvl.setText("Уровень: " + Double.toString(hero.getLvl()));
        getPoint.setText(Double.toString(hero.getPoint()));

        list_My_Stat = v.findViewById(R.id.list_My_Stat);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        list_My_Stat.setLayoutManager(layoutManager);
        myProfileStatAdapter = new MyProfileStatAdapter(hero, getPoint, newHeroStat,gg);
        myProfileStatAdapter.setOnPorofileClicListener(this);
        list_My_Stat.setAdapter(myProfileStatAdapter);
        button=(Button) v.findViewById(R.id.b_My_Apply);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hero.setPoint(Double.parseDouble(getPoint.getText().toString()));
                for (int i=0; i<4;i++) {
                    newHeroStat.get(i).setCount(buffer.get(i).getCount());
                }
                for (int i=0; i<4;i++) {
                    gg.get(i).setCount(newHeroStat.get(i).getCount());
                }
            }
        });

        return v;
    }@Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof Test2) {
            test = (Test2) context;
        } else {
            throw new ClassCastException(context.toString() + " must implement listener");
        }
    }

    @Override
    public void onDestroy() {

        super.onDestroy();
        test.test(true);
    }

    @Override
    public void onItemStatClick(View view, ArrayList<HeroStat> buffer) {
this.buffer=buffer;
    }

}
