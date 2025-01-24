package com.mycompany.oop;

public class ResidentialProperty {
    private String address;
    private int rooms;

    public ResidentialProperty(String address, int rooms) {
        this.address = address;
        this.rooms = rooms;
    }

    @Override
    public String toString() {
        return "ResidentialProperty{address='" + address + "', rooms=" + rooms + "}";
    }
}
