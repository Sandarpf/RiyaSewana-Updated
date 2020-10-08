package com.example.riyasewana.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.riyasewana.R;
import com.example.riyasewana.SearchParts.SearchParts;
import com.example.riyasewana.SearchVehicle.SearchVehicles;

/**
 * A simple {@link Fragment} subclass.
 */
public class Search extends Fragment {

    Button vehicleBtn , partsBtn;

    public Search() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_search, container, false);

     vehicleBtn = v.findViewById(R.id.search_screen_vehicle_btn);
     partsBtn = v.findViewById(R.id.search_screen_parts_btn);

     vehicleBtn.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {

             startActivity(new Intent(getActivity(), SearchVehicles.class));
         }
     });

        partsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                Intent intent = new Intent(getActivity(), SearchParts.class);
                startActivity(intent);

            }
        });

    return v;
    }
}