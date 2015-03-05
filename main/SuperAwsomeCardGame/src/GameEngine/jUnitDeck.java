package GameEngine;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

public class jUnitDeck {
	
	private Deck deckOne;
	private Deck deckTwo;
	private Card paradox;
	private Card scavenge;
	private Card bury;
	private Card wormhole;
	private Card beginStealth;

	@Before
	public void setUp() throws Exception {
		
		deckOne = new Deck();
		deckTwo = new Deck();
		
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
		
		//Six of these will go in the player's beginning hand, otherwise unable to buy, they are worth 1 stealth each
		beginStealth = new Card("Stealth 1", "This card gives you 1 stealth", 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, true, false, false);

	    //Deck one represents the main deck pile cards are drawn from
		deckOne.addCard(paradox, 4);
		deckOne.addCard(scavenge, 4);
		deckOne.addCard(bury, 4);
		deckOne.addCard(wormhole, 4);
		
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
	
	@Test(expected=IllegalStateException.class)
	public void testDrawWithNoCards() {
		deckTwo.draw();
	}
	
	@Test
	public void testDeckSizeDrawWithOneCard() {
		deckTwo.addCard(beginStealth);
		deckTwo.draw();
		assertEquals(0, deckTwo.size());
	}

	@Test
	public void testDeckSizeAfterAddMultipleCards() {
		deckTwo.addCard(beginStealth, 6);
		assertEquals(6, deckTwo.size());
	}
	
	@Test
	public void testGetMainDeck() throws SQLException {
		Deck deck = new Deck();
		deck = deck.getMainDeck();
		assertEquals(120, deck.size());
	}
	
	@Test
	public void testGetStarterDeck() throws SQLException {
		Deck deck = new Deck();
		deck = deck.getStarterDeck();
		assertEquals(10, deck.size());
	}

}
