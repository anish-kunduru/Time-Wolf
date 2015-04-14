package GameServer.GameEngine;

import java.rmi.RemoteException;


public interface Client {

	
	/**
	 * This method determines the action associated with the given Action object
	 * and passes it into the appropriate method.
	 * 
	 * @param a
	 */
	public void determineAction(Action a);
	
	/**
	 * Set the game engine on the client. The registry name of the game engine to attach to is being passed.
	 * @param ge The name of the game engine in the Java RMI registry.
	 */
	public void setGameEngine(String ge);
	
	
	
}
