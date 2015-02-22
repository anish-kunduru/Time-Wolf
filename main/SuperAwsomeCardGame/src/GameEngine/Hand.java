package GameEngine;

import java.util.ArrayList;
import java.util.Iterator;

public class Hand implements Iterable<Card> {

	private ArrayList<Card> cards = new ArrayList<Card>();
	private int drawSize;

	@Override
	public Iterator<Card> iterator() {
		return this.cards.iterator();
	}

	/**
	 * The size of the hand
	 * @param size Default draw size
	 */
	public Hand(int size) {
		this.drawSize = size;
	}
	
	/**
	 * Add a card to the hand.
	 * @param c the card to add
	 */
	public void addCard(Card c) {
		if(c == null) throw new NullPointerException();
		
		this.cards.add(c);
	}

	/**
	 * Get the card at position i
	 * @param i number of the card
	 * @return the card in that position in the hand.
	 */
	public Card get(int i) {
		if(i < 0 && i >= this.cards.size()) throw new IllegalArgumentException();
		return this.cards.get(i);
	}
	
	/**
	 * Remove card from hand at position i.
	 * @param i position of the card to remove from the hand
	 */
	public void remove(int i) {
		if(i < 0 && i >= this.cards.size()) throw new IllegalArgumentException();
		this.cards.remove(i);
	}


}
