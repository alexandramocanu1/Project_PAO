package model;

import service.AuditService;

// Ensure the correct types are used in Ticket class
public class Ticket {
    private int eventId;
    private String eventName;
    private int ticketId;
    private int userId;
    private double price;

    public Ticket(int eventId, String eventName, int ticketId, int userId, double price) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.ticketId = ticketId;
        this.userId = userId;
        this.price = price;
    }

    public int getEventId() {
        return eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public int getTicketId() {
        return ticketId;
    }

    public int getUserId() {
        return userId;
    }

    public double getPrice() {
        return price;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void displayTicketInfo() {
        System.out.println("Ticket Details:");
        System.out.println("Event ID: " + eventId);
        System.out.println("Event Name: " + eventName);
        System.out.println("Ticket ID: " + ticketId);
        System.out.println("User ID: " + userId);
    }
}
