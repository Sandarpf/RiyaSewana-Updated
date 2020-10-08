package com.example.riyasewana.SearchVehicle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.riyasewana.Adapters.VehicleSearchAdapter;
import com.example.riyasewana.Fragments.Search;
import com.example.riyasewana.Models.VehicleModel;
import com.example.riyasewana.R;

import java.util.ArrayList;

public class SearchVehicles extends AppCompatActivity {

    public static ArrayList<VehicleModel> vehicleList = new ArrayList<VehicleModel>();
    private ListView listView;
    SearchView vehicleSearchView;

    //private EditText searchBar;
    Button back_arrow_to_search;
    private VehicleSearchAdapter adapter;

    private Button allButton;
    private Button toyotaButton;
    private Button suzukiButton;
    private Button autoButton;
    private Button threeweelButton;
    private Button bikeButton;

    private String selectedFilter = "all";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_vehicle);

        back_arrow_to_search = findViewById(R.id.back_arrow_to_search);
        back_arrow_to_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fragmentManager = getSupportFragmentManager();
                Search searchFragment = new Search();
                fragmentManager.beginTransaction().replace(R.id.search_vehicle_container, searchFragment).commit();

            }
        });

        setUpVehicleData();
        setUpVehicleList();
        setUpOnClickListener();
        vehicleSearch();

        /*
        vehicleSearchView = (SearchView) findViewById(R.id.vehicle_list_search_view);

        vehicleSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                adapter.getFilter().filter(s);

                return false;
            }
        });

         */

        /*
        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                (SearchVehicles.this).adapter.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

         */
    }

    private void vehicleSearch()
    {

        SearchView searchView = findViewById(R.id.vehicle_list_search_view);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s)
            {
                ArrayList<VehicleModel> filteredVehicles = new ArrayList<VehicleModel>();

                for (VehicleModel vehicleModel: vehicleList)
                {
                    if (vehicleModel.getVehicle_name().toLowerCase().contains(s.toLowerCase()))
                    {
                        filteredVehicles.add(vehicleModel);
                    }
                }

                VehicleSearchAdapter adapter = new VehicleSearchAdapter(getApplicationContext(), 0, filteredVehicles);

                adapter.getFilter().filter(s);

                listView = (ListView) findViewById(R.id.vehicle_list_display);
                listView.setAdapter(adapter);

                return false;
            }
        });
    }


    private void setUpVehicleData()
    {
        VehicleModel toypta_prius = new VehicleModel("0", R.drawable.toyota_prius, "Toyota-Prius", "Rs.3,480,000", "127,000 km", "Auto", "Dehiwala");
        vehicleList.add(toypta_prius);

        VehicleModel toyota_hyundai = new VehicleModel("1",R.drawable.hyundai_xg30, "Hyundai-XG30", "Rs.1,280,000", "15,000 km", "Auto", "Athurugiriya");
        vehicleList.add(toyota_hyundai);

        VehicleModel suzuki_alto = new VehicleModel("2",R.drawable.suzuki_alto_2010, "Suzuki-Alto-2010", "Rs.180,000", "500 km", "Auto", "Kadana");
        vehicleList.add(suzuki_alto);

        VehicleModel bike_tvs_wego_2010 = new VehicleModel("3",R.drawable.bike_tvs_wego_2010, "Bike-TVS-Wego-2010", "Rs.272,900", "200 km", "Auto", "Mahabage");
        vehicleList.add(bike_tvs_wego_2010);

        VehicleModel toyota_premio = new VehicleModel("4",R.drawable.toyota_premio, "Toyota-Premio-2013", "Rs.7,650,000", "30,000 km", "Auto", "Kurunagela");
        vehicleList.add(toyota_premio);

        VehicleModel bajaj_three_wheeler = new VehicleModel("5",R.drawable.bajaj_three_wheeler, "Threewheeler-Bajaj", "Rs.250,000", "25,452 km", "Auto", "Galle");
        vehicleList.add(bajaj_three_wheeler);

        VehicleModel toyota_vitz_2007 = new VehicleModel("6",R.drawable.toyota_vitz_2007, "Toyota-Vitz-2004", "Rs.180,000", "2,500 km", "Auto", "Gampaha");
        vehicleList.add(toyota_vitz_2007);

        VehicleModel suzuki_celerio_zxi_2017 = new VehicleModel("7",R.drawable.suzuki_celerio_zxi_2017, "Suzuki-Celerio-ZXI-2017", "Rs.3,650,000", "5,000 km", "Auto", "Horana");
        vehicleList.add(suzuki_celerio_zxi_2017);

        VehicleModel tvs_king_three_wheeler = new VehicleModel("8",R.drawable.tvs_king_three_wheeler, "Threewheeler-TVS-King", "Rs.750,000", "2450 km", "Auto", "Rathnapura");
        vehicleList.add(tvs_king_three_wheeler);

        VehicleModel toyota_land_cruiser_prado = new VehicleModel("9",R.drawable.toyota_land_cruiser_prado, "Toyota-Land-Cruiser-2005", "Rs.130,000", "9,500,000 km", "Auto", "Malabe");
        vehicleList.add(toyota_land_cruiser_prado);

        VehicleModel bike_honda_dio_2017 = new VehicleModel("10",R.drawable.bike_honda_dio_2017, "Bike-Honda-Dio-2017", "Rs.350,000", "200 km", "Auto", "Panadura");
        vehicleList.add(bike_honda_dio_2017);

        VehicleModel suzuki_wagonr = new VehicleModel("11",R.drawable.suzuki_wagonr, "Suzuki-Wagonr", "Rs.2,100,000", "20,000 km", "Auto", "Kiribathgoda");
        vehicleList.add(suzuki_wagonr);
    }

    private void setUpVehicleList()
    {
        listView = (ListView) findViewById(R.id.vehicle_list_display);
        VehicleSearchAdapter adapter = new VehicleSearchAdapter(getApplicationContext(), 0, vehicleList);
        listView.setAdapter(adapter);
    }

    private void setUpOnClickListener()
    {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l)
            {
                VehicleModel selectedVehicle = (VehicleModel) (listView.getItemAtPosition(position));
                Intent showVehicleDetails = new Intent(getApplicationContext(), ViewSearchedVehicle.class);
                showVehicleDetails.putExtra("id", selectedVehicle.getVehicle_id());
                startActivity(showVehicleDetails);
            }
        });
    }

    private void filterList(String status)
    {
        selectedFilter = status;
        ArrayList<VehicleModel> filteredVehicles = new ArrayList<VehicleModel>();

        for(VehicleModel vehicleModel: vehicleList)
        {
            if (vehicleModel.getVehicle_name().toLowerCase().contains(status))
            {
                filteredVehicles.add(vehicleModel);
            }
        }
        VehicleSearchAdapter adapter = new VehicleSearchAdapter(getApplicationContext(), 0, filteredVehicles);
        listView.setAdapter(adapter);
    }

    public void all_filter_tapped(View view)
    {
        selectedFilter = "all";
        final VehicleSearchAdapter adapter = new VehicleSearchAdapter(getApplicationContext(), 0, vehicleList);
        listView.setAdapter(adapter);

    }

    public void toyota_filter_tapped(View view)
    {
        filterList("toyota");
    }

    public void suzuki_filter_tapped(View view)
    {
        filterList("suzuki");
    }

    public void auto_filter_tapped(View view)
    {
        filterList("tvs");
    }

    public void threeweel_filter_tapped(View view)
    {
        filterList("threewheeler");
    }

    public void bike_filter_tapped(View view)
    {
        filterList("bike");
    }

}

