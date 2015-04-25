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
