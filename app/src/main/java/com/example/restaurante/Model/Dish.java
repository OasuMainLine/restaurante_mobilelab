package com.example.restaurante.Model;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.Objects;

public class Dish implements Parcelable {
    private String id, descripction;
    private double price;
    private Uri image;

    public Dish(String id, String descripction, double price, Uri image) {
        this.id = id;
        this.descripction = descripction;
        this.price = price;
        this.image = image;
    }

    protected Dish(Parcel in) {
        id = in.readString();
        descripction = in.readString();
        price = in.readDouble();
        image = in.readParcelable(Uri.class.getClassLoader());
    }

    public static final Creator<Dish> CREATOR = new Creator<Dish>() {
        @Override
        public Dish createFromParcel(Parcel in) {
            return new Dish(in);
        }

        @Override
        public Dish[] newArray(int size) {
            return new Dish[size];
        }
    };

    public Uri getImage() {
        return image;
    }

    public void setImage(Uri image) {
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescripction() {
        return descripction;
    }

    public void setDescripction(String descripction) {
        this.descripction = descripction;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dish dish = (Dish) o;
        return Objects.equals(id, dish.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, descripction, price, image);
    }

    @Override
    public String toString() {
        return "Dish{" +
                "id='" + id + '\'' +
                ", descripction='" + descripction + '\'' +
                ", price=" + price +
                ", image=" + image +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.id);
        parcel.writeString(this.descripction);
        parcel.writeDouble(this.price);
        parcel.writeParcelable(this.image, i);
    }
}
