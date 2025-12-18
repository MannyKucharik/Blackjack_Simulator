package bj.Engine;

import bj.Model.Hand;

public final class RoundResult {

    private final Outcome outcome;
    private final double netUnits;

    private final Hand playerHand;
    private final Hand dealerHand;

    private final boolean playerBlackjack;
    private final boolean dealerBlackjack;
    private final boolean playerBusted;
    private final boolean dealerBusted;

    public RoundResult(Outcome outcome,
                       double netUnits,
                       Hand playerHand,
                       Hand dealerHand,
                       boolean playerBlackjack,
                       boolean dealerBlackjack,
                       boolean playerBusted,
                       boolean dealerBusted) {

        if (outcome == null) throw new IllegalArgumentException("outcome cannot be null");
        if (playerHand == null) throw new IllegalArgumentException("playerHand cannot be null");
        if (dealerHand == null) throw new IllegalArgumentException("dealerHand cannot be null");

        this.outcome = outcome;
        this.netUnits = netUnits;
        this.playerHand = playerHand;
        this.dealerHand = dealerHand;
        this.playerBlackjack = playerBlackjack;
        this.dealerBlackjack = dealerBlackjack;
        this.playerBusted = playerBusted;
        this.dealerBusted = dealerBusted;
    }

    public Outcome outcome() {
        return outcome;
    }

    public double netUnits() {
        return netUnits;
    }

    public Hand playerHand() {
        return playerHand;
    }

    public Hand dealerHand() {
        return dealerHand;
    }

    public boolean playerBlackjack() {
        return playerBlackjack;
    }

    public boolean dealerBlackjack() {
        return dealerBlackjack;
    }

    public boolean playerBusted() {
        return playerBusted;
    }

    public boolean dealerBusted() {
        return dealerBusted;
    }
}
