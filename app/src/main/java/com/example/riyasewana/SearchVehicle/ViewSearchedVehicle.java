package com.example.riyasewana.SearchVehicle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.riyasewana.Models.VehicleModel;
import com.example.riyasewana.R;

public class ViewSearchedVehicle extends AppCompatActivity {

    Button back_arrow_to_search_vehicle;
    VehicleModel selectedVehicle;
    private static final int REQUEST_CALL = 1;
    private TextView mobile_no_call;
    private Button vehicle_call;
    private TextView mobile_no_msg;
    private Button vehicle_message;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_searched_vehicle);

        back_arrow_to_search_vehicle = findViewById(R.id.back_arrow_to_search_vehicle);
        back_arrow_to_search_vehicle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewSearchedVehicle.this, SearchVehicles.class);
                startActivity(intent);
                finish();
            }
        });

        mobile_no_msg = findViewById(R.id.mobile_no_msg);
        vehicle_call = findViewById(R.id.vehicle_call);
        vehicle_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                vehicleCallButton();
            }
        });

        mobile_no_call = findViewById(R.id.mobile_no_call);
        vehicle_message = findViewById(R.id.vehicle_message);
        vehicle_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("android.intent.action.VIEW");
                Uri data = Uri.parse("sms:");
                intent.setData(data);
                startActivity(intent);
            }
        });

        getSelectedVehicle();
        setValues();
    }

    private void getSelectedVehicle()
    {
        Intent previousIntent = getIntent();
        String passedStringId = previousIntent.getStringExtra("id");
        selectedVehicle = SearchVehicles.vehicleList.get(Integer.valueOf(passedStringId));

    }

    private void setValues()
    {
        ImageView vehicle_img = (ImageView) findViewById(R.id.recycler_vehicle_img);
        TextView vehicle_name = (TextView) findViewById(R.id.vehicle_name);
        TextView vehicle_price = (TextView) findViewById(R.id.vehicle_price);
        TextView vehicle_mileage = (TextView) findViewById(R.id.vehicle_mileage);
        TextView vehicle_type = (TextView) findViewById(R.id.vehicle_type);
        TextView vehicle_location = (TextView) findViewById(R.id.vehicle_location);

        vehicle_img.setImageResource(selectedVehicle.getVehicle_img());
        vehicle_name.setText(selectedVehicle.getVehicle_name());
        vehicle_price.setText(selectedVehicle.getVehicle_price());
        vehicle_mileage.setText(selectedVehicle.getVehicle_mileage());
        vehicle_type.setText(selectedVehicle.getVehicle_type());
        vehicle_location.setText(selectedVehicle.getVehicle_location());
    }

    private void vehicleCallButton()
    {
        String number = vehicle_call.getText().toString();

        if (number.trim().length() > 0)
        {
            if (ContextCompat.checkSelfPermission(ViewSearchedVehicle.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
                ActivityCompat.requestPermissions(ViewSearchedVehicle.this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
            else
            {
                String dial = "tel: " + number;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        if (requestCode == REQUEST_CALL)
        {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                vehicleCallButton();
                Toast.makeText(this, "Calling the vehicle owner", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

}