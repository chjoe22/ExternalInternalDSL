package org.xtext.dsl.runtime;

import java.util.*;

public class GameRoute {

    private final String name;
    private final String description;
    private final List<GameEvent> events = new ArrayList<>();
    private final List<String> exits = new ArrayList<>();

    public GameRoute(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public void addEvent(GameEvent event) {
        events.add(event);
    }

    public void addExit(String routeName) {
        exits.add(routeName);
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<GameEvent> getEvents() {
        return events;
    }

    public List<String> getExits() {
        return exits;
    }
}