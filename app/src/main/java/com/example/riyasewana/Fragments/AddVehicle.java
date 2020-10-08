package com.example.riyasewana.Fragments;

import android.Manifest;
import android.content.ClipData;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.EventLog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.riyasewana.R;

import java.io.IOException;

import static android.app.Activity.RESULT_OK;
import static android.widget.Toast.*;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddVehicle extends Fragment {

    String v_name, v_tell , v_model , v_price , v_engine , v_mileage;
    ImageView vehicleFrontImg , vehicleBackImg , vehicleInsideImg , vehicleOtherImg;
    Button addVehicleFrontImgBtn, addVehicleBackImgBtn, addVehicleInsideImgBtn, addVehicleOtherImgBtn;
    Button addNewVehicleBtn;
    EditText vehicleName , vehicleTell , vehicleModel , vehiclePrice , vehicleEngineCC , vehicleMileage;
    CheckBox vehicleAirCondition , vehiclePowerStearing , vehiclePowerMirror , vehiclePowerWindow;
    EditText vehicleDescription;

    private static final int GALLERY_REQUEST_CODE = 123;

    //img - previous
    //private static final int GalleryPick = 1;

    private Uri imageUri;

    public AddVehicle() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_add_vehicle, container, false);

        addNewVehicleBtn = (Button) v.findViewById(R.id.add_vehicle_btn);

        addVehicleFrontImgBtn = (Button) v.findViewById(R.id.add_vehicle_front_img_btn);
        addVehicleBackImgBtn = (Button) v.findViewById(R.id.add_vehicle_back_img_btn);
        addVehicleInsideImgBtn = (Button) v.findViewById(R.id.add_vehicle_inside_img_btn);
        addVehicleOtherImgBtn = (Button) v.findViewById(R.id.add_vehicle_other_img_btn);

        vehicleFrontImg = (ImageView) v.findViewById(R.id.add_vehicle_front_images);
        vehicleBackImg = (ImageView) v.findViewById(R.id.add_vehicle_back_images);
        vehicleInsideImg = (ImageView) v.findViewById(R.id.add_vehicle_inside_images);
        vehicleOtherImg = (ImageView) v.findViewById(R.id.add_vehicle_other_images);

        vehicleName = (EditText) v.findViewById(R.id.add_vehicle_name_edit_text);
        vehicleTell = (EditText) v.findViewById(R.id.add_vehicle_tell_edit_text);
        vehicleModel = (EditText) v.findViewById(R.id.add_vehicle_model_edit_text);
        vehiclePrice = (EditText) v.findViewById(R.id.add_vehicle_price_edit_text);
        vehicleEngineCC = (EditText) v.findViewById(R.id.add_vehicle_engine_edit_text);
        vehicleMileage = (EditText) v.findViewById(R.id.add_vehicle_milage_edit_text);

        vehicleAirCondition = (CheckBox) v.findViewById(R.id.air_condition_checkbox);
        vehiclePowerStearing = (CheckBox) v.findViewById(R.id.power_steering_checkbox);
        vehiclePowerMirror = (CheckBox) v.findViewById(R.id.power_mirror_checkbox);
        vehiclePowerWindow = (CheckBox) v.findViewById(R.id.power_window_checkbox);

        vehicleDescription = (EditText) v.findViewById(R.id.add_vehicle_description_textview);

        addNewVehicleBtn = (Button) v.findViewById(R.id.add_vehicle_btn);


        v.findViewById(R.id.add_vehicle_front_img_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ActivityCompat.checkSelfPermission(getActivity(),
                        Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
                {
                    requestPermissions(
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            2000
                    );
                }
                else {
                    startGalleryVehicleFront();
                }
            }
        });

        /*
        v.findViewById(R.id.add_vehicle_back_img_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ActivityCompat.checkSelfPermission(getActivity(),
                        Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
                {
                    requestPermissions(
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            2000
                    );
                }
                else {
                    startGalleryVehicleBack();
                }
            }
        });

        v.findViewById(R.id.add_vehicle_inside_img_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ActivityCompat.checkSelfPermission(getActivity(),
                        Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
                {
                    requestPermissions(
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            2000
                    );
                }
                else {
                    startGalleryVehicleInside();
                }
            }
        });

        v.findViewById(R.id.add_vehicle_other_img_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ActivityCompat.checkSelfPermission(getActivity(),
                        Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
                {
                    requestPermissions(
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            2000
                    );
                }
                else {
                    startGalleryOther();
                }
            }
        });

         */


        /* previous 1
        vehicleFrontImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenGallery();
            }
        });

        vehicleBackImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenGallery();
            }
        });

        vehicleInsideImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenGallery();
            }
        });

        vehicleOtherImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenGallery();
            }
        });

         */

        addNewVehicleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateVehicleDetails();
            }
        });

        String [] vehicle_city_values =
                {"Select City","Ambalangoda","Ampara","Anuradapura","Avissawella","Balangoda","Bandaragama","Bandarawela","Battaramulla","Batticaloa","Beruwala","Boralesgamuwa","Chavakacheri","Chilaw","Colombo","Daluguma","Dambulla","Dehiwala","Divulapitiya","Dompe","Eheliyagoda","Embilipitiya","Galle","Gampaha","Gampola","Hambantota","Haputale"};
        Spinner spinner_vehicle_city = (Spinner) v.findViewById(R.id.add_vehicle_city_text);
        ArrayAdapter<String> adapter_vehicle_city = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, vehicle_city_values);
        adapter_vehicle_city.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner_vehicle_city.setAdapter(adapter_vehicle_city);

        String [] vehicle_type_values =
                {"Select Type","Car","Van","Jeep","Motorcycle","Wagon","Pickup","Bus","Lorry","Crew Cab","Three Wheel","Tractor","Heavy-Duty","Other"};
        Spinner spinner_vehicle_type = (Spinner) v.findViewById(R.id.add_vehicle_type_text);
        ArrayAdapter<String> adapter_vehicle_type = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, vehicle_type_values);
        adapter_vehicle_type.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner_vehicle_type.setAdapter(adapter_vehicle_type);

        String [] vehicle_make_values =
                {"Select Make","Toyota","Nissan","Honda","Suzuki","Mitsubishi","Bajaj","Mazda","Tata","Isuzu","TVS","Yamaha","Micro","Mahinddra","Hyundai"};
        Spinner spinner_vehicle_make = (Spinner) v.findViewById(R.id.add_vehicle_make_text);
        ArrayAdapter<String> adapter_vehicle_make = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, vehicle_make_values);
        adapter_vehicle_make.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner_vehicle_make.setAdapter(adapter_vehicle_make);

        String [] vehicle_condition_values =
                {"Select Condition","Antique","Brand New","Registered (Used)","Unregistered (Recondition)"};
        Spinner spinner_vehicle_condition = (Spinner) v.findViewById(R.id.add_vehicle_condition_text);
        ArrayAdapter<String> adapter_vehicle_condition = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, vehicle_condition_values);
        adapter_vehicle_condition.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner_vehicle_condition.setAdapter(adapter_vehicle_condition);

        String [] vehicle_year_values =
                {"Select Year","2020","2019","2018","2017","2016","2015","2014","2013","2012","2011","2010","2009","2008","2007","2006","2005","2004","2003","2002","2001","2000","1999","1998","1997","1996","1995"};
        Spinner spinner_vehicle_year = (Spinner) v.findViewById(R.id.add_vehicle_year_text);
        ArrayAdapter<String> adapter_vehicle_year = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, vehicle_year_values);
        adapter_vehicle_year.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner_vehicle_year.setAdapter(adapter_vehicle_year);

        String [] vehicle_transmission_values =
                {"Select Transmission","Automatic","Manual"};
        Spinner spinner_vehicle_transmission = (Spinner) v.findViewById(R.id.add_vehicle_transmission_text);
        ArrayAdapter<String> adapter_vehicle_transmission = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, vehicle_transmission_values);
        adapter_vehicle_transmission.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner_vehicle_transmission.setAdapter(adapter_vehicle_transmission);

        String [] vehicle_fuel_values =
                {"Select Fuel Type","Petrol","Diesel","Electronic","Hybrid"};
        Spinner spinner_vehicle_fuel = (Spinner) v.findViewById(R.id.add_vehicle_fuel_type_text);
        ArrayAdapter<String> adapter_vehicle_fuel = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, vehicle_fuel_values);
        adapter_vehicle_fuel.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner_vehicle_fuel.setAdapter(adapter_vehicle_fuel);

        return v;
    }

    private void startGalleryVehicleFront()
    {
        Intent cameraIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        cameraIntent.setType("image/*");
        if (cameraIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivityForResult(cameraIntent, 1000);
        }
    }

    private void startGalleryVehicleBack()
    {
        Intent cameraIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        cameraIntent.setType("image/*");
        if (cameraIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivityForResult(cameraIntent, 1000);
        }
    }

    private void startGalleryVehicleInside()
    {
        Intent cameraIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        cameraIntent.setType("image/*");
        if (cameraIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivityForResult(cameraIntent, 1000);
        }
    }

    private void startGalleryOther()
    {
        Intent cameraIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        cameraIntent.setType("image/*");
        if (cameraIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivityForResult(cameraIntent, 1000);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(resultCode == RESULT_OK) {
            if(requestCode == 1000){
                Uri returnUri = data.getData();
                Bitmap bitmapImage = null;
                try {
                    bitmapImage = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), returnUri);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                vehicleFrontImg.setImageBitmap(bitmapImage);
                vehicleBackImg.setImageBitmap(bitmapImage);
                vehicleInsideImg.setImageBitmap(bitmapImage);
                vehicleOtherImg.setImageBitmap(bitmapImage);
            }

        }
        //Uri returnUri;
        //returnUri = data.getData();
    }

    /*previous 1
    private void OpenGallery() {

        //startActivityForResult(Intent.createChooser(new Intent().setAction(Intent.ACTION_GET_CONTENT).setType("image/*"), "Select vehicle image"),GalleryPick);

        Intent galleryIntent = new Intent();
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(Intent.createChooser(galleryIntent, "Pick vehicle image"), GalleryPick);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GalleryPick && requestCode == RESULT_OK && data != null){
            imageUri = data.getData();

           switch (getId()){
                case R.id.add_vehicle_front_images:
                    vehicleFrontImg.setImageURI(imageUri);
                    break;
                case R.id.add_vehicle_back_images:
                    vehicleBackImg.setImageURI(imageUri);
                    break;
                case R.id.add_vehicle_inside_images:
                    vehicleInsideImg.setImageURI(imageUri);
                    break;
                case R.id.add_vehicle_other_images:
                    vehicleOtherImg.setImageURI(imageUri);
                    break;
                default:
                    //Invalid
            }

        }
    }
     */


    private void validateVehicleDetails()
    {
        v_name = vehicleName.getText().toString();
        v_tell = vehicleTell.getText().toString();
        v_model = vehicleModel.getText().toString();
        v_price = vehiclePrice.getText().toString();
        v_engine = vehicleEngineCC.getText().toString();
        v_mileage = vehicleMileage.getText().toString();


        if (TextUtils.isEmpty(v_name)){
            Toast.makeText(getActivity(), "Please enter your name" , LENGTH_LONG).show();
        }
        else if (TextUtils.isEmpty(v_tell)){
            Toast.makeText(getActivity(), "Please enter your mobile number" , LENGTH_LONG).show();
        }
        else if (TextUtils.isEmpty(v_model)){
            Toast.makeText(getActivity(), "Please enter vehicle model" , LENGTH_LONG).show();
        }
        else if (TextUtils.isEmpty(v_price)){
            Toast.makeText(getActivity(), "Please enter vehicle price" , LENGTH_LONG).show();
        }
        else if (TextUtils.isEmpty(v_engine)){
            Toast.makeText(getActivity(), "Please enter vehicle engine capacity" , LENGTH_LONG).show();
        }
        else if (TextUtils.isEmpty(v_mileage)){
            Toast.makeText(getActivity(), "Please enter vehicle mileage" , LENGTH_LONG).show();
        }
        /*
        else if (imageUri == null){
            Toast.makeText(getActivity(),"Vehicle image is required" , LENGTH_LONG).show();
        }*/
        else {
            Profile profile = new Profile();
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.container_fragment, profile);
            transaction.commit();
        }
        addVelicleToList();
    }

    private void addVelicleToList() {

    }

    /* public void onCheckboxClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        String str="";
        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.air_condition_checkbox:
                str = checked?"Air Condition Selected":"Air Condition Deselected";
                break;
            case R.id.power_mirror_checkbox:
                str = checked?"Power Mirror Selected":"Power Mirror Deselected";
                break;
            case R.id.power_steering_checkbox:
                str = checked?"Power Steering Selected":"Power Steering Deselected";
                break;
            case R.id.power_window_checkbox:
                str = checked?"Power Window Selected":"Power Window Deselected";
                break;
        }

        //makeText(getApplicationContext(), str, LENGTH_SHORT).show();
    } */

   /* private void startGallery() {

        Intent vehicleImgIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        vehicleImgIntent.setType("image/*");
        if (vehicleImgIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivityForResult(vehicleImgIntent, 1000);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode == RESULT_OK) {
            if(requestCode == 1000){
                Uri returnUri = data.getData();
                Bitmap bitmapImage = null;
                try {
                    bitmapImage = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), returnUri);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                vehicleFrontImg.setImageBitmap(bitmapImage);
            }
        }
        //Uri returnUri;
        //returnUri = data.getData();
    } */
}