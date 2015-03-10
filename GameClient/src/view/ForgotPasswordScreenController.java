/**
 * @author Anish Kunduru
 * 
 * This program is our handler for RegistrationScreen.fxml.
 */

package view;

public class ForgotPasswordScreenController implements ControlledScreen
{
   // So we can set the screen's parent later on.
   MainController parentController;
   
   
   /**
    * This method will allow for the injection of each screen's parent.
    */
   public void setScreenParent(MainController screenParent)
   {
      parentController = screenParent;
   }
}
