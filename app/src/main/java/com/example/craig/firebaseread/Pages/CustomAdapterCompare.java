package com.example.craig.firebaseread.Pages;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.craig.firebaseread.R;

import java.util.ArrayList;

/**
 * Created by craig on 25/04/2017.
 */

public class CustomAdapterCompare extends ArrayAdapter<Double>{
    private final ArrayList<Double> total;
    private final Integer[]imageList;
    public CustomAdapterCompare(Context context,ArrayList<Double> total,Integer[]imageList) {
        super(context, R.layout.custom_row_compare,total);
        this.total = total;
        this.imageList = imageList;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflate = LayoutInflater.from(getContext());
        View customView = inflate.inflate(R.layout.custom_row_compare,parent,false);
        TextView txtView = (TextView) customView.findViewById(R.id.txtRow);
        txtView.setText("Total Price = €" + String.format("%.2f", total.get(position)));
        ImageView image = (ImageView) customView.findViewById(R.id.imageView);
        image.setImageResource(imageList[position]);
        return customView;
    }
}
