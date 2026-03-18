package Internal;

import java.util.List;

public class PokemonInternal {

    private interface Builder{
        PlayerBuilder player(String name);
        TrainerBuilder trainer(String name);
        EventBuilder event(String name);
    }
    private interface PlayerBuilder extends Builder{
        PokemonBuilder pokemon(String name, int level, List<String> types);
        PlayerBuilder money(int amount);
        PlayerBuilder bag(ItemBuilder item, int amount);
    }
    private interface PokemonBuilder extends Builder{
        PokemonBuilder hp(int hp);
        PokemonBuilder attack(int attack);
        PokemonBuilder defense(int defense);
        PokemonBuilder speed(int speed);
        MoveBuilder moves(String moves);
    }
    private interface TrainerBuilder extends Builder{
        PokemonBuilder pokemon(String name, int level, List<String> types);
    }
    private interface ItemBuilder extends Builder{
        ItemBuilder effect(String effect);
        ItemBuilder price(int price);
    }
    private interface MoveBuilder extends Builder{
        MoveBuilder power(int power);
        MoveBuilder accuracy(int accuracy);
        MoveBuilder type(String type);
        MoveBuilder pp(int pp);
    }
    private interface EventBuilder extends Builder{
        EventBuilder description(String description);
        EventBuilder trigger(String trigger);
    }
    static Builder system(){
        return null;
    }

    public static void main(String[] args) {
        build();
    }
    public static void build(){
        system()
                .player("Nikolaj")
                    .money(5000)
                    .bag(null, 10)
                    .pokemon("Pikachu", 25, List.of("Electric"))
                        .hp(35).attack(55).defense(40).speed(90)
                        .moves("Thunderbolt").power(90).accuracy(100).type("Electric").pp(15)
                .trainer("Ash")
                    .pokemon("Bulbasaur", 15, List.of("Grass", "Poison"))
                    .hp(45).attack(49).defense(49).speed(45)
                    .moves("Vine Whip").power(45).accuracy(100).type("Grass").pp(25)
                .event("Battle")
                    .description("A battle between trainers")
                    .trigger("When two trainers meet");
    }
}
