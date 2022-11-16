package gaming.femboy.tinnitus;

import gaming.femboy.tinnitus.event.Event;

import java.util.ArrayList;
import java.util.List;

public interface Listener {

    default void addEventReaction(EventManager manager, Reactor<? extends Event> event) {
        boolean flag = manager.getReactions().containsKey(this.getClass());
        if (flag) {
            manager.getReactions().get(this.getClass()).add(event);
        } else {
            ArrayList<Reactor<? extends Event>> list = new ArrayList<>();
            list.add(event);
            manager.getReactions().put(this.getClass(), list);
        }
    }

    default int processEvent(Class<? extends Listener> listenerClass, EventManager manager, Event event) {
        int acceptedCount = 0;
        List<Reactor<? extends Event>> list = manager.getReactions().get(listenerClass);
        if (list == null) return acceptedCount;
        for (Reactor<? extends Event> reaction : list) {
            if (reaction.react(event)) {
                acceptedCount++;
            }
        }
        return acceptedCount;
    }

}
