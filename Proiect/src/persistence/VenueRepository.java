package persistence;

import model.Venue;
import java.util.ArrayList;
import java.util.List;

public class VenueRepository {
    private List<Venue> venues = new ArrayList<>();

    public void addVenue(Venue venue) {
        venues.add(venue);
    }

    public List<Venue> getAllVenues() {
        return new ArrayList<>(venues);
    }

    public Venue findVenueById(String venueId) {
        return venues.stream()
                .filter(venue -> venue.getVenueId().equals(venueId))
                .findFirst()
                .orElse(null);
    }

    public void updateVenue(Venue venue) {
        int index = findIndexById(venue.getVenueId());
        if (index >= 0) {
            venues.set(index, venue);
        }
    }

    public void deleteVenue(String venueId) {
        int index = findIndexById(venueId);
        if (index >= 0) {
            venues.remove(index);
        }
    }

    private int findIndexById(String venueId) {
        for (int i = 0; i < venues.size(); i++) {
            if (venues.get(i).getVenueId().equals(venueId)) {
                return i;
            }
        }
        return -1;
    }
}
