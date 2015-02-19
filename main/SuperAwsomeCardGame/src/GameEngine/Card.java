package GameEngine;


public final class Card {

	//Non mechanical properties
	final private String name;
	final private String description;
	
	//Non-action related properties
	final private int costBuy;
	final private int costAttack;
	final private int vp;
	final private int power;
	final private int money;	
	
	//Action related properties
	final private int preturnDiscard;
	final private int postturnDiscard;
	final private int drawCards;
	final private int othersDrawCards;
	final private int trashCardsMandatory;
	final private int trashCardsOptional;
	final private int trashForPower;
	final private int removeFromPlayArea;
	final private int othersDiscard;
	final private int giveCurseCards;
	final private boolean takeAnotherTurn;
	final private boolean refreshPlayArea;
	final private boolean trashAfterUse;
	
	
	/**
	 * @param name Name of the card
	 * @param description Description of the card
	 * @param costBuy Cost to buy
	 * @param costAttack Cost to defeat in an attack
	 * @param vp Victory Points
	 * @param power The attack power of the card.
	 * @param money The amount of currency.
	 * @param preturnDiscard Number of cards to discard prior to other action effects.
	 * @param postturnDiscard Number of cards to discard after other action effects.
	 * @param drawCards Number of cards to draw.
	 * @param othersDrawCards Number of cards other players draw.
	 * @param trashCardsMandatory Number of cards that must be trashed.
	 * @param trashCardsOptional Number of cards that may be trashed.
	 * @param trashForPower Number of cards to trash for power. Cost = Power.
	 * @param removeFromPlayArea Number of cards to discard from play area.
	 * @param othersDiscard Number of cards other players must discard down to.
	 * @param giveCurseCards Number of curse cards to give other players.
	 * @param takeAnotherTurn If true, then the player can take another turn after this one.
	 * @param refreshPlayArea Discard all cards in the play area and replace.
	 * @param trashAfterUse
	 */
	public Card(String name, String description, int costBuy, int costAttack,
			int vp, int power, int money, int preturnDiscard,
			int postturnDiscard, int drawCards, int othersDrawCards,
			int trashCardsMandatory, int trashCardsOptional, int trashForPower, int removeFromPlayArea,
			int othersDiscard, int giveCurseCards, boolean takeAnotherTurn, boolean refreshPlayArea,
			boolean trashAfterUse) {
		super();
		this.name = name;
		this.description = description;
		this.costBuy = costBuy;
		this.costAttack = costAttack;
		this.vp = vp;
		this.power = power;
		this.money = money;
		this.preturnDiscard = preturnDiscard;
		this.postturnDiscard = postturnDiscard;
		this.drawCards = drawCards;
		this.othersDrawCards = othersDrawCards;
		this.trashCardsMandatory = trashCardsMandatory;
		this.trashCardsOptional = trashCardsOptional;
		this.trashForPower = trashForPower;
		this.removeFromPlayArea = removeFromPlayArea;
		this.othersDiscard = othersDiscard;
		this.giveCurseCards = giveCurseCards;
		this.takeAnotherTurn = takeAnotherTurn;
		this.refreshPlayArea = refreshPlayArea;
		this.trashAfterUse = trashAfterUse;
	}


	/**
	 * Returns the name of the card, as it should be printed on the card.
	 * @return the name of the card
	 */
	public String getName() {
		return name;
	}


	/**
	 * Returns the human readable description of the card.
	 * @return the description of the card
	 */
	public String getDescription() {
		return description;
	}


	/**
	 * The cost to buy the card with currency.
	 * @return the cost to buy the card
	 */
	public int getCostBuy() {
		return costBuy;
	}


	/**
	 * The cost to defeat the card with an attack.
	 * @return the cost to defeat a card
	 */
	public int getCostAttack() {
		return costAttack;
	}


	/**
	 * The victory points the card is worth at the end of the game.
	 * @return the number of victory points
	 */
	public int getVp() {
		return vp;
	}


	/**
	 * The power of the attack the card gives.
	 * @return the amount of power
	 */
	public int getPower() {
		return power;
	}


	/**
	 * The amount of currency the card is worth.
	 * @return the amount of money
	 */
	public int getMoney() {
		return money;
	}


	/**
	 * Number of cards to discard prior to other action effects listed on the card. Discarding is split since for some combinations discarding before has a significant difference from discarding after. 
	 * @return the number of cards to discard before actions
	 */
	public int getPreturnDiscard() {
		return preturnDiscard;
	}


	/**
	 * Number of cards to discard after other action effects listed on the card. Discarding is split since for some combinations discarding before has a significant difference from discarding after. 
	 * @return the number of cards to discard after actions
	 */
	public int getPostturnDiscard() {
		return postturnDiscard;
	}


	/**
	 * The number of cards the user draws from their deck.
	 * @return the number of cards to draw
	 */
	public int getDrawCards() {
		return drawCards;
	}


	/**
	 * The number of cards other players will draw because of this card.
	 * @return the number of cards other players draw
	 */
	public int getOthersDrawCards() {
		return othersDrawCards;
	}


	/**
	 * The number of cards the player must trash.
	 * @return the number of cards to trash
	 */
	public int getTrashCardsMandatory() {
		return trashCardsMandatory;
	}


	/**
	 * The player may trash up to this many cards.
	 * @return the number of cards to trash
	 */
	public int getTrashCardsOptional() {
		return trashCardsOptional;
	}


	/**
	 * The number of cards to trash card for power based on the original cost of the card.
	 * @return number of cards to trash for power
	 */
	public int getTrashForPower() {
		return trashForPower;
	}
	/**
	 * @param removeFromPlayArea Number of cards to discard from play area.
	 * @param othersDiscard Number of cards other players must discard down to.
	 * @param takeAnotherTurn If true, then the player can take another turn after this one.
	 * @param refreshPlayArea Discard all cards in the play area and replace.
	 */

	/**
	 * This allows the player to remove and replace this number of cards from the play area.
	 * @return the number of cards to remove and replace from play area
	 */
	public int getRemoveFromPlayArea() {
		return removeFromPlayArea;
	}


	/**
	 * The number of cards the other players must discard down to.
	 * @return the number of cards to discard down to
	 */
	public int getOthersDiscard() {
		return othersDiscard;
	}


	/**
	 * If true, then the card allows the player to take another turn immediatly following this one.
	 * @return true if it grants player another turn
	 */
	public boolean isTakeAnotherTurn() {
		return takeAnotherTurn;
	}


	/**
	 * If true, then the player can refresh all the cards in the play area.
	 * @return true if it causes play area to refresh
	 */
	public boolean isRefreshPlayArea() {
		return refreshPlayArea;
	}


	/**
	 * Give a number of curse cards to all other players
	 * @return the giveCurseCards
	 */
	public int getGiveCurseCards() {
		return giveCurseCards;
	}


	/**
	 * @return the trashAfterUse
	 */
	public boolean isTrashAfterUse() {
		return trashAfterUse;
	}
	
	
}
