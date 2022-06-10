package com.example.connect_a_brituser.UI.Faculty;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.connect_a_brituser.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class FacultyFragment extends Fragment {


    private RecyclerView itDepartment,managementDepartment,hotelDepartment;
    private LinearLayout itNoData,managementNoData,hotelNoData;
    private List<FacultyData> itList,mangList,hotelList;
    private FacultyAdaptor adapter;
    private DatabaseReference databaseReference,dbRef;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_faculty, container, false);


        itDepartment = view.findViewById(R.id.itDepartment);
        managementDepartment = view.findViewById(R.id.managementDepartment);
        hotelDepartment = view.findViewById(R.id.hotelDepartment);
        itNoData = view.findViewById(R.id.itNoData);
        managementNoData = view.findViewById(R.id.managementNoData);
        hotelNoData = view.findViewById(R.id.hotelNoData);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Faculty");


        itDepartment();
        managementDepartment();
        hotelDepartment();

        return view;
    }

    private void itDepartment() {
        dbRef=databaseReference.child("IT");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                itList= new ArrayList<>();
                if(!snapshot.exists()){
                    itNoData.setVisibility(View.VISIBLE);
                    itDepartment.setVisibility(View.GONE);

                }
                else
                {
                    itNoData.setVisibility(View.GONE);
                    itDepartment.setVisibility(View.VISIBLE);
                    for( DataSnapshot dataSnapshot: snapshot.getChildren())
                    {
                        FacultyData data = dataSnapshot.getValue(FacultyData.class);
                        itList.add(data);
                    }
                    itDepartment.setHasFixedSize(true);
                    itDepartment.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter=  new FacultyAdaptor(itList,getContext(),"IT");
                    itDepartment.setAdapter(adapter);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void managementDepartment() {
        dbRef=databaseReference.child("Management");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mangList= new ArrayList<>();
                if(!snapshot.exists()){
                    managementNoData.setVisibility(View.VISIBLE);
                    managementDepartment.setVisibility(View.GONE);

                }
                else
                {
                    managementNoData.setVisibility(View.GONE);
                    managementDepartment.setVisibility(View.VISIBLE);
                    for( DataSnapshot dsnapshot: snapshot.getChildren())
                    {
                        FacultyData data = dsnapshot.getValue(FacultyData.class);
                        mangList.add(data);
                    }
                    managementDepartment.setHasFixedSize(true);
                    managementDepartment.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter=  new FacultyAdaptor(mangList,getContext(),"Management");
                    managementDepartment.setAdapter(adapter);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void hotelDepartment() {
        dbRef=databaseReference.child("Hotel Management");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                hotelList= new ArrayList<>();
                if(!snapshot.exists()){
                    hotelNoData.setVisibility(View.VISIBLE);
                    hotelDepartment.setVisibility(View.GONE);

                }
                else
                {
                    hotelNoData.setVisibility(View.GONE);
                    hotelDepartment.setVisibility(View.VISIBLE);
                    for( DataSnapshot dsnapshot: snapshot.getChildren())
                    {
                        FacultyData data = dsnapshot.getValue(FacultyData.class);
                        hotelList.add(data);
                    }
                    hotelDepartment.setHasFixedSize(true);
                    hotelDepartment.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter=  new FacultyAdaptor(hotelList,getContext(),"Hotel Management");
                    hotelDepartment.setAdapter(adapter);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}

