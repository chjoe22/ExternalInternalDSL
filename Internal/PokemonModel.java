package Internal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PokemonModel {
    public final List<Player> players = new ArrayList<>();
    public final List<Trainer> trainers = new ArrayList<>();
    public final List<PokemonDef> pokemon = new ArrayList<>();
    public final List<Move> moves = new ArrayList<>();
    public Explore explore;

    public void printOverview() {
        System.out.println("Moves:");
        for (Move m : moves) {
            System.out.println("  " + m);
        }

        System.out.println("Pokemon:");
        for (PokemonDef p : pokemon) {
            System.out.println("  " + p);
        }

        System.out.println("Players:");
        for (Player p : players) {
            System.out.println("  " + p);
        }

        System.out.println("Trainers:");
        for (Trainer t : trainers) {
            System.out.println("  " + t);
        }
    }
}

class Player {
    public String name;
    public int money;
    public final List<PokemonInstance> team = new ArrayList<>();

    @Override
    public String toString() {
        return "player " + name + " money=" + money + " party=" + team;
    }
}

class Trainer {
    public String name;
    public final List<PokemonInstance> team = new ArrayList<>();

    @Override
    public String toString() {
        return "trainer " + name + " party=" + team;
    }
}

class PokemonDef {
    public boolean wild;
    public String name;

    public int hp;
    public int attack;
    public int defense;
    public int spatk;
    public int spdef;
    public int speed;

    public String type;
    public final List<String> moves = new ArrayList<>();

    @Override
    public String toString() {
        return (wild ? "wild " : "") +
                "pokemon " + name +
                " hp=" + hp +
                " attack=" + attack +
                " defense=" + defense +
                " spatk=" + spatk +
                " spdef=" + spdef +
                " speed=" + speed +
                " type=" + type +
                " moves=" + moves;
    }
}

class PokemonInstance {
    public String species;
    public int level;

    public PokemonInstance(String species, int level) {
        this.species = species;
        this.level = level;
    }

    @Override
    public String toString() {
        return species + " lvl " + level;
    }
}

class Move {
    public String name;
    public String type;
    public int power;
    public int accuracy;
    public int pp;

    @Override
    public String toString() {
        return "move " + name +
                " type=" + type +
                " power=" + power +
                " accuracy=" + accuracy +
                " pp=" + pp;
    }
}

class Explore {
    public final List<Route> routes = new ArrayList<>();
}

class Route {
    public String name;
    public String type;
    public String description;
    public final List<Event> events = new ArrayList<>();
    public final List<String> exits = new ArrayList<>();
}

abstract class Event {
    public String name;
}

class WildEncounter extends Event {
    public boolean catchable;
    public final List<Action> actions = new ArrayList<>();
    public String winNext;
    public String loseNext;
}

class TrainerBattle extends Event {
    public String opponent;
    public String winNext;
    public String loseNext;
}

class RandomItem extends Event {
    public int quantity;
    public String description;
    public String next;
}

class HealingCenter extends Event {
    public String description;
    public String next;
}

class Action {
    public String name;
    public String description;
}
