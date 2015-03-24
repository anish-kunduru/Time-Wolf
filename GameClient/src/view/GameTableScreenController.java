/**
 /**
 * This program is our handler for GameTableScreen.fxml.
 */

package view;

import java.sql.SQLException;

import GameServer.GameEngine.Action;
import GameServer.GameEngine.Card;
import GameServer.GameEngine.Deck;
import GameServer.GameEngine.Hand;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GameTableScreenController implements ControlledScreen {

	// IMPORTANT NOTE: IF YOU RENAME ANYTHING WITH AN FXML TAG IN FRONT OF IT,
	// YOU WILL NEED TO RE-LINK IT IN THE GAME TABLE SCREEN.
	// OTHERWISE, IT SIMPLY WON'T WORK AND/OR THE MAIN CONTROLLER WILL THROW AN
	// EXCEPTION TO THE CONSOLE...

	// FXML Components

	@FXML
	private Button endTurnButton;

	@FXML
	private Label cardsInGameDeckLabel; // Set text to show num of cards in main
										// deck
	@FXML
	private Label playerTurnLabel; // Set text to reflect whose turn it is.
	@FXML
	private Label playerCardsInDeckLabel; // Set text to show num of cards in
											// player's deck

	// Labels to show the number of VP points each player has

	@FXML
	private Label playerOneVP;
	@FXML
	private Label playerTwoVP;
	@FXML
	private Label playerThreeVP;
	@FXML
	private Label yourVP;

	// Labels to show current values of stealth and attack
	@FXML
	private Label Attack;
	@FXML
	private Label Stealth;

	// Images for the cards currently in player's hand
	@FXML
	private ImageView playerHandOne;
	@FXML
	private ImageView playerHandTwoo;
	@FXML
	private ImageView playerHandThree;
	@FXML
	private ImageView playerHandFour;
	@FXML
	private ImageView playerHandFive;
	@FXML
	private ImageView playerHandSix;
	@FXML
	private ImageView playerHandSeven;
	@FXML
	private ImageView playerHandEight;
	@FXML
	private ImageView playerHandNine;
	@FXML
	private ImageView playerHandTen;
	@FXML
	private ImageView playerHandEleven;
	@FXML
	private ImageView playerHandTwelve;
	@FXML
	private ImageView playerHandThirteen;

	// Images that represent the 5 current cards on the table.
	@FXML
	private ImageView gameTableCardOne;
	@FXML
	private ImageView gameTableCardTwo;
	@FXML
	private ImageView gameTableCardThree;
	@FXML
	private ImageView gameTableCardFour;
	@FXML
	private ImageView gameTableCardFive;

	@FXML
	private ImageView playerDeckImage; // Image representing player's deck
	@FXML
	private ImageView lastDiscardImage; // Image representing player's discard
										// deck
	@FXML
	private ImageView biteDeckImage; // Image representing the bite card deck
	@FXML
	private ImageView lurkDeckImage; // Image representing the lurk card deck
	@FXML
	private ImageView notSoImportantHistoricalFigureImage; // Image representing
															// the NSIHF card
															// deck

	@FXML
	private TextArea playLog; // Log for game actions.

	private ImageView[] playerHandImages;

	private ImageView[] gameTableImages;

	private Card cardForAction;
	private Action action;
	private boolean isTurn;
	private boolean isDiscard;
	private int stealth;
	private int attack;
	private int counter;

	// So we can set the screen's parent later on.
	MainController parentController;

	/**
	 * Initializes the controller class. Automatically called after the FXML
	 * file has been loaded.
	 * 
	 * @throws SQLException
	 */
	@FXML
	public void initialize() throws SQLException {

		// Objects used for testing, will be provided by server in the future.
		Deck starterDeck = new Deck();
		starterDeck = Deck.getStarterDeck();

		Deck mainDeck = new Deck();
		mainDeck = Deck.getMainDeck();

		Hand playerHand = new Hand(5);
		Hand tableHand = new Hand(5);

		starterDeck.draw(playerHand);
		mainDeck.draw(tableHand);
		String[] playerNames = new String[] { "Player One", "Player Two",
				"Player Three" };

		attack = 5;
		stealth = 5;
		isTurn = true;

		// KEEP. Puts imageviews into arrays.

		gameTableImages = new ImageView[] { gameTableCardOne, gameTableCardTwo,
				gameTableCardThree, gameTableCardFour, gameTableCardFive };

		playerHandImages = new ImageView[] { playerHandOne, playerHandTwoo,
				playerHandThree, playerHandFour, playerHandFive, playerHandSix,
				playerHandSeven, playerHandEight, playerHandNine,
				playerHandTen, playerHandEleven, playerHandTwelve,
				playerHandThirteen };

		// Initializes the game table when page is opened. This includes
		// adding effects as well as populating fields.
		initializeTable(playerHand, tableHand, playerNames);

		// Handles ending the turn on button clicked
		endTurn();

		// Handles action when a main table card is clicked
		onTableCardClicked(mainDeck, stealth, attack);

		System.out.println(playerHandNine);

		onPlayerCardClicked();
		Card c = new Card("Bury");
		Hand h = new Hand(3);
		h.addCard(starterDeck.draw());
		h.addCard(starterDeck.draw());
		h.addCard(starterDeck.draw());
		Action a = new Action(5, 1, c, h);
		determineAction(a);

	}

	/**
	 * Helper method pulls a card to the front of the display. Intended to be
	 * used with the hand card objects.
	 * 
	 * @param imageToShow
	 *            The image that you wish to pull to the front of the display.
	 */
	private void showOnMouseEntered(ImageView imageToShow) {
		imageToShow.setOnMouseEntered(event -> {
			imageToShow.toFront();
		});
	}

	/**
	 * Helper method associates a listener to each passed image that adds a
	 * highlight effect to an image on mouse hover.
	 * 
	 * @param imageToHighlight
	 *            The image that you wish to add the highlight effect to.
	 */
	private void highlightOnMouseEntered(ImageView imageToHighlight) {
		imageToHighlight.setOnMouseEntered(event -> {
			// Create ambient light
				Light.Distant light = new Light.Distant();
				light.setAzimuth(-135.0);

				// Create lighting effect
				Lighting lighting = new Lighting();
				lighting.setLight(light);
				lighting.setSurfaceScale(4.0);

				// Apply lighting.
				imageToHighlight.setEffect(lighting);
			});
	}

	/**
	 * Helper method that removes any effect added to an image when the mouse is
	 * no longer hovering over it. Intended to be used with the
	 * highlightOnMouseEntered() method.
	 * 
	 * @param imageToUnhighlight
	 *            The image that you wish to remove effects from.
	 */
	private void unhighlightOnMouseExited(ImageView imageToUnhighlight) {
		imageToUnhighlight.setOnMouseExited(event -> {
			imageToUnhighlight.setEffect(null);
		});
	}

	/**
	 * This method will allow for the injection of each screen's parent.
	 */
	public void setScreenParent(MainController screenParent) {
		parentController = screenParent;
	}

	/**
	 * Helper method that takes care of adding the highlight effect to the
	 * appropriate cards.
	 */
	public void highlightEffect() {

		highlightOnMouseEntered(biteDeckImage);
		highlightOnMouseEntered(lurkDeckImage);
		highlightOnMouseEntered(notSoImportantHistoricalFigureImage);
		highlightOnMouseEntered(playerDeckImage);

		for (int i = 0; i < gameTableImages.length; i++) {
			highlightOnMouseEntered(gameTableImages[i]);
		}

		// ///////////////////////////////////////////////////////////////

		unhighlightOnMouseExited(biteDeckImage);
		unhighlightOnMouseExited(lurkDeckImage);
		unhighlightOnMouseExited(notSoImportantHistoricalFigureImage);
		unhighlightOnMouseExited(playerDeckImage);

		for (int i = 0; i < gameTableImages.length; i++) {
			unhighlightOnMouseExited(gameTableImages[i]);
		}

	}

	/**
	 * Helper method that takes care of the "bring to front" effect on the
	 * appropriate cards.
	 */

	public void showCard() {

		for (int i = 0; i < playerHandImages.length; i++) {
			showOnMouseEntered(playerHandImages[i]);
		}

	}

	/**
	 * Method called to initialize the game play area when the screen is first
	 * loaded. Takes care of both effects and populating fields.
	 * 
	 * @param playerHand
	 * @param gameTableHand
	 * @param playerNames
	 */
	public void initializeTable(Hand playerHand, Hand gameTableHand,
			String[] playerNames) {

		// Add highlight effects
		highlightEffect();

		// Add bring to front effects
		showCard();

		// Set the claw, lurk, and notSoImportantHistoricalFigure deck images
		biteDeckImage.setImage(new Image("cards/bite.png"));
		lurkDeckImage.setImage(new Image("cards/lurk.png"));
		notSoImportantHistoricalFigureImage.setImage(new Image(
				"cards/notSoImportantHistoricalFigure.png"));

		// Set the face down card image.
		playerDeckImage.setImage(new Image("cards/faceDownCard.png"));

		// Set button text
		endTurnButton.setText("End Turn");

		// Set cards in deck label
		cardsInGameDeckLabel.setText("Cards In Deck: 10");

		// Set player VP labels
		if (playerNames.length == 1) {
			playerOneVP.setText(playerNames[0] + ": 1,000 BCE");
			yourVP.setText("You are in year 1,000 BCE");
		}

		if (playerNames.length == 2) {
			playerOneVP.setText(playerNames[0] + ": 1,000 BCE");
			playerTwoVP.setText(playerNames[1] + ": 1,000 BCE");
			yourVP.setText("You are in year 1,000 BCE");
		}

		if (playerNames.length == 3) {
			playerOneVP.setText(playerNames[0] + ": 1,000 BCE");
			playerTwoVP.setText(playerNames[1] + ": 1,000 BCE");
			playerThreeVP.setText(playerNames[2] + ": 1,000 BCE");
			yourVP.setText("You are in year 1,000 BCE");
		}

		// Populate hand image fields for player and main table
		for (int i = 0; i < 5; i++) {
			gameTableImages[i].setImage(new Image(gameTableHand.get(i)
					.getImagePath()));
			gameTableImages[i].setId(gameTableHand.get(i).getName());
		}

		for (int i = 0; i < 5; i++) {
			playerHandImages[i].setImage(new Image(playerHand.get(i)
					.getImagePath()));
			playerHandImages[i].setId(playerHand.get(i).getName());
		}

	}

	private Action onTableCardClickedEvent(ImageView image, Deck deck,
			int stealth, int attack) {
		image.setOnMouseClicked(event -> {
			if (isTurn) {
				// Check to see if player can afford card first.
				Card oldCard;
				try {
					oldCard = new Card(image.getId());
					if (oldCard.getCostAttack() > attack
							|| oldCard.getCostStealth() > stealth) {
						playLog.appendText("Can't afford that card. \n");
						action = null;
					}

					else {

						// Append action to the play log.
						// TODO get player's name

						// System.out.println(oldCard.getCardType());
						if (oldCard.getCardType().equals("Action")) {
							playLog.appendText("You stole card "
									+ oldCard.getName() + ". "
									+ oldCard.getDescription() + "\n");
						} else {
							playLog.appendText("You defeated "
									+ oldCard.getName() + ". "
									+ oldCard.getDescription() + "\n");
						}

						// Create action with old card
						try {
							cardForAction = new Card(image.getId());
						} catch (Exception e) {
							e.printStackTrace();
						}
						action = new Action(1, cardForAction);
						// System.out.println(action.getCard().getName());

						// Change image to a new card's image, reset id to new
						// card's
						// name,
						Card card = deck.draw();
						image.setImage(new Image(card.getImagePath()));
						image.setId(card.getName());
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else {
				action = null;
			}

		});
		return action;
	}

	private void onTableCardClicked(Deck deck, int stealth, int attack)
			throws SQLException {
		for (int i = 0; i < 5; i++) {
			onTableCardClickedEvent(gameTableImages[i], deck, stealth, attack);
		}
	}

	private void endTurn() {
		// TODO Broken
		endTurnButton.setOnMouseClicked(event -> {
			isTurn = false;
		});
	}

	private Action onPlayerCardClickedEvent(ImageView image) {

		image.setOnMouseClicked(event -> {

			if (isTurn && isDiscard && counter > 0) {
				try {
					Card c = new Card(image.getId());
					playLog.appendText("You discarded card " + c.getName()
							+ ". " + c.getDescription() + "\n");

					lastDiscardImage.setImage(image.getImage());
					image.setImage(null);

					counter--;
					if (counter == 0)
						isDiscard = false;

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (isTurn) {
				for (int i = 0; i < playerHandImages.length; i++) {
					if (playerHandImages[i].getId() == null) {
						System.out.println(playerHandImages[i].getId());
						playerHandImages[i].setVisible(false);
					}
				}

				try {
					Card oldCard = new Card(image.getId());
					playLog.appendText("You played card " + oldCard.getName()
							+ ". " + oldCard.getDescription() + "\n");
					lastDiscardImage.setImage(image.getImage());
					image.setImage(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			else {
				action = null;
			}
		});
		return action;
	}

	private void onPlayerCardClicked() {
		for (int i = 0; i < playerHandImages.length; i++) {
			onPlayerCardClickedEvent(playerHandImages[i]);
		}
	}

	private void determineAction(Action a) {
		if (a.getAction() == 0) {
			playCard(a);
		} else if (a.getAction() == 1) {
			acquireCard(a);
		} else if (a.getAction() == 5) {
			discardCard(a);
		} else if (a.getAction() == 6) {

		}
	}

	private void playCard(Action a) {
		Card c = a.getCard();
		if (c.getCardType().equals("Action")) {
			playLog.appendText(a.getPlayerName() + " stole card " + c.getName()
					+ ". " + c.getDescription() + "\n");
		} else {
			playLog.appendText("Player defeated " + c.getName() + ". "
					+ c.getDescription() + "\n");
		}

	}

	private void acquireCard(Action a) {
		playLog.appendText(a.getPlayerName() + " played card "
				+ a.getCard().getName() + ". " + a.getCard().getDescription()
				+ "\n");
	}

	private void discardCard(Action a) {
		Card c = a.getCard();
		int discard = 0;

		if (c.getPreturnDiscard() != 0)
			discard = c.getPreturnDiscard();
		else
			discard = c.getPostturnDiscard();
		isDiscard = true;
		counter = 3;
		//
		// for (int i = 0; i < discard; i++) {
		// isDiscard = true;
		// }
		// isDiscard = false;
		int j = 0;
		for (int i = 0; i < a.getHand().size(); i++) {
			while (playerHandImages[j].getImage() != null) {
				j++;
			}
			playerHandImages[j].setImage(new Image(a.getHand().get(i)
					.getImagePath()));
		}

	}
	
	

	// private void discardCardEvent(ImageView img) {
	// img.setOnMouseClicked(event -> {
	// if (isDiscard && counter > 0) {
	// try {
	// Card c = new Card(img.getId());
	// playLog.appendText("You discarded card " + c.getName()
	// + ". " + c.getDescription() + "\n");
	//
	// lastDiscardImage.setImage(img.getImage());
	// img.setImage(null);
	//
	//
	//
	// } catch (Exception e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }
	// });
	// }
	//
	// private void discardCardClicked() {
	// for (int i = 0; i < playerHandImages.length; i++) {
	// discardCardEvent(playerHandImages[i]);
	// }
	// }

}