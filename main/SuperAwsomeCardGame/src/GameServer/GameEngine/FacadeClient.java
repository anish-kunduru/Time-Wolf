package GameServer.GameEngine;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class FacadeClient extends UnicastRemoteObject implements Client {
	
	public FacadeClient() throws RemoteException {
		super();
		
	}


	public Client c;

	
/*
	@Override
	public void determineAction(Action a) throws RemoteException {
		this.c.determineAction(a);
	}
	*/
	@Override
	public void setGameEngine(String ge) throws RemoteException {
		this.c.setGameEngine(ge);
	}
/*
	@Override
	public void initializeTable(Hand playerHand, Hand gameTableHand,
			String[] playerNames) throws RemoteException {
		System.out.println("In inittializetable in facade client.");
		this.c.initializeTable(playerHand, gameTableHand, playerNames);
		
	}
*/
}
