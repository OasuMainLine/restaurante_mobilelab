package com.example.restaurante.Model;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;

public class RecordDishes  {
    private ArrayList<Dish> dishesList;
    public static final int CODE_OK = 0;
    public static final int CODE_NOT_FOUND = 1;
    public static final int CODE_REGISTERED_ALREADY = 2;
    public RecordDishes() {
        this.dishesList = new ArrayList<Dish>();
    }

    public int add(Dish dish){
        boolean isDishRegistered = this.dishesList.indexOf(dish) != -1;

        if(isDishRegistered){
            return CODE_REGISTERED_ALREADY;
        }
        this.dishesList.add(dish);
        return CODE_OK;
    }


    public int delete(String id){
        Dish dish = get(id);
        boolean isDeleted = this.dishesList.remove(dish);
        if(isDeleted){
            return CODE_OK;
        }
        return CODE_NOT_FOUND;
    }

    public Dish get(String id){
        for(Dish dish : dishesList){
            if(dish.getId().equalsIgnoreCase(id)){
                return dish;
            }
        }
        return null;
    }
    public Dish get(int position){
        Dish dish = this.dishesList.get(position);
        return dish;
    }

    public int modify(Dish dish){

        int dishPosition = dishesList.indexOf(dish);
        if(dishPosition == -1) return CODE_NOT_FOUND;
        dishesList.set(dishPosition, dish);
        return CODE_OK;
    }

    public ArrayList getArray() { return dishesList;}
    public void setArray(ArrayList<Dish> dishesList) {this.dishesList = dishesList;}


}
