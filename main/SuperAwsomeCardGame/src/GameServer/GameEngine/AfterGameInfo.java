package GameServer.GameEngine;

import GameServer.Users.User;

public class AfterGameInfo {

	private User user;
	private int rank;
	private int VP;
	private int numDeck;
	
	public AfterGameInfo(User user, int rank, int VP, int numDeck){
		this.user = user;
		this.rank = rank;
		this.VP = VP;
		this.numDeck = numDeck;
	}
	
	public User getUser(){
		return user;
	}
	
	public int getRank(){
		return rank;
	}
	
	public int getVP(){
		return VP;
	}
	
	public int getNumDeck(){
		return numDeck;
	}
}
