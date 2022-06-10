package com.example.connect_a_brituser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.connect_a_brituser.UI.Club.ClubActivity;
import com.example.connect_a_brituser.UI.Extra.CalenderActivity;
import com.example.connect_a_brituser.UI.Extra.DeveloperActivity;
import com.example.connect_a_brituser.UI.User.LoginActivity;
import com.example.connect_a_brituser.UI.User.RegisterActivity;
import com.example.connect_a_brituser.UI.User.UserActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    private BottomNavigationView bottomNavigationView;
    private NavController navController;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationMenu;
    FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView=findViewById(R.id.bottomNavigationView);

        auth=FirebaseAuth.getInstance();
        if(auth.getCurrentUser()==null)
        {
            startActivity(new Intent(MainActivity.this, RegisterActivity.class));
        }

        navController= Navigation.findNavController(this,R.id.frame_layout);
        NavigationUI.setupWithNavController(bottomNavigationView,navController);
        drawerLayout=findViewById(R.id.drawerLayout);
        navigationMenu=findViewById(R.id.navigation_menu);


        toggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.start,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        navigationMenu.setNavigationItemSelectedListener(this);
        NavigationUI.setupWithNavController(bottomNavigationView,navController);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(toggle.onOptionsItemSelected(item)){
            return true;
        }
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
    Intent intent;
        switch (item.getItemId()){

            case R.id.navigation_website:
//                Toast.makeText(this, "website", Toast.LENGTH_SHORT).show();
                gotoUrl("https://www.thebritishcollege.edu.np/");
                break;case
                    R.id.navigation_email:
                gotoUrl("https://mail.google.com/mail");
                break;
//
            case R.id.navigation_developer:
                intent=new Intent(MainActivity.this, DeveloperActivity.class);
                startActivity(intent);
                break;
                case R.id.navigation_clubs:
                intent=new Intent(MainActivity.this, ClubActivity.class);
                startActivity(intent);
                break;
            case R.id.navigation_logout:
                intent=new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                break;


            case R.id.navigation_chat:
                intent=new Intent(MainActivity.this, UserActivity.class);
                startActivity(intent);
                break;

            case R.id.navigation_calender:
                intent=new Intent(MainActivity.this, CalenderActivity.class);
                startActivity(intent);
                break;
        }
        return true;
    }

    private void gotoUrl(String s) {
        Uri uri= Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }
}