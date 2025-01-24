package com.mycompany.oop;

public class Property {
    private String id;
    private double size;
    private String owner;

    public Property(String id, double size, String owner) {
        this.id = id;
        this.size = size;
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Property{id='" + id + "', size=" + size + ", owner='" + owner + "'}";
    }
}
