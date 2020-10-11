package com.example.riyasewana.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.riyasewana.Fragments.Donate;
import com.example.riyasewana.R;

import java.util.List;


public class DonateAdapter extends PagerAdapter {

    private List<Donate> donates;
    private LayoutInflater layoutInflater;
    private Context context;

    public DonateAdapter(List<Donate>donates, Context context){
        this.donates = donates;
        this.context = context;
    }

    @Override
    public int getCount() {
        return donates.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.donates,container,false);

        ImageView imageView;
        TextView title,desc;

        imageView = view.findViewById(R.id.donate_image);
        title = view.findViewById(R.id.donate_title);
        desc = view.findViewById(R.id.donate_desc);

        imageView.setImageResource(donates.get(position).getImage());
        title.setText(donates.get(position).getTitle());
        desc.setText(donates.get(position).getDesc());

        container.addView(view,0);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }

}

