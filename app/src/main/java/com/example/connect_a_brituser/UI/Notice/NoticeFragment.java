package com.example.connect_a_brituser.UI.Notice;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.connect_a_brituser.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class NoticeFragment extends Fragment {

    private RecyclerView noticeRecycler;
    private ProgressBar progressBar;
    private ArrayList<NoticeData> list;
    private NoticeAdaptor adaptor;
    private DatabaseReference databaseReference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_notice, container, false);

        noticeRecycler=(RecyclerView) view.findViewById(R.id.noticeRecycler);
        progressBar=view.findViewById(R.id.progressBar);
        databaseReference= FirebaseDatabase.getInstance().getReference().child("Newsfeed");


        noticeRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        noticeRecycler.setHasFixedSize(true);
        getNotice();

        return view;
    }

    private void getNotice() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list= new ArrayList<>();
                for(DataSnapshot d: snapshot.getChildren()){
                    NoticeData data=d.getValue(NoticeData.class);
                    list.add(0,data);

                }

                adaptor = new NoticeAdaptor(getContext(),list);
                adaptor.notifyDataSetChanged();
                progressBar.setVisibility(View.GONE);

                noticeRecycler.setAdapter(adaptor);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });


    }
}