package com.samoylov.gameproject.authorization;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.samoylov.gameproject.MainActivity;
import com.samoylov.gameproject.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {
    private TextView RedText;
    private EditText UserName, UserPassword;
    private Button LoginBn;
    OnLoginFromActivityListener loginFromActivityListener;

    public interface OnLoginFromActivityListener {
        public void performRegister();

        public void performLogin(String name);
    }

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        RedText = view.findViewById(R.id.register_text);
        UserName = view.findViewById(R.id.user_namw);
        UserPassword = view.findViewById(R.id.user_pass);
        LoginBn = view.findViewById(R.id.login_bn);

        LoginBn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performLogin();

            }
        });

        RedText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginFromActivityListener.performRegister();
            }
        });
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Activity activity = (Activity) context;
        loginFromActivityListener = (OnLoginFromActivityListener) activity;
    }

    public void performLogin() {
        final String username = UserName.getText().toString();
        String password = UserPassword.getText().toString();
        Call<User> call = MainActivity.apiInterface.performUserLogin(username, password);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.body() == null) {

                    MainActivity.prefConfig.writeLoginStatus(true);
                    MainActivity.prefConfig.displayToast("Offline Version...");
                    loginFromActivityListener.performLogin(username);
                } else if (response.body().getResponse().equals("ok")) {
                    MainActivity.prefConfig.writeLoginStatus(true);
                    loginFromActivityListener.performLogin(response.body().getName());
                } else if (response.body().getResponse().equals("failed")) {
                    MainActivity.prefConfig.displayToast("Login Failed...");
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable throwable) {

            }
        });
        UserName.setText("");
        UserPassword.setText("");
    }
}

