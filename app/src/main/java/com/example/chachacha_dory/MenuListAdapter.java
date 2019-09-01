package com.example.chachacha_dory;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

public class MenuListAdapter extends BaseAdapter {
    private ArrayList<ResponseMenu.MenuClass.FoodClass> foodList;
    MenuListAdapter(ArrayList<ResponseMenu.MenuClass.FoodClass> foods){
        foodList= foods;
    }

    @Override
    public int getCount() {
        return foodList.size();
    }

    @Override
    public Object getItem(int position) {
        return foodList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        if(convertView==null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_menu, parent,false);
        }

        TextView menuName = convertView.findViewById(R.id.menuNameText);
        TextView menuPrice = convertView.findViewById(R.id.menuPriceText);

        ResponseMenu.MenuClass.FoodClass foods = foodList.get(position);

        menuName.setText(foods.getMenuName());
        menuPrice.setText(foods.getMenuPrice());

        return convertView;
    }

    public void addMenu(String name, String price){
        ResponseMenu.MenuClass.FoodClass item = new ResponseMenu.MenuClass.FoodClass();
        item.setMenuName(name);
        item.setMenuPrice(price);

        foodList.add(item);
    }
}
