package model;

public class WildEncounterEvent extends GameEvent {

    private String name;
    private boolean catchable;
    private String winRoute;
    private String loseRoute;

    public WildEncounterEvent(String name, boolean catchable, String winRoute, String loseRoute) {
        this.name = name;
        this.catchable = catchable;
        this.winRoute = winRoute;
        this.loseRoute = loseRoute;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        result.append("        encounter ").append(name).append(" wild pokemon");

        if (catchable) {
            result.append(" catchable");
        }

        result.append(" {\n");
        result.append("            action attack -> outcome : \"You attack the wild Pokemon.\"\n");
        result.append("            action catch -> outcome : \"You throw a Pokeball.\"\n");
        result.append("            action flee -> outcome : \"You run away.\"\n");

        if (winRoute != null) {
            result.append("            win goto ").append(winRoute).append("\n");
        }

        if (loseRoute != null) {
            result.append("            lose goto ").append(loseRoute).append("\n");
        }

        result.append("        }");

        return result.toString();
    }
}