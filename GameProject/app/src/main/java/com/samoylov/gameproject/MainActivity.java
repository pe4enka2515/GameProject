package com.samoylov.gameproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.samoylov.gameproject.authorization.ApiClient;
import com.samoylov.gameproject.authorization.ApiInterface;
import com.samoylov.gameproject.authorization.LoginFragment;
import com.samoylov.gameproject.authorization.PrefConfig;
import com.samoylov.gameproject.authorization.RegistrationFragment;
import com.samoylov.gameproject.locations.Location;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements LoginFragment.OnLoginFromActivityListener {
    EditText name;
    Button create;
    public static PrefConfig prefConfig;
    public static ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prefConfig = new PrefConfig(this);
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        if (findViewById(R.id.fragment_container) != null) {
            if (savedInstanceState != null) {
                return;
            }
            if (prefConfig.readLoginStatus()) {
                setCreate(prefConfig.readName());
            } else {
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.fragment_container, new LoginFragment()).commit();
            }
        }
//        name=(EditText)findViewById(R.id.name);
//        create=(Button)findViewById(R.id.create);
    }

    public void setCreate(String string) {

        Location location1 = new Location("Москва", "Вы в центре мира");
        Location location2 = new Location("Замкадье", "Вы в жопе мира");

        Data.bdLocations.add(location1);
        Data.bdLocations.add(location2);

        final Hero hero = new Hero(string,20, 0, 0, 0, 0, 1, "Замкадье", 20, 0);
        Hero hero2=new Hero("Настя",10, 0, 0, 0, 0, 1, "Замкадье", 10, 0);
        hero2.setLocation("Замкадье");
        Hero hero3=new Hero("Kirill",20, 0, 0, 0, 0, 1, "Замкадье", 20, 0);
        hero3.setLocation("Замкадье");
        Hero hero4=new Hero("Sasha",10, 0, 0, 0, 0, 1, "Москва", 10, 0);
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
        Intent intent = new Intent(this, World.class);
//
//        Запрос данных героя (базвый класс)/////////////////////////////////////////////////////////////////////////////
//
//
//
        startActivity(intent);
        finish();
    }


    @Override
    public void performRegister() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new RegistrationFragment()).addToBackStack(null).commit();

    }

    @Override
    public void performLogin(String name) {
        prefConfig.writeName(name);
        setCreate(name);
    }
}
