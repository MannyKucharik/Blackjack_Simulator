package bj.Engine;

public final class Rules {

    private final int decks;
    private final boolean dealerHitsSoft17;
    private final double blackjackPayout;
    private final double betUnit;

    /**
     * Creates a Rules object with common defaults:
     * - 6 decks
     * - dealer stands on soft 17 (S17)
     * - blackjack pays 3:2 (1.5)
     * - bet unit = 1.0
     */
    public Rules() {
        this(6, false, 1.5, 1.0);
    }

    /**
     * @param decks number of decks (must be >= 1)
     * @param dealerHitsSoft17 true = H17 table, false = S17 table
     * @param blackjackPayout payout multiplier for a natural blackjack win (must be > 0)
     *                        Example: 1.5 for 3:2, 1.2 for 6:5
     * @param betUnit base bet size in "units" (must be > 0)
     */
    public Rules(int decks, boolean dealerHitsSoft17, double blackjackPayout, double betUnit) {
        if (decks < 1) {
            throw new IllegalArgumentException("decks must be >= 1");
        }
        if (blackjackPayout <= 0.0) {
            throw new IllegalArgumentException("blackjackPayout must be > 0");
        }
        if (betUnit <= 0.0) {
            throw new IllegalArgumentException("betUnit must be > 0");
        }

        this.decks = decks;
        this.dealerHitsSoft17 = dealerHitsSoft17;
        this.blackjackPayout = blackjackPayout;
        this.betUnit = betUnit;
    }

    public int decks() {
        return decks;
    }

    public boolean dealerHitsSoft17() {
        return dealerHitsSoft17;
    }

    public double blackjackPayout() {
        return blackjackPayout;
    }

    public double betUnit() {
        return betUnit;
    }
}
