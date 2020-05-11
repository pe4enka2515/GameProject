package com.samoylov.gameproject.locations;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import com.samoylov.gameproject.Data;

import java.util.ArrayList;

public class PlayersList implements RowType {
    private String player;
    private Context context;

    public PlayersList(String player, Context context){
        this.player=player;
        this.context=context;
    }
    public View.OnClickListener getOnClickListener(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Click!", Toast.LENGTH_SHORT).show();
            }
        };
    }
}
