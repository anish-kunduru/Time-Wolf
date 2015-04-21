package GameServer;

public class GameInfo {
	
	private String name;
	private int numPlayers;
	private boolean chat;
	private boolean privacy;
	private String[] players;
	
	public GameInfo(String name, int numPlayers, String[] players){
		
		this.name = name;
		this.numPlayers = numPlayers;
		//this.chat = chat;
		//this.privacy = privacy;
		this.players = players;
		
	}
	
	public String getName(){
		return name;
	}
	
	public int getNumPlayers(){
		return numPlayers;
	}
	
	public boolean getChat(){
		return chat;
	}
	
	public boolean getPrivacy(){
		return privacy;
	}
	
	public String[] getPlayers(){
		return players;
	}
	
	public void addPlayer(String player){
		players[players.length] = player;
	}

}
