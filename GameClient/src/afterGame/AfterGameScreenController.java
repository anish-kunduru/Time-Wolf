/**
 * @author Anish Kunduru
 * 
 * This program is our handler for AfterGameScreen.fxml.
 */

package afterGame;

import javafx.fxml.FXML;
import view.ControlledScreen;
import view.MainController;

public class AfterGameScreenController implements ControlledScreen
{
   // Reference to Parent type (to be set by setScreenParent).
   MainController parentController;
   
   /**
    * Called automatically by the linked FXML at program launch via dependency injection.
    */
   @FXML
   public void initialize()
   {
      
   }
   
   /**
    * This method will allow the injection of the Parent.
    */
   @Override
   public void setScreenParent(MainController screenParent)
   {
      parentController = screenParent;
   }

}
