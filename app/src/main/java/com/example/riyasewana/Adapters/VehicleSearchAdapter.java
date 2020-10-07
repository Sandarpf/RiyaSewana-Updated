package com.example.riyasewana.Adapters;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.riyasewana.Models.VehicleModel;
import com.example.riyasewana.R;

import java.util.ArrayList;
import java.util.List;

public class VehicleSearchAdapter extends ArrayAdapter<VehicleModel>
{
    public VehicleSearchAdapter(Context context, int resource, List<VehicleModel> vehicleList)
    {
        super(context,resource, vehicleList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        VehicleModel vehicleModel = getItem(position);

        if (convertView == null){

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.vehicle_list, parent,false);
        }

        ImageView vehicle_img = (ImageView) convertView.findViewById(R.id.recycler_vehicle_img);
        TextView vehicle_name = (TextView) convertView.findViewById(R.id.vehicle_name);
        TextView vehicle_price = (TextView) convertView.findViewById(R.id.vehicle_price);
        TextView vehicle_mileage = (TextView) convertView.findViewById(R.id.vehicle_mileage);
        TextView vehicle_type = (TextView) convertView.findViewById(R.id.vehicle_type);
        TextView vehicle_location = (TextView) convertView.findViewById(R.id.vehicle_location);

        vehicle_img.setImageResource(vehicleModel.getVehicle_img());
        vehicle_name.setText(vehicleModel.getVehicle_name());
        vehicle_price.setText(vehicleModel.getVehicle_price());
        vehicle_mileage.setText(vehicleModel.getVehicle_mileage());
        vehicle_type.setText(vehicleModel.getVehicle_type());
        vehicle_location.setText(vehicleModel.getVehicle_location());

        return convertView;
    }
}


 /*
public class VehicleSearchAdapter extends RecyclerView.Adapter<VehicleSearchAdapter.ViewHolder> implements Filterable {

    List<VehicleModel> vehicleList;
    List<VehicleModel> searchVehicleList;
    Dialog vehicleDialog;


    public VehicleSearchAdapter(List<VehicleModel> vehicleList) {

        this.vehicleList = vehicleList;
        searchVehicleList = new ArrayList<>(vehicleList);
    }

    @NonNull
    @Override
    public VehicleSearchAdapter.ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.vehicle_list,parent,false);
        final ViewHolder viewHolder = new ViewHolder(view);

        vehicleDialog = new Dialog(parent.getContext());
        vehicleDialog.setContentView(R.layout.view_vehicle_details);

        viewHolder.view_vehicle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ImageView vehicle_img = (ImageView) vehicleDialog.findViewById(R.id.view_vehicle_img);
                TextView vehicle_name = (TextView) vehicleDialog.findViewById(R.id.vehicle_name_view);
                TextView vehicle_price = (TextView) vehicleDialog.findViewById(R.id.vehicle_price_view);
                TextView vehicle_location = (TextView) vehicleDialog.findViewById(R.id.vehicle_location_view);

                vehicle_img.setImageResource(vehicleList.get(viewHolder.getAdapterPosition()).getVehicle_img());
                vehicle_name.setText(vehicleList.get(viewHolder.getAdapterPosition()).getVehicle_name());
                vehicle_price.setText(vehicleList.get(viewHolder.getAdapterPosition()).getVehicle_price());
                vehicle_location.setText(vehicleList.get(viewHolder.getAdapterPosition()).getVehicle_location());

                Toast.makeText(parent.getContext(),"View vehicle details" +String.valueOf(viewHolder.getAdapterPosition()),Toast.LENGTH_SHORT).show();
                vehicleDialog.show();
            }
        });

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

        LinearLayout view_vehicle;
        ImageView vehicleImgView;
        TextView vehicleName , vehiclePrice , vehicleMileage , vehicleType , vehicleLocation;
        LinearLayout linearLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            view_vehicle = (LinearLayout) itemView.findViewById(R.id.vehicle_linear_layout);

            vehicleImgView = itemView.findViewById(R.id.recycler_vehicle_img);
            vehicleName = itemView.findViewById(R.id.vehicle_name);
            vehiclePrice = itemView.findViewById(R.id.vehicle_price);
            vehicleMileage = itemView.findViewById(R.id.vehicle_mileage);
            vehicleType = itemView.findViewById(R.id.vehicle_type);
            vehicleLocation = itemView.findViewById(R.id.vehicle_location);

            linearLayout = itemView.findViewById(R.id.vehicle_linear_layout);
            
        }
    }

    public interface OnVehicleListener{
        void onVehicleClick(int position);
    }
}*/
