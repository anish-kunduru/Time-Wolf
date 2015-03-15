/**
 * @author Anish Kunduru
 * 
 * This program is our handler for GameLobbyScreen.fxml.
 */

package view;

import javafx.fxml.FXML;

public class GameLobbyScreenController implements ControlledScreen
{
   // So we can set the screen's parent later on.
   MainController parentController;

   /**
    * Initializes the controller class. Automatically called after the FXML file has been loaded.
    */
   @FXML
   public void initialize()
   {
   }

   /**
    * This method will allow for the injection of each screen's parent.
    */
   public void setScreenParent(MainController screenParent)
   {
      parentController = screenParent;
   }
   
   
   public MainController getParentController()
   {
      return parentController;
   }
}
