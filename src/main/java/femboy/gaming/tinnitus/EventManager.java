package femboy.gaming.tinnitus;

import femboy.gaming.tinnitus.event.Event;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class EventManager {

    private List<Listener> listenerRegistry = new LinkedList<>();

    public int registerListener(Listener listener) {
        int index = listenerRegistry.size();
        listenerRegistry.add(listener);
        return index;
    }

    public int invokeEvent(Event event) {
        AtomicInteger acceptedCount = new AtomicInteger();
        listenerRegistry.forEach(l -> acceptedCount.addAndGet(l.processEvent(event)));
        return acceptedCount.get();
    }

    public void clear() {
        listenerRegistry.clear();
    }

}
