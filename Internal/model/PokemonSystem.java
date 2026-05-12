package model;

import java.util.ArrayList;
import java.util.List;

public class PokemonSystem {

    private String name;
    private List<Move> moves;
    private List<PokemonSpecies> pokemon;
    private List<Player> players;
    private List<Trainer> trainers;
    private List<Route> routes;

    public PokemonSystem(String name) {
        this.name = name;
        this.moves = new ArrayList<>();
        this.pokemon = new ArrayList<>();
        this.players = new ArrayList<>();
        this.trainers = new ArrayList<>();
        this.routes = new ArrayList<>();
    }

    public void addMove(Move move) {
        moves.add(move);
    }

    public void addPokemon(PokemonSpecies pokemon) {
        this.pokemon.add(pokemon);
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void addTrainer(Trainer trainer) {
        trainers.add(trainer);
    }

    public void addRoute(Route route) {
        routes.add(route);
    }

    public Player getPlayer(String name) {
        return players.stream()
                .filter(player -> player.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Player not found: " + name));
    }

    public Trainer getTrainer(String name) {
        return trainers.stream()
                .filter(trainer -> trainer.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Trainer not found: " + name));
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        result.append("// ").append(name).append("\n\n");

        for (Move move : moves) {
            result.append(move).append("\n\n");
        }

        for (PokemonSpecies pokemonSpecies : pokemon) {
            result.append(pokemonSpecies).append("\n\n");
        }

        for (Player player : players) {
            result.append(player).append("\n\n");
        }

        for (Trainer trainer : trainers) {
            result.append(trainer).append("\n\n");
        }

        result.append("explore {\n");

        for (Route route : routes) {
            result.append(route).append("\n");
        }

        result.append("}");

        return result.toString();
    }
}