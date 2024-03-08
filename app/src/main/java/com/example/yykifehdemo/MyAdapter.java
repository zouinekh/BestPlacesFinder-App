package com.example.yykifehdemo;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.RvViewHolder>{
    public List<destination> items;
    public Context context;
    public LatLng currentLocation ;
    MyAdapter(Context context, List<destination> places, LatLng userLocation){
        this.context=context;
        this.items=places;
        this.currentLocation = userLocation != null ? userLocation : new LatLng(36.6959, 10.1647);    }

    @NonNull
    @Override
    public RvViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.destinations,parent,false);

        return new RvViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RvViewHolder holder, int position) {
        destination place= items.get(position);
        holder.title.setText(place.getName());
        holder.description.setText(place.getDescription());
        holder.myImage.setImageResource(place.getImage());

        Intent i =new Intent( context, Map.class);
        i.putExtra("DestinationLatitude",place.getLocation().latitude);
        i.putExtra("DestinationLongitude",place.getLocation().longitude);
        i.putExtra("currentLatitude",currentLocation.latitude);
        i.putExtra("currentLongitude",currentLocation.longitude);
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class RvViewHolder extends RecyclerView.ViewHolder{
        public ImageView myImage;
        public TextView title;
        public TextView description;
        public Button button;
        public RvViewHolder(@NonNull View itemView) {
            super(itemView);
            myImage=itemView.findViewById(R.id.imageView2);
            title=itemView.findViewById(R.id.titreItem);
            description = itemView.findViewById(R.id.description);
            button=itemView.findViewById(R.id.button);
        }
    }
}
