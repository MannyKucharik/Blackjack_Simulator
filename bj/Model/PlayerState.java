package bj.Model;

public final class PlayerState {

    private Hand playerHand;
    private Card dealerUpCard;

    public PlayerState(Hand playerHand, Card dealerUpCard) {
        if (playerHand == null) throw new IllegalArgumentException("playerHand cannot be null");
        if (dealerUpCard == null) throw new IllegalArgumentException("dealerUpCard cannot be null");
        this.playerHand = playerHand;
        this.dealerUpCard = dealerUpCard;
    }

    public int bestPlayerTotal(){
        return playerHand.bestTotal();
    }

    public boolean playerSoft(){
        return playerHand.isSoft();
    }

    public int dealerUpcardValue() {
        if (dealerUpCard.isAce()) {
            return 11;
        }
        return dealerUpCard.hardValue();
    }
}
