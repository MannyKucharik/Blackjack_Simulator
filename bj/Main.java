package bj;

import bj.Engine.GameEngine;
import bj.Engine.Rules;
import bj.Engine.Simulator;
import bj.Model.Shoe;
import bj.Strategy.Basic;
import bj.Util.ReportFormatter;
import bj.Util.Stats;
import bj.Strategy.Basic;
import bj.Strategy.Strategy;

public final class Main {

    public static void main(String[] args) {

        // Configuration (any)
        int handsToPlay = 100_000;

        // Rules defaults: 6 decks, S17, blackjack 3:2 (1.5), bet=1.0
        Rules rules = new Rules();

        // Shoe created from rules
        Shoe shoe = new Shoe(rules.decks());

        // Choose strategy
        Strategy strategy = new Basic(17);

        // Engine + Simulator
        GameEngine engine = new GameEngine(rules);
        Simulator sim = new Simulator(engine, shoe, strategy);

        // Run
        Stats stats = sim.run(handsToPlay);

        // Report
        String strategyName;
        try {
            strategyName = strategy.name();
        } catch (Throwable t) {
            strategyName = strategy.getClass().getSimpleName();
        }

        System.out.println(ReportFormatter.format(rules, strategyName, stats));
    }
}
