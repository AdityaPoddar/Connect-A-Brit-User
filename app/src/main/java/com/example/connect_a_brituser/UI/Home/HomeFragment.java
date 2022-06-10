package com.example.connect_a_brituser.UI.Home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.slidingpanelayout.widget.SlidingPaneLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.connect_a_brituser.R;
import com.google.android.material.slider.Slider;

import java.util.ArrayList;


public class HomeFragment extends Fragment {

        private ImageSlider imageSlider;
        private ImageView map;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home, container, false);

        imageSlider= view.findViewById(R.id.slider);


        ArrayList<SlideModel> images= new ArrayList<>();
        images.add(new SlideModel(R.drawable.slider1,null));
        images.add(new SlideModel(R.drawable.slider2,null));
        images.add(new SlideModel(R.drawable.slider3,null));
        images.add(new SlideModel(R.drawable.slider4,null));
        images.add(new SlideModel(R.drawable.slider5,null));
        images.add(new SlideModel(R.drawable.slider6,null));
        images.add(new SlideModel(R.drawable.slider7,null));
        images.add(new SlideModel(R.drawable.slider8,null));
        images.add(new SlideModel(R.drawable.slider9,null));
        images.add(new SlideModel(R.drawable.slider10,null));

        imageSlider.setImageList(images,ScaleTypes.CENTER_CROP);



        map=view.findViewById(R.id.map);
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenMap();
            }
        });

        return view;
    }

    private void OpenMap() {
        Uri uri= Uri.parse("geo:0,0?q=The British College");
        Intent intent = new Intent(Intent.ACTION_VIEW,uri);
        intent.setPackage("com.google.android.apps.maps");
        startActivity(intent);
    }
}