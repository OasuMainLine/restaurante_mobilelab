package com.example.restaurante.Model;

import android.net.Uri;

import java.util.Objects;

public class Dish {
    private String id, descripction;
    private double price;
    private Uri image;

    public Dish(String id, String descripction, double price, Uri image) {
        this.id = id;
        this.descripction = descripction;
        this.price = price;
        this.image = image;
    }

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
        return Double.compare(dish.price, price) == 0 && Objects.equals(id, dish.id) && Objects.equals(descripction, dish.descripction) && Objects.equals(image, dish.image);
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
}
