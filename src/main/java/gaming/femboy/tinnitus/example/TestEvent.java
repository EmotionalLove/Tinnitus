package gaming.femboy.tinnitus.example;

import gaming.femboy.tinnitus.event.Event;

public class TestEvent extends Event {

    public long getTimeReacted() {
        return System.currentTimeMillis();
    }
}
