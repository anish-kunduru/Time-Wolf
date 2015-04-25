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

	

	@Override
	public void determineAction(Action a) throws RemoteException {
		this.c.determineAction(a);
	}
	
	@Override
	public void setGameEngine(String ge) throws RemoteException {
		this.c.setGameEngine(ge);
	}

	@Override
	public void initializeTable(Hand playerHand, Hand gameTableHand,
			String[] playerNames) throws RemoteException {
		System.out.println("In inittializetable in facade client.");
		
		for(int i=0; i < playerHand.size(); i++) 
			System.out.println("\t" + playerHand.get(i).getName());
		System.out.println("Hand Complete.");
		for(int i=0; i < gameTableHand.size(); i++) 
			System.out.println("\t" + gameTableHand.get(i).getName());
		System.out.println("Game Table Cards Complete.");
		for(int i=0; i < playerNames.length; i++) 
			System.out.println("\t" + playerNames[i]);
		System.out.println("Game Table Cards Complete.");
		
		this.c.initializeTable(playerHand, gameTableHand, playerNames);
		
	}

}
