package com.example.connect_a_brituser.UI.Club;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.connect_a_brituser.R;


import java.util.List;

public class ClubAdaptor extends RecyclerView.Adapter<ClubAdaptor.ClubViewAdaptor> {


    private List<ClubData> list;
    private Context context;


    public ClubAdaptor() {
    }

    public ClubAdaptor(List<ClubData> list, Context context) {
        this.list = list;
        this.context = context;

    }

    @NonNull
    @Override
    public ClubViewAdaptor onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.club_list_layout,parent,false);
        return new ClubAdaptor.ClubViewAdaptor(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClubViewAdaptor holder, int position) {

        ClubData data = list.get(position);
        holder.userclubemaillist.setText(data.getEmail());
        holder.userclubgenderlist.setText(data.getGender());




    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ClubViewAdaptor extends RecyclerView.ViewHolder {
        TextView userclubemaillist,userclubgenderlist;


        public ClubViewAdaptor(@NonNull View itemView) {
            super(itemView);
            userclubemaillist=itemView.findViewById(R.id.userclubemaillist);
            userclubgenderlist=itemView.findViewById(R.id.userclubgenderlist);

        }
    }
}

