import java.sql.SQLException;
import java.util.ArrayList;

import GameEngine.Deck;
import GameEngine.GameEngine;


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
	
	




	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
