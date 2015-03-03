package GameEngine;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class jUnitPlayer {
	
	private Deck deckOne;
	private Deck deckTwo;
	private Deck deckStart;
	private DiscardPile discard;
	private Hand hand;
	private Card paradox;
	private Card scavenge;
	private Card bury;
	private Card wormhole;
	private Card beginStealth;
	private Card buyStealth;
	private Card beginAttack;
	private Card buyAttack;
	private Card dataBase;
	private Player player;

	@Before
	public void setUp() throws Exception {
		
		deckOne = new Deck();
		deckTwo = new Deck();
		
		discard = new DiscardPile();
		
		hand = new Hand(5);
		
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
		
		//Card that is always available to buy, similiar to the mystic in ascension
		buyStealth = new Card("Stealth 2", "This card gives you 2 stealth", 3, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, true, false, false);
		
		//Four of these will go in the player's beginning hand, otherwise unable to buy, they are worth 1 attack each
		beginAttack = new Card("Attack 1", "This card gives you 1 attack", 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, true, false, false);
		
		//Card that is always available to buy, similiar to the heavy infantry in ascension
		buyAttack = new Card("Attack 2", "This card gives you 2 attack", 3, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, true, false, false);

	    //Deck one represents the main deck pile cards are drawn from
		deckOne.addCard(paradox, 4);
		deckOne.addCard(scavenge, 4);
		deckOne.addCard(bury, 4);
		deckOne.addCard(wormhole, 4);
		
		//deckStart represents a beginning deck for individual player
		deckStart = new Deck();
		deckStart.addCard(beginStealth, 6);
		deckStart.addCard(beginAttack, 4);
		
		player = new Player(1, true, 10, 10, hand, discard, deckStart);
		
	}

	@Test
	public void testGetUserID() {
		assertEquals(1, player.getUserID());
	}
	
	@Test
	public void testGetIsTurn() {
		assertEquals(true, player.getIsTurn());
	}
	
	@Test
	public void testActions() {
		assertEquals(10, player.getActions());
	}
	
	@Test
	public void testGetCurrency() {
		assertEquals(10, player.getCurrency());
	}
	
	@Test
	public void testGetHand() {
		assertEquals(hand, player.getHand());
	}
	
	@Test
	public void testGetDiscard() {
		assertEquals(discard, player.getDiscardPile());
	}
	
	@Test
	public void testGetDeck() {
		assertEquals(deckStart, player.getDeck());
	}
	
	@Test
	public void testAddActions() {
		player.addActions(5);
		assertEquals(15, player.getActions());
	}
	
	@Test
	public void testAddCurrency() {
		player.addCurrency(5);
		assertEquals(15, player.getCurrency());
	}

}
