package model;

import java.util.ArrayList;
import java.util.List;

public class PokemonSpecies {

    private boolean wild;
    private String name;
    private String hp;
    private String attack;
    private String defense;
    private String spAtk;
    private String spDef;
    private String speed;
    private String type;
    private List<String> moves;

    public PokemonSpecies(
            boolean wild,
            String name,
            String hp,
            String attack,
            String defense,
            String spAtk,
            String spDef,
            String speed,
            String type
    ) {
        this.wild = wild;
        this.name = name;
        this.hp = hp;
        this.attack = attack;
        this.defense = defense;
        this.spAtk = spAtk;
        this.spDef = spDef;
        this.speed = speed;
        this.type = type;
        this.moves = new ArrayList<>();
    }

    public void addMove(String moveName) {
        moves.add(moveName);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        if (wild) {
            result.append("wild ");
        }

        result.append("pokemon ").append(name).append(" {\n");

        result.append("    hp ").append(hp)
                .append(" attack ").append(attack)
                .append(" defense ").append(defense)
                .append(" sp.atk ").append(spAtk)
                .append(" sp.def ").append(spDef)
                .append(" speed ").append(speed)
                .append("\n");

        result.append("    type \"").append(type).append("\" ");
        result.append("move (");

        for (int i = 0; i < moves.size(); i++) {
            result.append(moves.get(i));

            if (i < moves.size() - 1) {
                result.append(", ");
            }
        }

        result.append(")\n");
        result.append("}");

        return result.toString();
    }
}