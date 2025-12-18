package bj.Test;

import bj.Model.Shoe;

public class ShoeTest {
    public static void main(String[] args) {
        Shoe shoe = new Shoe();
        for (int i = 0; i < 52; i++) shoe.deal();
            System.out.println(shoe.cardsRemaining());
    }
}