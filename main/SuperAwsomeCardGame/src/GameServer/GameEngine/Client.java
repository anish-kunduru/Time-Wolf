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
	
	public void startTurn() throws RemoteException;

	public void setOtherPlayerTurn(String player) throws RemoteException;
	
}
