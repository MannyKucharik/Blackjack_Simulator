package bj.Test;

import bj.Model.*;
import bj.Strategy.*;

public class StrategyTest {

    public static void main(String[] args) {

        // Create a hand with total 16 (10 + 6)
        Hand playerHand = new Hand();
        playerHand.add(new Card(Rank.ACE, Suit.HEARTS));
        playerHand.add(new Card(Rank.SEVEN, Suit.CLUBS));

        // Dealer shows a 10
        Card dealerUpCard = new Card(Rank.TEN, Suit.SPADES);

        PlayerState state = new PlayerState(playerHand, dealerUpCard);

        Strategy strategy = new Basic(17);

        Action decision = strategy.decide(state);

        System.out.println("Player total: " + state.bestPlayerTotal());
        System.out.println("Dealer upcard: " + state.dealerUpcardValue());
        System.out.println("Strategy decision: " + decision);
    }
}
