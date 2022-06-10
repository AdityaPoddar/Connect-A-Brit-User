package com.example.connect_a_brituser.UI.UserChat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.connect_a_brituser.R;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class MessageAdaptor extends RecyclerView.Adapter {

    private Context context;
    ArrayList<MessageData> messageDataArrayList;
    private int Item_send=1,Item_recieve=2;

    public MessageAdaptor() {
    }

    public MessageAdaptor(Context context, ArrayList<MessageData> messageDataArrayList) {
        this.context = context;
        this.messageDataArrayList = messageDataArrayList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType==Item_send)
        {
            View view= LayoutInflater.from(context).inflate(R.layout.sender,parent,false);
            return new SenderViewHolder(view);
        }
        else
        {
            View view= LayoutInflater.from(context).inflate(R.layout.reciever,parent,false);
            return new RecieverViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        MessageData messageData=messageDataArrayList.get(position);

        if(holder.getClass()==SenderViewHolder.class)
        {
            SenderViewHolder viewHolder=(SenderViewHolder) holder;
            viewHolder.txtMessages.setText(messageData.getMessage());

        }
        else
        {
            RecieverViewHolder viewHolder=(RecieverViewHolder)  holder;
            viewHolder.txtMessages.setText(messageData.getMessage());
        }

    }

    @Override
    public int getItemCount() {
        return messageDataArrayList.size();
    }

    @Override
    public int getItemViewType(int position) {
        MessageData messageData=messageDataArrayList.get(position);
        if(FirebaseAuth.getInstance().getCurrentUser().getUid().equals(messageData.getSenderID()))
        {
            return Item_send;
        }
        else
        {
            return Item_recieve;
        }

    }

    public class SenderViewHolder extends RecyclerView.ViewHolder
    {
        TextView txtMessages;

        public SenderViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMessages=itemView.findViewById(R.id.txtMessages);
        }
    }

    public class RecieverViewHolder extends RecyclerView.ViewHolder
    {
        TextView txtMessages;

        public RecieverViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMessages=itemView.findViewById(R.id.txtMessages);

        }
    }
}
