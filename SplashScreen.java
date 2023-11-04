package com.example.smartbasket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        SharedPreferences sp = getSharedPreferences("Users", MODE_PRIVATE);
        boolean isLoggedIn = sp.getBoolean("LoggedIN", false);
        Handler handler = new Handler();

        if (isLoggedIn) {
            handler.postDelayed(() -> startActivity(new Intent(this, MainActivity.class)), 2500 );
        } else {
            handler.postDelayed(() -> startActivity(new Intent(this, LoginActivity.class)), 2500 );
        }

    }

}
