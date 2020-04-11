package com.samoylov.gameproject.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.samoylov.gameproject.Hero;
import com.samoylov.gameproject.HeroStat;
import com.samoylov.gameproject.R;

import java.util.ArrayList;

public class MyProfileStatAdapter extends RecyclerView.Adapter<MyProfileStatAdapter.ViewHolder> {
    public interface OnPorofileClicListener {
        void onItemStatClick(View view, ArrayList<HeroStat> buffer);
    }

    private static OnPorofileClicListener mProfListener;
    ArrayList<HeroStat> heroStats;
    ArrayList<HeroStat> newHeroStat;
    ArrayList<HeroStat> buffer = new ArrayList<HeroStat>();
    Hero hero;
    //    HeroStat newHeroStat;
    private double pPoint, g;
    TextView getPoint;
    double mPoint = 0;

    public MyProfileStatAdapter(Hero hero, TextView getPoint, ArrayList<HeroStat> newHeroStat, ArrayList<HeroStat> heroStats) {
        this.hero = hero;
        this.getPoint = getPoint;
        this.newHeroStat = newHeroStat;
        this.heroStats = heroStats;

        setBuffer(this.buffer);
        this.pPoint = hero.getPoint();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int listId = R.layout.item_my_profile_stat;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View v = inflater.inflate(listId, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        holder.nameMyStat.setText(heroStats.get(position).getName());
        holder.resultMyStat.setText(Double.toString(heroStats.get(position).getCount()));


        final double[] d = {heroStats.get(position).getCount()};

        holder.mStat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (buffer.get(position).getCount() != heroStats.get(position).getCount()) {
                    d[0] = d[0] - 1;
                    buffer.get(position).setCount(d[0]);
                    holder.resultMyStat.setText(Double.toString(buffer.get(position).getCount()));
                    mPoint--;
                    pPoint++;
//                    hero.setPoint(pPoint);
                    getPoint.setText(Double.toString(pPoint));
                    mProfListener.onItemStatClick(view, buffer);
                }
            }
        });
        holder.pStat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pPoint != 0) {
                    d[0] = d[0] + 1;

                    buffer.get(position).setCount(d[0]);
                    holder.resultMyStat.setText(Double.toString(buffer.get(position).getCount()));
                    pPoint--;
                    mPoint++;
//                    hero.setPoint(pPoint);
                    getPoint.setText(Double.toString(pPoint));
                    mProfListener.onItemStatClick(view, buffer);
                }
            }
        });
    }


    public void setOnPorofileClicListener(OnPorofileClicListener listener) {
        mProfListener = listener;
    }

    @Override
    public int getItemCount() {
        return heroStats.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView nameMyStat, resultMyStat, costStat;
        private Button mStat, pStat;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameMyStat = (TextView) itemView.findViewById(R.id.nameMyStat);
            resultMyStat = (TextView) itemView.findViewById(R.id.resultMyStat);
            costStat = (TextView) itemView.findViewById(R.id.costStat);

            mStat = (Button) itemView.findViewById(R.id.mMStat);
            pStat = (Button) itemView.findViewById(R.id.pMStat);


        }

    }

    public void setBuffer(ArrayList<HeroStat> buffer) {

        for (int i = 0; i < 4; i++) {
            buffer.add(new HeroStat(newHeroStat.get(i).getName(), newHeroStat.get(i).getCount()));

        }
    }
}
