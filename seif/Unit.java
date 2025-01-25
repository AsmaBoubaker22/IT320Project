package com.mycompany.main;

public class Unit extends ResidentialProperty {

    private String address;             // matches unit.address in MySQL
    private int floorLevel;
    private boolean hasBalcony;
    private boolean hasElevator;

    // Constructor
    public Unit(
            int propertyId,
            int landsize,
            Location location,
            int rooms,
            int bathrooms,
            int parkingSpot,
            int buildingSize,
            int yearBuilt,
            int floorLevel,
            boolean hasBalcony,
            boolean hasElevator) {
        super(propertyId, landsize, location, rooms, bathrooms, parkingSpot, buildingSize, yearBuilt);
        this.address = (location != null) ? location.getAddress() : null;
        this.floorLevel = floorLevel;
        this.hasBalcony = hasBalcony;
        this.hasElevator = hasElevator;
    }

    @Override
    public String getPropertyType() {
        return "Unit";
    }

    // GET/SET address
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    // GET/SET other fields
    public int getFloorLevel() { return floorLevel; }
    public void setFloorLevel(int floorLevel) { this.floorLevel = floorLevel; }

    public boolean hasBalcony() { return hasBalcony; }
    public void setHasBalcony(boolean hasBalcony) { this.hasBalcony = hasBalcony; }

    public boolean hasElevator() { return hasElevator; }
    public void setHasElevator(boolean hasElevator) { this.hasElevator = hasElevator; }

    // Not overriding any super method; remove @Override
    public void displayFeatures() {
        System.out.println("Unit Features:");
        System.out.println("Address: " + (address == null ? "N/A" : address));
        System.out.println("Floor Level: " + floorLevel);
        System.out.println("Balcony: " + (hasBalcony ? "Yes" : "No"));
        System.out.println("Elevator: " + (hasElevator ? "Yes" : "No"));
        super.displayResidentialDetails();
    }

    @Override
    public String toString() {
        return "Unit [address=" + address
                + ", floorLevel=" + floorLevel
                + ", hasBalcony=" + hasBalcony
                + ", hasElevator=" + hasElevator
                + "]";
    }
}
