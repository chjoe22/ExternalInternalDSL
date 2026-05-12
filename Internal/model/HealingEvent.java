package model;

public class HealingEvent extends GameEvent {

    private String name;
    private String description;
    private String nextRoute;

    public HealingEvent(String name, String description, String nextRoute) {
        this.name = name;
        this.description = description;
        this.nextRoute = nextRoute;
    }

    @Override
    public String toString() {
        String result = "        heal " + name + " at \"" + description + "\"";

        if (nextRoute != null) {
            result += " then goto " + nextRoute;
        }

        return result;
    }
}