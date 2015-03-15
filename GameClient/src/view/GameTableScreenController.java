/**
 * @author Anish Kunduru
 *
 */

package view;

import GameServer.GameEngine.Hand;
import javafx.fxml.FXML;

public class GameTableScreenController implements ControlledScreen
{
   // Game state data.
   Hand playerHand;
   
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
}
