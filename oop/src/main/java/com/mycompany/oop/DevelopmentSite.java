package com.mycompany.oop;

public class DevelopmentSite {
    private String id;
    private String name;

    public DevelopmentSite(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "DevelopmentSite{id='" + id + "', name='" + name + "'}";
    }
}
