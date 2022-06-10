package com.example.connect_a_brituser.UI.Club;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.connect_a_brituser.R;

public class ClubActivity extends AppCompatActivity {

    ImageView leader,money,drama,sports;
    Button List;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Clubs");

        leader=findViewById(R.id.leader);
        money=findViewById(R.id.money);
        drama=findViewById(R.id.drama);
        sports=findViewById(R.id.sport);
        List=findViewById(R.id.List);

        leader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ClubActivity.this,LeaderClubActivity.class));

            }
        });
        money.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            startActivity(new Intent(ClubActivity.this,BuisnessClubActivity.class));
            }
        });
        drama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ClubActivity.this,DramaClubActivity.class));

            }
        });
        sports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ClubActivity.this,SportClubActivity.class));

            }
        });
        List.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ClubActivity.this,ListClubActivity.class));

            }
        });
    }
}