package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

// log of alarm events
public class EventLog implements Iterable<Event> {
    /**
     * the only EventLog in the system (Singleton Design Pattern)
     */
    private static EventLog theLog;
    private Collection<Event> events;

    //constructs an event log
    private EventLog() {
        events = new ArrayList<Event>();
    }

    // MODIFIES: this
    // EFFECTS: Gets instance of EventLog - creates it if it doesn't already exist.
    public static EventLog getInstance() {
        if (theLog == null) {
            theLog = new EventLog();
        }

        return theLog;
    }

    //MODIFIES: this
    //EFFECTS: adds event to log
    public void logEvent(Event e) {
        events.add(e);
    }

    //MODIFIES: this
    //EFFECTS: clears log
    public void clear() {
        events.clear();
        logEvent(new Event("Event log cleared."));
    }

    @Override
    public Iterator<Event> iterator() {
        return events.iterator();
    }
}
