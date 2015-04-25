package GameServer.GameEngine;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

public class FacadeClient implements Client, Remote {
	
	public Client c;

	

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
		System.out.println("In inittializetable in facade client.");
		this.c.initializeTable(playerHand, gameTableHand, playerNames);
		
	}

}
