package bj.Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Shoe{
    private final int deckCount;
    private final List<Card> cards;

    public Shoe() {
        this(1);
    }

    public Shoe(int decks) {
        if (decks < 1) {
            throw new IllegalArgumentException("Number of decks must be >= 1");
        }
        this.deckCount = decks;
        this.cards = new ArrayList<>();
        reset();
    }

    public void reset() {
        cards.clear();

        for (int i = 0; i < deckCount; i++) {
            createDeck();
        }
        shuffle();
    }
    
    public void shuffle(){
        Collections.shuffle(cards);
    }

    private void createDeck(){
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                cards.add(new Card(rank, suit));
            }
        }
    }
    public Card deal(){
        if (cards.isEmpty()){
            throw new IllegalStateException("Cannot deal from empty shoe.");
        }
        return (cards.remove(cards.size()-1));
    }

    public int cardsRemaining(){
        return cards.size();
    }

    public int totalCards(){
        return deckCount * cards.size();
    }
    public double penetration() {
        return 1.0 - ((double) cardsRemaining() / totalCards());
    }

    public int decks() {
        return deckCount;
    }
}

