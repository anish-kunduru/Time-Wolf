package GameEngine;

/**An object that will have a user's ID for each player in a game to 
 *hold on to current games statistics.Making a separate object instead 
 *of tagging these properties onto User prevents a lot of unnecessary null
 *data floating around on user objects when a user is not in a game.
**/

public class Player {
	
	private int userID;
	private boolean isTurn;
	private int actions;
	private int currency;
	private Hand hand;
	private DiscardPile discard;
	private Deck deck;
	
	/**
	 * @param userID Foreign key to user objects
	 * @param isTurn Returns true if it's player's turn, false otherwise
	 * @param actions Number of actions player has left
	 * @param currency Number of currency player has left //TODO: multiple currencies
	 * @param hand Player's hand
	 * @param discard Player's discard pile
	 * @param deck Player's deck
	 */
	
	public Player(int userID, boolean isTurn, int actions, int currency, Hand hand, DiscardPile discard, Deck deck){
		
		this.userID = userID;
		this.isTurn = isTurn;
		this.actions = actions;
		this.currency = currency;
		this.hand = hand;
		this.discard = discard;
		this.deck = deck;
		
	}
	
	/**
	 * Adds the given number of currency to the total currency a player has
	 * @param numCurrency
	 */
	
	public void addCurrency(int numCurrency){
		currency += numCurrency;
	}
	
	/**
	 * Adds the given number of actions to the total actions a player has
	 * @param numActions
	 */
	
	public void addActions(int numActions){
		actions += numActions;
	}
	
	/**
	 * Returns the user's ID
	 * @return The user's ID
	 */
	
	public int getUserID() {
		return userID;
	}
	
	/**
	 * Returns true if it's the player's turn, false otherwise
	 * @return isTurn
	 */
	
	public boolean getIsTurn() {
		return isTurn;
	}
	
	/**
	 * Returns the number of actions the player has left
	 * @return Player's actions
	 */
	
	public int getActions() {
		return actions;
	}
	
	/**
	 * Returns the amount of currency player has left
	 * @return Player's currency
	 */
	
	public int getCurrency() {
		return currency;
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
