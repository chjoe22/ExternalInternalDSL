package Internal;

import static Internal.PokemonDSL.model;

public class Main {
    public static void main(String[] args) {
        PokemonModel pokemonModel = model()
                .move("Tackle")
                    .type("normal")
                    .power(40)
                    .accuracy(100)
                    .pp(35)

                .move("ThunderShock")
                    .type("electric")
                    .power(40)
                    .accuracy(100)
                    .pp(30)

                .pokemon("Pikachu")
                    .hp(35)
                    .attack(55)
                    .defense(40)
                    .spatk(50)
                    .spdef(50)
                    .speed(90)
                    .type("electric")
                    .moves("Tackle", "ThunderShock")

                .wildPokemon("Rattata")
                    .hp(30)
                    .attack(56)
                    .defense(35)
                    .spatk(25)
                    .spdef(35)
                    .speed(72)
                    .type("normal")
                    .moves("Tackle")

                .player("Ash")
                    .money(3000)
                    .party("Pikachu", 5)

                .trainer("Brock")
                    .party("Pikachu", 10)

                .explore()

                .route("Route1")
                    .type("wilderness")
                    .description("A grassy route")
                    .encounter("WildBattle")
                        .catchable()
                        .action("attack", "Attack the wild Pokemon")
                        .action("catch", "Try to catch the Pokemon")
                        .action("flee", "Run away")
                        .winGoto("Route2")
                        .loseGoto("Route1")
                    .exits("Route2")

                .route("Route2")
                    .type("town")
                    .description("A small town")
                    .heal("PokemonCenter", "The town healing center")
                        .thenGoto("Route2")

                .build();

        pokemonModel.printOverview();
    }
}