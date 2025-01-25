package com.mycompany.main;

public class Townhouse extends ResidentialProperty {

    private String address;           // matches townhouse.address in MySQL
    private int numberOfSharedWalls;
    private int numberOfLevels;

    // Constructor
    public Townhouse(
            int propertyId,
            int landsize,
            Location location,
            int rooms,
            int bathrooms,
            int parkingSpot,
            int buildingSize,
            int yearBuilt,
            int numberOfSharedWalls,
            int numberOfLevels) {
        super(propertyId, landsize, location, rooms, bathrooms, parkingSpot, buildingSize, yearBuilt);
        this.address = (location != null) ? location.getAddress() : null;
        this.numberOfSharedWalls = numberOfSharedWalls;
        this.numberOfLevels = numberOfLevels;
    }

    @Override
    public String getPropertyType() {
        return "TownHouse";
    }

    // GET/SET address
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    // GET/SET other fields
    public int getNumberOfSharedWalls() { return numberOfSharedWalls; }
    public void setNumberOfSharedWalls(int numberOfSharedWalls) {
        this.numberOfSharedWalls = numberOfSharedWalls;
    }

    public int getNumberOfLevels() { return numberOfLevels; }
    public void setNumberOfLevels(int numberOfLevels) {
        this.numberOfLevels = numberOfLevels;
    }

    // Not overriding from parent, so remove @Override
    public void displayFeatures() {
        System.out.println("Townhouse Features:");
        System.out.println("Address: " + (address == null ? "N/A" : address));
        System.out.println("Number of Shared Walls: " + numberOfSharedWalls);
        System.out.println("Number of Levels: " + numberOfLevels);
        super.displayResidentialDetails();
    }

    @Override
    public String toString() {
        return "Townhouse [address=" + address
                + ", numberOfSharedWalls=" + numberOfSharedWalls
                + ", numberOfLevels=" + numberOfLevels
                + "]";
    }
}
