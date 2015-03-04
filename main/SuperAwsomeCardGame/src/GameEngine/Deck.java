package GameEngine;

import java.sql.SQLException;
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
	
	public Deck getMainDeck() throws SQLException{
		
		//TODO - # of each card in deck
		
		Deck main = new Deck();
		
		DBHelper dbh = new DBHelper();
		String query = "SELECT * FROM Cards WHERE Deck='Main'";
		java.sql.ResultSet rs = dbh.executeQuery(query);
		
		while(rs.next()){
			String name = rs.getString("Name");
			String description = rs.getString("Description");
			int costBuy = rs.getInt("CostBuy");
			int costAttack = rs.getInt("CostAttack");
			int vp = rs.getInt("VP");
			int power = rs.getInt("Attack");
			int money = rs.getInt("Stealth");
			int preturnDiscard = rs.getInt("PreturnDiscard");
			int postturnDiscard = rs.getInt("PostturnDiscard");
			int drawCards = rs.getInt("DrawCards");
			int othersDrawCards = rs.getInt("OthersDrawCards");
			int trashCardsMandatory = rs.getInt("TrashCardsMandatory");
			int trashCardsOptional = rs.getInt("TrashCardsOptional");
			int trashForPower = rs.getInt("TrashForAttack");
			int removeFromPlayArea = rs.getInt("RemoveFromPlayArea");
			int othersDiscard = rs.getInt("OthersDiscard");
			int giveCurseCards = rs.getInt("OthersLoseVP");
			boolean takeAnotherTurn = rs.getBoolean("TakeAnotherTurn");
			boolean refreshPlayArea = rs.getBoolean("RefreshPlayArea");
			boolean trashAfterUse = rs.getBoolean("TrashAfterUse");
			
			Card toAdd = new Card(name, description, costBuy, costAttack, vp, power, money, preturnDiscard, postturnDiscard,
					drawCards, othersDrawCards, trashCardsMandatory, trashCardsOptional, trashForPower, removeFromPlayArea,
					othersDiscard, giveCurseCards, takeAnotherTurn, refreshPlayArea, trashAfterUse);
			
			main.addCard(toAdd);
		}
		return main;
	}	
	
	public Deck getStarterDeck() throws SQLException{
		
		Deck starter = new Deck();
		
		DBHelper dbh = new DBHelper();
		String query = "SELECT * FROM Cards WHERE Deck='Starter'";
		java.sql.ResultSet rs = dbh.executeQuery(query);
		
		while(rs.next()){
			String name = rs.getString("Name");
			String description = rs.getString("Description");
			int costBuy = rs.getInt("CostBuy");
			int costAttack = rs.getInt("CostAttack");
			int vp = rs.getInt("VP");
			int power = rs.getInt("Attack");
			int money = rs.getInt("Stealth");
			int preturnDiscard = rs.getInt("PreturnDiscard");
			int postturnDiscard = rs.getInt("PostturnDiscard");
			int drawCards = rs.getInt("DrawCards");
			int othersDrawCards = rs.getInt("OthersDrawCards");
			int trashCardsMandatory = rs.getInt("TrashCardsMandatory");
			int trashCardsOptional = rs.getInt("TrashCardsOptional");
			int trashForPower = rs.getInt("TrashForAttack");
			int removeFromPlayArea = rs.getInt("RemoveFromPlayArea");
			int othersDiscard = rs.getInt("OthersDiscard");
			int giveCurseCards = rs.getInt("OthersLoseVP");
			boolean takeAnotherTurn = rs.getBoolean("TakeAnotherTurn");
			boolean refreshPlayArea = rs.getBoolean("RefreshPlayArea");
			boolean trashAfterUse = rs.getBoolean("TrashAfterUse");
			int numInDeck = rs.getInt("NumInDeck");
			
			Card toAdd = new Card(name, description, costBuy, costAttack, vp, power, money, preturnDiscard, postturnDiscard,
					drawCards, othersDrawCards, trashCardsMandatory, trashCardsOptional, trashForPower, removeFromPlayArea,
					othersDiscard, giveCurseCards, takeAnotherTurn, refreshPlayArea, trashAfterUse);
			
			starter.addCard(toAdd, numInDeck);
		}
		
		return starter;
	}


	
}
