package GameEngine;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class junitTests {
	
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
	private Card williamShakespeare;
	private Card influentialCourtier;
	private Card possibleLeader;
	private Card luckInTiming;

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
		
		williamShakespeare = new Card("William Shakespeare", "Attack to move forward 100 years!", 0, 6, 50, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, true, false, false);
		
		influentialCourtier = new Card("Influential Courtier", "Attack to move forward 10 years.", 0, 3, 10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, false, false, false );
		
		possibleLeader = new Card("Possible Leader", "Attack to move forward 4 years.", 0, 2, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, false, false, false );
		
		luckInTiming = new Card("The Lucks in the Timing", "Draw 1 card and gain 2 stealth when played from hand.", 3, 0, 0, 0, 2, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, false, false, false);

	    //Deck one represents the main deck pile cards are drawn from
		deckOne.addCard(paradox, 4);
		deckOne.addCard(scavenge, 4);
		deckOne.addCard(bury, 4);
		deckOne.addCard(wormhole, 4);
		
		//deckStart represents a beginning deck for individual player
		deckStart = new Deck();
		deckStart.addCard(beginStealth, 6);
		deckStart.addCard(beginAttack, 4);
	}
	
	//Tests for Card Class

	@Test
	public void testGetName() {
		assertEquals("Scavenge", scavenge.getName());
	}
	
	@Test
	public void testGetDescription() {
		assertEquals("Draw two additional cards!", scavenge.getDescription());
	}
	
	@Test
	public void testGetCostBuy() {
		assertEquals(3, buyStealth.getCostBuy());
	}
	
	@Test
	public void testGetVp() {
		assertEquals(0, scavenge.getVp());
	}
	
	@Test
	public void testGetCostAttack() {
		assertEquals(4, scavenge.getCostBuy());
	}
	
	@Test
	public void testGetPower() {
		assertEquals(2, buyAttack.getPower());
	}
	
	@Test
	public void testGetMoney() {
		assertEquals(2, buyStealth.getMoney());
	}
	
	@Test
	public void testGetPreturnDiscard() {
		assertEquals(0, scavenge.getPreturnDiscard());
	}
	
	@Test
	public void testGetPostTurnDiscard() {
		assertEquals(0, scavenge.getPostturnDiscard());
	}
	
	@Test
	public void testGetDrawCards() {
		assertEquals(2, scavenge.getDrawCards());
	}
	
	@Test
	public void testGetOthersDrawCards() {
		assertEquals(0, scavenge.getOthersDrawCards());
	}
	
	@Test
	public void testGetTrashCardsMandatory() {
		assertEquals(0, scavenge.getTrashCardsMandatory());
	}
	
	@Test
	public void testGetTrashCardsOptional() {
		assertEquals(0, scavenge.getTrashCardsOptional());
	}
	
	@Test
	public void testGetTrashForPower() {
		assertEquals(0, scavenge.getTrashForPower());
	}
	
	@Test
	public void testGetRemoveFromPlayArea() {
		assertEquals(0, scavenge.getRemoveFromPlayArea());
	}
	
	@Test
	public void testGetOthersDiscard() {
		assertEquals(0, scavenge.getOthersDiscard());
	}
	
	@Test
	public void testIsTakeAnotherTurn() {
		assertEquals(true, bury.isTakeAnotherTurn());
	}
	
	@Test
	public void testIsRefreshPlayArea() {
		assertEquals(false, scavenge.isRefreshPlayArea());
	}
	
	@Test
	public void getGiveCurseCards() {
		assertEquals(1, paradox.getGiveCurseCards());
	}
	
	@Test
	public void testIsTrashAfterUse() {
		assertEquals(true, paradox.isTrashAfterUse());
	}
	
	//Tests for Deck Class
	
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
	
	//Tests for DiscardPile Class
	
	@Test
	public void testDiscardSizeAfterDiscards() {
		discard.discard(beginAttack);
		discard.discard(scavenge);
		discard.discard(buyStealth);
		assertEquals(3, discard.size());
	}
	
	@Test
	public void testDiscardSizeAfterAddToDeck() {
		discard.discard(beginAttack);
		discard.discard(scavenge);
		discard.discard(buyStealth);
		discard.addToDeck(deckTwo);
		assertEquals(0, discard.size());
	}
	
	@Test
	public void testDeckSizeeAfterAddToDeck() {
		discard.discard(beginAttack);
		discard.discard(scavenge);
		discard.discard(buyStealth);
		discard.addToDeck(deckTwo);
		assertEquals(3, deckTwo.size());
	}
	
	//Tests for Hand Class

	@Test
	public void testHandSizeAfterInitialization() {
		assertEquals(0, hand.size());
	}
	
	@Test
	public void testHandSizeAfterAddCard() {
		hand.addCard(beginAttack);
		hand.addCard(beginAttack);
		hand.addCard(beginAttack);
		hand.addCard(beginStealth);
		hand.addCard(beginStealth);
		assertEquals(5, hand.size());
	}
	
	@Test
	public void testHandSizeAfterAddCardRemove() {
		hand.addCard(beginAttack);
		hand.addCard(beginAttack);
		hand.addCard(beginAttack);
		hand.addCard(beginStealth);
		hand.addCard(beginStealth);
		hand.remove(3);
		assertEquals(4, hand.size());
	}
	
	@Test
	public void testHandSizeAfterAddCardRemove2() {
		hand.addCard(beginAttack);
		hand.remove(0);
		assertEquals(0, hand.size());
	}
	
	@Test
	public void testGetCard() {
		hand.addCard(beginAttack);
		hand.addCard(beginAttack);
		hand.addCard(beginAttack);
		hand.addCard(beginStealth);
		hand.addCard(beginStealth);
		assertEquals(beginStealth, hand.get(4));
	}
	
	@Test
	public void testSizeAfterAddingMoreThanDefault() {
		hand.addCard(beginAttack);
		hand.addCard(beginAttack);
		hand.addCard(beginAttack);
		hand.addCard(beginStealth);
		hand.addCard(beginStealth);
		hand.addCard(beginStealth);
		assertEquals(6, hand.size());
	}
	
	@Test
	public void testUserStats() {
		UserStats userStat = new UserStats(1);
		assertEquals(1, userStat.getGamesPlayed());
	}
	
}
