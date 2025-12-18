package bj.Strategy;

import bj.Model.PlayerState;

public interface Strategy {

    String name();

    Action decide(PlayerState state);
}