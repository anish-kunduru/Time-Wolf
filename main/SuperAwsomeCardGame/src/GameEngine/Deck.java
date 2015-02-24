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
	 * Add a single card to the draw deck
	 * @param c Card
	 */
	public void addCard(Card c) {
		this.addCard(c, 1); //Have the full version do the work
	}
	
	/**
	 * Draw a card from the deck.
	 * @return the card drawn
	 */
	public Card draw() {
		int index;
		
		if(this.cards.size() == 0) throw new IllegalStateException();
		
		
		if(this.cards.size() == 1) { //If there is only one card, then it isn't so random
			index = 0;
		} else { 
			//Determine a random card to return
			index = this.random.nextInt(this.cards.size() - 1);
		}
		
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
