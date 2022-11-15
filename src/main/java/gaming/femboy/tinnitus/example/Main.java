package gaming.femboy.tinnitus.example;

import gaming.femboy.tinnitus.EventManager;
import gaming.femboy.tinnitus.Listener;
import gaming.femboy.tinnitus.Reactor;

import java.util.Random;

public class Main implements Listener {

    private EventManager manager = new EventManager();

    public void start() {
        manager.registerListener(this);
        TestEvent event = new TestEvent();
        manager.invokeEvent(event);
        PreExitTestEvent event1 = new PreExitTestEvent();
        manager.invokeEvent(event1);
        if (event1.isCancelled()) {
            manager.clear();
            start();
        }
        return;
    }

    private final Reactor<?> onTest = new Reactor<TestEvent>(e -> {
        System.out.println("Nice! " + e.getTimeReacted());
    }).register(this);

    private final Reactor<?> onExit = new Reactor<PreExitTestEvent>(e -> {
        Random random = new Random();
        e.setCancelled(random.nextBoolean());
    }).register(this);

    public static void main(String[] args) {
        new Main().start();

    }
}