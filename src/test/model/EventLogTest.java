package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.Gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EventLogTest {


    private Event e1;
    private Event e2;
    private Event e3;
    private EventLog eventLogCurrent;

    @BeforeEach
    public void loadEvents() {
        eventLogCurrent = EventLog.getInstance();
        e1 = new Event("event1");
        e2 = new Event("event2");
        e3 = new Event("event3");
        eventLogCurrent.logEvent(e1);
        eventLogCurrent.logEvent(e2);
        eventLogCurrent.logEvent(e3);

    }

    @Test
    public void testLogEvent() {
        List<Event> l = new ArrayList<Event>();

        EventLog el = EventLog.getInstance();
        for (Event next : el) {
            l.add(next);
        }

        assertTrue(l.contains(e1));
        assertTrue(l.contains(e2));
        assertTrue(l.contains(e3));
    }

    @Test
    public void testClear() {
        EventLog el = EventLog.getInstance();
        el.clear();
        Iterator<Event> itr = el.iterator();
        assertTrue(itr.hasNext());
        assertEquals("Event log cleared.", itr.next().getDescription());
        assertFalse(itr.hasNext());
    }
}
