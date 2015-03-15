/**
 * @author Anish Kunduru
 *
 * The purpose of this program is to store MainView's application state data.
 */

package view;

public class MainData
{
  
   // Login information.
   private MainController mainController;
   
   /**
    * Default constructor to use in singleton.
    */
   public MainData()
   {
   }
   
   public void setMainController(MainController mainController)
   {
      this.mainController = mainController;
   }
   
   public MainController getMainController()
   {
      return mainController;
   }
}
