package persistence;

import model.Event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventRepository {
    private Map<String, Event> eventMap;

    public EventRepository() {
        this.eventMap = new HashMap<>();
    }

    public void addEvent(Event event) {
        eventMap.put(event.getEventId(), event);
    }

    public Event getEventById(String eventId) {
        return eventMap.get(eventId);
    }

    public List<Event> getAllEvents() {
        return new ArrayList<>(eventMap.values());
    }

    public void removeEvent(String eventId) {
        eventMap.remove(eventId);
    }
}
