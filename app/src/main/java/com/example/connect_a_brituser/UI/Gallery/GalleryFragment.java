package com.example.connect_a_brituser.UI.Gallery;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.connect_a_brituser.R;
import com.example.connect_a_brituser.UI.Notice.NoticeData;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class GalleryFragment extends Fragment {

    RecyclerView socialRecycler,internationalRecycler,admissionRecycler;
    GallaryAdaptor adaptor;
    DatabaseReference databaseReference;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_gallery, container, false);

        socialRecycler=view.findViewById(R.id.socialRecycler);
        internationalRecycler=view.findViewById(R.id.internationalRecycler);
        admissionRecycler=view.findViewById(R.id.admissionRecycler);

        databaseReference= FirebaseDatabase.getInstance().getReference().child("Gallery");

        getAdmissionImage();
        getInternationalImage();
        getSocialImage();

        return view;
    }

    private void getAdmissionImage() {
        databaseReference.child("New Admission").addValueEventListener(new ValueEventListener() {

            List<String> imageList= new ArrayList<>();
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot d : snapshot.getChildren()) {
                    String data = (String) d.getValue();
                    imageList.add(data);
                }

                adaptor=new GallaryAdaptor(getContext(),imageList);
                admissionRecycler.setLayoutManager(new GridLayoutManager(getContext(),2));
                admissionRecycler.setAdapter(adaptor);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "Something Went Wrong!!", Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void getInternationalImage() {
        databaseReference.child("International Mobility Program").addValueEventListener(new ValueEventListener() {

            List<String> imageList= new ArrayList<>();
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot d : snapshot.getChildren()) {
                    String data = (String) d.getValue();
                    imageList.add(data);
                }

                adaptor=new GallaryAdaptor(getContext(),imageList);
                internationalRecycler.setLayoutManager(new GridLayoutManager(getContext(),2));
                internationalRecycler.setAdapter(adaptor);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "Something Went Wrong!!", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void getSocialImage() {
        databaseReference.child("Social Events").addValueEventListener(new ValueEventListener() {

            List<String> imageList= new ArrayList<>();
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot d : snapshot.getChildren()) {
                    String data = (String) d.getValue();
                    imageList.add(data);
                }

                adaptor=new GallaryAdaptor(getContext(),imageList);
                socialRecycler.setLayoutManager(new GridLayoutManager(getContext(),2));
                socialRecycler.setAdapter(adaptor);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "Something Went Wrong!!", Toast.LENGTH_SHORT).show();
            }
        });

    }
}