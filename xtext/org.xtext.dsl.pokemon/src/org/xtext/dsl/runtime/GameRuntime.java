package org.xtext.dsl.runtime;

import java.util.*;

public class GameRuntime {

    private final Scanner scanner = new Scanner(System.in);
    private final Map<String, GameRoute> routes = new LinkedHashMap<>();
    private final List<String> playerParty = new ArrayList<>();
    private GameRoute currentRoute;

    public void addRoute(GameRoute route) {
        routes.put(route.getName(), route);
    }

    public void addToParty(String pokemonName) {
        playerParty.add(pokemonName);
        print(pokemonName + " was added to your party.");
    }

    public void printParty() {
        if (playerParty.isEmpty()) {
            print("Your party is currently empty.");
            return;
        }

        print("Your current party:");
        for (String pokemon : playerParty) {
            print("- " + pokemon);
        }
    }

    public void setStartRoute(String routeName) {
        currentRoute = routes.get(routeName);

        if (currentRoute == null) {
            System.out.println("Start route not found: " + routeName);
        }
    }

    public void start() {
        System.out.println("Welcome to the Pokemon Interactive Fiction Game!");

        if (currentRoute == null) {
            System.out.println("No start route selected.");
            return;
        }

        while (currentRoute != null) {
            showCurrentRoute();
            handleRoute();
        }

        System.out.println("Game ended.");
    }

    private void showCurrentRoute() {
        System.out.println();
        System.out.println("=== " + currentRoute.getName() + " ===");

        if (currentRoute.getDescription() != null && !currentRoute.getDescription().isEmpty()) {
            System.out.println(currentRoute.getDescription());
        }
    }

    private void handleRoute() {
        List<GameEvent> events = currentRoute.getEvents();

        for (GameEvent event : events) {
            String nextRoute = event.play(this);

            if (nextRoute != null && routes.containsKey(nextRoute)) {
                currentRoute = routes.get(nextRoute);
                return;
            }
        }

        chooseExit();
    }

    private void chooseExit() {
        List<String> exits = currentRoute.getExits();

        if (exits.isEmpty()) {
            System.out.println("There are no more exits. The game ends here.");
            currentRoute = null;
            return;
        }

        System.out.println();
        System.out.println("Where do you want to go?");

        for (int i = 0; i < exits.size(); i++) {
            System.out.println((i + 1) + ". " + exits.get(i));
        }

        int choice = readChoice(1, exits.size());
        currentRoute = routes.get(exits.get(choice - 1));
    }

    public String chooseAction(List<String> actions) {
        System.out.println();
        System.out.println("Choose an action:");

        for (int i = 0; i < actions.size(); i++) {
            System.out.println((i + 1) + ". " + actions.get(i));
        }

        int choice = readChoice(1, actions.size());
        return actions.get(choice - 1);
    }

    private int readChoice(int min, int max) {
        while (true) {
            System.out.print("> ");

            try {
                int choice = Integer.parseInt(scanner.nextLine());

                if (choice >= min && choice <= max) {
                    return choice;
                }
            } catch (NumberFormatException e) {
                // Invalid input, ask again.
            }

            System.out.println("Invalid choice. Try again.");
        }
    }

    public void print(String text) {
        System.out.println(text);
    }
}