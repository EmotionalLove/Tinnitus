package femboy.gaming.tinnitus;

import femboy.gaming.tinnitus.event.Event;

import java.util.function.Consumer;

public class Reactor<T extends Event> {

    private final Consumer<T> func;

    public Reactor(Consumer<T> func) {
        this.func = func;
    }

    protected boolean react(Event event) {
        // we need a better solution here
        // i am brain dead
        try {
            func.accept((T) event);
        } catch (ClassCastException x) {
            return false;
        }
        return true;
    }

    public Reactor<?> register(Listener list) {
        list.addEventReaction(this);
        return this;
    }
}
