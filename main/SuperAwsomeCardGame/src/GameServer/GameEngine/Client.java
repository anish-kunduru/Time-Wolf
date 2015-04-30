package GameServer.GameEngine;

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface Client extends Remote {

	
	/**
	 * This method determines the action associated with the given Action object
	 * and passes it into the appropriate method.
	 * 
	 * @param a
	 */
	public void determineAction(Action a) throws RemoteException;
	
	/**
	 * Set the game engine on the client. The registry name of the game engine to attach to is being passed.
	 * @param ge The name of the game engine in the Java RMI registry.
	 */
	public void setGameEngine(String ge) throws RemoteException;
	
	/**
	 * Method called to initialize the game play area when the screen is first
	 * loaded. Takes care of both effects and populating fields.
	 * 
	 * @param playerHand
	 * @param gameTableHand
	 * @param playerNames
	 */
	public void initializeTable(Hand playerHand, Hand gameTableHand,
			String[] playerNames) throws RemoteException;
	
	
	/**
     * Starts this client's players turn
     */
	public void startTurn() throws RemoteException;

	/**
     * Set's the turn to a player other than this client's player
     */	
	public void setOtherPlayerTurn(String player) throws RemoteException;

	/**
	 * Update the player stats of the logged in player
	 * 
	 * @param st
	 * @param at
	 * @param vp
	 * @param players
	 */
	public void updatePlayerStats(int st, int at, int vp, int numDeck, String[] players) throws RemoteException;

	/**
	  * Updates other players stats not including the client's logged in player.
	  * 
	  * @param vp
	  *            players total vp
	  * @param players
	  *            string of players
	  * @param username
	  *            username of player that you want to change data for
	  */
	public void updateOtherPlayersStats(int vp, String[] players, String username) throws RemoteException;

	/**
    * Set's the current player's hand after end turn
    */
	public void setPlayerHand(Hand hand) throws RemoteException;
	
	/**
	* Adds all cards to player's hands that were drawn
	*/
	public void drawCards(Action a) throws RemoteException;
	
	/**
    * Adds all cards to player's hands that were drawn
    */
	public void discardCard(Action a) throws RemoteException;
	
	/**
    * Updates the cards on the table so all player's see same cards after a card is stolen/attacked
    */
	public void setNewTableCards(Hand hand) throws RemoteException;
	
	/**
    * ends the game, sends the player's to after game screen
    */
	public void endGame(int vp[], int cardsInDeck[], String playerNames[]) throws RemoteException;
	
	/**
    * Used to see when a player has left the game
    */
	public void checkOnline() throws RemoteException;
	
	/**
    * This method handles action playCard by getting the card from the Action
    * object and building the appropriate play log message from it.
    * 
    * @param a
    */
	public void acquireCard(Action a) throws RemoteException;
	
	/**
    * This method handles the action aquireCard by getting the card from the
    * Action object and building the appropriate play log message from it.
    * 
    * @param a
    */
	public void playCard(Action a) throws RemoteException;
	
	/**
    * This method handles the trash card state.
    * 
    * @param a
    */
	public void trashCard(Action a) throws RemoteException;
	
}
