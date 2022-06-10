package com.example.connect_a_brituser.UI.Extra;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CalendarView;

import com.example.connect_a_brituser.R;

public class CalenderActivity extends AppCompatActivity {


    CalendarView calendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        calendarView=findViewById(R.id.calenderView);

    }
}