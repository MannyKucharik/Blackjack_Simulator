package bj.Util;

import bj.Engine.Rules;

public final class ReportFormatter {

    private ReportFormatter() { }

    public static String format(Rules rules, String strategyName, Stats stats) {
        if (rules == null) throw new IllegalArgumentException("rules cannot be null");
        if (strategyName == null) throw new IllegalArgumentException("strategyName cannot be null");
        if (stats == null) throw new IllegalArgumentException("stats cannot be null");

        long n = stats.hands();

        StringBuilder sb = new StringBuilder();
        sb.append("===== BLACKJACK SIM REPORT =====\n");
        sb.append("Hands: ").append(n).append("\n");
        sb.append("Strategy: ").append(strategyName).append("\n");
        sb.append("Decks: ").append(rules.decks()).append("\n");
        sb.append("Dealer hits soft 17: ").append(rules.dealerHitsSoft17()).append("\n");
        sb.append("Blackjack payout: ").append(rules.blackjackPayout()).append("\n");
        sb.append("Bet unit: ").append(rules.betUnit()).append("\n\n");

        sb.append("Results:\n");
        sb.append("  Wins:   ").append(stats.wins()).append(" (").append(pct(stats.winRate())).append(")\n");
        sb.append("  Losses: ").append(stats.losses()).append(" (").append(pct(stats.lossRate())).append(")\n");
        sb.append("  Pushes: ").append(stats.pushes()).append(" (").append(pct(stats.pushRate())).append(")\n\n");

        sb.append("Events:\n");
        sb.append("  Player blackjacks: ").append(stats.playerBlackjacks()).append("\n");
        sb.append("  Dealer blackjacks: ").append(stats.dealerBlackjacks()).append("\n");
        sb.append("  Player busts:      ").append(stats.playerBusts()).append("\n");
        sb.append("  Dealer busts:      ").append(stats.dealerBusts()).append("\n\n");

        sb.append("Payout:\n");
        sb.append("  Total net units: ").append(round(stats.totalNetUnits())).append("\n");
        sb.append("  EV (units/hand): ").append(round(stats.evPerHand())).append("\n");

        return sb.toString();
    }

    private static String pct(double x) {
        return String.format("%.2f%%", x * 100.0);
    }

    private static String round(double x) {
        return String.format("%.4f", x);
    }
}
