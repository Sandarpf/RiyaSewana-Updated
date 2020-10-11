package com.example.riyasewana.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.riyasewana.Extra.ContactUs;
import com.example.riyasewana.R;
import com.example.riyasewana.SearchParts.SearchParts;
import com.example.riyasewana.SearchVehicle.SearchVehicles;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Home#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Home extends Fragment {

    Button vehicles,parts,contact;

    public Home() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v =   inflater.inflate(R.layout.fragment_home, container, false);
        vehicles = v.findViewById(R.id.button2);
        parts = v.findViewById(R.id.button);
        contact = v.findViewById(R.id.contactHome);


        vehicles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), SearchVehicles.class));
            }
        });

        parts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), SearchParts.class));
            }
        });

        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ContactUs.class));
            }
        });


        return v;
    }
}