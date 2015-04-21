/**
 * @author Anish Kunduru
 * 
 * This program is our handler for AfterGameScreen.fxml.
 */

package afterGame;

import GameServer.GameEngine.AfterGameInfo;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import view.ControlledScreen;
import view.MainController;

public class AfterGameScreenController implements ControlledScreen {
	// Reference to Parent type (to be set by setScreenParent).
	MainController parentController;

	@FXML
	private Label rankPlayerOne;
	@FXML
	private Label rankPlayerTwo;
	@FXML
	private Label rankPlayerThree;
	@FXML
	private Label rankPlayerFour;

	@FXML
	private Label namePlayerOne;
	@FXML
	private Label namePlayerTwo;
	@FXML
	private Label namePlayerThree;
	@FXML
	private Label namePlayerFour;

	@FXML
	private Label vpPlayerOne;
	@FXML
	private Label vpPlayerTwo;
	@FXML
	private Label vpPlayerThree;
	@FXML
	private Label vpPlayerFour;

	@FXML
	private Label deckPlayerOne;
	@FXML
	private Label deckPlayerTwo;
	@FXML
	private Label deckPlayerThree;
	@FXML
	private Label deckPlayerFour;

	@FXML
	private Label[] rankList;
	@FXML
	private Label[] nameList;
	@FXML
	private Label[] vpList;
	@FXML
	private Label[] deckList;

	@FXML
	private Button likePlayerOne;
	@FXML
	private Button likePlayerTwo;
	@FXML
	private Button likePlayerThree;
	@FXML
	private Button likePlayerFour;

	@FXML
	private Button dislikePlayerOne;
	@FXML
	private Button dislikePlayerTwo;
	@FXML
	private Button dislikePlayerThree;
	@FXML
	private Button dislikePlayerFour;

	@FXML
	private Button[] likeButtons;
	@FXML
	private Button[] dislikeButtons;

	private boolean[] canPress;
	int count;

	@FXML
	private CheckBox reasonOne;
	@FXML
	private CheckBox reasonTwo;
	@FXML
	private CheckBox reasonThree;
	@FXML
	private CheckBox reasonFour;

	@FXML
	private Label feedbackLabel;


	@FXML
	private Button submit;

	/**
	 * Called automatically by the linked FXML at program launch via dependency
	 * injection.
	 */
	@FXML
	public void initialize() {
		rankList = new Label[] { rankPlayerOne, rankPlayerTwo, rankPlayerThree,
				rankPlayerFour };
		nameList = new Label[] { namePlayerOne, namePlayerTwo, namePlayerThree,
				namePlayerFour };
		vpList = new Label[] { vpPlayerOne, vpPlayerTwo, vpPlayerThree,
				vpPlayerFour };
		deckList = new Label[] { deckPlayerOne, deckPlayerTwo, deckPlayerThree,
				deckPlayerFour };

		likeButtons = new Button[] { likePlayerOne, likePlayerTwo,
				likePlayerThree, likePlayerFour };
		dislikeButtons = new Button[] { dislikePlayerOne, dislikePlayerTwo,
				dislikePlayerThree, dislikePlayerFour };

		canPress = new boolean[] {true, true, true, true};

		reasonOne.setVisible(false);
		reasonTwo.setVisible(false);
		reasonThree.setVisible(false);
		reasonFour.setVisible(false);

		feedbackLabel.setVisible(false);
		submit.setVisible(false);

		likeFeedbackEvent();
		disLikeFeedbackEvent();

		// This part is for testing purposes only
		AfterGameInfo playerOne = new AfterGameInfo("Player One Test", 1, 50,
				100);
		AfterGameInfo playerTwo = new AfterGameInfo("Player Two Test", 2, 40,
				80);

		AfterGameInfo[] playerArray = new AfterGameInfo[] { playerOne,
				playerTwo };

		setAfterGameInfo(playerArray);
	}

