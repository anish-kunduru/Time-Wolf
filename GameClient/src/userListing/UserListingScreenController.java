/**
 * @author Anish Kunduru
 *
 * This program is our handler for UserListingScreen.fxml.
 */

package userListing;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

import GameServer.Users.LogIn;
import GameServer.Users.User;
import singleton.MainModel;
import view.ControlledScreen;
import view.MainController;
import view.MainView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

public class UserListingScreenController implements ControlledScreen {
	
   // So we can set the screen's parent later on.
	private MainController parentController;

	// Table components.
	private ArrayList<User> users;
	private ObservableList<UserRow> tableData;

	@FXML
	private TableView<UserRow> userTable;
	@FXML
	private TableColumn usernameColumn;
	@FXML
	private TableColumn emailColumn;
	@FXML
	private TableColumn bannedColumn;
	@FXML
	private TableColumn roleColumn;

	// User components.
	@FXML
	private TextField usernameTextField;
	@FXML
	private TextField emailTextField;
	@FXML
	private CheckBox bannedCheckBox;
	@FXML
	private TextArea bannedReasonTextArea;
	@FXML
	private CheckBox administratorRoleCheckBox;
	@FXML
	private CheckBox moderatorRoleCheckBox;
	@FXML
	private CheckBox userRoleCheckBox;
	@FXML
	private Button resetPasswordButton;

	@FXML
	private ImageView profilePictureImageView;
	@FXML
	private Button removePhotoButton;

	// Save/Cancel components.
	@FXML
	private Label errorLabel;

	@FXML
	private Button saveChangesButton;
	@FXML
	private Button cancelButton;

	private LogIn login;

