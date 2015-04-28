package GameServer.GameEngine;


import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import javafx.application.Platform;


public class FacadeClient extends UnicastRemoteObject implements Client {
	
	public FacadeClient() throws RemoteException {
		
		
	}


	public Client c;

	

	@Override
	public void determineAction(Action a) throws RemoteException {
		//This is used to handle FX threading
		Platform.runLater(() ->
		{
			try {
				this.c.determineAction(a);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}
	
	@Override
	public void setGameEngine(String ge) throws RemoteException {
		//This is used to handle FX threading
		Platform.runLater(() ->
		{
			try {
				this.c.setGameEngine(ge);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
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
		
		//This is used to handle FX threading
		Platform.runLater(() ->
		{
			try {
				this.c.initializeTable(playerHand, gameTableHand, playerNames);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		
		
	}

	@Override
	public void startTurn() throws RemoteException {
		//This is used to handle FX threading
		Platform.runLater(() ->
		{
			try {
				System.out.println("Before start turn.");
				this.c.startTurn();
				System.out.println("After start turn.");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}
	
	@Override
	public void setOtherPlayerTurn(String player){
		Platform.runLater(() ->
		{
			try {
				this.c.setOtherPlayerTurn(player);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	};
	
	public void updatePlayerStats(int st, int at, int vp, String players[]){
		Platform.runLater(() ->
		{
			try {
				this.c.updatePlayerStats(st, at, vp, players);
			} catch (Exception e){ 
				e.printStackTrace();
			}
		});
	};
	
	public void updateOtherPlayersStats(int vp, String players[], String username){
		Platform.runLater(() ->
		{
			try {
				this.c.updateOtherPlayersStats(vp, players, username);
			} catch (Exception e){ 
				e.printStackTrace();
			}
		});
	};
	
	public void setPlayerHand(Hand hand){
		Platform.runLater(() ->
		{
			try {
				this.c.setPlayerHand(hand);
			} catch (Exception e){ 
				e.printStackTrace();
			}
		});
	};
	


}