	public void setAfterGameInfo(AfterGameInfo[] afterGameInfo) {

		int i = 0;

		for (i = 0; i < afterGameInfo.length; i++) {
			rankList[i].setText("" + afterGameInfo[i].getRank());
			nameList[i].setText(afterGameInfo[i].getName());
			vpList[i].setText("" + afterGameInfo[i].getVP());
			deckList[i].setText("" + afterGameInfo[i].getNumDeck());
		}

		while (i < 4) {
			likeButtons[i].setVisible(false);
			dislikeButtons[i].setVisible(false);
			i++;
		}
	}

	private void disLikeFeedbackEvent() {
		dislikeButtons[0].setOnMouseClicked(event -> {
			if (canPress[0]) {
				reasonOne.setVisible(true);
				reasonTwo.setVisible(true);
				reasonThree.setVisible(true);
				reasonFour.setVisible(true);

				feedbackLabel.setVisible(true);
				submit.setVisible(true);
				feedbackLabel
						.setText("Please Select Your Reason For This Rating");
				canPress[0] = false;
			}
		});
		
		dislikeButtons[1].setOnMouseClicked(event -> {
			if (canPress[1]) {
				reasonOne.setVisible(true);
				reasonTwo.setVisible(true);
				reasonThree.setVisible(true);
				reasonFour.setVisible(true);

				feedbackLabel.setVisible(true);
				submit.setVisible(true);
				feedbackLabel
						.setText("Please Select Your Reason For This Rating");
				canPress[1] = false;
			}
		});
		
		dislikeButtons[2].setOnMouseClicked(event -> {
			if (canPress[2]) {
				reasonOne.setVisible(true);
				reasonTwo.setVisible(true);
				reasonThree.setVisible(true);
				reasonFour.setVisible(true);

				feedbackLabel.setVisible(true);
				submit.setVisible(true);
				feedbackLabel
						.setText("Please Select Your Reason For This Rating");
				canPress[2] = false;
			}
		});
		
		dislikeButtons[3].setOnMouseClicked(event -> {
			if (canPress[3]) {
				reasonOne.setVisible(true);
				reasonTwo.setVisible(true);
				reasonThree.setVisible(true);
				reasonFour.setVisible(true);

				feedbackLabel.setVisible(true);
				submit.setVisible(true);
				feedbackLabel
						.setText("Please Select Your Reason For This Rating");
				canPress[3] = false;
			}
		});
	}

	private void likeFeedbackEvent() {
		likeButtons[0].setOnMouseClicked(event -> {
			if (canPress[0]) {
				feedbackLabel.setText("Thank You For Your Feedback");
				feedbackLabel.setVisible(true);
				reasonOne.setVisible(false);
				reasonTwo.setVisible(false);
				reasonThree.setVisible(false);
				reasonFour.setVisible(false);

				submit.setVisible(false);
				canPress[0] = false;
			}
		});
		
		likeButtons[1].setOnMouseClicked(event -> {
			if (canPress[1]) {
				feedbackLabel.setText("Thank You For Your Feedback");
				feedbackLabel.setVisible(true);
				reasonOne.setVisible(false);
				reasonTwo.setVisible(false);
				reasonThree.setVisible(false);
				reasonFour.setVisible(false);

				submit.setVisible(false);
				canPress[1] = false;
			}
		});
		
		likeButtons[2].setOnMouseClicked(event -> {
			if (canPress[2]) {
				feedbackLabel.setText("Thank You For Your Feedback");
				feedbackLabel.setVisible(true);
				reasonOne.setVisible(false);
				reasonTwo.setVisible(false);
				reasonThree.setVisible(false);
				reasonFour.setVisible(false);

				submit.setVisible(false);
				canPress[2] = false;
			}
		});
		
		likeButtons[3].setOnMouseClicked(event -> {
			if (canPress[3]) {
				feedbackLabel.setText("Thank You For Your Feedback");
				feedbackLabel.setVisible(true);
				reasonOne.setVisible(false);
				reasonTwo.setVisible(false);
				reasonThree.setVisible(false);
				reasonFour.setVisible(false);

				submit.setVisible(false);
				canPress[3] = false;
			}
		});
	}


	/**
	 * This method will allow the injection of the Parent.
	 */
	@Override
	public void setScreenParent(MainController screenParent) {
		parentController = screenParent;
	}

}
