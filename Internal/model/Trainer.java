package model;
import java.util.ArrayList;

import java.util.List;

public class Trainer {

    private String name;

    private List<PokemonInstance> party;

    public Trainer(String name) {

        this.name = name;

        this.party = new ArrayList<>();

    }

    public void addPokemon(PokemonInstance pokemon) {

        party.add(pokemon);

    }

    public String getName() {

        return name;

    }

    @Override

    public String toString() {

        StringBuilder result = new StringBuilder();

        result.append("trainer ").append(name).append(" {\n");

        result.append("    party (");

        for (int i = 0; i < party.size(); i++) {

            result.append(party.get(i));

            if (i < party.size() - 1) {

                result.append(", ");

            }

        }

        result.append(")\n");

        result.append("}");

        return result.toString();

    }

}