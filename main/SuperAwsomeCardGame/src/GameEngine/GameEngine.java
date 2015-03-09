package GameEngine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.Iterator;


public class GameEngine {
	
	private String name;
	private Player[] players;
	private int totalNumOfPlayers;
	private int currentNumOfPlayers;
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
		
		
	}
	
	/**
	 * Add player "p" to the game.
	 * @param p the player to add.
	 * @return True if the player has been added successfully.
	 */
	public boolean addPlayer(Player p) {
		//We can only have so many players in a game.
		if(this.currentNumOfPlayers == this.totalNumOfPlayers) return false;
		
		//Add the player if there is room.
		this.players[this.currentNumOfPlayers] = p;
		this.currentNumOfPlayers++;
		
		return true;
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

	
	

	public static void main(String[] args) throws SQLException {

		Card c;
		Deck playerDeck = new Deck();  //The deck the player starts with
		Deck maingame = new Deck(); //The deck for the center of the game.
		
		//  open up standard input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input; //place to put input from command line.

		//Add cards to the deck here
		
		//Card paradox causes all other players to go back 10 years by adding a "paradox" card to their deck
		Card paradox = new Card("Paradox");
				
		//Card scavenge allows the player to draw two additional cards when played from hand
		Card scavenge = new Card("Scavenge");
				
		//Card bury allows a player to discard two cards and pick up two new ones
		Card bury = new Card("Bury");
				
		//Card wormhole makes all other players discard 2 cards from hand
		Card wormhole = new Card("Wormhole");
		
		//Six of these will go in the player's beginning hand, otherwise unable to buy, they are worth 1 stealth each
		Card beginStealth = new Card("Prowl");
				
		//Card that is always available to buy, similar to the mystic in ascension
		Card buyStealth = new Card("Lurk");
				
		//Four of these will go in the player's beginning hand, otherwise unable to buy, they are worth 1 attack each
		Card beginAttack = new Card("Claw");
				
		//Card that is always available to buy, similar to the heavy infantry in ascension
		Card buyAttack = new Card("Bite");
		
		Card williamShakespeare = new Card("Shakespeare");
		
		Card influentialCourtier = new Card("Influential Courtier", "Attack to move forward 10 years.", 0, 3, 10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, false, false, false );
		Card possibleLeader = new Card("Possible Leader", "Attack to move forward 4 years.", 0, 2, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, false, false, false );
		Card neverBorn  = new Card("Great Inventor Never Born", "Attack to move forward 5 years.", 0, 2, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, false, false, false );
		Card luckInTiming = new Card("The Lucks in the Timing", "Draw 1 card and gain 2 stealth when played from hand.", 3, 0, 0, 0, 2, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, false, false, false);
		Card timeLoopStrike = new Card("Time Loop Strike", "Draw 1 card and gain 2 attack when played from hand.", 3, 0, 0, 2, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, false, false, false); 
		
		//TODO - More action cards
		//historical figure cards costing anywhere between 1-10 attack to defeat
		
		
		//Build the starter deck
		playerDeck.addCard(beginStealth, 6);
		playerDeck.addCard(beginAttack, 4);
		
		
		//Build the main game deck
		maingame.addCard(scavenge, 10);
		maingame.addCard(influentialCourtier, 15);
		maingame.addCard(possibleLeader, 15);
		maingame.addCard(luckInTiming, 10);
		maingame.addCard(buyStealth, 10);
		maingame.addCard(buyAttack, 10);
		maingame.addCard(timeLoopStrike, 10);
		maingame.addCard(neverBorn, 15);
		maingame.addCard(williamShakespeare);
		
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

}
