package dsl;

import model.*;

public abstract class PokemonInternal {

    protected PokemonSystem system;
    protected PokemonSpecies currentPokemon;
    protected Route currentRoute;

    protected abstract void build();

    public PokemonInternal(String name) {
        system = new PokemonSystem(name);
        this.build();
    }

    public PokemonInternal move(String name, String type, int power, int accuracy, int pp) {
        Move move = new Move(name, type, power, accuracy, pp);
        system.addMove(move);
        return this;
    }

    public PokemonInternal pokemon(
            String name,
            String hp,
            String attack,
            String defense,
            String spAtk,
            String spDef,
            String speed,
            String type
    ) {
        PokemonSpecies pokemon = new PokemonSpecies(false, name, hp, attack, defense, spAtk, spDef, speed, type);
        system.addPokemon(pokemon);
        currentPokemon = pokemon;
        return this;
    }

    public PokemonInternal wildPokemon(
            String name,
            String hp,
            String attack,
            String defense,
            String spAtk,
            String spDef,
            String speed,
            String type
    ) {
        PokemonSpecies pokemon = new PokemonSpecies(true, name, hp, attack, defense, spAtk, spDef, speed, type);
        system.addPokemon(pokemon);
        currentPokemon = pokemon;
        return this;
    }

    public PokemonInternal pokemonMove(String moveName) {
        currentPokemon.addMove(moveName);
        return this;
    }

    public PokemonInternal player(String name, int money) {
        Player player = new Player(name, money);
        system.addPlayer(player);
        return this;
    }

    public PokemonInternal playerPokemon(String playerName, String pokemonName, int level) {
        Player player = system.getPlayer(playerName);
        player.addPokemon(new PokemonInstance(pokemonName, level));
        return this;
    }

    public PokemonInternal trainer(String name) {
        Trainer trainer = new Trainer(name);
        system.addTrainer(trainer);
        return this;
    }

    public PokemonInternal trainerPokemon(String trainerName, String pokemonName, int level) {
        Trainer trainer = system.getTrainer(trainerName);
        trainer.addPokemon(new PokemonInstance(pokemonName, level));
        return this;
    }

    public PokemonInternal route(String name, String type, String description) {
        Route route = new Route(name, type, description);
        system.addRoute(route);
        currentRoute = route;
        return this;
    }

    public PokemonInternal randomItem(String name, int quantity, String description) {
        currentRoute.addEvent(new RandomItemEvent(name, quantity, description));
        return this;
    }

    public PokemonInternal heal(String name, String description, String nextRoute) {
        currentRoute.addEvent(new HealingEvent(name, description, nextRoute));
        return this;
    }

    public PokemonInternal wildEncounter(String name, boolean catchable, String winRoute, String loseRoute) {
        currentRoute.addEvent(new WildEncounterEvent(name, catchable, winRoute, loseRoute));
        return this;
    }

    public PokemonInternal trainerBattle(String name, String trainerName, String winRoute, String loseRoute) {
        currentRoute.addEvent(new TrainerBattleEvent(name, trainerName, winRoute, loseRoute));
        return this;
    }

    public PokemonInternal exits(String... routeNames) {
        for (String routeName : routeNames) {
            currentRoute.addExit(routeName);
        }
        return this;
    }

    @Override
    public String toString() {
        return system.toString();
    }
}