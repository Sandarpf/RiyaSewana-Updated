package com.example.riyasewana.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.riyasewana.Adapters.FavouritesAdapter;
import com.example.riyasewana.Models.VehicleModel;
import com.example.riyasewana.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Favourites#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Favourites extends Fragment {

    public Favourites() {
        // Required empty public constructor
    }

    RecyclerView recyclerView;
    List<VehicleModel> vehicleList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favourites, container, false);


        recyclerView = view.findViewById(R.id.recycler_favourites);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        recyclerView.setAdapter(new FavouritesAdapter(vehicleData()) {
        });


        return view;
    }

    private List<VehicleModel> vehicleData() {

        vehicleList = new ArrayList<VehicleModel>();
        VehicleModel toypta_prius = new VehicleModel("0", R.drawable.toyota_prius, "Toyota-Prius", "Rs.3,480,000", "127,000 km", "Auto", "Dehiwala");
        vehicleList.add(toypta_prius);

        VehicleModel toyota_hyundai = new VehicleModel("1",R.drawable.hyundai_xg30, "Hyundai-XG30", "Rs.1,280,000", "15,000 km", "Auto", "Athurugiriya");
        vehicleList.add(toyota_hyundai);

        VehicleModel suzuki_alto = new VehicleModel("2",R.drawable.suzuki_alto_2010, "Suzuki-Alto-2010", "Rs.180,000", "500 km", "Auto", "Kadana");
        vehicleList.add(suzuki_alto);

        return vehicleList;
    }

}