package com.example.connect_a_brituser.UI.Notice;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.connect_a_brituser.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NoticeAdaptor extends RecyclerView.Adapter<NoticeAdaptor.NoticeViewAdaptor> {
    private Context context;
    private ArrayList<NoticeData> list;

    public NoticeAdaptor(Context context, ArrayList<NoticeData> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public NoticeViewAdaptor onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.notice_layout, parent, false);
        return new NoticeViewAdaptor(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoticeViewAdaptor holder, int position) {


        NoticeData data = list.get(position);
        holder.noticeTitle.setText(data.getTitle());
        holder.date.setText(data.getDate());
        holder.time.setText(data.getTime());
        try {
            if (data.getImage() != null)
                Picasso.get().load(data.getImage()).into(holder.noticeView);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class NoticeViewAdaptor extends RecyclerView.ViewHolder {


        private TextView noticeTitle, date, time;
        private ImageView noticeView;


        public NoticeViewAdaptor(@NonNull View itemView) {
            super(itemView);


            noticeTitle = itemView.findViewById(R.id.noticeTitle);
            noticeView = itemView.findViewById(R.id.noticeView);
            date = itemView.findViewById(R.id.date);
            time = itemView.findViewById(R.id.time);
        }
    }
}
