package service;

import model.Event;
import persistence.EventRepository;

import java.util.List;

public class EventService {
    private EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public void addEvent(Event event) {
        eventRepository.addEvent(event);
    }

    public Event getEventById(String eventId) {
        return eventRepository.getEventById(eventId);
    }

    public List<Event> getAllEvents() {
        return eventRepository.getAllEvents();
    }

    public void removeEvent(String eventId) {
        eventRepository.removeEvent(eventId);
    }
}
