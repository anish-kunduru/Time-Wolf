/**
 * Our main GUI program displays our application.
 * 
 * @author Anish Kunduru
 */

package view;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainView extends Application
{

   // Constants that represent the screen names and locations in the workspace.
   public static final String LOGIN_SCREEN = "login";
   public static final String LOGIN_SCREEN_FXML = "LoginScreen.fxml";

   public static final String FORGOT_PASSWORD_SCREEN = "forgotPassword";
   public static final String FORGOT_PASSWORD_SCREEN_FXML = "ForgotPasswordScreen.fxml";

   public static final String REGISTRATION_SCREEN = "registration";
   public static final String REGISTRATION_SCREEN_FXML = "RegistrationScreen.fxml";
   
   public static final String GAME_LOBBY_SCREEN = "gameLobby";
   public static final String GAME_LOBBY_SCREEN_FXML = "GameLobbyScreen.fxml";

   @Override
   public void start(Stage primaryStage)
   {
      // Instantiate MainController and load the screens.
      MainController mainController = new MainController();

      mainController.loadScreen(LOGIN_SCREEN, LOGIN_SCREEN_FXML);
      mainController.loadScreen(REGISTRATION_SCREEN, REGISTRATION_SCREEN_FXML);
      mainController.loadScreen(FORGOT_PASSWORD_SCREEN, FORGOT_PASSWORD_SCREEN_FXML);
      mainController.loadScreen(GAME_LOBBY_SCREEN, GAME_LOBBY_SCREEN_FXML);

      // Display the first screen.
      mainController.displayScreen(LOGIN_SCREEN);

      // Initialize display components.
      Group root = new Group();
      Scene scene = new Scene(root);

      // Add mainController.
      root.getChildren().addAll(mainController);

      // Pin the root to scene and display it.
      primaryStage.setScene(scene);
      primaryStage.show();
   }

   /**
    * This method is actually not used in a correctly deployed JavaFX application. Instead, the start method above is called.
    * This main serves as a fallback in case of improper configuration.
    */
   public static void main(String[] args)
   {
      launch(args);
   }
}
