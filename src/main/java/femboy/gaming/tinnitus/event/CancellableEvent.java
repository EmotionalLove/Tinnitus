package femboy.gaming.tinnitus.event;

public class CancellableEvent extends Event {

    private boolean cancelled;

    public CancellableEvent(boolean cancelledByDefault) {
        this.cancelled = cancelledByDefault;
    }

    public CancellableEvent() {
        this(false);
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    public void cancel() {
        setCancelled(true);
    }
}
