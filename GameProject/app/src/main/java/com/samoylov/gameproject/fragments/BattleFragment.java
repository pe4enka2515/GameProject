package com.samoylov.gameproject.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.samoylov.gameproject.Data;
import com.samoylov.gameproject.Hero;
import com.samoylov.gameproject.Kostyl;
import com.samoylov.gameproject.Mob;
import com.samoylov.gameproject.R;
import com.samoylov.gameproject.adapters.BattleAdapter;
import com.samoylov.gameproject.adapters.BattleLogoAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class BattleFragment extends Fragment {
    private RecyclerView heroList, enemyList, logoRV;
    private RecyclerView.LayoutManager manager;
    private RecyclerView.LayoutManager manager2;
    private RecyclerView.LayoutManager manager3;
    private BattleAdapter heroBattleAdapter, enemyBattleAdapter;
    private BattleLogoAdapter battleLogoAdapter;
    private Mob enemy;
    private Hero hero;
    private Kostyl kostyl;
    private Button attack;
    private ArrayList<LogoText> logoTexts= new ArrayList<LogoText>();

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
        manager3 = new LinearLayoutManager(getActivity());

        heroList = view.findViewById(R.id.heroList);
        enemyList = view.findViewById(R.id.enemyList);
        logoRV = view.findViewById(R.id.logList);

        attack = view.findViewById(R.id.bAttack);

        heroList.setLayoutManager(manager);
        enemyList.setLayoutManager(manager2);
        logoRV.setLayoutManager(manager3);

        logoTexts.add(0, new LogoText("Вы напали на "+enemy.getName() +"!",""));

        heroBattleAdapter = new BattleAdapter(hero);
        enemyBattleAdapter = new BattleAdapter(enemy);
        battleLogoAdapter=new BattleLogoAdapter(logoTexts);
        heroList.setAdapter(heroBattleAdapter);
        enemyList.setAdapter(enemyBattleAdapter);

        logoRV.setAdapter(battleLogoAdapter);

        attack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //атака/////////////////////////////////////////////////////////////////////////////////////////////
                //получаю дамаг игрока и монстра
                //если хп >0 тогда бой продолжается иначе
                //конец боя//
                //запрос результат////////////////////////////////////////////////////////////////////////////////////
//Поменял 10.05
                battle();
            }
        });
        return view;
    }

    void battle() {
        hod1(enemy, hero);
        if (enemy.getHp_now() <= 0) {
            Data.bdMob.remove(enemy);
            hero.setEXP(hero.getEXP() + enemy.getEXP());

            logoTexts.add(0, new LogoText("Получено: " ,"Опыта: "+ enemy.getEXP()));
            logoTexts.add(0, new LogoText("Вы победили "+enemy.getName() +"!",""));
            battleLogoAdapter.notifyDataSetChanged();
            attack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getActivity().getSupportFragmentManager().beginTransaction().
                            replace(R.id.containerFragments, new FragmentLocation()).commit();
                }
            });
        }

        enemyBattleAdapter.notifyDataSetChanged();

        hod2(hero, enemy);

        if (hero.getHp_now() <= 0) {
            //enemy.getLvl() (Сделать увелечение лвла)
            logoTexts.add(0, new LogoText("Вас поимел "+enemy.getName() +"!",""));
            battleLogoAdapter.notifyDataSetChanged();
            attack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getActivity().getSupportFragmentManager().beginTransaction().
                            replace(R.id.containerFragments, new FragmentLocation()).commit();
                }
            });
        }

        heroBattleAdapter.notifyDataSetChanged();
        hero.UpLvl();
    }

    public void hod1(Mob defending, Hero attacking) {
        if (Math.random() * 100 <= attacking.getAcc()) {//попадание
            if (Math.random() * 100 <= attacking.getCritChance()) {//Шанс крита
                double Crit = attacking.getCritPower();
                double dmg = attacking.getDmg();
                defending.setHp_now(defending.getHp_now() - dmg * Crit);//Крит прошел, расчет урона с крита
                logoTexts.add(0,new LogoText(attacking.getName(), "Кританул и нанес " + (dmg * Crit) + " урона"));
                battleLogoAdapter.notifyDataSetChanged();
            } else {
                double dmg = attacking.getDmg();
                defending.setHp_now(defending.getHp_now() - dmg);//крит не прошел, расчет урон без крита
                logoTexts.add(0,new LogoText(attacking.getName(), "Нанес " + dmg + " урона"));
                battleLogoAdapter.notifyDataSetChanged();
            }
        } else {
            logoTexts.add(0,new LogoText(attacking.getName(), "Промазал"));
            battleLogoAdapter.notifyDataSetChanged();
        }
    }

    public void hod2(Hero defending, Mob attacking) {
        if (Math.random() * 100 <= attacking.getAcc()) {//попадание
            if (Math.random() * 100 <= attacking.getCritChance()) {//Шанс крита
                double Crit = attacking.getCritPower();
                double dmg = attacking.getDmg();
                defending.setHp_now(defending.getHp_now() - dmg * Crit);//Крит прошел, расчет урона с крита
                logoTexts.add(0,new LogoText(attacking.getName(), "Кританул и нанес " + (dmg * Crit) + " урона"));
                battleLogoAdapter.notifyDataSetChanged();
            } else {
                double dmg = attacking.getDmg();
                defending.setHp_now(defending.getHp_now() - dmg);//крит не прошел, расчет урон без крита
                logoTexts.add(0,new LogoText(attacking.getName(), "Нанес " + dmg + " урона"));
                battleLogoAdapter.notifyDataSetChanged();
            }
        } else {
            logoTexts.add(0,new LogoText(attacking.getName(), "Промазал"));
            battleLogoAdapter.notifyDataSetChanged();
        }
    }

    public static class LogoText {
        private String nLogo, tLogo;

        public LogoText(String nLogo, String tLogo) {
            this.nLogo = nLogo;
            this.tLogo = tLogo;
        }

        public String getnLogo() {
            return nLogo;
        }

        public String gettLogo() {
            return tLogo;
        }
    }
}
