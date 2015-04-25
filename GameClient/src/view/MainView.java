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
      mainController.loadScreen(LEADERBOARDS_SCREEN, LEADERBOARDS_SCREEN_FXML);

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
