package bj.Test;

import bj.Engine.GameEngine;
import bj.Engine.RoundResult;
import bj.Engine.Rules;
import bj.Model.Shoe;
import bj.Strategy.Strategy;
import bj.Strategy.Basic;

public class EngineTest {

    public static void main(String[] args) {

        Rules rules = new Rules();               // defaults: 6 decks, S17, 3:2, bet=1
        Shoe shoe = new Shoe(rules.decks());     // build shoe from rules
        GameEngine engine = new GameEngine(rules);

        Strategy strat = new Basic(17);  // HIT until 17, else STAND

        int handsToRun = 20;
        int wins = 0, losses = 0, pushes = 0;
        double net = 0.0;

        for (int i = 1; i <= handsToRun; i++) {

            // Simple reshuffle policy so we never run out of cards mid-test
            if (shoe.cardsRemaining() < 60) {
                shoe.reset();
            }

            RoundResult r = engine.playRound(shoe, strat);

            net += r.netUnits();
            switch (r.outcome()) {
                case WIN -> wins++;
                case LOSS -> losses++;
                case PUSH -> pushes++;
            }

            System.out.println("---- Hand " + i + " ----");
            System.out.println("Outcome: " + r.outcome() + " | netUnits: " + r.netUnits());
            System.out.println("Player total: " + r.playerHand().bestTotal()
                    + " | soft=" + r.playerHand().isSoft()
                    + " | busted=" + r.playerBusted()
                    + " | BJ=" + r.playerBlackjack());
            System.out.println("Dealer total: " + r.dealerHand().bestTotal()
                    + " | soft=" + r.dealerHand().isSoft()
                    + " | busted=" + r.dealerBusted()
                    + " | BJ=" + r.dealerBlackjack());
            System.out.println();
        }

        System.out.println("===== SUMMARY (" + handsToRun + " hands) =====");
        System.out.println("Wins: " + wins + " | Losses: " + losses + " | Pushes: " + pushes);
        System.out.println("Total net units: " + net);
    }
}

