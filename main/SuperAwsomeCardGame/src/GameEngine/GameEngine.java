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
		
		//TODO - More action cards, Beginning cards worth 1 buy and 1 stealth each,
		//cards that are always available worth 2 buy and 2 stealth costing 3? each,
		//historical figure cards costing anywhere between 1-6 stealth to defeat
		
		
		
		//Rest of the game engine stuff here.
		
	}

}
