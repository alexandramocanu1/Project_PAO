package model;

import service.AuditService;

public class Venue {
    private String venueId;
    private String name;
    private String location;

    public Venue(String venueId, String name, String location) {
        this.venueId = venueId;
        this.name = name;
        this.location = location;
    }

    public String getVenueId() {
        return venueId;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public void setVenueId(String venueId) {
        this.venueId = venueId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void displayVenueInfo() {
        System.out.println("Venue Details:");
        System.out.println("Venue ID: " + venueId);
        System.out.println("Name: " + name);
        System.out.println("Location: " + location);
        AuditService.logAction("displayVenueInfo");
    }

}
