package bj.Engine;

import bj.Model.Shoe;
import bj.Util.Stats;
import bj.Strategy.Strategy;

public final class Simulator {

    private final GameEngine engine;
    private final Shoe shoe;
    private final Strategy strategy;

    private final int reshuffleWhenBelow;

    public Simulator(GameEngine engine, Shoe shoe, Strategy strategy) {
        this(engine, shoe, strategy, 60);
    }

    public Simulator(GameEngine engine, Shoe shoe, Strategy strategy, int reshuffleWhenBelow) {
        if (engine == null) throw new IllegalArgumentException("engine cannot be null");
        if (shoe == null) throw new IllegalArgumentException("shoe cannot be null");
        if (strategy == null) throw new IllegalArgumentException("strategy cannot be null");
        if (reshuffleWhenBelow < 1) throw new IllegalArgumentException("reshuffleWhenBelow must be >= 1");

        this.engine = engine;
        this.shoe = shoe;
        this.strategy = strategy;
        this.reshuffleWhenBelow = reshuffleWhenBelow;
    }

    public Stats run(int handsToPlay) {
        if (handsToPlay < 1) throw new IllegalArgumentException("handsToPlay must be >= 1");

        Stats stats = new Stats();

        for (int i = 0; i < handsToPlay; i++) {
            if (shoe.cardsRemaining() < reshuffleWhenBelow) {
                shoe.reset();
            }

            RoundResult r = engine.playRound(shoe, strategy);
            stats.record(r);
        }

        return stats;
    }
}
