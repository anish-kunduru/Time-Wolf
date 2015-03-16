/**
 * @author Anish Kunduru
 *
 * This is the controller of controllers.
 * It's job is to seamlessly manage handoffs between different FXML pages and their respective controllers.
 */

package view;

import java.util.HashMap;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;

public class MainController extends StackPane
{
   // A hash table that contains all the screens.
   private HashMap<String, Node> screens = new HashMap<>();

   /**
    * Implicit super() to StackPane.
    */
   public MainController()
   {
   }

   /**
    * Adds the screen to the collection.
    * 
    * @param screenName The name of the screen.
    * @param screen The screen to add to the collection
    */
   public void addScreen(String screenName, Node screen)
   {
      screens.put(screenName, screen);
   }

   /**
    * Return the Node with the appropriate name.
    * 
    * @param screenName The screen ID that you wish to get.
    * @return The Node with the screenName.
    */
   public Node getScreen(String screenName)
   {
      return screens.get(screenName);
   }

   /**
    * Loads the appropriate FXML and add it to our internal list of screens.
    * 
    * @param screenName The name of the screen that will be used as a screen ID.
    * @param screenLocation The location of the FXML file in the workspace.
    * @return true if it was loaded, false if there was an error.
    */
   public boolean loadScreen(String screenName, String screenLocation)
   {
      try
      {
         // Get the FXML loader and load it.
         FXMLLoader FXMLLoader = new FXMLLoader(getClass().getResource(screenLocation));
         Parent loadScreen = (Parent) FXMLLoader.load();

         // Get controller associated to the screen.
         ControlledScreen screenController = ((ControlledScreen) FXMLLoader.getController());

         // Set the parent for the screen.
         screenController.setScreenParent(this);

         // Add the screen to our hash map.
         addScreen(screenName, loadScreen);

         return true;
      }
      catch (Exception e)
      {
         System.out.println(e.getMessage());
         
         return false;
      }
   }

   /**
    * Display the screen with a predefined name by adding it to the root of the StackPane.
    * 
    * @param stringName
    * @return true is the screen is being displayed, false if hasn't been loaded yet.
    */
   public boolean displayScreen(final String screenName)
   {
      // Check if screen is loaded.
      if (screens.get(screenName) != null)
      {
         // Check if a screen is being displayed.
         if (!getChildren().isEmpty())
         {
            // Remove the displayed screen.
            getChildren().remove(0);

            // Display the screenName.
            getChildren().add(0, screens.get(screenName));
         }
         else
         {
            // There is nothing being displayed, just show screenName.
            getChildren().add(screens.get(screenName));
         }

         return true;
      }
      else
         return false;
   }
}
