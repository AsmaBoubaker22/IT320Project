package com.mycompany.oop;

public class Buyer {
    private String id;
    private String name;
    private int age;

    public Buyer(String id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Buyer{id='" + id + "', name='" + name + "', age=" + age + "}";
    }
}
