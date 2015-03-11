package GameServer.GameEngine;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import GameServer.Users.User;

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
		paradox = new Card("Paradox");
		
		//Card scavenge allows the player to draw two additional cards when played from hand
		scavenge = new Card("Scavenge");
		
		//Card bury allows a player to discard two cards and pick up two new ones
		bury = new Card("Bury");
		
		//Card wormhole makes all other players discard 2 cards from hand
		wormhole = new Card("Wormhole");
		
		//Six of these will go in the player's beginning hand, otherwise unable to buy, they are worth 1 stealth each
		beginStealth = new Card("Prowl");
		
		//Card that is always available to buy, similiar to the mystic in ascension
		buyStealth = new Card("Lurk");
		
		//Four of these will go in the player's beginning hand, otherwise unable to buy, they are worth 1 attack each
		beginAttack = new Card("Claw");
		
		//Card that is always available to buy, similiar to the heavy infantry in ascension
		buyAttack = new Card("Bite");
		
	    //Deck one represents the main deck pile cards are drawn from
		deckOne.addCard(paradox, 4);
		deckOne.addCard(scavenge, 4);
		deckOne.addCard(bury, 4);
		deckOne.addCard(wormhole, 4);
		
		//deckStart represents a beginning deck for individual player
		deckStart = new Deck();
		deckStart.addCard(beginStealth, 6);
		deckStart.addCard(beginAttack, 4);
		
		User user = new User();
		player = new Player(user);
		
	}


	@Test
	public void testGetIsTurn() {
		assertEquals(true, player.getIsTurn());
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
	public void newPlayerHandSize() {
		assertEquals(10, player.getDeck().size());
	}
	
	

}
