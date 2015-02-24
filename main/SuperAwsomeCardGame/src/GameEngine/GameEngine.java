package GameEngine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;


public class GameEngine {

	public static void main(String[] args) {

		Card c;
		Deck playerDeck = new Deck();  //The deck the player starts with
		Deck maingame = new Deck(); //The deck for the center of the game.
		
		//  open up standard input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input; //place to put input from command line.

		//Add cards to the deck here
		
		//Card paradox causes all other players to go back 10 years by adding a "paradox" card to their deck
		Card paradox = new Card("Paradox", "There can only be one! All other players go back 10 years", 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				1, true, false, true);
				
		//Card scavenge allows the player to draw two additional cards when played from hand
		Card scavenge = new Card("Scavenge", "Draw two additional cards!", 4, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, true, false, false);
				
		//Card bury allows a player to discard two cards and pick up two new ones
		Card bury = new Card("Bury", "Discard two cards and replace them with two new", 4, 0, 0, 0, 0, 2, 0, 2, 0, 0, 0, 0, 0, 0, 0, 
						true, false, true);
				
		//Card wormhole makes all other players discard 2 cards from hand
		Card wormhole = new Card("Wormhole", "All other players must discard 2 cards", 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 
						true, false, true);
		
		//Six of these will go in the player's beginning hand, otherwise unable to buy, they are worth 1 stealth each
		Card beginStealth = new Card("Stealth 1", "This card gives you 1 stealth", 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, true, false, false);
				
		//Card that is always available to buy, similiar to the mystic in ascension
		Card buyStealth = new Card("Stealth 2", "This card gives you 2 stealth", 3, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, true, false, false);
				
		//Four of these will go in the player's beginning hand, otherwise unable to buy, they are worth 1 attack each
		Card beginAttack = new Card("Attack 1", "This card gives you 1 attack", 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, true, false, false);
				
		//Card that is always available to buy, similiar to the heavy infantry in ascension
		Card buyAttack = new Card("Attack 2", "This card gives you 2 attack", 3, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, true, false, false);
		
		Card williamShakespeare = new Card("William Shakespeare", "Attack to move forward 100 years!", 0, 6, 50, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, true, false, false);
		
		Card influentialCourtier = new Card("Influential Courtier", "Attack to move forward 10 years.", 0, 3, 10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, false, false, false );
		Card possibleLeader = new Card("Possible Leader", "Attack to move forward 4 years.", 0, 2, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, false, false, false );
		Card luckInTiming = new Card("The Lucks in the Timing", "Draw 1 card and gain 2 stealth when played from hand.", 3, 0, 0, 0, 2, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, false, false, false);

		
		//TODO - More action cards
		//historical figure cards costing anywhere between 1-10 attack to defeat
		
		
		//Build the starter deck
		playerDeck.addCard(beginStealth, 6);
		playerDeck.addCard(beginAttack, 4);
		
		
		//Build the main game deck
		maingame.addCard(scavenge, 10);
		maingame.addCard(influentialCourtier, 10);
		maingame.addCard(possibleLeader, 10);
		maingame.addCard(luckInTiming, 10);
		maingame.addCard(buyStealth, 10);
		maingame.addCard(buyAttack, 10);
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
					
					attack += c.getPower();
					stealth += c.getMoney();
					
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
					
					if(c.getCostAttack() <= attack && c.getCostBuy() <= stealth) {
						if(c.getCostAttack() > 0) {
							vp += c.getVp(); //otherwise we get vp
						} else {
							
							//If it isn't a card we attack, then add it to discard
							discard.discard(c);
						}
						
						stealth -= c.getCostBuy();
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
					
					//Reset the avaiable currency to zero
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
			if(h.get(i).getCostBuy() > 0) System.out.println("\t\tCosts Stealth: " + h.get(i).getCostBuy());
		}
		
	}

}
