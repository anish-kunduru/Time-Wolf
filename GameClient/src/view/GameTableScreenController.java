/**
 * @author Anish Kunduru
 *
 * This program is our handler for GameTableScreen.fxml.
 */

package view;

import java.sql.SQLException;

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

	// Images for the cards currently in player's hand

	@FXML
	private ImageView playerHandOne;
	@FXML
	private ImageView playerHandTwo;
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

		Deck starterDeck = new Deck();
		starterDeck.getStarterDeck();

		Deck mainDeck = new Deck();
		mainDeck.getMainDeck();

		// Add effects to cards.
		highlightOnMouseEntered(biteDeckImage);
		highlightOnMouseEntered(lurkDeckImage);
		highlightOnMouseEntered(notSoImportantHistoricalFigureImage);

		highlightOnMouseEntered(gameTableCardOne);
		highlightOnMouseEntered(gameTableCardTwo);
		highlightOnMouseEntered(gameTableCardThree);
		highlightOnMouseEntered(gameTableCardFour);
		highlightOnMouseEntered(gameTableCardFive);

		highlightOnMouseEntered(playerDeckImage);

		// ///////////////////////////////////////////////////////////////

		unhighlightOnMouseExited(biteDeckImage);
		unhighlightOnMouseExited(lurkDeckImage);
		unhighlightOnMouseExited(notSoImportantHistoricalFigureImage);

		unhighlightOnMouseExited(gameTableCardOne);
		unhighlightOnMouseExited(gameTableCardTwo);
		unhighlightOnMouseExited(gameTableCardThree);
		unhighlightOnMouseExited(gameTableCardFour);
		unhighlightOnMouseExited(gameTableCardFive);

		unhighlightOnMouseExited(playerDeckImage);

		// In an attempt to make hand fit into a smaller place, I overlapped
		// cards and when the mouse enters the cards space it brings that card
		// to the front.
		showOnMouseEntered(playerHandOne);
		showOnMouseEntered(playerHandTwo);
		showOnMouseEntered(playerHandThree);
		showOnMouseEntered(playerHandFour);
		showOnMouseEntered(playerHandFive);
		showOnMouseEntered(playerHandSix);
		showOnMouseEntered(playerHandSeven);
		showOnMouseEntered(playerHandEight);
		showOnMouseEntered(playerHandNine);
		showOnMouseEntered(playerHandTen);
		showOnMouseEntered(playerHandEleven);
		showOnMouseEntered(playerHandTwelve);

		/*
		 * hideOnMouseExit(playerHandOne); hideOnMouseExit(playerHandTwo);
		 * hideOnMouseExit(playerHandThree); hideOnMouseExit(playerHandFour);
		 * hideOnMouseExit(playerHandFive); hideOnMouseExit(playerHandSix);
		 * hideOnMouseExit(playerHandSeven); hideOnMouseExit(playerHandEight);
		 * hideOnMouseExit(playerHandNine); hideOnMouseExit(playerHandTen);
		 * hideOnMouseExit(playerHandEleven); hideOnMouseExit(playerHandTwelve);
		 */

		// Act as if card was played and a new one was drawn.
		// This is just an example of how the mouseListeners would work.
		// You will likely want to create a helper method like one of the ones
		// that I created below.
		// Use lambda expressions, they make your life easier and the code more
		// readable.
		// There is also no need to explicitly call the ActionEvent like you
		// would in an nested inner class listener, they are automagically
		// implied. :)
		playerHandOne.setOnMousePressed(event -> {
			lastDiscardImage.setImage(playerHandOne.getImage());
			playerHandOne.setImage(new Image("cards/bonaparte.png"));
		});

		// An example of how Text Area works.
		// NOTE: I turned on text wrapping for our playLog component. You can
		// change those properties and more in gameTableScreen.fxml.
		// You can also set them directly in code, but it is better to do it in
		// the FXML to be consistent, since our code generally only has change
		// states.
		playLog.appendText("asdfasdfasdfasdfa\nsdfasdfasdfasdfsdfasdfa\nsdfasdfasdfasdfasdfasdfasdfasdfasdfasdf\nasdfasdfasdfasdfasdfasdfasdf");
		playLog.appendText("\nMore blah...");

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
	 * Helper method sends a card back to the display so that they are tiled.
	 * Intended to be used with the hand card objects.
	 * 
	 * @param imageToShow
	 *            The image that you you wish to send to the back of the
	 *            display.
	 */
	private void hideOnMouseExit(ImageView imageToHide) {
		imageToHide.setOnMouseExited(event -> {
			imageToHide.toBack();
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

	public void initiliaze(Hand playerHand, Hand gameTableHand, String[] playerNames) {
		
		// Set the claw, lurk, and notSoImportantHistoricalFigure deck images.

		biteDeckImage.setImage(new Image("cards/bite.png"));
		lurkDeckImage.setImage(new Image("cards/lurk.png"));
		notSoImportantHistoricalFigureImage.setImage(new Image(
				"cards/notSoImportantHistoricalFigure.png"));

		// Set the face down card image.
		playerDeckImage.setImage(new Image("cards/faceDownCard.png"));

		// Initial card states.
		gameTableCardOne.setImage(new Image("cards/charlesDarwin.png"));
		gameTableCardTwo.setImage(new Image("cards/butterflyEffect.png"));
		gameTableCardThree.setImage(new Image("cards/joanOfArc.png"));
		gameTableCardFour.setImage(new Image("cards/laserSword.png"));
		gameTableCardFive.setImage(new Image("cards/cheatingTime.png"));

		playerHandOne.setImage(new Image("cards/bite.png"));
		playerHandTwo.setImage(new Image("cards/claw.png"));
		playerHandThree.setImage(new Image("cards/lurk.png"));
		playerHandFour.setImage(new Image("cards/gandhi.png"));
		playerHandFive.setImage(new Image("cards/joanOfArc.png"));
		playerHandSix.setImage(new Image("cards/testCard.png"));
		playerHandSeven.setImage(new Image("cards/testCard.png"));
		playerHandEight.setImage(new Image("cards/testCard.png"));
		playerHandNine.setImage(new Image("cards/testCard.png"));
		playerHandTen.setImage(new Image("cards/testCard.png"));
		playerHandEleven.setImage(new Image("cards/testCard.png"));
		playerHandTwelve.setImage(new Image("cards/testCard.png"));
	}

}
