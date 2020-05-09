package com.samoylov.gameproject.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.samoylov.gameproject.Data;
import com.samoylov.gameproject.Hero;
import com.samoylov.gameproject.Kostyl;
import com.samoylov.gameproject.Mob;
import com.samoylov.gameproject.R;
import com.samoylov.gameproject.adapters.BattleAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class BattleFragment extends Fragment {
    private RecyclerView heroList, enemyList;
    private RecyclerView.LayoutManager manager;
    private RecyclerView.LayoutManager manager2;
    private BattleAdapter heroBattleAdapter, enemyBattleAdapter;
    private Mob enemy;
    private Hero hero;
    private Kostyl kostyl;
    private Button attack;

    public BattleFragment() {
        // Required empty public constructor
    }

    public static BattleFragment newInstance(Hero hero, Mob mob) {
        BattleFragment fragment = new BattleFragment();
        fragment.hero = hero;
        fragment.enemy = mob;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_battle, container, false);
        manager = new LinearLayoutManager(getActivity());
        manager2 = new LinearLayoutManager(getActivity());
        heroList = view.findViewById(R.id.heroList);
        enemyList = view.findViewById(R.id.enemyList);
        attack = view.findViewById(R.id.bAttack);
        attack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //атака/////////////////////////////////////////////////////////////////////////////////////////////
                //получаю дамаг игрока и монстра
                //если дамаг не -1 тогда бой продолжается иначе
                //конец боя//
                //запрос результат////////////////////////////////////////////////////////////////////////////////////
                enemy.setHp_now(enemy.getHp_now() - hero.getDmg());//тут можно передать параметры моба для вычисления дамага!!!!!!!
                if (enemy.getHp_now() <= 0) {
                    Data.bdMob.remove(enemy);
                    hero.setEXP(hero.getEXP() + enemy.getEXP());
                    getActivity().getSupportFragmentManager().beginTransaction().
                            replace(R.id.containerFragments,new FragmentLocation()).commit();
                }
                enemyBattleAdapter.notifyDataSetChanged();
                hero.setHp_now(hero.getHp_now() - enemy.getDmg());
                heroBattleAdapter.notifyDataSetChanged();
                if (hero.getHp_now() <= 0) {
                    //enemy.getLvl() (Сделать увелечение лвла)
                    getActivity().getSupportFragmentManager().beginTransaction().
                            replace(R.id.containerFragments,new FragmentLocation()).commit();
                }
                heroBattleAdapter.notifyDataSetChanged();
                hero.UpLvl();
            }
        });
        heroList.setLayoutManager(manager);
        enemyList.setLayoutManager(manager2);
        heroBattleAdapter = new BattleAdapter(hero);
        enemyBattleAdapter = new BattleAdapter(enemy);
        heroList.setAdapter(heroBattleAdapter);
        enemyList.setAdapter(enemyBattleAdapter);
        return view;
    }

}