	/**
	 * Initializes the controller class. Automatically called after the FXML
	 * file has been loaded. Uses the login remote object to create a list of
	 * users and their information which can then be changed by an admin.
	 */
	@FXML
	public void initialize() {
		// Get list of users.
		// NOTE: THIS CODE ISN'T VERY EFFICIENT AND USES A TON OF MEMORY.
		// WE KNOW THIS, BUT THIS SHOULD SUFFICE FOR THE PURPOSES OF THIS DEMO.
		try {
			users = MainModel.getModel().currentLoginData()
					.getLogInConnection().getUserList();
		} catch (RemoteException | SQLException e) {
			System.out.println("Error reading users from the database.");
		}

		// Bind table elements to their appropriate values.
		usernameColumn
				.setCellValueFactory(new PropertyValueFactory<UserRow, String>(
						"username"));
		emailColumn
				.setCellValueFactory(new PropertyValueFactory<UserRow, String>(
						"email"));
		bannedColumn
				.setCellValueFactory(new PropertyValueFactory<UserRow, String>(
						"isBanned"));
		roleColumn
				.setCellValueFactory(new PropertyValueFactory<UserRow, String>(
						"role"));

		// Bind the table values.
		tableData = FXCollections.observableArrayList();
		userTable.setItems(tableData);

		// Populate the table.
		for (int i = 0; i < users.size(); i++) {
			UserRow currentEntry = new UserRow(); // new row.

			User currentUser = users.get(i); // Get index in ArrayList.

			currentEntry.username.set(currentUser.getUsername()); // Set username.
			currentEntry.email.set(currentUser.getEmail()); // Set e-mail.
			currentEntry.isBanned.set(currentUser.isBanned()); // Set banned.

			currentEntry.bannedReason.set("bannedReason");

			// Set role.
			if (currentUser.isAdmin())
				currentEntry.role.set("Administrator");
			else if (currentUser.isModerator())
				currentEntry.role.set("Moderator");
			else
				currentEntry.role.set("User");

			// Add to observableArrayList.
			tableData.add(currentEntry);
		}

		// Event handling for selected row on userTable.
		userTable.setOnMouseClicked(event -> {
			// Check to make sure something is selected.
				if (userTable.getSelectionModel().getSelectedIndex() != -1) {
					usernameTextField.setText(userTable.getSelectionModel()
							.getSelectedItem().username.get());
					emailTextField.setText(userTable.getSelectionModel()
							.getSelectedItem().email.get());

					bannedCheckBox.setSelected(userTable.getSelectionModel()
							.getSelectedItem().isBanned.get());
					checkBannedCheckBoxText();

					bannedReasonTextArea.setText(userTable.getSelectionModel()
							.getSelectedItem().bannedReason.get());

					String role = userTable.getSelectionModel()
							.getSelectedItem().role.get();
					if (role.equals("Administrator")) {
						administratorRoleCheckBox.setSelected(true);
						moderatorRoleCheckBox.setSelected(false);
						userRoleCheckBox.setSelected(false);
					} else if (role.equals("Moderator")) {
						moderatorRoleCheckBox.setSelected(true);
						administratorRoleCheckBox.setSelected(false);
						userRoleCheckBox.setSelected(false);
					} else {
						userRoleCheckBox.setSelected(true);
						administratorRoleCheckBox.setSelected(false);
						moderatorRoleCheckBox.setSelected(false);
					}

					// TO-DO: SET PROFILE IMAGE.
					/*
					 * User now has byte[] of image (must be stored this way to be serializable)
					 *  getImageBytes()
					 *  Get file by doing the following:
					 *  FileOutputStream fos = new FileOutputStream("pathname");
					 *  fos.write(myByteArray);
					 *  fos.close();
					 *  Then convert file to image and display
					 *  
					 */
			}
		});

		// Remove profile picture.
		removePhotoButton.setOnAction(event -> {
			// Check if a user is selected.
				if (userTable.getSelectionModel().getSelectedIndex() != -1) {
					String username = userTable.getSelectionModel()
							.getSelectedItem().username.get();

					try {
						login.removeAvatar(username);
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
		});

		// Remove password.
		resetPasswordButton.setOnAction(event -> {
			// Check if a user is selected.
				if (userTable.getSelectionModel().getSelectedIndex() != -1) {
					String username = userTable.getSelectionModel()
							.getSelectedItem().username.get();

					// TO-DO: RESET PASSWORD.

				/*
				 * IDEA FOR RESET PASSWORD:
				 * 
				 * There is a resetPassword boolean in the database. When this
				 * button is pushed, that field is tripped true. The login
				 * screen checks for the appropriate field, and when the user
				 * attempts to login, they are re-directed to the password reset
				 * screen. The password reset screen will always mark this
				 * resetPassword boolean false after a successful password
				 * changes.
				 * 
				 * Such functionality will also allow users all users passwords
				 * to easily be changed at particular intervals (security).
				 */
			}
		});

		// Call helper method when user toggles this switch.
		bannedCheckBox.setOnAction(event -> {
			checkBannedCheckBoxText();
		});

		// Check roles switching logic handling by calling helper method.
		administratorRoleCheckBox.setOnAction(event -> {
			moderatorRoleCheckBox.setSelected(false);
			userRoleCheckBox.setSelected(false);
		});

		moderatorRoleCheckBox.setOnAction(event -> {
			administratorRoleCheckBox.setSelected(false);
			userRoleCheckBox.setSelected(false);
		});

		userRoleCheckBox.setOnAction(event -> {
			administratorRoleCheckBox.setSelected(false);
			moderatorRoleCheckBox.setSelected(false);
		});

		// Send to game lobby screen.
		cancelButton.setOnAction(event -> {
			MainModel.getModel().currentMainData().getMainController()
					.displayScreen(MainView.GAME_LOBBY_SCREEN);
		});

		// Save changes.
		saveChangesButton.setOnAction(event -> {
			String username = usernameTextField.getText();

			String email = emailTextField.getText();

			int role;
			if (administratorRoleCheckBox.isSelected())
				role = 2;
			else if (moderatorRoleCheckBox.isSelected())
				role = 1;
			else
				role = 0;

			boolean banned = bannedCheckBox.isSelected();

			String bannedReason = bannedReasonTextArea.getText();

			try {
				login = (LogIn) Naming.lookup("//localhost/auth");
				User u = login.getUser(username);
				u.setEmail(email);
				u.setRole(role);
				u.setBannedStatus(banned);
				u.setBannedReason(bannedReason);
				login.save(u);
			} catch (Exception e) {
				System.out.println("Could not retrieve remote object.");
				e.printStackTrace();
			}
			// Since the table isn't bound to the database (for now), we will
			// need to re-initialize the page for the settings to be visible to
			// the user.
				errorLabel.setText("Changes saved.");
				initialize();
			});
	}

	/**
	 * Private helper method to set the text value of bannedCheckBox.
	 */
	private void checkBannedCheckBoxText() {
		if (bannedCheckBox.isSelected())
			bannedCheckBox.setText("Yes");
		else
			bannedCheckBox.setText("No");
	}

	/**
	 * This method will allow for the injection of each screen's parent.
	 */
	public void setScreenParent(MainController screenParent) {
		parentController = screenParent;
	}
}