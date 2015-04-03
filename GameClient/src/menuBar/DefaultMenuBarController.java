/**
 * @author Anish Kunduru
 * 
 * This program is our handler for DefaultMenuBar.fxml.
 */

package menuBar;

import singleton.MainModel;
import view.MainView;
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
      fileNewGame.setOnAction(event ->
      {
         MainModel.getModel().currentMainData().getMainController().displayScreen(MainView.CREATE_GAME_SCREEN);
      });
      
      fileGameLobby.setOnAction(event ->
      {
         MainModel.getModel().currentMainData().getMainController().displayScreen(MainView.GAME_LOBBY_SCREEN);
      });
      
      fileSearchGames.setOnAction(event ->
      {
         MainModel.getModel().currentMainData().getMainController().displayScreen(MainView.SEARCH_GAME_SCREEN);
      }); 
      
      fileLogOut.setOnAction(event ->
      {
         MainModel.getModel().currentMainData().getMainController().displayScreen(MainView.LOGIN_SCREEN);
      });
      
      // Exits and terminates the application.
      fileExit.setOnAction(event ->
      {
         MainModel.getModel().currentMainData().getMainStage().close();
      });

      editProfile.setOnAction(event ->
      {
         MainModel.getModel().currentMainData().getMainController().reloadScreen(MainView.PROFILE_SCREEN, MainView.PROFILE_SCREEN_FXML);
         MainModel.getModel().currentMainData().getMainController().displayScreen(MainView.PROFILE_SCREEN);
      });

      helpAbout.setOnAction(event ->
      {
         // MainModel.getModel().currentMainData().getMainController().displayScreen(MainView.ABOUT_SCREEN);
      });
      
      helpGameRules.setOnAction(event ->
      {
         MainModel.getModel().currentMainData().getMainController().displayScreen(MainView.GAME_RULES_SCREEN);
      });
      
      adminUserListing.setOnAction(event ->
      {
         MainModel.getModel().currentMainData().getMainController().displayScreen(MainView.USER_LISTING_SCREEN);
      });
      
      menuBar.setOnMouseEntered(event ->
      {
         if (MainModel.getModel().currentLoginData().getIsAdmin())
            administratorMenu.setVisible(true);
         else
            administratorMenu.setVisible(false);
      });
   }
}
