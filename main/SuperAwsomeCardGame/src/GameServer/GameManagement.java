package GameServer;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;

import GameServer.GameEngine.Deck;
import GameServer.GameEngine.GameEngine;
import GameServer.Users.User;


public class GameManagement extends UnicastRemoteObject implements Runnable, Remote {
   
   //private static final long serialVersionUID = 1L;
	
	private ArrayList<GameEngine> games;
	private Deck startingDeck;
	private Deck mainDeck;
	
	
	
	/**
	 * Create the game managment class. 
	 * This is the primary class that manages all the game engines and such for 
	 * the entirety of the system.
	 * @throws SQLException
	 */
	public GameManagement() throws SQLException, RemoteException {
		
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
	public ArrayList<String> listJoinableGames()
	{
		//TODO : We can't return game engines here... String is probably not enough though
		ArrayList<String> available = new ArrayList<String>();
		for(int i = 0; i < games.size(); i++)
		{
			if(!games.get(i).isRunning() && !games.get(i).isFinished())
			{
				available.add(games.get(i).getName());
			}
		}
		
		return available;
	}
	

	public ArrayList<GameEngine> SearchGames(String name)
	{
		ArrayList<GameEngine> games = SearchGames(name, 0);
		return games;
		
	}
	public ArrayList<GameEngine> SearchGames(int players)
	{
		ArrayList<GameEngine> games =  SearchGames("", players);
		return games;
	}
	public ArrayList<GameEngine> SearchGames(String name, int players)
	{
		boolean nameSearch = (name != "");
		boolean playerSearch = (players > 0);
		ArrayList<GameEngine> available = new ArrayList<GameEngine>();
		for(int i = 0; i < games.size(); i++)
		{
			if(!games.get(i).isRunning() && !games.get(i).isFinished())
			{
				if((games.get(i).getName().equals(name) || !nameSearch) && 
						(games.get(i).getTotalNumOfPlayers() == players || !playerSearch))
				{
					available.add(games.get(i));
				}
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
		if(game < 0 || game >= this.games.size()) return false;
		
		GameEngine g = this.games.get(game);
		
		//The game can't have already have started and the game must be full
		if(g.hasStarted() || !g.isFull()) return false;
		
		//Return the status of the game engine startup
		return g.start();
	}



	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {
		
		try {
			GameManagement m = null;
			try {
				m = new GameManagement();
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			User u1 = new User();
			User u2 = new User();
			
			u1.setUsername("Player 1");
			u2.setUsername("Player 2");
			
			m.createGame(2, "The Game");
			
			m.addUserToGame(0, u1, "//Path/ToClient");
			m.addUserToGame(0, u2, "//Path/ToClient");
			
			
			
			if(m.startGame(0)) {
				System.out.println("Good."); 
			} else {
				System.out.println("Bad.");
			}
			
			while(true)
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
