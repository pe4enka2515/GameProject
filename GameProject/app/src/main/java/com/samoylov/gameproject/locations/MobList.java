package com.samoylov.gameproject.locations;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.samoylov.gameproject.Mob;

import java.util.ArrayList;

public class MobList implements RowType {
    private String mob;
    private Context context;

    public MobList(String mob, Context context){
        this.mob=mob;
        this.context=context;
    }
    public View.OnClickListener getOnClickListener(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Click mob!", Toast.LENGTH_SHORT).show();
            }
        };
    }
}
