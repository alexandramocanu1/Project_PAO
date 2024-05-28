package model;

import service.AuditService;
import java.util.List;
import java.util.ArrayList;

public class User {
    private String name;
    private String email;
    private String userId;

    // Constructor existent
    public User() {
    }

    // Constructor existent
    public User(String name, String email) {
        this.name = name;
        this.email = email;
        this.userId = "USER_" + System.currentTimeMillis();
    }

    // Constructor nou
    public User(String userId, String name, String email) {
        this.userId = userId;
        this.name = name;
        this.email = email;
    }

    // Metode getter și setter
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getUserId() {
        return userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void displayUserInfo() {
        System.out.println("User ID: " + userId + ", Name: " + name + ", Email: " + email);
    }

    public void viewEventsByType(List<Event> events, String eventType) {
        System.out.println("Viewing available events for " + eventType + "...");
        List<Event> filteredEvents = filterEventsByType(events, eventType);
        if (filteredEvents.isEmpty()) {
            System.out.println("No " + eventType + " events available.");
        } else {
            for (Event event : filteredEvents) {
                event.displayEventInfo();
            }
        }
        AuditService.logAction("viewEventsByType");
    }

    private List<Event> filterEventsByType(List<Event> events, String eventType) {
        List<Event> filteredEvents = new ArrayList<>();
        for (Event event : events) {
            if (event.getType().equals(eventType)) {
                filteredEvents.add(event);
            }
        }
        return filteredEvents;
    }

    public void buyTicket(Event event) {
        System.out.println("Buying ticket for event: " + event.getName());
        if (event.getAvailableTickets() > 0) {
            event.setAvailableTickets(event.getAvailableTickets() - 1);
            System.out.println("Ticket purchased successfully!");
            AuditService.logAction("buyTicket");
        } else {
            System.out.println("Sorry, no tickets available for this event.");
        }
    }

    public void cancelTicket(Event event) {
        System.out.println("Canceling ticket for event: " + event.getName());
        event.setAvailableTickets(event.getAvailableTickets() + 1);
        System.out.println("Ticket canceled successfully!");
        AuditService.logAction("cancelTicket");
    }
}
