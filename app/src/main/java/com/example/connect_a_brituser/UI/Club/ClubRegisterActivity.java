package com.example.connect_a_brituser.UI.Club;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.connect_a_brituser.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ClubRegisterActivity extends AppCompatActivity {

    EditText clubEmail,clubGender;
    Button clubRegisterBtn;
    Spinner clubCategory;
    String category;
    private DatabaseReference databaseReference;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club_register);


        clubEmail=findViewById(R.id.clubEmail);
        ;
        clubRegisterBtn=findViewById(R.id.clubRegisterBtn);
        clubCategory=findViewById(R.id.clubCategory);
        clubGender=findViewById(R.id.clubGender);
        databaseReference= FirebaseDatabase.getInstance().getReference().child("Clubs");

        String[] items = new String[]{"Select Category","LeaderShip","Drama","Sports","Business"};
        clubCategory.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,items));

        clubCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                category=clubCategory.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        clubRegisterBtn.setOnClickListener(new View.OnClickListener() {
            String email=clubEmail.getText().toString();
            String gender=clubGender.getText().toString();

            @Override
            public void onClick(View view) {
                 if(category.equals("Select Category"))
                {
                    Toast.makeText(ClubRegisterActivity.this, "Please Select a Category", Toast.LENGTH_SHORT).show();
                }
//                 else if(email.isEmpty() || gender.isEmpty())
//                 {
//                     Toast.makeText(ClubRegisterActivity.this, "Enter Values", Toast.LENGTH_SHORT).show();
//                 }

                 else
                 {
                     uploadData();
                 }
            }
        });




    }

    private void uploadData() {
        String email=clubEmail.getText().toString();
        String gender=clubGender.getText().toString();

        final String uniqueKey=databaseReference.push().getKey();
        databaseReference=databaseReference.child(category);
        ClubData data = new ClubData(email,gender);
        databaseReference.child(uniqueKey).setValue(data).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(ClubRegisterActivity.this, "Admin Has been notified", Toast.LENGTH_SHORT).show();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(ClubRegisterActivity.this, "Something Went Wrong!!", Toast.LENGTH_SHORT).show();

            }
        });
    }
}