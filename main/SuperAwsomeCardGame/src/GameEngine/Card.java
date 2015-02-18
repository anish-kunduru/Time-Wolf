package GameEngine;

public class Card {

	//Non mechanical properties
	private String name;
	private String description;
	
	//Non-action related properties
	private int costBuy;
	private int costAttack;
	private int vp;
	
	
	//Action related properties
	private int preturnDiscard;
	private int postturnDiscard;
	private int drawCards;
	private int othersDrawCards;
	private int trashCards;
	private int trashForPower;
	private int removeFromPlayArea;
	private int othersDiscard;

	
	
}
