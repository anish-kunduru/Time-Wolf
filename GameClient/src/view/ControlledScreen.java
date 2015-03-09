/**
 * @author Anish Kunduru
 * 
 * This interface needs to be implemented by all the screen controllers so that we can set multiple screens seamlessly.
 */

package view;

public interface ControlledScreen
{
   /**
    * This method will allow us to pull the parent screen.
    * 
    * @param screenParent The parent controller.
    */
   public void setScreenParent(MainController screenParent);
}
