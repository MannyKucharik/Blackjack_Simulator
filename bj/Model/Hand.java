package bj.Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Hand {

    private final List<Card> cards;

    public Hand(){
        this.cards = new ArrayList<>();
    }
    
    public void add(Card card){
        if (card == null) {
            throw new IllegalArgumentException("card cannot be null");
        }
        cards.add(card);
    }

     public List<Card> cards() {
        return Collections.unmodifiableList(cards);
    }

    public int hardTotal(){
        int sum = 0;
        for(Card c : cards){
            sum += c.hardValue();
        }
        return sum;
    }
    public int bestTotal(){
        int hard = hardTotal();
        int aceCount = 0;
        for(Card c : cards){
            if (c.hardValue() == 1){
                aceCount++;
            }
        }
        if (aceCount >= 1 && hard + 10 <= 21){
            return hard + 10;
        }
        return hard;
    }

    public boolean isSoft(){
        int hard = hardTotal();
        int aceCount = 0;
        for(Card c : cards){
            if (c.hardValue() == 1){
                aceCount++;
            }
        }
        return (aceCount>=1 && hard+10<=21);
    }

    public boolean isBlackjack(){
        return(cards.size() == 2 && bestTotal()==21);
    }

    
    public boolean isBusted(){
        return(bestTotal()>21);
    }
}