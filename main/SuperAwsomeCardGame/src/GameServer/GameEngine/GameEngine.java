package GameServer.GameEngine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.Iterator;

import GameServer.Users.User;


public class GameEngine implements Runnable {
	
	
	private Player[] players;
	private int totalNumOfPlayers;
	private int currentNumOfPlayers;
	private int currentPlayerIndex = 0;
	
	private String name;
	private boolean isRunning = false;
	private boolean isFinished = false;
	
	
	private Deck startingDeck;
	
	private Deck mainDeck;
	private DiscardPile mainDiscard = new DiscardPile();
	private Hand mainPlayAreaCards = new Hand(4);
	private static final Card defaultAttack = new Card("Not So Important Historical Figure", "You think you may have read about this guy once.", 0, 2, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, false, false, false);
	private static final Card defaultBuyStealth = new Card("Prowl", "Gain 2 stealth when played.", 3, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, false, false, false);;
	private static final Card defaultBuyAttack = new Card("Bite", "Gain 2 attack when played.", 3, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, false, false, false);;
	
	

	public GameEngine(int numOfPlayers, String name, Deck startingDeck, Deck mainDeck) {
		super();
		
		//Create the array of players and initialize info about the number of players.
		this.totalNumOfPlayers = numOfPlayers;
		this.currentNumOfPlayers = 0;
		this.players = new Player[numOfPlayers];
		
		this.mainDeck = (Deck) mainDeck.clone();
		this.startingDeck = startingDeck;
		this.name = name;
		
		//Initialize the main play area cards.
		for(int i = 0; i < 4; i++) this.mainPlayAreaCards.addCard(this.mainDeck.draw());
		
		
	}
	
	/**
	 * Add player "p" to the game.
	 * @param p the player to add.
	 * @return True if the player has been added successfully.
	 * @throws SQLException 
	 */
	public boolean addPlayer(User u) {
		
		
		//We can only have so many players in a game, and we can't add null players
		if(this.currentNumOfPlayers == this.totalNumOfPlayers || u == null) return false;
		
		//Create the player object using the user
		//Player p = new Player(u.getID(), false, 0, 0, new Hand(5), new DiscardPile(), (Deck)this.startingDeck.clone());
		Player p = new Player(u, (Deck)this.startingDeck.clone());
		
		
		//Add the player if there is room.
		this.players[this.currentNumOfPlayers] = p;
		this.currentNumOfPlayers++;
		
		return true;
	}
	
	
	/**
	 * Start the game.
	 * @return true if the game thread started succesffully.
	 */
	public boolean start() {
		//Must not have already started and the game table must be full
		if(this.hasStarted() || !this.isFull()) return false;
		
		//Start the thread
		Thread t = new Thread(this);
		t.start();
		
		if(!t.isAlive()) return false;
		
		//This is set here and in the thread. Since with threading either can 
		//jump to running the new thread or the original thread
		this.isRunning = true; //Mark it as running
		
		return false;
	}
	
	
	
	
	/**
	 * Get the name of the game.
	 * @return the name of the game
	 */
	public String getName() {
		return name;
	}


	/**
	 * Get the total number of players allowed to play
	 * @return total number of players
	 */
	public int getTotalNumOfPlayers() {
		return totalNumOfPlayers;
	}

	/**
	 * Get the current number of players in the game.
	 * @return the current number of players
	 */
	public int getCurrentNumOfPlayers() {
		return currentNumOfPlayers;
	}

	
	/**
	 * Is the game full?
	 * @return true if full
	 */
	public boolean isFull() {
		return this.currentNumOfPlayers == this.totalNumOfPlayers;
	}
	
	
	/**
	 * Is the game running?
	 * @return true if game is running
	 */
	public boolean isRunning() {
		return isRunning;
	}

	/**
	 * Is the game finished?
	 * @return true if the game is finished
	 */
	public boolean isFinished() {
		return isFinished;
	}

