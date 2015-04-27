/**
 * @author Anish Kunduru
 *
 * This program is our handler for CreateGameScreen.fxml.
 */

package createGame;

import framework.AbstractScreenController;
import framework.ControlledScreen;
import singleton.MainModel;
import view.MainController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class CreateGameScreenController implements ControlledScreen {
	// FXML Components.
	@FXML
	private Button createGameButton;

	@FXML
	private TextField lobbyNameTextField;
	@FXML
	private TextField passwordTextField;
	@FXML
	private ComboBox<Integer> numberPlayersComboBox;

	@FXML
	private CheckBox chatCheckBox;

	// So we can set the screen's parent later on.
	MainController parentController;

	/**
	 * Initializes the controller class. Automatically called after the FXML
	 * file has been loaded.
	 */
	@FXML
	public void initialize() {
		numberPlayersComboBox.getItems().add(2);
		numberPlayersComboBox.getItems().add(3);
		numberPlayersComboBox.getItems().add(4);
		numberPlayersComboBox.getSelectionModel().select(0);

		// Set appropriate text if box is unchecked.
		chatCheckBox.setOnAction(event -> {
			if (chatCheckBox.isSelected())
				chatCheckBox.setText("On");
			else
				chatCheckBox.setText("Off");
		});

		createGameButton.setOnAction(event -> {
			int players = numberPlayersComboBox.getSelectionModel()
					.getSelectedItem();
			String name = lobbyNameTextField.getText();

			int id = 0;
			try {
				id = MainModel.getModel().currentGameLobbyData().getGameManager()
						.createGame(players, name);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			MainModel.getModel().currentGameLobbyData().setID(id);
			parentController.goToGameTableScreen();
		});
	}

	/**
	 * This method will allow for the injection of each screen's parent.
	 */
	@Override
	public void setScreenParent(AbstractScreenController screenParent) {
		parentController = (MainController) screenParent;
	}
}
