package Internal;

import java.util.Arrays;

public class PokemonDSL {
    private final PokemonModel model = new PokemonModel();

    private Player currentPlayer;
    private Trainer currentTrainer;
    private PokemonDef currentPokemon;
    private Move currentMove;
    private Route currentRoute;
    private Event currentEvent;

    private PokemonDSL() {
    }

    public static PokemonDSL model() {
        return new PokemonDSL();
    }

    public PokemonModel build() {
        return model;
    }

    public PokemonDSL move(String name) {
        Move move = new Move();
        move.name = name;
        model.moves.add(move);

        clearCurrent();
        currentMove = move;

        return this;
    }

    public PokemonDSL type(String type) {
        if (currentMove != null) {
            currentMove.type = type;
        } else if (currentPokemon != null) {
            currentPokemon.type = type;
        } else if (currentRoute != null) {
            currentRoute.type = type;
        } else {
            throw new IllegalStateException("type() must be used after move(), pokemon(), or route()");
        }

        return this;
    }

    public PokemonDSL power(int power) {
        requireMove();
        currentMove.power = power;
        return this;
    }

    public PokemonDSL accuracy(int accuracy) {
        requireMove();
        currentMove.accuracy = accuracy;
        return this;
    }

    public PokemonDSL pp(int pp) {
        requireMove();
        currentMove.pp = pp;
        return this;
    }

    public PokemonDSL pokemon(String name) {
        PokemonDef pokemon = new PokemonDef();
        pokemon.name = name;
        model.pokemon.add(pokemon);

        clearCurrent();
        currentPokemon = pokemon;

        return this;
    }

    public PokemonDSL wildPokemon(String name) {
        PokemonDef pokemon = new PokemonDef();
        pokemon.name = name;
        pokemon.wild = true;
        model.pokemon.add(pokemon);

        clearCurrent();
        currentPokemon = pokemon;

        return this;
    }

    public PokemonDSL hp(int hp) {
        requirePokemon();
        currentPokemon.hp = hp;
        return this;
    }

    public PokemonDSL attack(int attack) {
        requirePokemon();
        currentPokemon.attack = attack;
        return this;
    }

    public PokemonDSL defense(int defense) {
        requirePokemon();
        currentPokemon.defense = defense;
        return this;
    }

    public PokemonDSL spatk(int spatk) {
        requirePokemon();
        currentPokemon.spatk = spatk;
        return this;
    }

    public PokemonDSL spdef(int spdef) {
        requirePokemon();
        currentPokemon.spdef = spdef;
        return this;
    }

    public PokemonDSL speed(int speed) {
        requirePokemon();
        currentPokemon.speed = speed;
        return this;
    }

    public PokemonDSL moves(String... moveNames) {
        requirePokemon();
        currentPokemon.moves.addAll(Arrays.asList(moveNames));
        return this;
    }

    public PokemonDSL player(String name) {
        Player player = new Player();
        player.name = name;
        model.players.add(player);

        clearCurrent();
        currentPlayer = player;

        return this;
    }

    public PokemonDSL money(int money) {
        requirePlayer();
        currentPlayer.money = money;
        return this;
    }

    public PokemonDSL trainer(String name) {
        Trainer trainer = new Trainer();
        trainer.name = name;
        model.trainers.add(trainer);

        clearCurrent();
        currentTrainer = trainer;

        return this;
    }

    public PokemonDSL party(String species, int level) {
        PokemonInstance instance = new PokemonInstance(species, level);

        if (currentPlayer != null) {
            currentPlayer.team.add(instance);
        } else if (currentTrainer != null) {
            currentTrainer.team.add(instance);
        } else {
            throw new IllegalStateException("party() must be used after player() or trainer()");
        }

        return this;
    }

    public PokemonDSL explore() {
        model.explore = new Explore();
        clearCurrent();
        return this;
    }

    public PokemonDSL route(String name) {
        if (model.explore == null) {
            model.explore = new Explore();
        }

        Route route = new Route();
        route.name = name;
        model.explore.routes.add(route);

        clearCurrent();
        currentRoute = route;

        return this;
    }

