package GameEngine;

import static org.junit.Assert.*;

import java.sql.ResultSet;

import org.junit.Before;
import org.junit.Test;

public class jUnitCard {
	
	private Card paradox;
	private Card scavenge;
	private Card bury;
	private Card wormhole;
	private Card beginStealth;
	private Card buyStealth;
	private Card beginAttack;
	private Card buyAttack;
	private Card dataBase;

	@Before
	public void setUp() throws Exception {
		
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
	
		DBHelper dbh = new DBHelper();
		String query = "SELECT * FROM Cards WHERE Name='Paradox'";
		ResultSet rs = dbh.executeQuery(query);
		
		if(rs.first()){
			String name = rs.getString("Name");
			String description = rs.getString("Description");
			int costBuy = rs.getInt("CostBuy");
			int costAttack = rs.getInt("CostAttack");
			int vp = rs.getInt("VP");
			int power = rs.getInt("Power");
			int money = rs.getInt("Money");
			int preturnDiscard = rs.getInt("PreturnDiscard");
			int postturnDiscard = rs.getInt("PostturnDiscard");
			int drawCards = rs.getInt("DrawCards");
			int othersDrawCards = rs.getInt("OthersDrawCards");
			int trashCardsMandatory = rs.getInt("TrashCardsMandatory");
			int trashCardsOptional = rs.getInt("TrashCardsOptional");
			int trashForPower = rs.getInt("TrashForPower");
			int removeFromPlayArea = rs.getInt("RemoveFromPlayArea");
			int othersDiscard = rs.getInt("OthersDiscard");
			int giveCurseCards = rs.getInt("OthersLoseVP");
			boolean takeAnotherTurn = rs.getBoolean("TakeAnotherTurn");
			boolean refreshPlayArea = rs.getBoolean("RefreshPlayArea");
			boolean trashAfterUse = rs.getBoolean("TrashAfterUse");
			
			dataBase = new Card(name, description, costBuy, costAttack, vp, power, money, preturnDiscard, postturnDiscard,
					drawCards, othersDrawCards, trashCardsMandatory, trashCardsOptional, trashForPower, removeFromPlayArea,
					othersDiscard, giveCurseCards, takeAnotherTurn, refreshPlayArea, trashAfterUse);
		}
		else{
			String name = null;
			String description = null;
			int costBuy = 0;
			int costAttack = 0;
			int vp = 0;
			int power = 0; 
			int money = 0;
			int preturnDiscard = 0;
			int postturnDiscard = 0;
			int drawCards = 0;
			int othersDrawCards = 0;
			int trashCardsMandatory = 0;
			int trashCardsOptional = 0;
			int trashForPower = 0;
			int removeFromPlayArea = 0;
			int othersDiscard = 0;
			int giveCurseCards = 0;
			boolean takeAnotherTurn = false;
			boolean refreshPlayArea = false;
			boolean trashAfterUse = false;
			
			dataBase = new Card(name, description, costBuy, costAttack, vp, power, money, preturnDiscard, postturnDiscard,
					drawCards, othersDrawCards, trashCardsMandatory, trashCardsOptional, trashForPower, removeFromPlayArea,
					othersDiscard, giveCurseCards, takeAnotherTurn, refreshPlayArea, trashAfterUse);
		}
	}

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
	
	@Test
	public void testDBCard() {
		assertEquals(true, dataBase.isTakeAnotherTurn());
	}

}
