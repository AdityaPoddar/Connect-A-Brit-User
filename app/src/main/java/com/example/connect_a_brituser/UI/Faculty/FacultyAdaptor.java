package com.example.connect_a_brituser.UI.Faculty;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.connect_a_brituser.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FacultyAdaptor extends RecyclerView.Adapter<FacultyAdaptor.FacultyViewAdaptor> {

    private List<FacultyData> list;
    private Context context;
    private  String category;

    public FacultyAdaptor(List<FacultyData> list, Context context, String category) {
        this.list = list;
        this.context = context;
        this.category=category;
    }

    @NonNull
    @Override
    public FacultyViewAdaptor onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.faculty_card_layout,parent,false);
        return new FacultyViewAdaptor(view);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onBindViewHolder(@NonNull FacultyViewAdaptor holder, int position) {

        FacultyData facultyData = list.get(position);
        holder.facultyname.setText(facultyData.getName());
        holder.facultyemail.setText(facultyData.getEmail());
        holder.facultypost.setText(facultyData.getPost());
        try {
            Picasso.get().load(facultyData.getImage()).placeholder(R.drawable.avatar).into(holder.facultyImage);
        } catch (Exception e) {
            e.printStackTrace();
        }



    }

    public class FacultyViewAdaptor extends RecyclerView.ViewHolder {

        private TextView facultyname,facultyemail,facultypost;

        private ImageView facultyImage;

        public FacultyViewAdaptor(@NonNull View itemView) {
            super(itemView);
            facultyname=itemView.findViewById(R.id.facultyname);
            facultyemail=itemView.findViewById(R.id.facultyemail);
            facultypost=itemView.findViewById(R.id.facultypost);

            facultyImage=itemView.findViewById(R.id.facultyImage);

        }
    }
}
