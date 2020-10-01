package com.example.riyasewana.Adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.riyasewana.Models.VehicleModel;
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

        holder.vehicleImgView.setImageResource(vehicleList.get(position).getVehicle_img());
        holder.vehicleName.setText(vehicleList.get(position).getVehicle_name());
        holder.vehiclePrice.setText(vehicleList.get(position).getVehicle_price());
        holder.vehicleMileage.setText(vehicleList.get(position).getVehicle_mileage());
        holder.vehicleType.setText(vehicleList.get(position).getVehicle_type());
        holder.vehicleLocation.setText(vehicleList.get(position).getVehicle_location());

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent();
            }
        });
    }

    @Override
    public int getItemCount() {

        return vehicleList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView vehicleImgView;
        TextView vehicleName , vehiclePrice , vehicleMileage , vehicleType , vehicleLocation;
        LinearLayout linearLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            vehicleImgView = itemView.findViewById(R.id.recycler_vehicle_img);
            vehicleName = itemView.findViewById(R.id.vehicle_name);
            vehiclePrice = itemView.findViewById(R.id.vehicle_price);
            vehicleMileage = itemView.findViewById(R.id.vehicle_mileage);
            vehicleType = itemView.findViewById(R.id.vehicle_type);
            vehicleLocation = itemView.findViewById(R.id.vehicle_location);

            linearLayout = itemView.findViewById(R.id.vehicle_linear_layout);
            
        }
    }
}
