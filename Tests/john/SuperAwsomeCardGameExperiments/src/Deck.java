
public class Deck {
	
	int counter = 0;
	
	public Card drawCard() {
		this.counter++;
		
		if(counter == 1) {
			
			return new Card("Draw 2", 4, 0, 2);
		} 
		
		if(this.counter == 4) {
			this.counter = 0;
		}
		return new Card("Victory PTS + 5", 6, 5, 0);
	}

}
