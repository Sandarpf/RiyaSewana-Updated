package com.example.riyasewana.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.riyasewana.LoginRegister.Welcome;
import com.example.riyasewana.R;
import com.google.firebase.auth.FirebaseAuth;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Profile#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Profile extends Fragment {

    ImageView logout, add;
    Button donateUs, myAds, settings;

    public Profile() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_profile, container, false);
        logout = v.findViewById(R.id.logout_image);
        donateUs = v.findViewById(R.id.donate_us);
        add = v.findViewById(R.id.add_ad_image);
        myAds = v.findViewById(R.id.myads);
        settings = v.findViewById(R.id.settings);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent i = new Intent(getContext(), Welcome.class);
                startActivity(i);
                getActivity().finish();
            }
        });

        donateUs.setOnClickListener(new View.OnClickListener() {
            // @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),DonateUs.class));
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Add add = new Add();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.container_fragment, add);
                transaction.commit();
            }
        });

        myAds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyAds myAds = new MyAds();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.container_fragment, myAds);
                transaction.commit();
            }
        });
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Settings settings = new Settings();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.container_fragment, settings);
                transaction.commit();
            }
        });

        return v;
    }
}