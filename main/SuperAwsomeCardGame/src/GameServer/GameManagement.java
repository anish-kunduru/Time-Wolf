package GameServer;
import java.sql.SQLException;
import java.util.ArrayList;

import GameServer.GameEngine.Deck;
import GameServer.GameEngine.GameEngine;
import GameServer.Users.User;


public class GameManagement implements Runnable {
	
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
	
	
	public void createGame(int numberOfPlayers, String gameName) {
		GameEngine ge = new GameEngine(numberOfPlayers, gameName, this.startingDeck, this.mainDeck);
		this.games.add(ge);
	}
	
	public boolean addUserToGame(int game, User u) {
		
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
		
		ge.addPlayer(u);
		
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
