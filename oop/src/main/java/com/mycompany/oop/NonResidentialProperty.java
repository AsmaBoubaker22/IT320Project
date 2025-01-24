package com.mycompany.oop;

public class NonResidentialProperty {
    private String name;
    private double value;

    public NonResidentialProperty(String name, double value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String toString() {
        return "NonResidentialProperty{name='" + name + "', value=" + value + "}";
    }
}
