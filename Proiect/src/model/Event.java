package model;

public class Event {
    private String eventId;
    private String name;
    private String date;
    private String location;
    private int availableTickets;

    public Event(String eventId, String name, String date, String location, int availableTickets) {
        this.eventId = eventId;
        this.name = name;
        this.date = date;
        this.location = location;
        this.availableTickets = availableTickets;
    }

    public String getType() {
        String lowercaseName = name.toLowerCase();
        if (lowercaseName.contains("movie")) {
            return "Movie";
        } else if (lowercaseName.contains("concert")) {
            return "Concert";
        } else if (lowercaseName.contains("sport")) {
            return "Sport";
        } else {
            return "Other";
        }
    }

    public String getEventId() {
        return eventId;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getLocation() {
        return location;
    }

    public int getAvailableTickets() {
        return availableTickets;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setAvailableTickets(int availableTickets) {
        this.availableTickets = availableTickets;
    }

    public void displayEventInfo() {
        System.out.println("Event Details:");
        System.out.println("Event ID: " + eventId);
        System.out.println("Name: " + name);
        System.out.println("Date: " + date);
        System.out.println("Location: " + location);
        System.out.println("Available Tickets: " + availableTickets);
    }

}