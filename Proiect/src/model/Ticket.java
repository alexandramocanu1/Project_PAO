package model;

import service.AuditService;

public class Ticket {
    private String eventId;
    private String eventName;
    private String ticketId;
    private String userId;

    public Ticket(String eventId, String eventName, String ticketId, String userId) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.ticketId = ticketId;
        this.userId = userId;
        AuditService.logAction("TicketCreated");
    }

    public String getEventId() {
        return eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public String getTicketId() {
        return ticketId;
    }

    public String getUserId() {
        return userId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void displayTicketInfo() {
        System.out.println("Ticket Details:");
        System.out.println("Event ID: " + eventId);
        System.out.println("Event Name: " + eventName);
        System.out.println("Ticket ID: " + ticketId);
        System.out.println("User ID: " + userId);
    }
}
