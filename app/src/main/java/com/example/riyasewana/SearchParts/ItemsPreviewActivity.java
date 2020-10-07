package com.example.riyasewana.SearchParts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.riyasewana.Models.ItemsModel;
import com.example.riyasewana.R;

public class ItemsPreviewActivity extends AppCompatActivity {

    ImageView imageView;
    TextView t1;
    TextView t2;
    TextView t3;
    TextView t4;
    ItemsModel itemsModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items_preview);

        imageView = findViewById(R.id.imageView);
        t1 = findViewById(R.id.textView1);
        t2 = findViewById(R.id.textView2);
        t3 = findViewById(R.id.textView3);
        t4 = findViewById(R.id.textView4);

        Intent intent = getIntent();
        if(intent.getExtras() != null){
            itemsModel = (ItemsModel) intent.getSerializableExtra("items");
            imageView.setImageResource(itemsModel.getImage());
            t1.setText(itemsModel.getName());
            t2.setText(itemsModel.getPrice());
            t3.setText(itemsModel.getLocation());
            t4.setText(itemsModel.getDiscription());
        }



    }
}