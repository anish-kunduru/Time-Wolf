/**
 * @author Anish Kunduru
 * 
 * This program is our handler for GameLobbyScreen.fxml.
 */

package gameLobby;

import view.ControlledScreen;
import view.MainController;
import view.MainView;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class GameLobbyScreenController implements ControlledScreen
{
   // FXML Components
   @FXML
   private Button joinButton;
   @FXML
   private Button searchButton;
   @FXML
   private Button createButton;
   
   // So we can set the screen's parent later on.
   MainController parentController;

   /**
    * Initializes the controller class. Automatically called after the FXML file has been loaded. Calls remote game 
    * management object and from that object it obtains a list of games. It also initializes a chat server. 
    */
   @FXML
   public void initialize()
   {
      // TO-DO: REDIRECT LOGIC.
      
      // TO-DO: INITIALIZE TABLE COMPONENTS.
      
      // TO-DO: INITALIZE CHAT.
      
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
    * This method will allow for the injection of each screen's parent.
    */
   public void setScreenParent(MainController screenParent)
   {
      parentController = screenParent;
   }
}