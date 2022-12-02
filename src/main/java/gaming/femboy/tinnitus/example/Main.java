package gaming.femboy.tinnitus.example;

import gaming.femboy.tinnitus.EventManager;
import gaming.femboy.tinnitus.Listener;
import gaming.femboy.tinnitus.Reactor;

import java.util.Random;

public class Main implements Listener {

    private EventManager manager = new EventManager();

    public void start() {
        manager.registerListener(this);
        manager.registerListener(new EmptyListener());
        TestEvent event = new TestEvent();
        manager.invokeEvent(event);
        PreExitTestEvent event1 = new PreExitTestEvent();
        manager.invokeEvent(event1);
        if (event1.isCancelled()) {
            manager.clear();
            start();
            return;
        }
        System.out.println("Ok we're done");
        manager.removeListener(this);
        TestEvent event2 = new TestEvent();
        manager.invokeEvent(event2);
        return;
    }

    private final Reactor<?> onTest = new Reactor<TestEvent>(e -> {
        System.out.println("Nice! " + e.getTimeReacted());
    }).register(this.manager, this);

    private final Reactor<?> onExit = new Reactor<PreExitTestEvent>(e -> {
        Random random = new Random();
        e.setCancelled(random.nextBoolean());
    }).register(this.manager, this);

    public static void main(String[] args) {
        new Main().start();

    }
}