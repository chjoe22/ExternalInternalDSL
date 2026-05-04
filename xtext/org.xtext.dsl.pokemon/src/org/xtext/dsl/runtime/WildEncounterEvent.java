package org.xtext.dsl.runtime;

import java.util.Random;

public class WildEncounterEvent extends BattleEvent {
    private final String name;
    private final boolean catchable;
    private final Random random = new Random();

    public WildEncounterEvent(
            String name,
            String playerPokemonName,
            int playerStartHp,
            int playerAttack,
            String wildPokemonName,
            int wildStartHp,
            int wildAttack,
            boolean catchable,
            String winNext,
            String loseNext
    ) {
        super(
                playerPokemonName,
                playerStartHp,
                playerAttack,
                wildPokemonName,
                wildStartHp,
                wildAttack,
                winNext,
                loseNext
        );

        this.name = name;
        this.catchable = catchable;
    }

    @Override
    public String play(GameRuntime runtime) {
        runtime.print("A wild " + enemyPokemonName + " appeared!");
        return runBattle(runtime, "wild", true, catchable);
    }

    @Override
    protected String tryCatch(GameRuntime runtime, boolean catchable) {
        if (!catchable) {
            runtime.print(enemyPokemonName + " cannot be caught.");
            return null;
        }

        int catchChance = 50;
        int roll = random.nextInt(100) + 1;

        runtime.print("You throw a Pokeball...");
        runtime.print("Catch roll: " + roll + " / " + catchChance);

        if (roll <= catchChance) {
            runtime.print("You caught " + enemyPokemonName + "!");
            runtime.addToParty(enemyPokemonName);
            runtime.printParty();
            return winNext;
        }

        runtime.print(enemyPokemonName + " broke free!");
        return null;
    }
}