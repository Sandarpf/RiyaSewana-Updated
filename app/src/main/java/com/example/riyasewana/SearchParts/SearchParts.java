package com.example.riyasewana.SearchParts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.riyasewana.Models.ItemsModel;
import com.example.riyasewana.R;

import java.util.ArrayList;
import java.util.List;

public class SearchParts extends AppCompatActivity {

    String names[] = {"Tyre set for car","Off-Road lights set","Side mirror set for car","Car horn","4 valves carbulator","Break Calipers","Allow-Wheel set for BMW"};
    String price[] = {"RS.50,000","RS.20,000","RS.5,000","RS.30,000","15,000","RS.26,500","RS.75,000"};
    String location[] = {"Malabe","Colombo 05","Kottawa","Moratuwa","Kandy","Waththala","Sri Jayawardhanapura"};
    String discription[] = {"CEAT tyre set for sale. If you grt this before november you can get 10% discout from this."
            ,"Off-Road light system set for sale. If you grt this before november you can get 15% discout from this."
            ,"Car side mirror set for sale. If you grt this before november you can get 10% discout from this."
            ,"Car horn set for sale. If you grt this before november you can get 10% discout from this."
            ,"4 Valves carbulator for sale. If you grt this before november you can get 10% discout from this."
            ,"Breake caliper set for sale. If you grt this before november you can get 10% discout from this."
            ,"Allow-Wheel set for BMW car for sale. If you grt this before november you can get 10% discout from this."};
    int images[] = {R.drawable.tyres,R.drawable.offroadlight,R.drawable.sidemirror,R.drawable.horn,R.drawable.carbulator,R.drawable.breakeclaiper,R.drawable.wheel};

    List<ItemsModel> itemsModelList = new ArrayList<>();

    ListView listView;

    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_parts);

        listView = findViewById(R.id.listview);


        for(int i = 0; i < names.length; i++){

            ItemsModel itemsModel = new ItemsModel(names[i],price[i],location[i],discription[i],images[i]);

            itemsModelList.add(itemsModel);

        }

        customAdapter = new CustomAdapter(itemsModelList,this);

        listView.setAdapter(customAdapter);


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


        getMenuInflater().inflate(R.menu.search_menu,menu);

        MenuItem menuItem = menu.findItem(R.id.searchView);

        SearchView searchView = (SearchView) menuItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                Log.e("Main"," data search"+newText);

                customAdapter.getFilter().filter(newText);



                return true;
            }
        });


        return true;

    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();


        if(id == R.id.searchView){

            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public class CustomAdapter extends BaseAdapter implements Filterable {

        private List<ItemsModel> itemsModelsl;
        private List<ItemsModel> itemsModelListFiltered;
        private Context context;

        public CustomAdapter(List<ItemsModel> itemsModelsl, Context context) {
            this.itemsModelsl = itemsModelsl;
            this.itemsModelListFiltered = itemsModelsl;
            this.context = context;
        }



        @Override
        public int getCount() {
            return itemsModelListFiltered.size();
        }

        @Override
        public Object getItem(int position) {
            return itemsModelListFiltered.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View view = getLayoutInflater().inflate(R.layout.row_items,null);


            TextView names = view.findViewById(R.id.name);
            TextView price = view.findViewById(R.id.price);
            TextView location = view.findViewById(R.id.location);
            ImageView imageView = view.findViewById(R.id.images);

            names.setText(itemsModelListFiltered.get(position).getName());
            price.setText(itemsModelListFiltered.get(position).getPrice());
            location.setText(itemsModelListFiltered.get(position).getLocation());
            imageView.setImageResource(itemsModelListFiltered.get(position).getImage());

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e("main activity","item clicked");
                    startActivity(new Intent(SearchParts.this,ItemsPreviewActivity.class).putExtra("items",itemsModelListFiltered.get(position)));

                }
            });

            return view;
        }



        @Override
        public Filter getFilter() {
            Filter filter = new Filter() {
                @Override
                protected FilterResults performFiltering(CharSequence constraint) {

                    FilterResults filterResults = new FilterResults();
                    if(constraint == null || constraint.length() == 0){
                        filterResults.count = itemsModelsl.size();
                        filterResults.values = itemsModelsl;

                    }else{
                        List<ItemsModel> resultsModel = new ArrayList<>();
                        String searchStr = constraint.toString().toLowerCase();

                        for(ItemsModel itemsModel:itemsModelsl){
                            if(itemsModel.getName().contains(searchStr) || itemsModel.getPrice().contains(searchStr)){
                                resultsModel.add(itemsModel);

                            }
                            filterResults.count = resultsModel.size();
                            filterResults.values = resultsModel;
                        }


                    }

                    return filterResults;
                }

                @Override
                protected void publishResults(CharSequence constraint, FilterResults results) {

                    itemsModelListFiltered = (List<ItemsModel>) results.values;
                    notifyDataSetChanged();

                }
            };
            return filter;
        }
    }

}
