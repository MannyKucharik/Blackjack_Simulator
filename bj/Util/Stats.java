package bj.Util;

import bj.Engine.Outcome;
import bj.Engine.RoundResult;

public final class Stats {

    private long hands;
    private long wins;
    private long losses;
    private long pushes;

    private long playerBlackjacks;
    private long dealerBlackjacks;

    private long playerBusts;
    private long dealerBusts;

    private double totalNetUnits;

    public void record(RoundResult r) {
        if (r == null) throw new IllegalArgumentException("RoundResult cannot be null");

        hands++;
        totalNetUnits += r.netUnits();

        Outcome o = r.outcome();
        if (o == Outcome.WIN) wins++;
        else if (o == Outcome.LOSS) losses++;
        else pushes++;

        if (r.playerBlackjack()) playerBlackjacks++;
        if (r.dealerBlackjack()) dealerBlackjacks++;

        if (r.playerBusted()) playerBusts++;
        if (r.dealerBusted()) dealerBusts++;
    }

    public long hands() { return hands; }
    public long wins() { return wins; }
    public long losses() { return losses; }
    public long pushes() { return pushes; }

    public long playerBlackjacks() { return playerBlackjacks; }
    public long dealerBlackjacks() { return dealerBlackjacks; }
    public long playerBusts() { return playerBusts; }
    public long dealerBusts() { return dealerBusts; }

    public double totalNetUnits() { return totalNetUnits; }

    public double winRate() { return hands == 0 ? 0.0 : (double) wins / hands; }
    public double lossRate() { return hands == 0 ? 0.0 : (double) losses / hands; }
    public double pushRate() { return hands == 0 ? 0.0 : (double) pushes / hands; }

    public double evPerHand() { return hands == 0 ? 0.0 : totalNetUnits / hands; }
}
