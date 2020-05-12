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
import com.samoylov.gameproject.Test1;

import java.util.ArrayList;

public class BattleAdapter extends RecyclerView.Adapter<BattleAdapter.ViewHolder1> {
    private Test1 test1;
    int k;
    public BattleAdapter (Test1 test1){
        this.test1 =test1;
        if (test1 instanceof Hero){
            this.k=0;
        }else {
            this.k=1;
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
        viewHolder.name.setText(test1.getName2());
        viewHolder.hp_now.setText(""+ test1.getHp_now2());
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public  class ViewHolder1 extends RecyclerView.ViewHolder {
        TextView name, hp_now;
        public ViewHolder1(@NonNull View itemView,int k) {
            super(itemView);
            if (k==0){
                name=itemView.findViewById(R.id.nHero);
                hp_now=itemView.findViewById(R.id.hpHero);
            }else if (k==1){
                name=itemView.findViewById(R.id.nEnemy);
                hp_now=itemView.findViewById(R.id.hpEnemy);
            }
        }
    }
}
