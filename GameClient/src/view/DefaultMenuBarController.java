/**
 * @author Anish Kunduru
 * 
 * This program is our handler for DefaultMenuBar.fxml.
 */
package view;

import javafx.fxml.FXML;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class DefaultMenuBarController
{
   // Functional components.
   @FXML
   private MenuItem fileNewGame;
   @FXML
   private MenuItem editProfile;
   @FXML
   private MenuItem helpAbout;
   @FXML
   private MenuBar menuBar;

   /**
    * Initializes the controller class. Automatically called after the FXML file has been loaded.
    */
   @FXML
   public void initialize()
   {
      // So that the menubar is the correct length.
      menuBar.prefWidthProperty().bind(MainView.getMainController().widthProperty());
      
      // Event handlers.
      fileNewGame.setOnAction(event ->
      {
         MainView.getMainController().displayScreen(MainView.LOGIN_SCREEN); // TEMP SO THAT WE CAN SET IT TO SOMETHING.
         // MainView.getMainController().displayScreen(MainView.CREATE_GAME_SCREEN);
      });

      editProfile.setOnAction(event ->
      {
         // MainView.getMainController().displayScreen(MainView.EDIT_PROFILE_SCREEN);
      });

      helpAbout.setOnAction(event ->
      {
         // MainView.getMainController().displayScreen(MainView.ABOUT_SCREEN);
      });
   }
}
