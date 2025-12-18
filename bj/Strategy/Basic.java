package bj.Strategy;

import bj.Model.PlayerState;

public final class Basic implements Strategy {

    private final int hitUntil;

    public Basic(int hitUntil) {
        if (hitUntil < 1 || hitUntil > 21) {
            throw new IllegalArgumentException("hitUntil must be between 1 and 21");
        }
        this.hitUntil = hitUntil;
    }

    @Override
    public String name() {
        return "Basic(hit<" + hitUntil + ")";
    }

    @Override
    public Action decide(PlayerState state) {
        if (state == null) {
            throw new IllegalArgumentException("state cannot be null");
        }

        int total = state.bestPlayerTotal();
        return (total < hitUntil) ? Action.HIT : Action.STAND;
    }
}
