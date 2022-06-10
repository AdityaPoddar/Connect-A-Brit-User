package com.example.connect_a_brituser.UI.Club;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.connect_a_brituser.R;

public class SportClubActivity extends AppCompatActivity {
    Button clubRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sport_club);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        clubRegister=findViewById(R.id.registerClub);

        clubRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SportClubActivity.this,ClubRegisterActivity.class));
            }
        });
    }
}