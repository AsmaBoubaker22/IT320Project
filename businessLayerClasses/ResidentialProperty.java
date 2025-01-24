/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.it320projet;

/**
 *
 * @author Asma
 */
public abstract class ResidentialProperty extends Property { 
    private int rooms;
    private int bathrooms;
    private int parkingSpot;
    private int buildingSize;
    private int yearBuilt;
    
    // Constructor
    public ResidentialProperty(int propertyId, int landsize, Location location, int rooms, int bathrooms, int parkingSpot, int buildingSize, int yearBuilt) {
        super(propertyId, landsize, location);
        this.rooms = rooms;
        this.bathrooms = bathrooms;
        this.parkingSpot = parkingSpot;
        this.buildingSize = buildingSize;
        this.yearBuilt = yearBuilt;
    }
    
    public void displayResidentialDetails() {
        System.out.println("Residential Property Details:");
        System.out.println("Year Built: " + yearBuilt);
        System.out.println("Rooms: " + rooms);
        System.out.println("Bathrooms: " + bathrooms);
        System.out.println("Parking Spots: " + parkingSpot);
        System.out.println("Building Size: " + buildingSize + " sqm");
        super.displayBasicDetails();
    }

    public int getRooms() {
        return rooms;
    }
    public int getBathrooms() {
        return bathrooms;
    }
    public int getParkingSpot() {
        return parkingSpot;
    }
    public int getBuildingSize() {
        return buildingSize;
    }
    public int getYearBuilt() {
        return yearBuilt;
    }

    public void setRooms(int rooms) {
        this.rooms = rooms;
    }
    public void setBathrooms(int bathrooms) {
        this.bathrooms = bathrooms;
    }
    public void setParkingSpot(int parkingSpot) {
        this.parkingSpot = parkingSpot;
    }
    public void setBuildingSize(int buildingSize) {
        this.buildingSize = buildingSize;
    }
    public void setYearBuilt(int yearBuilt) {
        this.yearBuilt = yearBuilt;
    }
}

