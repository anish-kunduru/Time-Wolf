package GameEngine;

import java.sql.SQLException;

/**An object that will have a user's ID for each player in a game to 
 *hold on to current games statistics.Making a separate object instead 
 *of tagging these properties onto User prevents a lot of unnecessary null
 *data floating around on user objects when a user is not in a game.
**/

public class Player {
	
	private User user;
	private boolean isTurn;
	private Hand hand;
	private DiscardPile discard;
	private Deck deck;
	private int stealth;
	private int attack;
	private int VP;
	
	/**
	 * @param userID Foreign key to user objects
	 * @param isTurn Returns true if it's player's turn, false otherwise
	 * @param actions Number of actions player has left
	 * @param currency Number of currency player has left //TODO: multiple currencies
	 * @param hand Player's hand
	 * @param discard Player's discard pile
	 * @param deck Player's deck
	 * @throws SQLException 
	 */
	
	public Player(User user, boolean isTurn, Hand hand) throws SQLException{
		
		//Initliaize given values
		this.user = user;
		this.isTurn = isTurn;
		this.hand = hand;
		
		//Initialize discard pile to an empty DiscardPile
		DiscardPile discardPile = new DiscardPile();
		this.discard = discardPile;
		
		//Initiliaze deck to the starter deck
		Deck starterDeck = new Deck();
		starterDeck.getStarterDeck();
		this.deck = starterDeck;
		
		//Initialize int values to 0
		this.attack = 0;
		this.stealth = 0;
		this.VP = 0;
		
	}
	
	public void resetPlayer(){
		stealth = 0;
		attack = 0;
	}
	
	
	/**
	 * Returns the user's ID
	 * @return The user's ID
	 */
	
	public int getUserID() {
		return user.getID();
	}
	
	/**
	 * Returns true if it's the player's turn, false otherwise
	 * @return isTurn
	 */
	
	public boolean getIsTurn() {
		return isTurn;
	}
	
	
	/**
	 * Returns the Hand object of the player
	 * @return Player's hand
	 */
	
	public Hand getHand() {
		return hand;
	}
	
	/**
	 * Returns the DiscardPile object of the player
	 * @return Player's discard pile
	 */
	
	public DiscardPile getDiscardPile() {
		return discard;
	}
	
	/**
	 * Returns the Deck object of the Player
	 * @return Player's deck
	 */
	
	public Deck getDeck() {
		return deck;
	}

}
