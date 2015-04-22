/**
 * @author Anish Kunduru
 * 
 * This program is our handler for AfterGameScreen.fxml.
 */

package afterGame;

import java.rmi.RemoteException;

import GameServer.GameEngine.AfterGameInfo;
import GameServer.Users.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import singleton.MainModel;
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
	private int count;

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

		canPress = new boolean[] { true, true, true, true };

		reasonOne.setVisible(false);
		reasonTwo.setVisible(false);
		reasonThree.setVisible(false);
		reasonFour.setVisible(false);

		feedbackLabel.setVisible(false);
		submit.setVisible(false);

		// This part is for testing purposes only
		User userOne = null;
		try {
			userOne = MainModel.getModel().currentLoginData()
					.getLogInConnection().logIn("jkhaynes", "password1");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		User userTwo = null;
		try {
			userTwo = MainModel.getModel().currentLoginData()
					.getLogInConnection().logIn("ssimmons", "password");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		AfterGameInfo playerOne = new AfterGameInfo(userOne, 1, 50, 100);
		AfterGameInfo playerTwo = new AfterGameInfo(userTwo, 2, 40, 80);

		AfterGameInfo[] playerArray = new AfterGameInfo[] { playerOne,
				playerTwo };

		// End of test code

		setAfterGameInfo(playerArray);
		likeFeedbackEvent(playerArray, userTwo);
		disLikeFeedbackEvent(playerArray, userTwo);
		submitNegativeFeedbackEvent(playerArray, userTwo);
	}

	public void setAfterGameInfo(AfterGameInfo[] afterGameInfo) {

		int i = 0;

		for (i = 0; i < afterGameInfo.length; i++) {
			rankList[i].setText("" + afterGameInfo[i].getRank());
			nameList[i].setText(afterGameInfo[i].getUser().getUsername());
			vpList[i].setText("" + afterGameInfo[i].getVP());
			deckList[i].setText("" + afterGameInfo[i].getNumDeck());
		}

		while (i < 4) {
			likeButtons[i].setVisible(false);
			dislikeButtons[i].setVisible(false);
			i++;
		}
	}

	private void submitNegativeFeedbackEvent(AfterGameInfo[] agi, User user) {
		submit.setOnMouseClicked(event -> {
			if (reasonOne.isSelected()) {
				MainModel
						.getModel()
						.currentLoginData()
						.getLogInConnection()
						.insertFeedback(agi[count].getUser().getID(),
								user.getID(), false, reasonOne.getText());
			} else if (reasonTwo.isSelected()) {
				MainModel
						.getModel()
						.currentLoginData()
						.getLogInConnection()
						.insertFeedback(agi[count].getUser().getID(),
								user.getID(), false, reasonTwo.getText());
			} else if (reasonThree.isSelected()) {
				MainModel
						.getModel()
						.currentLoginData()
						.getLogInConnection()
						.insertFeedback(agi[count].getUser().getID(),
								user.getID(), false, reasonThree.getText());
			} else if (reasonOne.isSelected()) {
				MainModel
						.getModel()
						.currentLoginData()
						.getLogInConnection()
						.insertFeedback(agi[count].getUser().getID(),
								user.getID(), false, reasonThree.getText());
			}
			feedbackLabel.setText("Thank you for your feedback!");

			reasonOne.setVisible(false);
			reasonTwo.setVisible(false);
			reasonThree.setVisible(false);
			reasonFour.setVisible(false);

			submit.setVisible(false);
			
			canPress[count] = false;
		});
	}

	private void disLikeFeedbackEvent(AfterGameInfo[] agi, User user) {
		dislikeButtons[0].setOnMouseClicked(event -> {
			if (agi[0].getUser().getID() == user.getID()) {
				feedbackLabel.setText("You cannot rate yourself!");
				feedbackLabel.setVisible(true);
			} else if (canPress[0]) {
				reasonOne.setVisible(true);
				reasonTwo.setVisible(true);
				reasonThree.setVisible(true);
				reasonFour.setVisible(true);

				feedbackLabel.setVisible(true);
				submit.setVisible(true);
				feedbackLabel
						.setText("Please Select Your Reason For This Rating");
				count = 0;
			}
		});

		dislikeButtons[1].setOnMouseClicked(event -> {
			if (agi[1].getUser().getID() == user.getID()) {
				feedbackLabel.setText("You cannot rate yourself!");
				feedbackLabel.setVisible(true);
			} else if (canPress[1]) {
				reasonOne.setVisible(true);
				reasonTwo.setVisible(true);
				reasonThree.setVisible(true);
				reasonFour.setVisible(true);

				feedbackLabel.setVisible(true);
				submit.setVisible(true);
				feedbackLabel
						.setText("Please Select Your Reason For This Rating");
				count = 1;
			}
		});

		dislikeButtons[2].setOnMouseClicked(event -> {
			if (agi[2].getUser().getID() == user.getID()) {
				feedbackLabel.setText("You cannot rate yourself!");
				feedbackLabel.setVisible(true);
			} else if (canPress[2]) {
				reasonOne.setVisible(true);
				reasonTwo.setVisible(true);
				reasonThree.setVisible(true);
				reasonFour.setVisible(true);

				feedbackLabel.setVisible(true);
				submit.setVisible(true);
				feedbackLabel
						.setText("Please Select Your Reason For This Rating");
				count = 2;
			}
		});

		dislikeButtons[3].setOnMouseClicked(event -> {
			if (agi[3].getUser().getID() == user.getID()) {
				feedbackLabel.setText("You cannot rate yourself!");
				feedbackLabel.setVisible(true);
				
				reasonOne.setVisible(false);
				reasonTwo.setVisible(false);
				reasonThree.setVisible(false);
				reasonFour.setVisible(false);
				
				submit.setVisible(false);
			} else if (canPress[3]) {
				reasonOne.setVisible(true);
				reasonTwo.setVisible(true);
				reasonThree.setVisible(true);
				reasonFour.setVisible(true);

				feedbackLabel.setVisible(true);
				submit.setVisible(true);
				feedbackLabel
						.setText("Please Select Your Reason For This Rating");
				count = 3;
			}
		});
	}

	private void likeFeedbackEvent(AfterGameInfo[] agi, User user) {
		likeButtons[0].setOnMouseClicked(event -> {
			if (agi[0].getUser().getID() == user.getID()) {
				feedbackLabel.setText("You cannot rate yourself!");
				feedbackLabel.setVisible(true);
			} else if (canPress[0]) {
				feedbackLabel.setText("Thank You For Your Feedback");
				feedbackLabel.setVisible(true);
				reasonOne.setVisible(false);
				reasonTwo.setVisible(false);
				reasonThree.setVisible(false);
				reasonFour.setVisible(false);

				submit.setVisible(false);
				canPress[0] = false;

				MainModel
						.getModel()
						.currentLoginData()
						.getLogInConnection()
						.insertFeedback(agi[0].getUser().getID(), user.getID(),
								true, "");
			}
		});

		likeButtons[1].setOnMouseClicked(event -> {
			if (agi[1].getUser().getID() == user.getID()) {
				feedbackLabel.setText("You cannot rate yourself!");
				feedbackLabel.setVisible(true);
			} else if (canPress[1]) {
				feedbackLabel.setText("Thank You For Your Feedback");
				feedbackLabel.setVisible(true);
				reasonOne.setVisible(false);
				reasonTwo.setVisible(false);
				reasonThree.setVisible(false);
				reasonFour.setVisible(false);

				submit.setVisible(false);
				canPress[1] = false;
				MainModel
						.getModel()
						.currentLoginData()
						.getLogInConnection()
						.insertFeedback(agi[1].getUser().getID(), user.getID(),
								true, "");
			}
		});

		likeButtons[2].setOnMouseClicked(event -> {
			if (agi[2].getUser().getID() == user.getID()) {
				feedbackLabel.setText("You cannot rate yourself!");
				feedbackLabel.setVisible(true);
			} else if (canPress[2]) {
				feedbackLabel.setText("Thank You For Your Feedback");
				feedbackLabel.setVisible(true);
				reasonOne.setVisible(false);
				reasonTwo.setVisible(false);
				reasonThree.setVisible(false);
				reasonFour.setVisible(false);

				submit.setVisible(false);
				canPress[2] = false;
				MainModel
						.getModel()
						.currentLoginData()
						.getLogInConnection()
						.insertFeedback(agi[2].getUser().getID(), user.getID(),
								true, "");
			}
		});

		likeButtons[3].setOnMouseClicked(event -> {
			if (agi[3].getUser().getID() == user.getID()) {
				feedbackLabel.setText("You cannot rate yourself!");
				feedbackLabel.setVisible(true);
			} else if (canPress[3]) {
				feedbackLabel.setText("Thank You For Your Feedback");
				feedbackLabel.setVisible(true);
				reasonOne.setVisible(false);
				reasonTwo.setVisible(false);
				reasonThree.setVisible(false);
				reasonFour.setVisible(false);

				submit.setVisible(false);
				canPress[3] = false;
				MainModel
						.getModel()
						.currentLoginData()
						.getLogInConnection()
						.insertFeedback(agi[3].getUser().getID(), user.getID(),
								true, "");
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
