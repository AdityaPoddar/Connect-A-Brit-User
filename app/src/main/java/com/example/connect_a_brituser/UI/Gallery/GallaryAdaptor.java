package com.example.connect_a_brituser.UI.Gallery;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.connect_a_brituser.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class GallaryAdaptor extends RecyclerView.Adapter<GallaryAdaptor.GalleryViewAdaptor>{

    private Context context;
    private List<String> list;

    public GallaryAdaptor(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public GallaryAdaptor.GalleryViewAdaptor onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.gallery_layout,parent,false);

        return new GalleryViewAdaptor(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GallaryAdaptor.GalleryViewAdaptor holder, int position) {


        Picasso.get().load(list.get(position)).into(holder.imageView);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,GalleryFullView.class);
                intent.putExtra("image",list.get(holder.getAdapterPosition()));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class GalleryViewAdaptor extends RecyclerView.ViewHolder {

        ImageView imageView;

        public GalleryViewAdaptor(@NonNull View itemView) {
            super(itemView);



            imageView=itemView.findViewById(R.id.image);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);

        }
    }
}
