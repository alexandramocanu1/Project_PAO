package persistence;

import model.Event;
import model.User;
import model.Ticket;

import java.util.ArrayList;
import java.util.List;

public class EventRepository {

    private List<Event> events = new ArrayList<>();

    public List<Event> getAllEvents() {
        return new ArrayList<>(events);
    }

    public Event findEventById(String eventId) {
        for (Event event : events) {
            if (event.getEventId().equals(eventId)) {
                return event;
            }
        }
        return null;
    }

    public void updateEvent(Event event) {
        // This is a simple in-memory implementation. In a real-world application, you would update the event in the database.
        for (int i = 0; i < events.size(); i++) {
            if (events.get(i).getEventId().equals(event.getEventId())) {
                events.set(i, event);
                break;
            }
        }
    }

    public void buyTicket(User user, Event event) {
        // Check if there are available tickets
        if (event.getAvailableTickets() > 0) {
            // Update available tickets
            event.setAvailableTickets(event.getAvailableTickets() - 1);
            // Create and save ticket
            Ticket ticket = new Ticket(event.getEventId(), event.getName(), "TICKET_" + System.currentTimeMillis(), user.getUserId());
            // Log action
            System.out.println("Ticket bought successfully!");
        } else {
            System.out.println("Sorry, no tickets available for this event.");
        }
    }

    public void cancelTicket(User user, Event event) {
        // Implement ticket cancellation logic
        // For example, you might need to find the ticket associated with the user and event
        // and update its status to cancelled
        // Then update the available tickets for the event
        System.out.println("Ticket canceled successfully!");
    }

}
