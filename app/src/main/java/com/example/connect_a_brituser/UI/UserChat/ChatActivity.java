package com.example.connect_a_brituser.UI.UserChat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.connect_a_brituser.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;

public class ChatActivity extends AppCompatActivity {


    private RecyclerView chatRecycler;
    private  EditText chatEditText;
    private Button chatSendBtn;
    private TextView chatTextView;
    String hisId,hisName,myId,senderRoom,recieverRoom;
    DatabaseReference  databaseReference,dbRef,chatRef;
    FirebaseAuth auth;
    ArrayList<MessageData> messageDataArrayList;
    MessageAdaptor messageAdaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);



        chatEditText=findViewById(R.id.chatEditText);
        chatRecycler=findViewById(R.id.chatRecycler);
        chatSendBtn=findViewById(R.id.chatSendBtn);
        chatTextView=findViewById(R.id.chatTextView);
        databaseReference= FirebaseDatabase.getInstance().getReference();
        auth=FirebaseAuth.getInstance();

        hisId=getIntent().getStringExtra("uId");
        myId=auth.getUid();
        senderRoom=myId+hisId;
        recieverRoom=hisId+myId;
        hisName=getIntent().getStringExtra("name");
        messageDataArrayList=new ArrayList<>();



        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(true);

        messageAdaptor=new MessageAdaptor(ChatActivity.this,messageDataArrayList);
        chatRecycler.setLayoutManager(linearLayoutManager);
        chatRecycler.setAdapter(messageAdaptor);


        chatRef=databaseReference.child("Chat").child(senderRoom).child("Messages");
        chatRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                messageDataArrayList.clear();
                for(DataSnapshot snapshot1:snapshot.getChildren())
                {
                    MessageData data=snapshot1.getValue(MessageData.class);
                    messageDataArrayList.add(data);
                }
                messageAdaptor.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        dbRef=databaseReference.child("User").child(auth.getUid());
        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        chatTextView.setText(hisName);

        chatSendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message=chatEditText.getText().toString();
                if(TextUtils.isEmpty(message))
                {
                    Toast.makeText(ChatActivity.this, "No text to send", Toast.LENGTH_SHORT).show();
                    return;
                }
                else
                {
                    chatEditText.setText("");
                    Date date = new Date();
                    MessageData data= new MessageData(message,myId,date.getTime());
                    databaseReference.child("Chat").child(senderRoom).child("Messages").push().setValue(data).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            databaseReference.child("Chat").child(recieverRoom).child("Messages").push().setValue(data).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                }
                            });

                        }
                    });
                }
            }
        });




    }

    private void sendMessage(String message) {

    }
}