    public PokemonDSL description(String description) {
        if (currentRoute != null) {
            currentRoute.description = description;
        } else if (currentEvent instanceof RandomItem item) {
            item.description = description;
        } else if (currentEvent instanceof HealingCenter heal) {
            heal.description = description;
        } else {
            throw new IllegalStateException("description() must be used after route(), randomItem(), or heal()");
        }

        return this;
    }

    public PokemonDSL exits(String... routeNames) {
        requireRoute();
        currentRoute.exits.addAll(Arrays.asList(routeNames));
        return this;
    }

    public PokemonDSL encounter(String name) {
        requireRoute();

        WildEncounter encounter = new WildEncounter();
        encounter.name = name;
        currentRoute.events.add(encounter);

        currentEvent = encounter;

        return this;
    }

    public PokemonDSL catchable() {
        if (!(currentEvent instanceof WildEncounter encounter)) {
            throw new IllegalStateException("catchable() must be used after encounter()");
        }

        encounter.catchable = true;
        return this;
    }

    public PokemonDSL battle(String name, String opponent) {
        requireRoute();

        TrainerBattle battle = new TrainerBattle();
        battle.name = name;
        battle.opponent = opponent;
        currentRoute.events.add(battle);

        currentEvent = battle;

        return this;
    }

    public PokemonDSL randomItem(String name, int quantity, String description) {
        requireRoute();

        RandomItem item = new RandomItem();
        item.name = name;
        item.quantity = quantity;
        item.description = description;
        currentRoute.events.add(item);

        currentEvent = item;

        return this;
    }

    public PokemonDSL heal(String name, String description) {
        requireRoute();

        HealingCenter heal = new HealingCenter();
        heal.name = name;
        heal.description = description;
        currentRoute.events.add(heal);

        currentEvent = heal;

        return this;
    }

    public PokemonDSL action(String name, String description) {
        if (!(currentEvent instanceof WildEncounter encounter)) {
            throw new IllegalStateException("action() must be used after encounter()");
        }

        Action action = new Action();
        action.name = name;
        action.description = description;
        encounter.actions.add(action);

        return this;
    }

    public PokemonDSL winGoto(String routeName) {
        if (currentEvent instanceof WildEncounter encounter) {
            encounter.winNext = routeName;
        } else if (currentEvent instanceof TrainerBattle battle) {
            battle.winNext = routeName;
        } else {
            throw new IllegalStateException("winGoto() must be used after encounter() or battle()");
        }

        return this;
    }

    public PokemonDSL loseGoto(String routeName) {
        if (currentEvent instanceof WildEncounter encounter) {
            encounter.loseNext = routeName;
        } else if (currentEvent instanceof TrainerBattle battle) {
            battle.loseNext = routeName;
        } else {
            throw new IllegalStateException("loseGoto() must be used after encounter() or battle()");
        }

        return this;
    }

    public PokemonDSL thenGoto(String routeName) {
        if (currentEvent instanceof RandomItem item) {
            item.next = routeName;
        } else if (currentEvent instanceof HealingCenter heal) {
            heal.next = routeName;
        } else {
            throw new IllegalStateException("thenGoto() must be used after randomItem() or heal()");
        }

        return this;
    }

    private void clearCurrent() {
        currentPlayer = null;
        currentTrainer = null;
        currentPokemon = null;
        currentMove = null;
        currentRoute = null;
        currentEvent = null;
    }

    private void requireMove() {
        if (currentMove == null) {
            throw new IllegalStateException("This method must be used after move()");
        }
    }

    private void requirePokemon() {
        if (currentPokemon == null) {
            throw new IllegalStateException("This method must be used after pokemon()");
        }
    }

    private void requirePlayer() {
        if (currentPlayer == null) {
            throw new IllegalStateException("This method must be used after player()");
        }
    }

    private void requireRoute() {
        if (currentRoute == null) {
            throw new IllegalStateException("This method must be used after route()");
        }
    }
}
