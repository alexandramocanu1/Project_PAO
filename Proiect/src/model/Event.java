package model;

import java.time.LocalDate;


public class Event {
    private int id;
    private String name;
    private String type;
    private LocalDate eventDate;
    private int venueId;

    public Event(int id, String name, String type, LocalDate eventDate, int venueId) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.eventDate = eventDate;
        this.venueId = venueId;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getVenueId() {
        return venueId;
    }

    public int getId() {
        return id;
    }

    public void displayEventInfo() {
        System.out.println("Event ID: " + id);
        System.out.println("Event Name: " + name);
        System.out.println("Event Type: " + type);
        System.out.println("Event Date: " + eventDate);
        System.out.println("Venue ID: " + venueId);
    }
}
