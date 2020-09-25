package com.example.riyasewana.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

import com.example.riyasewana.R;

public class SliderAdapter extends PagerAdapter {
    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context) {
        this.context = context;
    }

    int images[] = {
            R.drawable.bording_screen_one,
            R.drawable.bording_screen_two,
            R.drawable.bording_screen_three
    };


    int headings[] = {
            R.string.welcomeToRiyaSewana,
            R.string.buyandsell,
            R.string.BargainLike
    };

    int subheadings[] = {
            R.string.experianceBetterWay,
            R.string.youDontWantTo,
            R.string.sellYouVehicle
    };


    int descriptions[] = {
        R.string.easiestWay,
        R.string.sellingAtHighestValue,
        R.string.putYourAd,
    };


    @Override
    public int getCount() {
        return headings.length;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slides_layout,container,false);

        ImageView imageView = view.findViewById(R.id.sliderImage);
        TextView heading = view.findViewById(R.id.sliderHeading);
        TextView subheading = view.findViewById(R.id.sliderSubHeading);
        TextView description = view.findViewById(R.id.sliderDescription);

        imageView.setImageResource(images[position]);
        heading.setText(headings[position]);
        subheading.setText(subheadings[position]);
        description.setText(descriptions[position]);


        container.addView(view);

        return view;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (LinearLayout)object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout)object);
    }
}
