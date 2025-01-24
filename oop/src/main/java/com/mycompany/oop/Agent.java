package com.mycompany.oop;

public class Agent {
    private String id;
    private String name;

    public Agent(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Agent{id='" + id + "', name='" + name + "'}";
    }
}
