package gaming.femboy.tinnitus;

import gaming.femboy.tinnitus.event.Event;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class EventManager {

    private List<Listener> listenerRegistry = new LinkedList<>();
    private HashMap<Class<? extends Listener>, List<Reactor<? extends Event>>> reactions = new HashMap<>();

    public int registerListener(Listener listener) {
        int index = listenerRegistry.size();
        listenerRegistry.add(listener);
        return index;
    }

    public boolean removeListener(int index) {
        if (index > listenerRegistry.size()) return false;
        var listener = listenerRegistry.get(index);
        removeListener(listener);
        return true;
    }

    public void removeListener(Listener listener) {
        reactions.remove(listener.getClass());
        listenerRegistry.remove(listener);
    }

    public int invokeEvent(Event event) {
        AtomicInteger acceptedCount = new AtomicInteger();
        listenerRegistry.forEach(l -> acceptedCount.addAndGet(l.processEvent(l.getClass(), this, event)));
        return acceptedCount.get();
    }

    public void clear() {
        listenerRegistry.clear();
    }

    public HashMap<Class<? extends Listener>, List<Reactor<? extends Event>>> getReactions() {
        return reactions;
    }
}
