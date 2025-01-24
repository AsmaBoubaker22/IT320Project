package com.mycompany.oop;

public class Location {
    private String city;
    private String state;

    public Location(String city, String state) {
        this.city = city;
        this.state = state;
    }

    @Override
    public String toString() {
        return "Location{city='" + city + "', state='" + state + "'}";
    }
}
