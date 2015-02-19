package GameEngine;

/**An object that will have a user's ID for each player in a game to 
 *hold on to current games statistics.Making a separate object instead 
 *of tagging these properties onto User prevents a lot of unnecessary null
 *data floating around on user objects when a user is not in a game.
**/

public class Player {
	
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
		
	}

}