	/**
	 * Has the game started?
	 * @return true if the game started
	 */
	public boolean hasStarted() {
		return isRunning || isFinished;
	}

	
	
	
	public static void main(String[] args) {

		Card c;
		Deck playerDeck;
		Deck maingame;
		try {
			playerDeck = Deck.getStarterDeck(); //The deck the player starts with
			maingame = Deck.getMainDeck(); //The deck for the center of the game.
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return;
		}  
		
		
		//  open up standard input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input; //place to put input from command line.

		
		//Starting the game engine
		
		int vp = 0;
		int stealth = 0;
		int attack = 0;
		int turnNum = 0;
		Hand playArea = new Hand(4);
		Hand playerHand = new Hand(5);
		DiscardPile discard = new DiscardPile();
		
		playerHand.addCard(playerDeck.draw());
		playerHand.addCard(playerDeck.draw());
		playerHand.addCard(playerDeck.draw());
		playerHand.addCard(playerDeck.draw());
		playerHand.addCard(playerDeck.draw());
		
		
		//Initialize the first four cards in main game area
		playArea.addCard(maingame.draw());
		playArea.addCard(maingame.draw());
		playArea.addCard(maingame.draw());
		playArea.addCard(maingame.draw());
		
		
		System.out.println("Welcome to the Super Awsome Card Game Demo v1.0!!!!!!");
		System.out.println();
		System.out.println("How fast can you get to 200 years?");
		System.out.println();
		
		while(vp < 200) {
			turnNum++;
			System.out.println("Start Turn " + turnNum);
			
			while(true) {
				System.out.println("VP: " + vp + "\tStealth: " + stealth + "\tAttack: " + attack);
				System.out.println("Hand Size: " + playerHand.size() + "\tDeck Size: " + playerDeck.size() + "\tDiscard Size: " + discard.size());
				System.out.println("Choose:");
				System.out.println("\t(1) Play Card From Hand");
				System.out.println("\t(2) Purchase or Attack Card from Center.");
				System.out.println("\t(3) View Hand.");
				System.out.println("\t(4) View Cards in Center.");
				System.out.println("\t(5) End Turn");
				System.out.println("Enter Choice: ");
				
				try {
					input = br.readLine();
				} catch (IOException e) {
					e.printStackTrace();
					continue;
				}
				
				if(input.trim().equals("1")) { //play acard from hand.
					GameEngine.printHand(playerHand);
					System.out.println("Play card #: ");
					try {
						input = br.readLine();
					} catch (IOException e) {
						e.printStackTrace();
						continue;
					}
					
					c = playerHand.get(Integer.parseInt(input) -1);
					playerHand.remove(Integer.parseInt(input) - 1);
					
					if(c.getDrawCards() > 0) {
						//Draw X cards
						for(int i = 0; i < c.getDrawCards(); i++) {
							
							playerHand.addCard(playerDeck.draw());
							
							//If necessary reshuffle the discard pile into the deck
							if(playerDeck.size() == 0) {
								discard.addToDeck(playerDeck);
							}
							
						}
						
					}
					
					attack += c.getAttack();
					stealth += c.getStealth();
					
					discard.discard(c);
					
				} else if(input.trim().equals("2")) {
					GameEngine.printCenter(playArea);
					
					System.out.println("Buy or Attack Card #: ");
					try {
						input = br.readLine();
					} catch (IOException e) {
						e.printStackTrace();
						continue;
					}
					
					c = playArea.get(Integer.parseInt(input) -1);
					
					if(c.getCostAttack() <= attack && c.getCostStealth() <= stealth) {
						if(c.getCostAttack() > 0) {
							vp += c.getVp(); //otherwise we get vp
						} else {
							
							//If it isn't a card we attack, then add it to discard
							discard.discard(c);
						}
						
						stealth -= c.getCostStealth();
						attack -= c.getCostAttack();
						
						playArea.remove(Integer.parseInt(input) - 1);
						playArea.addCard(maingame.draw());
					} else {
						System.out.println("Sorry, but you cannot afford to buy or attack this card.");
					}
					
					
					
				} else if(input.trim().equals("3")) {
					GameEngine.printHand(playerHand);
				} else if(input.trim().equals("4")) {
					GameEngine.printCenter(playArea);
				} else if(input.trim().equals("5")) {
					
					
					//End of turn so discard hand and draw a new one.
					for(int i =  playerHand.size() -1; i >= 0; i--) {
						discard.discard(playerHand.get(i));
						playerHand.remove(i);
					}
					
					//Draw 5 cards
					for(int i = 0; i < 5; i++) {
						
						playerHand.addCard(playerDeck.draw());
						
						//If necessary reshuffle the discard pile into the deck
						if(playerDeck.size() == 0) {
							discard.addToDeck(playerDeck);
							
							System.out.println("Reshuffling deck. New deck size: " + playerDeck.size());
						}
						
					}
					
					//Reset the available currency to zero
					stealth = 0;
					attack = 0;									
					
					break;
				}
				
				
				
			}
			
		}
		
		System.out.println("Congratulations on winning!!!!");
		
	}
	
