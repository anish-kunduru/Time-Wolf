/**
 * Our main GUI program displays our application.
 * 
 * @author Anish Kunduru
 */

package view;

import singleton.MainModel;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainView extends Application
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
   
   @Override
   public void start(Stage primaryStage)
   {
      // Instantiate MainController and load the screens.
      MainController mainController = new MainController();

      mainController.loadScreen(LOGIN_SCREEN, LOGIN_SCREEN_FXML);
      mainController.loadScreen(REGISTRATION_SCREEN, REGISTRATION_SCREEN_FXML);
      mainController.loadScreen(FORGOT_PASSWORD_SCREEN, FORGOT_PASSWORD_SCREEN_FXML);
      mainController.loadScreen(CREATE_GAME_SCREEN, CREATE_GAME_SCREEN_FXML);
      mainController.loadScreen(SEARCH_GAME_SCREEN, SEARCH_GAME_SCREEN_FXML);
      mainController.loadScreen(GAME_TABLE_SCREEN, GAME_TABLE_SCREEN_FXML);
      mainController.loadScreen(GAME_RULES_SCREEN, GAME_RULES_SCREEN_FXML);
      mainController.loadScreen(USER_LISTING_SCREEN, USER_LISTING_SCREEN_FXML);
      mainController.loadScreen(PROFILE_SCREEN, PROFILE_SCREEN_FXML);
      mainController.loadScreen(AFTER_GAME_SCREEN, AFTER_GAME_SCREEN_FXML);
      mainController.loadScreen(GAME_LOBBY_SCREEN, GAME_LOBBY_SCREEN_FXML);
      
      // Add the controller to the singleton.
      MainModel.getModel().currentMainData().setMainController(mainController);

      // Display the first screen.
      mainController.displayScreen(LOGIN_SCREEN);

      // Initialize display components.
      Group root = new Group();
      Scene scene = new Scene(root);

      // Add mainController.
      root.getChildren().addAll(mainController);
      
      // Since our menuBar is a fixed size for now.
      primaryStage.setResizable(false);
      primaryStage.sizeToScene();

      // Pin the root to scene and display it.
      primaryStage.setScene(scene);
      primaryStage.show();
      
      // Set the title of the application.
      primaryStage.setTitle("Time Wolf");
      
      // Add the stage to the singleton.
      MainModel.getModel().currentMainData().setMainStage(primaryStage);
   }

   /**
    * This method is actually not used in a correctly deployed JavaFX application. Instead, the start method above is called. This main serves as a fallback in
    * case of improper configuration.
    */
   public static void main(String[] args)
   {
      launch(args);
   }
}
