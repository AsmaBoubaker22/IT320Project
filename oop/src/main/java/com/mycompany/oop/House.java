package com.mycompany.oop;

public class House {
    private String address;
    private double price;

    public House(String address, double price) {
        this.address = address;
        this.price = price;
    }

    @Override
    public String toString() {
        return "House{address='" + address + "', price=" + price + "}";
    }
}
