package com.example.connect_a_brituser.UI.Club;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.connect_a_brituser.R;
import com.example.connect_a_brituser.UI.Notice.NoticeAdaptor;
import com.example.connect_a_brituser.UI.Notice.NoticeData;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListClubActivity extends AppCompatActivity {

    RecyclerView listRecycler;
    DatabaseReference databaseReference,dbRef;
     ArrayList<ClubData> list;
     ClubAdaptor adaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_club);

        listRecycler=findViewById(R.id.listRecycler);
        databaseReference= FirebaseDatabase.getInstance().getReference().child("Clubs");

        clubList();



    }

    private void clubList() {
        dbRef=databaseReference.child("LeaderShip");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list= new ArrayList<>();

                for( DataSnapshot dataSnapshot: snapshot.getChildren())
                {
                    ClubData clubdata = dataSnapshot.getValue(ClubData.class);
                    list.add(clubdata);
                }
                listRecycler.setHasFixedSize(true);
                listRecycler.setLayoutManager(new LinearLayoutManager(ListClubActivity.this));
                adaptor=  new ClubAdaptor(list,ListClubActivity.this);


                listRecycler.setAdapter(adaptor);
            }



            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ListClubActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


            }


    }
