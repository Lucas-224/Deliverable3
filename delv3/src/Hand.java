/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;

/**
 * this class 
 * @author Lucas
 */
public class Hand {
    
 private ArrayList<Card> hand;   // The cards in the hand.

    public Hand() {
        hand = new ArrayList<Card>();
    }

    public void addCard(Card c) {
        hand.add(c);
    }

    public void removeCard(int index) {
        if (index < 0 || index >= hand.size()) {
            throw new IllegalArgumentException("Position does not exist in hand: " + index);
        }
        hand.remove(index);
    }

    public int getCardCount() {
        return hand.size();
    }
}
