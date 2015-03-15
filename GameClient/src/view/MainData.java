/**
 * @author Anish Kunduru
 *
 * The purpose of this program is to store MainView's application state data.
 */

package view;

public class MainData
{
  
   // Store reference to our mainController for GUI logic.
   private MainController mainController;
   
   /**
    * Default constructor to use in singleton.
    */
   public MainData()
   {
   }
   
   /**
    * Sets the main controller.
    * 
    * @param mainController The main controller.
    */
   public void setMainController(MainController mainController)
   {
      this.mainController = mainController;
   }
   
   /**
    * Gets the main controller
    * 
    * @return A valid MainController object.
    */
   public MainController getMainController()
   {
      return mainController;
   }
}
