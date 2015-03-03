package GameEngine;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class jUnitHand {
	
	private Hand hand;
	private Card beginStealth;
	private Card beginAttack;

	@Before
	public void setUp() throws Exception {
		
		hand = new Hand(5);
		
		//Six of these will go in the player's beginning hand, otherwise unable to buy, they are worth 1 stealth each
		beginStealth = new Card("Stealth 1", "This card gives you 1 stealth", 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, true, false, false);
		
		//Four of these will go in the player's beginning hand, otherwise unable to buy, they are worth 1 attack each
		beginAttack = new Card("Attack 1", "This card gives you 1 attack", 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, true, false, false);

	}

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


}
