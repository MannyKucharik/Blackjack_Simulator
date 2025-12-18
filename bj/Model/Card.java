package bj.Model;

public record Card(Rank rank, Suit suit) {

    public int hardValue(){
        return rank.hardValue();
    }

    public boolean isAce(){
        return rank == Rank.ACE;
    }
}
