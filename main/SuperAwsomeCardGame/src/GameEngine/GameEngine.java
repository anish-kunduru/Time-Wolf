package GameEngine;

public class GameEngine {

	public static void main(String[] args) {


		Deck starter = new Deck();  //The deck the player starts with
		Deck maingame = new Deck(); //The deck for the center of the game.

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
		Card buyStealth = new Card("Stealth 2", "This card gives you 2 stealth", 3, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, true, false, false);
				
		//Four of these will go in the player's beginning hand, otherwise unable to buy, they are worth 1 attack each
		Card beginAttack = new Card("Attack 1", "This card gives you 1 attack", 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, true, false, false);
				
		//Card that is always available to buy, similiar to the heavy infantry in ascension
		Card buyAttack = new Card("Attack 2", "This card gives you 2 attack", 3, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, true, false, false);
		
		//TODO - More action cards
		//historical figure cards costing anywhere between 1-6 attack to defeat
		
		
		//Rest of the game engine stuff here.
		
	}

}
