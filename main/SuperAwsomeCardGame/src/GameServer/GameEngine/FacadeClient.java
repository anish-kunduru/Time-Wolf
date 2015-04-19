package GameServer.GameEngine;

import java.rmi.Remote;
import java.rmi.RemoteException;

public class FacadeClient implements Client, Remote {
	
	Client c;

	/**
	 * Initialize the facade client with a reference to the actual end client.
	 * @param c The real client
	 */
	public FacadeClient(Client c) {
		this.c = c;
	}

	@Override
	public void determineAction(Action a) {
		this.c.determineAction(a);
	}
	
	@Override
	public void setGameEngine(String ge) {
		this.c.setGameEngine(ge);
	}

	@Override
	public void initializeTable(Hand playerHand, Hand gameTableHand,
			String[] playerNames) {
		// TODO Auto-generated method stub
		
	}

}
