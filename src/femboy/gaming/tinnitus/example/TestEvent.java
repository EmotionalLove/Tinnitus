package femboy.gaming.tinnitus.example;

import femboy.gaming.tinnitus.event.Event;

public class TestEvent extends Event {

    public long getTimeReacted() {
        return System.currentTimeMillis();
    }
}
