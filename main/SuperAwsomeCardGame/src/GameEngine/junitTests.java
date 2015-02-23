package GameEngine;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class junitTests {
	
	/*public Card(String name, String description, int costBuy, int costAttack,
			int vp, int power, int money, int preturnDiscard,
			int postturnDiscard, int drawCards, int othersDrawCards,
			int trashCardsMandatory, int trashCardsOptional, int trashForPower, int removeFromPlayArea,
			int othersDiscard, int giveCurseCards, boolean takeAnotherTurn, boolean refreshPlayArea,
			boolean trashAfterUse*/
	
	private Deck deckOne;
	private Card paradox;
	private Card scavenge;
	private Card bury;
	private Card wormhole;

	@Before
	public void setUp() throws Exception {
		
		deckOne = new Deck();
		
		//Card paradox causes all other players to go back 10 years by adding a "paradox" card to their deck
		paradox = new Card("Paradox", "There can only be one! All other players go back 10 years", 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				1, true, false, true);
		
		//Card scavenge allows the player to draw two additional cards when played from hand
		scavenge = new Card("Scavenge", "Draw two additional cards!", 4, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, true, false, false);
		
		//Card bury allows a player to discard two cards and pick up two new ones
		bury = new Card("Bury", "Discard two cards and replace them with two new", 4, 0, 0, 0, 0, 2, 0, 2, 0, 0, 0, 0, 0, 0, 0, 
				true, false, true);
		
		//Card wormhole makes all other players discard 2 cards from hand
		wormhole = new Card("Wormhole", "All other players must discard 2 cards", 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 
				true, false, true);
		
		//Gain a card costing up to $4
		
		//Trash a card in your hand. If you do, gain a card costing up to $3 more
		
		//Stealth card of value 2, cost 3
		
		//Attack card of value 2, cost 3
		
		deckOne.addCard(paradox, 4);
		deckOne.addCard(scavenge, 4);
		deckOne.addCard(bury, 4);
		deckOne.addCard(wormhole, 4);
		
	}

	@Test
	public void testGetName() {
		assertEquals("Scavenge", scavenge.getName());
	}
	
	@Test
	public void testDeckSize() {
		assertEquals(16, deckOne.size());
	}
	
	@Test
	public void testDeckSizeAfterDraw() {
		deckOne.draw();
		assertEquals(15, deckOne.size());
	}
	
	

}
