package GameEngine;

import java.util.ArrayList;

public class DiscardPile {
	
	
	private ArrayList<Card> cards = new ArrayList<Card>();
	
	/**
	 * Discard a card to the discard pile
	 * @param c the card to discard
	 */
	public void discard(Card c) {
		cards.add(c);
	}

}
