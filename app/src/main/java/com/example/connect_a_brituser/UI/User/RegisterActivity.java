package com.example.connect_a_brituser.UI.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.connect_a_brituser.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

//

    private EditText registerID,registerName,registerEmail,registerPassword,registerConPassword,registerYear,registerDepartment;
    private Button registerBtn;
    private TextView loginNowBtn;
    private DatabaseReference  databaseReference,dbRef;
    FirebaseAuth auth;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();


        registerID=findViewById(R.id.universityId);
        registerName=findViewById(R.id.registerName);
        registerEmail=findViewById(R.id.registerEmail);
        registerPassword=findViewById(R.id.registerPassword);
        registerConPassword=findViewById(R.id.registerConPassword);
        registerBtn=findViewById(R.id.registerBtn);
        loginNowBtn=findViewById(R.id.loginNowBtn);
        registerYear=findViewById(R.id.registerYear);
        registerDepartment=findViewById(R.id.registerDepartment);

        auth=FirebaseAuth.getInstance();


        databaseReference= FirebaseDatabase.getInstance().getReference();




        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkValidation();

            }
        });



        loginNowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });




    }

    private void checkValidation() {
        final String fullName=registerName.getText().toString();
        final String id=registerID.getText().toString();
        final String email=registerEmail.getText().toString();
        final String password=registerPassword.getText().toString();
        final String confirmPassword=registerConPassword.getText().toString();
        final String year=registerYear.getText().toString();
        final String department=registerDepartment.getText().toString();
        if(fullName.isEmpty())
        {
            Toast.makeText(RegisterActivity.this, "Enter FullName", Toast.LENGTH_SHORT).show();
        }
        else if(id.isEmpty())
        {
            Toast.makeText(RegisterActivity.this, "Enter University ID", Toast.LENGTH_SHORT).show();
        }
        else if(email.isEmpty())
        {
            Toast.makeText(RegisterActivity.this, "Enter Email", Toast.LENGTH_SHORT).show();
        }
//        else if(!email.matches(emailPattern))
//        {
//            Toast.makeText(RegisterActivity.this, "Invalid Email", Toast.LENGTH_SHORT).show();
//
//        }
        else if(password.isEmpty())
        {
            Toast.makeText(RegisterActivity.this, "Enter password", Toast.LENGTH_SHORT).show();
        }
        else if(confirmPassword.isEmpty())
        {
            Toast.makeText(RegisterActivity.this, " Confirm Password", Toast.LENGTH_SHORT).show();
        }
        else if(year.isEmpty())
        {
            Toast.makeText(RegisterActivity.this, "Enter Year", Toast.LENGTH_SHORT).show();
        }else if(department.isEmpty())
        {
            Toast.makeText(RegisterActivity.this, "Enter Department", Toast.LENGTH_SHORT).show();
        }
        else if(!password.equals(confirmPassword))
        {
            Toast.makeText(RegisterActivity.this, "Password are not Matching", Toast.LENGTH_SHORT).show();
        }
        else
        {
            insertUserData();

        }
    }

    private void insertUserData() {
        final String fullName=registerName.getText().toString();
        final String id=registerID.getText().toString();
        final String email=registerEmail.getText().toString();
        final String password=registerPassword.getText().toString();
        final String department=registerDepartment.getText().toString();
        final String confirmPassword=registerConPassword.getText().toString();
        final String year=registerYear.getText().toString();


        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    dbRef= databaseReference.child("Users").child(auth.getUid());
                    UserData data = new UserData(fullName,email,password,id,year,department,auth.getUid());
                    dbRef.setValue(data).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(RegisterActivity.this, "User Registered Successfully!!", Toast.LENGTH_SHORT).show();
                            finish();

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(RegisterActivity.this, "Something Went Wrong!!", Toast.LENGTH_SHORT).show();

                        }
                    });

                }
            }
        });







//



                }





}