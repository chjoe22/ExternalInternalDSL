package org.xtext.dsl.runtime;

public class TrainerBattleEvent extends BattleEvent {
    private final String name;
    private final String trainerName;

    public TrainerBattleEvent(
            String name,
            String trainerName,
            String playerPokemonName,
            int playerStartHp,
            int playerAttack,
            String enemyPokemonName,
            int enemyStartHp,
            int enemyAttack,
            String winNext,
            String loseNext
    ) {
        super(
                playerPokemonName,
                playerStartHp,
                playerAttack,
                enemyPokemonName,
                enemyStartHp,
                enemyAttack,
                winNext,
                loseNext
        );

        this.name = name;
        this.trainerName = trainerName;
    }

    @Override
    public String play(GameRuntime runtime) {
        runtime.print("Trainer " + trainerName + " challenges you to a battle!");
        return runBattle(runtime, trainerName + "'s", false, false);
    }
}