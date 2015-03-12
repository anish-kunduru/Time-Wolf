package GameServer.GameEngine;

import java.sql.SQLException;

import GameServer.Users.User;

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
	
	public Player(User user, Deck starerDeck) {
		
		//Initliaize given values
		this.user = user;
		this.isTurn = isTurn;
		this.hand = new Hand(5);
		this.isTurn = false;
		
		//Initialize discard pile to an empty DiscardPile
		DiscardPile discardPile = new DiscardPile();
		this.discard = discardPile;
		
		//Initiliaze deck to the starter deck
		this.deck = starerDeck;

		//Initialize hand
		this.deck.draw(this.hand);
		
		//Initialize int values to 0
		this.attack = 0;
		this.stealth = 0;
		this.VP = 0;
		
	}
	
	public void resetPlayer(){
		stealth = 0;
		attack = 0;
		
		int i = 0;
		while(hand.size() > 0){
			discard.discard(hand.remove(i));
			i++;
		}
		
		
	}
	
	
	/**
	 * Returns the user's ID
	 * @return The user's ID
	 */
	
	public User getUser() {
		return user;
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
	
	/**
	 * Gets the player's current available stealth
	 * @return Player's stealth
	 */
	public int getStealth() {
		return stealth;
	}
	
	/**
	 * Gets the player's current available attack
	 * @return Player's attack
	 */
	public int getAttack(){
		return attack;
	}
	
	/**
	 * Gets the player's current Victory Points
	 * @return Player's Victory Points
	 */
	public int getVP(){
		return VP;
	}
	
	/**
	 * Add stealth to player's total stealth
	 * @param toAdd
	 */
	public void addStealth(int stealth) {
		this.stealth += stealth;
	}
	
	/**
	 * Add attack to the player's total attack
	 * @param toAdd
	 */
	public void addAttack(int attack) {
		this.attack += attack;
	}
	
	/**
	 * Add Victory Points to the player's total VPs
	 * @param toAdd
	 */
	public void addVP(int VP) {
		this.VP += VP;
	}
	
	/**
	 * Set true is it's the player's turn, false otherwise
	 * @param isTurn
	 */
	
	public void isTurn(boolean isTurn){
		this.isTurn = isTurn;
	}
	
	/**
	 * Returns true if it's the player's turn, false otherwise
	 * @return isTurn
	 */
	public boolean getIsTurn(){
		return isTurn;
	}

}
