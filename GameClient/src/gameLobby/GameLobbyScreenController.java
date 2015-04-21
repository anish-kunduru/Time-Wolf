/**
 * @author Anish Kunduru
 * 
 * This program is our handler for GameLobbyScreen.fxml.
 */

package gameLobby;

import java.rmi.Naming;
import java.util.ArrayList;

import chat.Chat;
import GameServer.GameManagement;
import singleton.MainModel;
import view.ControlledScreen;
import view.MainController;
import view.MainView;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;

public class GameLobbyScreenController implements ControlledScreen
{
   // FXML Components

   // Table components.
   private ArrayList<String> games;
   private ObservableList<LobbyRow> tableData;

   @FXML
   private TableView<LobbyRow> gamesTable;
   @FXML
   private TableColumn nameColumn;
   @FXML
   private TableColumn typeColumn;
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
   private static TextArea chatBoxTextArea;
   @FXML
   private TextArea chatMessageTextArea;

   // So we can set the screen's parent later on.
   MainController parentController;

   // So that we can call it from different event listeners.
   private Chat chat;
   private GameManagement gameManagement;

   /**
    * Initializes the controller class. Automatically called after the FXML file has been loaded. Calls remote game management object and from that object it
    * obtains a list of games. It also initializes a chat server.
    */
   @FXML
   public void initialize()
   {
      // Initialize gameManagement.
      try
      {
         //gameManagement = (GameManagement) Naming.lookup("//localhost/game");

      }
      catch (Exception e)
      {
         // DEBUG
         System.out.println("Error initializing remote game management object.");
         e.printStackTrace();
      }

      // Initialize table
      //loadGameTable();

      // TO-DO: REDIRECT LOGIC.
      // Store the information that game table might need in the GameLobbyData singleton... unless you are supposed to pass something to the server, up to you
      // guys...
      // The singleton has already been created, and linked, you just need to define whatever you need/want to store.
      
      // Event handling for selected row on gamesTable.
      gamesTable.setOnMouseClicked(event ->
      {
         // Check to make sure something is selected.
         if (gamesTable.getSelectionModel().getSelectedIndex() != -1)
         {
            // Get the selected name.
            String selectedGame = gamesTable.getSelectionModel().getSelectedItem().name.get();
            
            // TODO: OKAY, WHAT DO WE WANT TO DO NEXT?
         }
      });

      // TO-DO: INITALIZE CHAT.
      //chat = new Chat(true, MainModel.getModel().currentLoginData().getUsername(), -1); // chatroomID = -1, because main lobby.

      reloadTableButton.setOnAction(event ->
      {
         loadGameTable(); // Reload game table.
      });

      joinButton.setOnAction(event ->
      {
         parentController.displayScreen(MainView.GAME_TABLE_SCREEN);
      });

      searchButton.setOnAction(event ->
      {
         parentController.displayScreen(MainView.SEARCH_GAME_SCREEN);
      });

      createButton.setOnAction(event ->
      {
         parentController.displayScreen(MainView.CREATE_GAME_SCREEN);
      });
   }
   
   /**
    * Will append an incoming message to the chat message box.
    * @param message The message that you wish to append.
    */
   public static void appendChatMessage(String message)
   {
      chatBoxTextArea.appendText("> " + message);
   }
   
   /**
    * To be called by the chat's "Send message" button.
    */
   public void sendMessage()
   {
      chat.bufferMessage(chatMessageTextArea.getText());
   }

   /**
    * A helper method that is called on initialize(), or whenever the user presses the refreshTableButton.
    */
   private void loadGameTable()
   {
      games = gameManagement.listJoinableGames();

      // Populate the table.
      for (int i = 0; i < games.size(); i++)
      {
         LobbyRow currentEntry = new LobbyRow(); // new row.

         String currentGame = games.get(i); // Get index in ArrayList.

         currentEntry.name.set(currentGame); // Set game name.
         currentEntry.type.set("gameType"); // Set game type.
         currentEntry.numberPlayers.set(2); // Set numberPlayers

         // The following are features we can add later if time permits:
         currentEntry.chat.set(true); // Chat will be enabled for all games for now.
         currentEntry.privateLobby.set(false); // All lobbies will be public for now.

         // Add to observableArrayList.
         tableData.add(currentEntry);
      }
   }

   /**
    * This method will allow for the injection of each screen's parent.
    */
   public void setScreenParent(MainController screenParent)
   {
      parentController = screenParent;
   }
}