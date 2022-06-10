package com.example.connect_a_brituser.UI.Extra;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.connect_a_brituser.R;

public class DeveloperActivity extends AppCompatActivity {

    private ImageView linkedIn,instagram,facebook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developer);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        linkedIn=findViewById(R.id.linkedIn);
        instagram=findViewById(R.id.instagram);
        facebook=findViewById(R.id.facebook);


        linkedIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goTouUrl("https://www.linkedin.com/in/aditya-agr%C3%A1w%C3%A2l-1b4b3a1a4/");
            }
        });
        instagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goTouUrl("https://www.instagram.com/_adityapoddar_/");
            }
        });
        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goTouUrl("https://www.facebook.com/aditya.poddar.338/");
            }
        });
    }

    private void goTouUrl(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }
}