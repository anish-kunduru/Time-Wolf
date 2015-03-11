/**
 * @author Anish Kunduru
 * 
 * This program is our handler for DefaultMenuBar.fxml.
 */
package view;

import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;

public class DefaultMenuBarController implements ControlledScreen
{
   // Functional components.
   @FXML
   private MenuItem fileNewGame;
   @FXML
   private MenuItem editProfile;
   @FXML
   private MenuItem helpAbout;
   
   // So we can set the screen's parent later on.
   MainController parentController;
   
   /**
    * Initializes the controller class.
    * Automatically called after the FXML file has been loaded.
    */
   @FXML
   public void initialize()
   {
      
      // Event handlers.
      fileNewGame.setOnAction(event ->
      {
         //parentController.displayScreen(MainView.CREATE_GAME_SCREEN);
      });
      
      editProfile.setOnAction(event ->
      {
         //parentController.displayScreen(MainView.EDIT_PROFILE_SCREEN);
      });
      
      helpAbout.setOnAction(event ->
      {
         //parentController.displayScreen(MainView.ABOUT_SCREEN);
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
