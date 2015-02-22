package GameEngine;

import java.util.ArrayList;
import java.util.Random;

public class Deck {
	
	private ArrayList<Card> cards = new ArrayList<Card>();
	private Random random = new Random();
	
	/**
	 * Adds a card num number of times to the deck.
	 * @param c adds card c to the deck
	 * @param number the number of times the card is added.
	 */
	public void addCard(Card c, int num) {
		//We must have a card and a valid number of cards
		if(c == null) throw new NullPointerException();
		if(num < 1) throw new IllegalArgumentException();
		
		//Add the card to the deck num times.
		for(int i = 0; i < num; i++) {
			this.cards.add(c);
		}
		
	}
	
	/**
	 * Draw a card from the deck.
	 * @return the card drawn
	 */
	public Card draw() {
		
		if(this.cards.size() == 0) throw new IllegalStateException();
		
		//Determine a random card to return
		int index = this.random.nextInt(this.cards.size() - 1);
		
		//Then return and remove it from the deck
		Card c = this.cards.get(index);
		this.cards.remove(index);
		
		
		return(c);
		
	}
	
	/**
	 * Gets the size of the deck.
	 * @return the number of cards remaining in the deck
	 */
	public int size() {
		return(this.cards.size());
	}


	
}
