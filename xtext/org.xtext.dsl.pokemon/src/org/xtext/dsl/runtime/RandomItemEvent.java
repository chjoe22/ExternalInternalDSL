package org.xtext.dsl.runtime;

public class RandomItemEvent implements GameEvent {

    private final String name;
    private final int quantity;
    private final String description;
    private final String next;

    public RandomItemEvent(String name, int quantity, String description, String next) {
        this.name = name;
        this.quantity = quantity;
        this.description = description;
        this.next = next;
    }

    @Override
    public String play(GameRuntime runtime) {
        runtime.print("You found " + quantity + " x " + name + ".");
        runtime.print(description);
        return next;
    }
}