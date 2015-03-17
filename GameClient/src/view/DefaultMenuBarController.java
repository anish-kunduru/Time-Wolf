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
   private MenuItem helpGameRules;
   
   @FXML
   private MenuBar menuBar;

   /**
    * Initializes the controller class. Automatically called after the FXML file has been loaded.
    */
   @FXML
   public void initialize()
   {
      // So that the menubar is the correct length. CURRENTLY NOT WORKING, TO FIGURE OUT LATER.
      // MainController mainController = MainModel.getModel().currentMainData().getMainController();
      // menuBar.prefWidth(mainController.getWidth());
      // menuBar.prefWidthProperty().bind(MainModel.getModel().currentMainData().getMainController().widthProperty());

      // Event handlers.
      fileNewGame.setOnAction(event ->
      {
         MainModel.getModel().currentMainData().getMainController().displayScreen(MainView.CREATE_GAME_SCREEN);
      });

      editProfile.setOnAction(event ->
      {
         // MainModel.getModel().currentMainData().getMainController().displayScreen(MainView.EDIT_PROFILE_SCREEN);
      });

      helpAbout.setOnAction(event ->
      {
         // MainModel.getModel().currentMainData().getMainController().displayScreen(MainView.ABOUT_SCREEN);
      });
      
      helpGameRules.setOnAction(event ->
      {
         MainModel.getModel().currentMainData().getMainController().displayScreen(MainView.GAME_RULES_SCREEN);
      });
      
   }
}
