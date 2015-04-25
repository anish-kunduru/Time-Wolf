/**
 * This is the controller of controllers.
 * Its job is to seamlessly manage handoffs between deifferent FXML pages and their respective controllers.
 * 
 * We create this by simply implementing the AbstractScreenController we created earlier (easy enough).
 */

package view;

import singleton.MainModel;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.scene.Parent;
import javafx.util.Duration;
import login.LoginScreenController;
import framework.AbstractScreenController;
import framework.ControlledScreen;

public class MainController extends AbstractScreenController
{
// Constants that represent the screen names and locations in the workspace.
   public static final String LOGIN_SCREEN = "login";
   public static final String LOGIN_SCREEN_FXML = "/login/LoginScreen.fxml";

   public static final String FORGOT_PASSWORD_SCREEN = "forgotPassword";
   public static final String FORGOT_PASSWORD_SCREEN_FXML = "/forgotPassword/ForgotPasswordScreen.fxml";

   public static final String REGISTRATION_SCREEN = "registration";
   public static final String REGISTRATION_SCREEN_FXML = "/registration/RegistrationScreen.fxml";

   public static final String GAME_LOBBY_SCREEN = "gameLobby";
   public static final String GAME_LOBBY_SCREEN_FXML = "/gameLobby/GameLobbyScreen.fxml";

   public static final String GAME_TABLE_SCREEN = "gameTable";
   public static final String GAME_TABLE_SCREEN_FXML = "/gameTable/GameTableScreen.fxml";

   public static final String CREATE_GAME_SCREEN = "createGame";
   public static final String CREATE_GAME_SCREEN_FXML = "/createGame/CreateGameScreen.fxml";

   public static final String SEARCH_GAME_SCREEN = "searchGame";
   public static final String SEARCH_GAME_SCREEN_FXML = "/searchGame/SearchGameScreen.fxml";

   public static final String GAME_RULES_SCREEN = "gameRules";
   public static final String GAME_RULES_SCREEN_FXML = "/gameRules/GameRulesScreen.fxml";

   public static final String USER_LISTING_SCREEN = "userListing";
   public static final String USER_LISTING_SCREEN_FXML = "/userListing/UserListingScreen.fxml";

   public static final String PROFILE_SCREEN = "profileScreen";
   public static final String PROFILE_SCREEN_FXML = "/profile/ProfileScreen.fxml";

   public static final String AFTER_GAME_SCREEN = "afterGameScreen";
   public static final String AFTER_GAME_SCREEN_FXML = "/afterGame/AfterGameScreen.fxml";

   public static final String LEADERBOARDS_SCREEN = "leaderboards";
   public static final String LEADERBOARDS_SCREEN_FXML = "/leaderboards/LeaderboardsScreen.fxml";

   
   /**
    * Method so that we can dynamically load screens and go them at run time.
    * A similar method will need to be created for each screen we create.
    */
   public void goToLoginScreen()
   {
      try
      {
         LoginScreenController loginController = (LoginScreenController) loadScreen("/login/LoginScreen.fxml");
         MainModel.getModel().currentControllerData().setCurrentController(LOGIN);
         MainModel.getModel().currentControllerData().setLoginScreenController(loginController);
      }
      catch (Exception e)
      {
         // DEBUG
         System.out.println("Epic fail trying to load login.\n" + e.getMessage());
         e.printStackTrace();
      }
   }
   
   public void goToGameBoardScreen()
   {
      try
      {
         GameBoardScreenController boardController = (GameBoardScreenController) loadScreen("/gameBoard/GameBoardScreen.fxml");
         MainModel.getModel().currentControllerData().setCurrentController(GAME_BOARD);
         MainModel.getModel().currentControllerData().setGameBoardScreenController(boardController);
      }
      catch (Exception e)
      {
         // DEBUG
         System.out.println("Error trying to load the game controller.");
         e.printStackTrace();
      }
   }
   
   /**
    * Make sure the current page gets to destroy its elements if it has something to destroy.
    * This will be called by the main application Stage.
    */
   public void closeApplication()
   {
      if (destroyableController != null)
         destroyableController.onDestroy();
   }
   
   /**
    * Replaces the page.
    * We will override this when we extend this class because we are fancy and want style (animations).
    * 
    * @param loadScreen The page that you wish to display.
    */
   @Override
   protected void displayPage(final Parent screen)
   {
      // For transition effects.
      final DoubleProperty opacity = opacityProperty();
      
      // Check if a screen is being displayed.
      if (!getChildren().isEmpty())
      {
         Timeline fade = new Timeline(new KeyFrame(Duration.ZERO, new KeyValue(opacity, 1.0)), new KeyFrame(Duration.millis(500), action ->
         {
            // Remove the displayed screen.
            getChildren().remove(0);

            // Display the passed screen.
            getChildren().add(0, screen);
            
            Timeline fadeIn = new Timeline(new KeyFrame(Duration.ZERO, new KeyValue(opacity, 0.0)), new KeyFrame(Duration.millis(400), new KeyValue(opacity, 1.0)));
            
            fadeIn.play();
         }, new KeyValue(opacity, 0.0))); 
         
         fade.play();
         
      }
      else
      {
         setOpacity(0.0);
         
         // There is nothing being displayed, just show the passed screen.
         getChildren().add(screen);
         
         Timeline fadeIn = new Timeline(new KeyFrame(Duration.ZERO, new KeyValue(opacity, 0.0)), new KeyFrame(Duration.millis(1250), new KeyValue(opacity, 1.0)));
         
         fadeIn.play();
      }
   }
}