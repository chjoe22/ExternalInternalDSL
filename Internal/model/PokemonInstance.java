package model;

public class PokemonInstance {

    private String pokemonName;

    private int level;

    public PokemonInstance(String pokemonName, int level) {

        this.pokemonName = pokemonName;

        this.level = level;

    }

    @Override

    public String toString() {

        return pokemonName + " lvl " + level;

    }

}