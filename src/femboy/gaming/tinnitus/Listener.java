package femboy.gaming.tinnitus;

import femboy.gaming.tinnitus.event.Event;

import java.util.ArrayList;
import java.util.List;

public interface Listener {

    List<Reactor<? extends Event>> reactions = new ArrayList<>();

    default void addEventReaction(Reactor<? extends Event> event) {
        reactions.add(event);
    }

    default int processEvent(Event event) {
        int acceptedCount = 0;
        for (Reactor<? extends Event> reaction : reactions) {
            if (reaction.react(event)) {
                acceptedCount++;
            }
        }
        return acceptedCount;
    }

}
