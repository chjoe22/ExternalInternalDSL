package model;

public class TrainerBattleEvent extends GameEvent {

    private String name;
    private String trainerName;
    private String winRoute;
    private String loseRoute;

    public TrainerBattleEvent(String name, String trainerName, String winRoute, String loseRoute) {
        this.name = name;
        this.trainerName = trainerName;
        this.winRoute = winRoute;
        this.loseRoute = loseRoute;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        result.append("        battle ")
                .append(name)
                .append(" vs ")
                .append(trainerName)
                .append(" {\n");

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