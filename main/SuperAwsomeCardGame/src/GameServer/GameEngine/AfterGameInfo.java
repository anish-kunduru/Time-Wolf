package GameServer.GameEngine;

public class AfterGameInfo {

	private String name;
	private int rank;
	private int VP;
	private int numDeck;
	
	public AfterGameInfo(String name, int rank, int VP, int numDeck){
		this.name = name;
		this.rank = rank;
		this.VP = VP;
		this.numDeck = numDeck;
	}
	
	public String getName(){
		return name;
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
