/**
* @author Anish Kunduru
 * 
 * This program is our handler for ProfileScreen.fxml.
 */

package profile;

import javafx.fxml.FXML;
import view.ControlledScreen;
import view.MainController;

public class ProfileScreenController implements ControlledScreen
{
   // So we can set the screen's parent later on.
   MainController parentController;
   
   /**
    * Initializes the controller class. Automatically called after the FXML file has been loaded.
    */
   @FXML
   public void initialize()
   {
      // TO-DO: REDIRECT LOGIC.
   }
   
   /**
    * This method will allow for the injection of each screen's parent.
    */
   public void setScreenParent(MainController screenParent)
   {
      parentController = screenParent;
   }
}
