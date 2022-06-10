package com.example.connect_a_brituser.UI.User;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.connect_a_brituser.R;
import com.example.connect_a_brituser.UI.UserChat.ChatActivity;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class UserAdaptor extends RecyclerView.Adapter<UserAdaptor.UserViewAdaptor> {

    private List<UserData> list;
    private Context context;

    public UserAdaptor(List<UserData> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public UserViewAdaptor onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.user_card_layout,parent,false);
        return new UserAdaptor.UserViewAdaptor(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewAdaptor holder, int position) {

        UserData data = list.get(position);

        if(FirebaseAuth.getInstance().getCurrentUser().equals(data.getuId()))
        {
            holder.itemView.setVisibility(View.GONE);
        }

        holder.username.setText(data.getName());
        holder.useremail.setText(data.getEmail());
        holder.userdepartment.setText(data.getDepartment());
//        try {
//            Picasso.get().load(data.getImage()).placeholder(R.drawable.avatar).into(holder.userimage);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        code for chat
        holder.userCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(context, ChatActivity.class);
                intent.putExtra("uId",data.getuId());
                intent.putExtra("name",data.getName());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class UserViewAdaptor extends RecyclerView.ViewHolder {
        private TextView username,useremail,userdepartment;
        private CardView userCardView;

        private ImageView userimage;

        public UserViewAdaptor(@NonNull View itemView) {
            super(itemView);
            username=itemView.findViewById(R.id.username);
            useremail=itemView.findViewById(R.id.useremail);
            userdepartment=itemView.findViewById(R.id.userdepartment);
            userCardView=itemView.findViewById(R.id.userCardView);

            userimage=itemView.findViewById(R.id.userimage);

        }
    }
}
