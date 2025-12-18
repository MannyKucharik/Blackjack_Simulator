package bj.Engine;

import bj.Model.Card;
import bj.Model.Hand;
import bj.Model.PlayerState;
import bj.Model.Shoe;
import bj.Strategy.Action;
import bj.Strategy.Strategy;

public final class GameEngine {

    private final Rules rules;

    public GameEngine(Rules rules) {
        if (rules == null) throw new IllegalArgumentException("rules cannot be null");
        this.rules = rules;
    }

    public RoundResult playRound(Shoe shoe, Strategy strategy) {
        if (shoe == null) throw new IllegalArgumentException("shoe cannot be null");
        if (strategy == null) throw new IllegalArgumentException("strategy cannot be null");

        Hand playerHand = new Hand();
        Hand dealerHand = new Hand();

        // ---- Initial deal: player, dealer(up), player, dealer(hole) ----
        playerHand.add(shoe.deal());
        dealerHand.add(shoe.deal());
        playerHand.add(shoe.deal());
        dealerHand.add(shoe.deal());

        Card dealerUpCard = dealerHand.cards().get(0);

        // ---- Natural blackjack checks ----
        boolean playerBJ = playerHand.isBlackjack();
        boolean dealerBJ = dealerHand.isBlackjack();

        if (playerBJ || dealerBJ) {
            Outcome outcome;
            double netUnits;

            if (playerBJ && dealerBJ) {
                outcome = Outcome.PUSH;
                netUnits = 0.0;
            } else if (playerBJ) {
                outcome = Outcome.WIN;
                netUnits = rules.betUnit() * rules.blackjackPayout();
            } else { // dealerBJ
                outcome = Outcome.LOSS;
                netUnits = -rules.betUnit();
            }

            return new RoundResult(
                    outcome,
                    netUnits,
                    playerHand,
                    dealerHand,
                    playerBJ,
                    dealerBJ,
                    playerHand.isBusted(),
                    dealerHand.isBusted()
            );
        }

        // ---- Player turn (strategy loop) ----
        while (true) {
            if (playerHand.isBusted()) {
                return new RoundResult(
                        Outcome.LOSS,
                        -rules.betUnit(),
                        playerHand,
                        dealerHand,
                        false,
                        false,
                        true,
                        false
                );
            }

            PlayerState state = new PlayerState(playerHand, dealerUpCard);
            Action action = strategy.decide(state);

            if (action == Action.STAND) {
                break;
            }

            // HIT
            playerHand.add(shoe.deal());
        }

        // ---- Dealer turn (rule-based) ----
        while (shouldDealerHit(dealerHand)) {
            dealerHand.add(shoe.deal());

            if (dealerHand.isBusted()) {
                return new RoundResult(
                        Outcome.WIN,
                        rules.betUnit(),
                        playerHand,
                        dealerHand,
                        false,
                        false,
                        false,
                        true
                );
            }
        }

        // ---- Compare totals and settle ----
        int playerTotal = playerHand.bestTotal();
        int dealerTotal = dealerHand.bestTotal();

        Outcome outcome;
        double netUnits;

        if (playerTotal > dealerTotal) {
            outcome = Outcome.WIN;
            netUnits = rules.betUnit();
        } else if (playerTotal < dealerTotal) {
            outcome = Outcome.LOSS;
            netUnits = -rules.betUnit();
        } else {
            outcome = Outcome.PUSH;
            netUnits = 0.0;
        }

        return new RoundResult(
                outcome,
                netUnits,
                playerHand,
                dealerHand,
                false,
                false,
                false,
                false
        );
    }

    // Dealer hits until reaching 17+.
    // If dealerHitsSoft17 == true, dealer hits on soft 17.
    private boolean shouldDealerHit(Hand dealerHand) {
        int total = dealerHand.bestTotal();

        if (total < 17) return true;
        if (total > 17) return false;

        // total == 17
        return rules.dealerHitsSoft17() && dealerHand.isSoft();
    }
}