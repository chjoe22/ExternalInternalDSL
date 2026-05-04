package org.xtext.dsl.runtime;

import java.util.List;

public abstract class BattleEvent implements GameEvent {
    protected final String playerPokemonName;
    protected final int playerStartHp;
    protected final int playerAttack;

    protected final String enemyPokemonName;
    protected final int enemyStartHp;
    protected final int enemyAttack;

    protected final String winNext;
    protected final String loseNext;

    public BattleEvent(
            String playerPokemonName,
            int playerStartHp,
            int playerAttack,
            String enemyPokemonName,
            int enemyStartHp,
            int enemyAttack,
            String winNext,
            String loseNext
    ) {
        this.playerPokemonName = playerPokemonName;
        this.playerStartHp = playerStartHp;
        this.playerAttack = playerAttack;
        this.enemyPokemonName = enemyPokemonName;
        this.enemyStartHp = enemyStartHp;
        this.enemyAttack = enemyAttack;
        this.winNext = winNext;
        this.loseNext = loseNext;
    }

    protected String runBattle(GameRuntime runtime, String enemyLabel, boolean allowCatch, boolean catchable) {
        int playerHp = playerStartHp;
        int enemyHp = enemyStartHp;

        runtime.print(playerPokemonName + " vs " + enemyLabel + " " + enemyPokemonName);

        while (playerHp > 0 && enemyHp > 0) {
            runtime.print("");
            runtime.print(playerPokemonName + " HP: " + playerHp);
            runtime.print(enemyPokemonName + " HP: " + enemyHp);

            List<String> actions = allowCatch
                    ? List.of("attack", "catch", "flee")
                    : List.of("attack", "flee");

            String action = runtime.chooseAction(actions);

            if (action.equals("flee")) {
                runtime.print("You fled.");
                return loseNext;
            }

            if (action.equals("catch")) {
                String result = tryCatch(runtime, catchable);

                if (result != null) {
                    return result;
                }
            }

            if (action.equals("attack")) {
                runtime.print(playerPokemonName + " attacks and deals " + playerAttack + " damage!");
                enemyHp -= playerAttack;

                if (enemyHp <= 0) {
                    runtime.print(enemyPokemonName + " fainted.");
                    runtime.print("You won the battle.");
                    return winNext;
                }
            }

            runtime.print(enemyPokemonName + " attacks and deals " + enemyAttack + " damage!");
            playerHp -= enemyAttack;

            if (playerHp <= 0) {
                runtime.print(playerPokemonName + " fainted.");
                runtime.print("You lost the battle.");
                return loseNext;
            }
        }

        return loseNext;
    }

    protected String tryCatch(GameRuntime runtime, boolean catchable) {
        runtime.print("Catching is not available in this battle.");
        return null;
    }
}