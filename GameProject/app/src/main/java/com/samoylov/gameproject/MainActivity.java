package com.samoylov.gameproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.samoylov.gameproject.locations.Location;

public class MainActivity extends AppCompatActivity {
    EditText name;
    Button create;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name=(EditText)findViewById(R.id.name);
        create=(Button)findViewById(R.id.create);
    }

    public void setCreate(View v){

        Location location1=new Location("Москва","Вы в центре мира");
        Location location2=new Location("Замкадье","Вы в жопе мира");

        Data.bdLocations.add(location1);
        Data.bdLocations.add(location2);

        Hero hero=new Hero(name.getText().toString(),10);
        Hero hero2=new Hero("Настя",10);
        hero2.setLocation("Замкадье");
        Hero hero3=new Hero("Kirill",10);
        hero3.setLocation("Замкадье");
        Hero hero4=new Hero("Sasha",10);
        hero4.setLocation("Москва");



        location1.addTransition("Замкадье");
        location1.addTransition("Дом");
//        location1.addDropList("5k RUB");

        location1.addOnLocation();
        location2.addTransition("Москва");
//        location2.addDropList("5 RUB");

        location2.addOnLocation();
        hero.setLocation("Москва");

        Data.bdHeros.add(hero);
        Data.bdHeros.add(hero2);
        Data.bdHeros.add(hero3);
        Data.bdHeros.add(hero4);
        Intent intent=new Intent(this,World.class);
        startActivity(intent);
        finish();
    }

}
