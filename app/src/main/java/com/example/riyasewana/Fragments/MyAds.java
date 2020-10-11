package com.example.riyasewana.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.riyasewana.Adapters.MyAdsAdapter;
import com.example.riyasewana.Models.VehicleModel;
import com.example.riyasewana.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyAds extends Fragment {

    public MyAds() {
        // Required empty public constructor
    }

    RecyclerView recyclerView;
    List<VehicleModel> vehicleList;
    ImageView myAdsBack;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_ads, container, false);

        myAdsBack = view.findViewById(R.id.my_ads_back);
        recyclerView = view.findViewById(R.id.recycler_myads);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        recyclerView.setAdapter(new MyAdsAdapter(vehicleData()) {
        });

        myAdsBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Profile profile = new Profile();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.container_fragment,profile);
                transaction.commit();
            }
        });

        return view;
    }

    private List<VehicleModel> vehicleData() {

        vehicleList = new ArrayList<VehicleModel>();
        VehicleModel bike_tvs_wego_2010 = new VehicleModel("3",R.drawable.bike_tvs_wego_2010, "Bike-TVS-Wego-2010", "Rs.272,900", "200 km", "Auto", "Mahabage");
        vehicleList.add(bike_tvs_wego_2010);

        VehicleModel toyota_premio = new VehicleModel("4",R.drawable.toyota_premio, "Toyota-Premio-2013", "Rs.7,650,000", "30,000 km", "Auto", "Kurunagela");
        vehicleList.add(toyota_premio);
        return vehicleList;
    }

}