/**
 * @author Anish Kunduru
 * 
 * This program is our handler for DefaultMenuBar.fxml.
 */

package menuBar;

import singleton.MainModel;
import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class DefaultMenuBarController
{
   // Functional components.
   @FXML
   private MenuItem fileNewGame;
   @FXML
   private MenuItem fileGameLobby;
   @FXML
   private MenuItem fileSearchGames;
   @FXML
   private MenuItem fileLogOut;
   @FXML
   private MenuItem fileExit;
   @FXML
   private MenuItem editProfile;
   @FXML
   private MenuItem helpAbout;
   @FXML
   private MenuItem helpGameRules;
   @FXML
   private MenuItem adminUserListing;
   @FXML
   private MenuItem fileLeaderboard;

   @FXML
   private Menu administratorMenu;

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

      fileLeaderboard.setOnAction(event ->
      {
         MainModel.getModel().currentMainData().getMainController().goToLeaderboardsScreen();
      });

      fileNewGame.setOnAction(event ->
      {
         MainModel.getModel().currentMainData().getMainController().goToCreateGameScreen();
      });

      fileGameLobby.setOnAction(event ->
      {
         MainModel.getModel().currentMainData().getMainController().goToGameLobbyScreen();
      });

      fileSearchGames.setOnAction(event ->
      {
         MainModel.getModel().currentMainData().getMainController().goToSearchGameScreen();
      });

      fileLogOut.setOnAction(event ->
      {
         MainModel.getModel().currentMainData().getMainController().goToLoginScreen();
      });

      // Exits and terminates the application.
      fileExit.setOnAction(event ->
      {
         MainModel.getModel().currentMainData().getMainStage().close();
      });

      editProfile.setOnAction(event ->
      {
         MainModel.getModel().profileData().setRedirectToClicked(false);
         MainModel.getModel().currentMainData().getMainController().goToProfileScreen();
      });

      helpAbout.setOnAction(event ->
      {
         MainModel.getModel().currentMainData().getMainController().goToAfterGameScreen(); // TODO TEMP ASSIGNMENT FOR TESTING.
      });

      helpGameRules.setOnAction(event ->
      {
         MainModel.getModel().currentMainData().getMainController().goToGameRulesScreen();
      });

      adminUserListing.setOnAction(event ->
      {
         MainModel.getModel().currentMainData().getMainController().goToUserListingScreen();
      });
      
      // Only make the administrator page viewable if an admin is logged in.
      if (MainModel.getModel().currentLoginData().getIsAdmin())
         administratorMenu.setVisible(true);
      else
         administratorMenu.setVisible(false);
   }
}
