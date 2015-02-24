
public class Card {
	
	private String name;
	
	private int cost, vp, drawCards;

	public Card(String name, int cost, int vp, int drawCards) {
		super();
		this.name = name;
		this.cost = cost;
		this.vp = vp;
		this.drawCards = drawCards;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public int getVp() {
		return vp;
	}

	public void setVp(int vp) {
		this.vp = vp;
	}

	public int getDrawCards() {
		return drawCards;
	}

	public void setDrawCards(int drawCards) {
		this.drawCards = drawCards;
	}

}
