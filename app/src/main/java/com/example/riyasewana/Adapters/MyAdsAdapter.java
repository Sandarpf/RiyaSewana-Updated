package com.example.riyasewana.Adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.riyasewana.Models.VehicleModel;
import com.example.riyasewana.R;

import java.util.ArrayList;
import java.util.List;

public class MyAdsAdapter extends RecyclerView.Adapter<MyAdsAdapter.ViewHolder> implements Filterable {

    List<VehicleModel> vehicleList;
    List<VehicleModel> searchVehicleList;



    public MyAdsAdapter(List<VehicleModel> vehicleList) {

        this.vehicleList = vehicleList;
        searchVehicleList = new ArrayList<>(vehicleList);
    }

    @NonNull
    @Override
    public MyAdsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.myads,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdsAdapter.ViewHolder holder, int position) {

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

    @Override
    public Filter getFilter() {
        return vehicleSearchFilter;
    }

    private Filter vehicleSearchFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {

            List<VehicleModel> filterVehicleList = new ArrayList<>();

            if (charSequence == null || charSequence.length() == 0) {
                filterVehicleList.addAll(searchVehicleList);
            }
            else {
                String filterPattern = charSequence.toString().toLowerCase().trim();

                for (VehicleModel vehicle : searchVehicleList) {
                    if (vehicle.getVehicle_name().toLowerCase().contains(filterPattern)) {
                        filterVehicleList.add(vehicle);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filterVehicleList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            vehicleList.clear();
            vehicleList.addAll((List) filterResults.values);
            notifyDataSetChanged();
        }
    };

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