	private static void printHand(Hand h) {
		System.out.println("In your hand:");
		
		for(int i = 0; i < h.size(); i++) {
			System.out.println("(" + (i+1) + ") " + h.get(i).getName() + ": " + h.get(i).getDescription());
		}
		
	}
	
	private static void printCenter(Hand h) {
		
		System.out.println("In the center area:");
		
		for(int i = 0; i < h.size(); i++) {
			System.out.println("(" + (i+1) + ") " + h.get(i).getName() + ": " + h.get(i).getDescription());
			if(h.get(i).getCostAttack() > 0) System.out.println("\t\tCosts Attack: " + h.get(i).getCostAttack());
			if(h.get(i).getCostStealth() > 0) System.out.println("\t\tCosts Stealth: " + h.get(i).getCostStealth());
		}
		
	}


	/**
	 * Prompt the user for input on what they want to do.
	 * @return the action to take
	 */
	private Action promptUserForAction() {
		
		Action chosen;
		
		//  open up standard input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input; //place to put input from command line.
		
		Player p = this.players[this.currentPlayerIndex];
		
		System.out.println("\n\n\n");
		System.out.println("VP: " + p.getVP() + "\tStealth: " + p.getStealth() + "\tAttack: " + p.getAttack());
		System.out.println("Hand Size: " + p.getHand().size() + "\tDeck Size: " + p.getDeck().size() + "\tDiscard Size: " + p.getDiscardPile().size());
		System.out.println("Choose:");
		System.out.println("\t(1) Play Card From Hand");
		System.out.println("\t(2) Purchase or Attack Card from Center.");
		System.out.println("\t(3) View Hand.");
		System.out.println("\t(4) View Cards in Center.");
		System.out.println("\t(5) End Turn");
		System.out.println("Enter Choice: ");
		
		try {
			input = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
			return this.promptUserForAction();
		}
		
		if(input.equals("1")) {
			
			GameEngine.printHand(p.getHand());
			
			System.out.println("Play card #: ");
			try {
				input = br.readLine();
			} catch (IOException e) {
				e.printStackTrace();
				input = "1";
			}
			
			int cardIndex = Integer.parseInt(input);
			
			chosen = new Action(Action.PLAY_CARD, cardIndex, p.getHand().get(cardIndex));
			
		} else if(input.equals("2")) {
			
			GameEngine.printHand(this.mainPlayAreaCards);
			
			System.out.println("Atack/Buy card #: ");
			try {
				input = br.readLine();
			} catch (IOException e) {
				e.printStackTrace();
				input = "1";
			}
			
			int cardIndex = Integer.parseInt(input);
			
			chosen = new Action(Action.AQUIRE_CARD, cardIndex, p.getHand().get(cardIndex));
			
		} else if(input.equals("3")) {
			GameEngine.printHand(p.getHand());
			chosen = this.promptUserForAction();
		} else if(input.equals("4")) {
			GameEngine.printHand(this.mainPlayAreaCards);
			chosen = this.promptUserForAction();
		} else if(input.equals("5")) {
			chosen = new Action(Action.END_TURN);			
		}
		
		return null;
	}
	
	@Override
	public void run() {
		// TODO The main loop of logic goes here for the game engine.
		
		Player current;
		
		
		
		//This is set here and in the start() function. Since with threading processing
		//can jump to running the new thread or the original thread
		this.isRunning = true; 
		
		
		int endingVP = 3000;
		
		// TODO Initialize the state of the GameEngine
		
		
		// TODO Initialize the state of the game on the clients
		
		
		// TODO Main Game Loop
		while(true) {
			
			//Stay true during turn
			while(true) {
				
				Action action = this.promptUserForAction();
				
			}			
			
		}
		
		
		
		
	}

}
