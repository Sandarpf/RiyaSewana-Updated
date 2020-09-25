package com.example.riyasewana.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.riyasewana.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchVehicle extends Fragment {

    public SearchVehicle() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_vehicle, container, false);
    }
}