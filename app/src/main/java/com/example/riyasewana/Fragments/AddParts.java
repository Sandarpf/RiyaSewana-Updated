package com.example.riyasewana.Fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.riyasewana.R;
import com.example.riyasewana.SearchParts.SearchParts;
import com.valdesekamdem.library.mdtoast.MDToast;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddParts extends Fragment {

    public AddParts() {
        // Required empty public constructor
    }

    EditText name;
    EditText contact;
    Spinner areaSpinner;
    Spinner conditionspinner;
    Spinner typeSpinner;
    Spinner catregorySpinner;
    EditText part;
    EditText price;
    EditText AddiInfo;
    Button button1;
    Button button4;
    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;

    private static final int PICK_IMAGE = 1;
    Uri imageUri;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_add_parts, container, false);

        name=(EditText)v.findViewById(R.id.username);
        contact=(EditText)v.findViewById(R.id.contactnum);
        areaSpinner=(Spinner)v.findViewById(R.id.area);
        conditionspinner=(Spinner)v.findViewById(R.id.condition);
        typeSpinner=(Spinner)v.findViewById(R.id.type);
        catregorySpinner=(Spinner)v.findViewById(R.id.category);
        part=(EditText)v.findViewById(R.id.brand);
        price=(EditText)v.findViewById(R.id.price);
        AddiInfo=(EditText)v.findViewById(R.id.additional);
        button1=(Button)v.findViewById(R.id.image1);
        button4=(Button)v.findViewById(R.id.addpart);
        imageView1=(ImageView)v.findViewById(R.id.imageView1);
        imageView2=(ImageView)v.findViewById(R.id.imageView2);
        imageView3=(ImageView)v.findViewById(R.id.imageView3);

        String [] values = {"Select City","Ambalangoda","Ampara","Anuradapura","Awissawella","Badulla","Balangoda",
                "Kottawa","Kandy","Kottawa","Kottawa","Kottawa","Kottawa","Kottawa","Kottawa","Kottawa","Kottawa",};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, values);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        areaSpinner.setAdapter(adapter);

        String [] conditions = {"Select Condition","Brand New","Used",};
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, conditions);
        adapter1.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        conditionspinner.setAdapter(adapter1);

        String [] type = {"Select Type","All","Car","Van","Jeep","Motocycle","Sport",
                "Wagon","Pick up","Bus","Lorry","Truck","Three wheel","Tractor","Heavy Duty","Others",};
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, type);
        adapter2.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        typeSpinner.setAdapter(adapter2);

        String [] category = {"Select Part Category","Air Conditioning & Heating","Air Intake & Fual Delivary","Brakes","Front steering & suspension","Engine","Battery",
                "Front Axle","Transmission","Catalytic converter","Muffler","Tailpipe","Rear axle","Rear suspension",};
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, category);
        adapter3.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        catregorySpinner.setAdapter(adapter3);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(name.getText().toString().isEmpty()){
                    MDToast mdToast = MDToast.makeText(getContext(), "User name required", MDToast.LENGTH_SHORT, MDToast.TYPE_ERROR);
                    mdToast.show();
                }else if(name.getText().toString().length() > 100){
                    MDToast mdToast = MDToast.makeText(getContext(), "User name too long", MDToast.LENGTH_SHORT, MDToast.TYPE_ERROR);
                    mdToast.show();
                }else if(contact.getText().toString().isEmpty()){
                    MDToast mdToast = MDToast.makeText(getContext(), "Contact number required", MDToast.LENGTH_SHORT, MDToast.TYPE_ERROR);
                    mdToast.show();
                }else if(contact.getText().toString().length() > 10){
                    MDToast mdToast = MDToast.makeText(getContext(), "Contact number too long", MDToast.LENGTH_SHORT, MDToast.TYPE_ERROR);
                    mdToast.show();
                }else if(contact.getText().toString().length() < 10){
                    MDToast mdToast = MDToast.makeText(getContext(), "Contact number too short", MDToast.LENGTH_SHORT, MDToast.TYPE_ERROR);
                    mdToast.show();
                }else if(part.getText().toString().isEmpty()){
                    MDToast mdToast = MDToast.makeText(getContext(), "Brand is required", MDToast.LENGTH_SHORT, MDToast.TYPE_ERROR);
                    mdToast.show();
                }else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setMessage("Warning, This add is valid for month only!!")
                            .setCancelable(false)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                    MDToast mdToast = MDToast.makeText(getContext(), "Added part successfully!!!", MDToast.LENGTH_SHORT, MDToast.TYPE_SUCCESS);
                                    mdToast.show();

                                     Intent intent = new Intent(getActivity(), SearchParts.class);
                                     startActivity(intent);
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();

                }
            }
        });

        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE) {
            imageUri = data.getData();
            imageView1.setImageURI(imageUri);

        }
    }
}