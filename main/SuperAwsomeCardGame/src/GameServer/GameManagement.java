package GameServer;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

import GameServer.GameEngine.Deck;
import GameServer.GameEngine.GameEngine;
import GameServer.Users.User;


public class GameManagement implements Runnable, Remote {
	
	private ArrayList<GameEngine> games;
	private Deck startingDeck;
	private Deck mainDeck;
	
	
	
	/**
	 * Create the game managment class. 
	 * This is the primary class that manages all the game engines and such for 
	 * the entirety of the system.
	 * @throws SQLException
	 */
	public GameManagement() throws SQLException {
		
		this.games = new ArrayList<GameEngine>();
		
		//Load the decks we're going to use up
		try {
			this.startingDeck = Deck.getStarterDeck();
			this.mainDeck = Deck.getMainDeck();
		} catch (SQLException e) {	
			e.printStackTrace();
			throw(e);
		}
		
	}
	
	/**
	 * Returns a list of games to join for the game lobby screen
	 * @return list of available games to join
	 */
	public ArrayList<GameEngine> ListJoinableGames()
	{
		ArrayList<GameEngine> available = new ArrayList<GameEngine>();
		for(int i = 0; i < games.size(); i++)
		{
			if(!games.get(i).isRunning() && !games.get(i).isFinished())
			{
				available.add(games.get(i));
			}
		}
		
		return available;
	}
	
	
	public void createGame(int numberOfPlayers, String gameName) {
		GameEngine ge;
		try {
			ge = new GameEngine(numberOfPlayers, gameName, this.startingDeck, this.mainDeck);
			this.games.add(ge);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public boolean addUserToGame(int game, User u, String clientRegistryName) {
		
		//User must be initialized
		if(u == null) {
			return false;
		}
		
		//We must have a valid game number
		if(game < 0 && game >= this.games.size()) {
			return false;
		}
		
		GameEngine ge = this.games.get(game);
		
		//Check to see if the game will allow additions
		//Can't add players to a running game or a full game
		if(ge.isRunning() || ge.isFull()) {
			return false;
		}
		
		
		ge.addPlayer(u, clientRegistryName);
		
		
		return true;
		
	}

	public boolean startGame(int game) {
		
		//If the game doesn't exist, we can't start it
		if(game >= 0 && game < this.games.size()) return false;
		
		GameEngine g = this.games.get(game);
		
		//The game can't have already have started and the game must be full
		if(g.hasStarted() || !g.isFull()) return false;
		
		//Start it
		if(g.start()) return true;
		
		//Otherwise return false
		return false;
	}



	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
