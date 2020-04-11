package com.samoylov.gameproject.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.samoylov.gameproject.Hero;
import com.samoylov.gameproject.Kostyl;
import com.samoylov.gameproject.Mob;
import com.samoylov.gameproject.R;

import java.util.ArrayList;

public class BattleAdapter extends RecyclerView.Adapter<BattleAdapter.ViewHolder1> {
    private ArrayList<Kostyl> kostyls=new ArrayList<>();
    private ArrayList<Hero> h;
    private int k;
    public BattleAdapter (Kostyl kostyl){
        this.kostyls.add(kostyl);
        if (kostyl instanceof Hero){
            k=0;
        }else {
            k=1;
        }

    }
    @NonNull
    @Override
    public ViewHolder1 onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        int itemId;
        if(k==0){
            itemId=R.layout.hero_item;
        }else {
            itemId=R.layout.enemy_item;
        }
        LayoutInflater inflater=LayoutInflater.from(viewGroup.getContext());
        View v=inflater.inflate(itemId,viewGroup,false);
        return new ViewHolder1(v,k);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder1 viewHolder, int i) {
       viewHolder.name.setText(kostyls.get(i).getName());
       viewHolder.hp.setText(Double.toString(kostyls.get(i).getHp()));
    }

    @Override
    public int getItemCount() {
        return kostyls.size();
    }

    public  class ViewHolder1 extends RecyclerView.ViewHolder {
        TextView name,hp;
        public ViewHolder1(@NonNull View itemView,int k) {
            super(itemView);
            if (k==0){
                name=itemView.findViewById(R.id.nHero);
                hp=itemView.findViewById(R.id.hpHero);
            }else {
                name=itemView.findViewById(R.id.nEnemy);
                hp=itemView.findViewById(R.id.hpEnemy);
            }
        }
    }
}
