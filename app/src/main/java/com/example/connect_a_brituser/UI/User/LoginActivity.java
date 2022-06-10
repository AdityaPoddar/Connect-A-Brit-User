package com.example.connect_a_brituser.UI.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.connect_a_brituser.MainActivity;
import com.example.connect_a_brituser.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {


    private EditText universityEmail,password;
    private Button loginBtn;
    private TextView registerNowBtn;
    private DatabaseReference databaseReference;
    FirebaseAuth auth;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().hide();

        universityEmail=findViewById(R.id.universityEmail);
        password=findViewById(R.id.password);
        loginBtn=findViewById(R.id.loginBtn);
        registerNowBtn=findViewById(R.id.registerNowBtn);
        auth=FirebaseAuth.getInstance();
        
        databaseReference= FirebaseDatabase.getInstance().getReference().child("Users");

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                final String universityemail=universityEmail.getText().toString();
                final String passwordTxt=password.getText().toString();


                if(universityemail.isEmpty())
                {
                    Toast.makeText(LoginActivity.this, "Enter Your University Email", Toast.LENGTH_SHORT).show();
                }
//                else if(!universityemail.matches(emailPattern))
//                {
//                    Toast.makeText(LoginActivity.this, "Invalid Email", Toast.LENGTH_SHORT).show();
//
//                }
                else if(passwordTxt.isEmpty())
                {
                    Toast.makeText(LoginActivity.this, "Enter Your Password", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    auth.signInWithEmailAndPassword(universityemail,passwordTxt).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            }
                            else
                            {
                                Toast.makeText(LoginActivity.this, "Error!!", Toast.LENGTH_SHORT).show();

                            }
                        }
                    });

                }
            }
        });

        registerNowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

    }
}