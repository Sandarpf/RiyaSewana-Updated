package com.example.riyasewana.Fragments;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.riyasewana.R;

import java.util.List;

public class VehicleSearchAdapter extends RecyclerView.Adapter<VehicleSearchAdapter.ViewHolder> {

    List<VehicleModel> vehicleList;

    public VehicleSearchAdapter(List<VehicleModel> vehiclesList) {

        this.vehicleList = vehiclesList;
    }

    @NonNull
    @Override
    public VehicleSearchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.vehicle_list,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull VehicleSearchAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView vehicleImgView;
        TextView vehicleName , vehiclePrice , vehicleMileage , vehicleType , vehicleLocation;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            vehicleImgView = itemView.findViewById(R.id.recycler_vehicle_img);
            
        }
    }
}
