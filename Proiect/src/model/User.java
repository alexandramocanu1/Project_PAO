package model;

import java.util.List;
import java.util.ArrayList;


//import type_event.TypeEvent;
//import dicount.Discount;

public class User {
    private String name;
    private String email;

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void viewEventsByType(List<Event> events, String eventType) {
        System.out.println("Viewing available events for " + eventType + "...");

        List<Event> filteredEvents = filterEventsByType(events, eventType);

        if (filteredEvents.isEmpty()) {
            System.out.println("No " + eventType + " events available.");
        } else {
            for (Event event : filteredEvents) {
                System.out.println("Event ID: " + event.getEventId());
                System.out.println("Event Name: " + event.getName());
                System.out.println("Date: " + event.getDate());
                System.out.println("Location: " + event.getLocation());
                System.out.println("-------------------------------------");
            }
        }
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
        } else {
            System.out.println("Sorry, no tickets available for this event.");
        }
    }

    public void cancelTicket(Event event) {
        System.out.println("Canceling ticket for event: " + event.getName());

        event.setAvailableTickets(event.getAvailableTickets() + 1);

        System.out.println("Ticket canceled successfully!");
    }

}
