/**
 * @author Anish Kunduru
 *
 * This is the controller of controllers.
 * It's job is to seamlessly manage handoffs between different FXML pages and their respective controllers.
 * 
 * http://docs.oracle.com/javafx/2/api/javafx/fxml/doc-files/introduction_to_fxml.html#nested_controllers
 */

package view;

import java.io.IOException;

import javafx.fxml.*;
import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;

public class MainController extends Application
{
   // Private class vars.
   private Parent root;
   private Scene scene;
   private Stage stage;
   
   public static void main(String[] args)
   {
      launch(args);
   }

   
   @Override
   public void start(Stage stage)
   {
      // Initialize display components.
      scene = new Scene(root);
      this.stage = stage;
      
      // Pin root to scene and display it.
      this.stage.setTitle("Super Awesome Card Game");
      this.stage.setScene(scene);
      this.stage.show();
   }
   
   /**
    * Displays the login screen.
    */
   public void showLoginScreen()
   {
      try
      {
         // Load LoginScreen.fxml
         FXMLLoader fxmlLoader = new FXMLLoader();
         fxmlLoader.setLocation(MainController.class.getResource("LoginScreen.fxml"));
         root = fxmlLoader.load();
      }
      catch (IOException e)
      {
         e.printStackTrace();
      }
   }
}
