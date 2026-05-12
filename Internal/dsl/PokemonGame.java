package dsl;

public class PokemonGame extends PokemonInternal {

    public PokemonGame() {
        super("Pokemon Game");
    }

    @Override
    protected void build() {
        move("Tackle", "Normal", 40, 100, 35);
        move("ThunderShock", "Electric", 60, 100, 30);

        pokemon("Pikachu", "100 + lvl * 10", "20 + lvl", "25", "50", "30", "50", "Electric")
                .pokemonMove("Tackle")
                .pokemonMove("ThunderShock");

        pokemon("Bulbasaur", "120 + lvl * 10", "10 + lvl", "30", "40", "35", "30", "Grass")
                .pokemonMove("Tackle");

        wildPokemon("Rattata", "50 + lvl * 6", "12 + lvl", "15", "10", "12", "45", "Normal")
                .pokemonMove("Tackle");

        player("Ash", 500)
                .playerPokemon("Ash", "Pikachu", 30)
                .playerPokemon("Ash", "Bulbasaur", 5);

        route("StarterTown", "town", "You just ventured out of the startertown.")
                .randomItem("Potion", 1, "You received 1 HP-Potion from Professor Oak.")
                .heal("Lab", "Professor Oak's Lab heals your Pokemon for free.", "TownCrossroad");

        route("TownCrossroad", "road", "You are outside Starter Town.")
                .exits("Wilderness");

        route("Wilderness", "wilderness", "You walk into the tall grass.")
                .wildEncounter("WildEncounter", true, "TownCrossroad", "TownCrossroad")
                .exits("TownCrossroad");
    }

    public static void main(String[] args) {
        PokemonGame game = new PokemonGame();
        System.out.println(game);
    }
}