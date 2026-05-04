package org.xtext.dsl.runtime;

public class HealingCenterEvent implements GameEvent {

    private final String name;
    private final String description;
    private final String next;

    public HealingCenterEvent(String name, String description, String next) {
        this.name = name;
        this.description = description;
        this.next = next;
    }

    @Override
    public String play(GameRuntime runtime) {
        runtime.print("Healing center: " + name);
        runtime.print(description);
        runtime.print("Your Pokemon have been healed.");
        return next;
    }
}