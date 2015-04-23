/**
 * @author Anish Kunduru
 * 
 * This program is our handler for GameLobbyScreen.fxml.
 */

package gameLobby;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.ArrayList;

import chat.Chat;
import chat.ChatLogBinding;
import GameServer.GameInfo;
import GameServer.IGameManagement;
import userListing.UserRow;
import view.ControlledScreen;
import view.MainController;
import view.MainView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;

public class GameLobbyScreenController implements ControlledScreen {
	// FXML Components

	// Table components.
	private ArrayList<GameInfo> games;
	private ObservableList<LobbyRow> tableData;

	@FXML
	private TableView<LobbyRow> gamesTable;
	@FXML
	private TableColumn nameColumn;
	@FXML
	private TableColumn numPlayersColumn;
	@FXML
	private TableColumn chatColumn;
	@FXML
	private TableColumn privateColumn;

	@FXML
	private Button reloadTableButton;
	@FXML
	private Button joinButton;
	@FXML
	private Button searchButton;
	@FXML
	private Button createButton;

	@FXML
	private TextArea chatBoxTextArea;
	@FXML
	private TextArea chatMessageTextArea;

	// So we can set the screen's parent later on.
	MainController parentController;

	// So that we can call it from different event listeners.
	private Chat chat;
	private IGameManagement gameManagement;

	ChatLogBinding chatLog;

	/**
	 * Initializes the controller class. Automatically called after the FXML
	 * file has been loaded. Calls remote game management object and from that
	 * object it obtains a list of games. It also initializes a chat server.
	 */
	@FXML
	public void initialize() {
		// Initialize gameManagement.
		try {
			gameManagement = (IGameManagement) Naming.lookup("//localhost/game");
		} catch (Exception e) {
			// DEBUG
			System.out.println("Error initializing remote game management object.");
			e.printStackTrace();
		}
		try {
			gameManagement.createGame(2, "Test");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Initialize table
		loadGameTable();

		// TO-DO: REDIRECT LOGIC.
		// Store the information that game table might need in the GameLobbyData
		// singleton... unless you are supposed to pass something to the server,
		// up to you
		// guys...
		// The singleton has already been created, and linked, you just need to
		// define whatever you need/want to store.

		// Event handling for selected row on gamesTable.
		gamesTable.setOnMouseClicked(event -> {
			// Check to make sure something is selected.
				if (gamesTable.getSelectionModel().getSelectedIndex() != -1) {
					// Get the selected name.
					String selectedGame = gamesTable.getSelectionModel().getSelectedItem().name.get();

					// TODO: OKAY, WHAT DO WE WANT TO DO NEXT?
				}
			});

		// Initialize chat elements.
		//chatLog = new ChatLogBinding();
		//chat = new Chat(true, "sample user", -1, chatLog);

		//chatBoxTextArea.textProperty().bind(chatLog.chatLog);

		reloadTableButton.setOnAction(event -> {
			loadGameTable(); // Reload game table.
			});

		joinButton.setOnAction(event -> {
			parentController.displayScreen(MainView.GAME_TABLE_SCREEN);
		});

		searchButton.setOnAction(event -> {
			parentController.displayScreen(MainView.SEARCH_GAME_SCREEN);
		});

		createButton.setOnAction(event -> {
			parentController.displayScreen(MainView.CREATE_GAME_SCREEN);
		});
	}

	/**
	 * To be called by the chat's "Send message" button.
	 */
	public void sendMessage() {
		String outgoingMsg = chatMessageTextArea.getText();

		chat.bufferMessage(outgoingMsg);

		chatMessageTextArea.clear();

		// DEBUG
		System.out.println("Outgoing: " + outgoingMsg);
	}

	/**
	 * A helper method that is called on initialize(), or whenever the user
	 * presses the refreshTableButton.
	 */
	private void loadGameTable() {
		try {
			games = gameManagement.listJoinableGames();

			// Bind table elements to their appropriate values.
			nameColumn.setCellValueFactory(new PropertyValueFactory<UserRow, String>("name"));
			numPlayersColumn.setCellValueFactory(new PropertyValueFactory<UserRow, String>("numberPlayers"));
			chatColumn.setCellValueFactory(new PropertyValueFactory<UserRow, String>("chat"));
			privateColumn.setCellValueFactory(new PropertyValueFactory<UserRow, String>("privateLobby"));

			// Bind the table values.
			tableData = FXCollections.observableArrayList();
			gamesTable.setItems(tableData);

			if (games != null) {
				// Populate the table.
				for (GameInfo currentGame : games) {
					LobbyRow currentEntry = new LobbyRow(); // new row.
					
					// Get number of players currently playing.
					int curPlayers = currentGame.getPlayers().size();
					// Get the total number of players.
					int maxPlayers = currentGame.getNumPlayers();
					
					// Combine number of players.
					String numberPlayers = curPlayers + "/" + maxPlayers;

					currentEntry.name.set(currentGame.getName()); // Set game name.
					currentEntry.numberPlayers.set(numberPlayers); // Set numberPlayers

					// The following are features we can add later if time permits:
					currentEntry.chat.set(true); // Chat will be enabled for all games for now.
					currentEntry.privateLobby.set(false); // All lobbies will be public for now.

					// Add to observableArrayList.
					tableData.add(currentEntry);
				}
				
				
			}
			// Note that this won't work at initial launch because of the way the controller is created... This would be fixed with my revised controller.
			else
				gamesTable.setPlaceholder(new Label("There are no games to join. Create one!"));
		} catch (RemoteException e) {
			System.out.println("There was an error in trying to create the table.");
		}
	}

	/**
	 * For testing to show it works.
	 */
	
	  @FXML private void testAlert() { Alert alert = new
	  Alert(AlertType.INFORMATION); alert.setTitle("Information Dialog");
	  alert.setHeaderText("You must have JDK 8u40 or newer!");
	  
	  alert.showAndWait(); }
	 

	/**
	 * This method will allow for the injection of each screen's parent.
	 */
	public void setScreenParent(MainController screenParent) {
		parentController = screenParent;
	}
}
