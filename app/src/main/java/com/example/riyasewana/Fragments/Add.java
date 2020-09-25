package com.example.riyasewana.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.riyasewana.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Add extends Fragment {

    Button vehicleAddBtn , partsAddBtn;

    public Add() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_add, container, false);

     vehicleAddBtn = v.findViewById(R.id.add_screen_vehicle_btn);
     partsAddBtn = v.findViewById(R.id.add_screen_parts_btn);

     vehicleAddBtn.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {

             AddVehicle addVehicle = new AddVehicle();
             FragmentTransaction transaction = getFragmentManager().beginTransaction();
             transaction.replace(R.id.container_fragment, addVehicle);
             transaction.commit();
         }
     });

        partsAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AddParts addParts = new AddParts();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.container_fragment, addParts);
                transaction.commit();
            }
        });

    return v;
    }
}