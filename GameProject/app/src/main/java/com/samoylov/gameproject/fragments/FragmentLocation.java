package com.samoylov.gameproject.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.samoylov.gameproject.Data;
import com.samoylov.gameproject.Hero;
import com.samoylov.gameproject.Kostyl;
import com.samoylov.gameproject.Mob;
import com.samoylov.gameproject.Test2;
import com.samoylov.gameproject.adapters.ExpListAdapter;
import com.samoylov.gameproject.R;
import com.samoylov.gameproject.locations.Location;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentLocation#newInstance} factory method to
 * create an instance of this fragment.
 */
public class  FragmentLocation extends Fragment implements ExpListAdapter.OnCardClickListener {
    private Location location;

    private ExpListAdapter adapter;
    private LinearLayout linearLayout;
    private ExpandableListView listView;
    private TextView lName, lDescription;
    private FragmentProfile fragmentProfile;
    private FragmentMyProfile fragmentMyProfile;
    private FragmentManager fragmentManager;
    private Button add1, add2, add3;
    private ArrayList<Kostyl> kostyls;

    private Hero hero;


// Нужен поток для переодического запроса////////////

    private Test2 listener;

    public FragmentLocation() {
    }

    public static FragmentLocation newInstance(FragmentManager fragmentManager) {
        FragmentLocation fragment = new FragmentLocation();
        fragment.fragmentManager = fragmentManager;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the la yout for this fragment
        View v = inflater.inflate(R.layout.fragment_location, container, false);
        listView = (ExpandableListView) v.findViewById(R.id.expanded_menu);
        add1 = v.findViewById(R.id.add1);
        add2 = v.findViewById(R.id.add2);
        add3 = v.findViewById(R.id.add3);
        linearLayout = (LinearLayout) v.findViewById(R.id.titleLoc);
        linearLayout.setBackgroundResource(R.drawable.krugliye_ugli);

        lName = (TextView) v.findViewById(R.id.lName);
        lDescription = (TextView) v.findViewById(R.id.lDescription);
        location = Data.bdLocations.get(Data.bdHeros.get(0).getLocationId());
        //запрос данных локации на которой находиться герой(id/название)////////////////////////////////////////////////////////////////////
        // обработка полученной лкации////////////
        setOnClick();
        startLoc();//отображение локации

        return v;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof Test2) {
            listener = (Test2) context;
        } else {
            throw new ClassCastException(context.toString() + " must implement listener");
        }
    }

    @Override
    public void onCardClick(View view, String name, int pos, String tag) {
        switch (name) {
            case "Замкадье":
                //запрос данных локации на которой нахдиться герой(id/название)/////////////////////////////////
                // обработка полученной лкации//////////////////////////////////////////////////////////////////
                location = Data.bdLocations.get(1);
                Data.bdHeros.get(0).setLocation(location.getLocName());
                location.addPlayersOnLocationList(location.getLocName());
                location.addMobList(location.getLocName());
                location.addOnLocation();
                lName.setText(location.getLocName());
                lDescription.setText(location.getLocDescription());
                //Создаем адаптер и передаем context и список с данными
                adapter = new ExpListAdapter(getContext(), location.getOnLocation());
                listView.setAdapter(adapter);
                break;
            case "Москва":
                Data.bdHeros.get(0).setLocation(name);

                location = Data.bdLocations.get(Data.bdHeros.get(0).getLocationId());
                startLoc();
            default:
                break;
        }

        if (pos == 1 && tag == null) {
            for (int i = 0; i < Data.bdHeros.size(); i++) {
                if (name == Data.bdHeros.get(i).getName()) {
                    listener.onSelected("null", i);

                }
            }
        }
        if (pos == 1 && tag == "b") {
            for (int i = 0; i < Data.bdHeros.size(); i++) {
                if (name == Data.bdHeros.get(0).getName()) {
                    listener.onSelected(tag, 0);
                    i = Data.bdHeros.size();
                } else {
                    if (name == Data.bdHeros.get(i).getName()) {
                        listener.onSelected("a", i);
                    }
                }
            }
        }
        if (pos==2){
            listener.onSelected(tag,0);
        }
    }

    @Override
    public void onClickBattle(int id) {
        listener.onBattle(id);
    }


    public void startLoc() {
        location.addPlayersOnLocationList(location.getLocName());
        location.addMobList(location.getLocName());
        location.addOnLocation();// создание локация
        lName.setText(location.getLocName());
        lDescription.setText(location.getLocDescription());
        //Создаем адаптер и передаем context и список с данными
        adapter = new ExpListAdapter(getContext(), location.getOnLocation());
        adapter.setOnCardClickListener(this);
        listView.setAdapter(adapter);


    }

    public void add1(View view) {

    }

    public void setOnClick(){
        add1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Mob Volk = new Mob("Мусорок",25,5,1,5, 20,
                        1, "Москва", 25, 15, 10);
                Data.bdMob.add(Volk);
                location.addMobList(location.getLocName());
                location.addOnLocation();
//                adapter = new ExpListAdapter(getContext(), location.getOnLocation());
//                listView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        });
        add2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Mob bomj = new Mob("Бомж",55,12,55,1, 20,
                        5, "Замкадье", 55, 180, 30);
                Data.bdMob.add(bomj);
                location.addMobList(location.getLocName());
                location.addOnLocation();
                adapter.notifyDataSetChanged();
            }
        });
        add3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Mob zaMKAD = new Mob("Замкадышь",2,8,40,50, 1,
                        7, "Москва", 2, 3, 500);
                Data.bdMob.add(zaMKAD);
                location.addMobList(location.getLocName());
                location.addOnLocation();
                adapter.notifyDataSetChanged();
            }
        });
    }

}
