package com.mycompany.oop;

public class Townhouse {
    private String address;
    private double price;
    private int floors;

    public Townhouse(String address, double price, int floors) {
        this.address = address;
        this.price = price;
        this.floors = floors;
    }

    @Override
    public String toString() {
        return "Townhouse{address='" + address + "', price=" + price + ", floors=" + floors + "}";
    }
}
