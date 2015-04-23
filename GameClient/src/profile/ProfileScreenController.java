/**
 * @author Anish Kunduru
 * 
 * This program is our handler for ProfileScreen.fxml.
 */

package profile;

import profile.KarmaRow;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

import GameServer.Users.Feedback;
import GameServer.Users.User;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import singleton.MainModel;
import userListing.UserRow;
import view.ControlledScreen;
import view.MainController;
import view.MainView;

public class ProfileScreenController implements ControlledScreen {
	// FXML components.
	@FXML
	private ObservableList<KarmaRow> tableData;
	@FXML
	private ObservableList<KarmaRow> tableData2;

	@FXML
	private TableView<KarmaRow> karmaTable;
	@FXML
	private TableColumn ratingColumn;
	@FXML
	private TableColumn playerColumn;
	@FXML
	private TableColumn commentColumn;
	
	@FXML
	private TableView<RecentGameStatsRow> statTable;
	@FXML
	private TableColumn gamesPlayedColumn;
	@FXML
	private TableColumn gamesWonColumn;
	@FXML
	private TableColumn ratioColumn;
	@FXML
	private TableColumn totalPointsColumn;
	@FXML
	private TableColumn avgPointsColumn;
	@FXML
	private TableColumn karmaColumn;

	@FXML
	private Label largeUsernameLabel;

	@FXML
	private ImageView profileImage;

	@FXML
	private Label usernameLabel;
	@FXML
	private Label emailLabel;
	@FXML
	private Label locationLabel;
	@FXML
	private Label paranoiaLabel;
	@FXML
	private Label userTypeLabel;

	@FXML
	private Button changeSettingsButton;
	@FXML
	private Button changePasswordButton;

	@FXML
	private TextField usernameTextField;
	@FXML
	private TextField emailTextField;
	@FXML
	private TextField locationTextField;
	@FXML
	private TextField paranoiaTextField;

	@FXML
	private PasswordField newPasswordTextField;
	@FXML
	private PasswordField checkPasswordTextField;

	@FXML
	private Label errorLabel;

	// So we can set the screen's parent later on.
	MainController parentController;

	/**
	 * Initializes the controller class. Automatically called after the FXML
	 * file has been loaded.
	 */
	@FXML
	public void initialize() {
		// TO-DO: REDIRECT LOGIC (if we have time).
		// For now, we will always point to the user that is logged in.

		if (MainModel.getModel().currentLoginData().getUsername() != null) {
			// TO-DO: Get image from database and set it.

			// Get personal information.
			String username = MainModel.getModel().currentLoginData().getUsername();
			String email = "undefined";
			String userType = "undefined";

			try {
				// Get user.
				User user = MainModel.getModel().currentLoginData().getLogInConnection().getUser(username);

				// Set email address.
				email = user.getEmail();

				// Find admin type.
				if (user.isAdmin())
					userType = "admin";
				else if (user.isModerator())
					userType = "moderator";
				else
					userType = "player";
			} catch (Exception e) {
				// DEBUG
				// Nothing much we can do at this point.
				System.out.println("There was an error populating the elements in the profile screen. Is the server down?");
			}

			// Set personal information.
			largeUsernameLabel.setText("Profile - " + username);
			usernameLabel.setText("Username: " + username);
			emailLabel.setText("E-mail: " + email);
			userTypeLabel.setText("User Type: " + userType);

			// The following features are not yet supported... Maybe later if we wish.
			locationLabel.setText("Location: -not supported-");
			paranoiaLabel.setText("Paranoia: -not supported-");

			loadKarmaTable();
		}

		// Event handlers.
		changeSettingsButton.setOnAction(event -> {
			// TO-DO: Change profile picture.
			// What else can/do we want to we change?
			});

		changePasswordButton.setOnAction(event -> {
			String password = newPasswordTextField.getText();
			String checkPassword = checkPasswordTextField.getText();

			if (password.equals(checkPassword)) {
				int userID = MainModel.getModel().currentLoginData().getUserID();

				try {
					MainModel.getModel().currentLoginData().getLogInConnection().resetPassword(userID, password);
					errorLabel.setText("Password reset successful!");
				} catch (Exception e) {
					errorLabel.setText("There was an error changing your password.");
				}
			} else
				errorLabel.setText("Your new passwords do not match.");
		});
	}

	private void loadKarmaTable() {

		// Bind table elements to their appropriate values.
		ratingColumn.setCellValueFactory(new PropertyValueFactory<UserRow, String>("rating"));
		playerColumn.setCellValueFactory(new PropertyValueFactory<UserRow, String>("player"));
		commentColumn.setCellValueFactory(new PropertyValueFactory<UserRow, String>("reasonGiven"));

		// Bind the table values.
		tableData = FXCollections.observableArrayList();
		karmaTable.setItems(tableData);

		String username = MainModel.getModel().currentLoginData().getUsername();
		User user = null;
		try {
			user = MainModel.getModel().currentLoginData().getLogInConnection().getUser(username);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		ArrayList<Feedback> feedback = new ArrayList<Feedback>();
		try {
			feedback = MainModel.getModel().currentLoginData().getLogInConnection().getFeedbackList(user.getID());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (int i = 0; i < feedback.size(); i++) {

			KarmaRow currentRow = new KarmaRow();
			if (feedback.get(i).isPositive()) {
				currentRow.rating.set("like");
			}
			else{
				currentRow.rating.set("dislike");
			}
			String userBy = MainModel.getModel().currentLoginData().getLogInConnection().getUsername(feedback.get(i).getByUserID());
			currentRow.player.set(userBy);
			
			
			currentRow.reasonGiven.set("" + feedback.get(i).getDescription());

			tableData.add(currentRow);
		}

	}

	/**
	 * This method will allow for the injection of each screen's parent.
	 */
	public void setScreenParent(MainController screenParent) {
		parentController = screenParent;
	}
}
