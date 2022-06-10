package com.example.connect_a_brituser.UI.Extra;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.connect_a_brituser.MainActivity;
import com.example.connect_a_brituser.R;
import com.example.connect_a_brituser.UI.User.LoginActivity;
import com.example.connect_a_brituser.UI.UserChat.ChatActivity;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        getSupportActionBar().hide();
//        will hide the tool bar for only this
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
//                after creating intent with 2s this will take it to MainActivity
                startActivity(new Intent(SplashScreen.this, LoginActivity.class));
                finish();
            }
        },1000);
    }
}