package model;
import java.util.ArrayList;
import java.util.List;

public class Route {

    private String name;

    private String type;

    private String description;

    private List<GameEvent> events;

    private List<String> exits;

    public Route(String name, String type, String description) {

        this.name = name;

        this.type = type;

        this.description = description;

        this.events = new ArrayList<>();

        this.exits = new ArrayList<>();

    }

    public void addEvent(GameEvent event) {

        events.add(event);

    }

    public void addExit(String routeName) {

        exits.add(routeName);

    }

    @Override

    public String toString() {

        StringBuilder result = new StringBuilder();

        result.append("    route ")

                .append(name)

                .append(" type ")

                .append(type)

                .append(" \"")

                .append(description)

                .append("\" {\n");

        for (GameEvent event : events) {

            result.append(event).append("\n");

        }

        if (!exits.isEmpty()) {

            result.append("        exits (");

            for (int i = 0; i < exits.size(); i++) {

                result.append(exits.get(i));

                if (i < exits.size() - 1) {

                    result.append(", ");

                }

            }

            result.append(")\n");

        }

        result.append("    }\n");

        return result.toString();

    }

}