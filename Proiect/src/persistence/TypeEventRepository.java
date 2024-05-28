package persistence;

import model.TypeEvent;
import java.util.ArrayList;
import java.util.List;

public class TypeEventRepository {
    private List<TypeEvent> typeEvents = new ArrayList<>();

    public void addTypeEvent(TypeEvent typeEvent) {
        typeEvents.add(typeEvent);
    }

    public List<TypeEvent> getAllTypeEvents() {
        return new ArrayList<>(typeEvents);
    }

    public TypeEvent findTypeEventById(String typeId) {
        return typeEvents.stream()
                .filter(typeEvent -> typeEvent.getTypeId().equals(typeId))
                .findFirst()
                .orElse(null);
    }

    public void updateTypeEvent(TypeEvent typeEvent) {
        int index = findIndexById(typeEvent.getTypeId());
        if (index >= 0) {
            typeEvents.set(index, typeEvent);
        }
    }

    public void deleteTypeEvent(String typeId) {
        int index = findIndexById(typeId);
        if (index >= 0) {
            typeEvents.remove(index);
        }
    }

    private int findIndexById(String typeId) {
        for (int i = 0; i < typeEvents.size(); i++) {
            if (typeEvents.get(i).getTypeId().equals(typeId)) {
                return i;
            }
        }
        return -1;
    }
}
