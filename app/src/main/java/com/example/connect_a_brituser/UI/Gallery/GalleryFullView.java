package com.example.connect_a_brituser.UI.Gallery;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.example.connect_a_brituser.R;
import com.github.chrisbanes.photoview.PhotoView;
import com.squareup.picasso.Picasso;

public class GalleryFullView extends AppCompatActivity {

    private PhotoView galleryFullView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_full_view);


        galleryFullView=findViewById(R.id.galleryFullView);

        String image=getIntent().getStringExtra("image");
        Picasso.get().load(image).into(galleryFullView);
    }
}