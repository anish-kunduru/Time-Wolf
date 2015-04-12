package GameServer.GameEngine;

import java.rmi.Remote;

public interface Client extends Remote {

	
	/**
	 * This method determines the action associated with the given Action object
	 * and passes it into the appropriate method.
	 * 
	 * @param a
	 */
	public void determineAction(Action a);
	
	
	
}
