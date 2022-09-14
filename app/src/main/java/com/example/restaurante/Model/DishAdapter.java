package com.example.restaurante.Model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.restaurante.R;

import java.util.ArrayList;

public class DishAdapter extends BaseAdapter {
    Context context;
    ArrayList<Dish> list;

    public DishAdapter(Context context, ArrayList<Dish> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.adapter, null);
        }

        TextView itemId = view.findViewById(R.id.itemId);
        TextView itemDescription = view.findViewById(R.id.itemDescription);
        TextView itemPrice = view.findViewById(R.id.itemPrice);

        ImageView itemImage = view.findViewById(R.id.itemImage);

        Dish dish = list.get(i);

        System.out.println(dish);
        itemId.setText(dish.getId());
        itemDescription.setText(dish.getDescripction());
        itemPrice.setText(dish.getPrice() + "");
        itemImage.setImageURI(dish.getImage());
        return view;

    }
}
