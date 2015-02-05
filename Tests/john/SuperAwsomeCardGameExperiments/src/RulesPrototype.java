import java.util.ArrayList;


public class RulesPrototype {

	public static void main(String[] args) {
		
		ArrayList<Card> c = new ArrayList<Card>();
		Deck d = new Deck();
		
		int vp = 0;
		int draws = 0;
		
		c.add(new Card("Draw 2", 2, 0, 2));
		c.add(new Card("Victory Points 5", 2, 5, 0));
		c.add(new Card("Draw 1", 2, 0, 2));
		c.add(new Card("Victory Points 2", 2, 5, 0));
		c.add(new Card("Draw 1", 6, 1, 1));
		
		for(int i = 0; i < 5; i++) {
			vp += c.get(i).getVp();
			draws += c.get(i).getDrawCards();
			System.out.println("Playing " + c.get(i).getName() + ": Adding " + c.get(i).getVp() +
					" victory points and getting " + c.get(i).getDrawCards() + " additional cards drawn.");
		}
		
		System.out.println("Final VP: " + vp + " Final Draw Count: " + draws);

	}

}
