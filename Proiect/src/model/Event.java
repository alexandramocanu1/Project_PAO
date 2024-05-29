package model;

import service.AuditService;

import java.sql.Date;

public class Event {
    private int id;
    private String name;
    private String type;
    private Date eventDate;
    private int venueId;

    public Event(int id, String name, String type, Date eventDate, int venueId) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.eventDate = eventDate;
        this.venueId = venueId;
    }

    // Getters și Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public int getVenueId() {
        return venueId;
    }

    public void setVenueId(int venueId) {
        this.venueId = venueId;
    }

    public void displayEventInfo() {
        System.out.println("Event ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Type: " + type);
        System.out.println("Date: " + eventDate);
        System.out.println("Venue ID: " + venueId);
        AuditService.logAction("displayEventInfo");
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", eventDate=" + eventDate +
                ", venueId=" + venueId +
                '}';
    }
}
