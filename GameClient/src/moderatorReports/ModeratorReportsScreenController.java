/**
 * @author Anish Kunduru
 * 
 * This program is our handler for ModeratorReports.fxml.
 */

package moderatorReports;

import view.MainController;
import framework.AbstractScreenController;
import framework.ControlledScreen;

public class ModeratorReportsScreenController implements ControlledScreen
{
   // So we can set the screen's parent later on.
   MainController parentController;

   /**
    * This method will allow for the injection of each screen's parent.
    */
   @Override
   public void setScreenParent(AbstractScreenController screenParent)
   {
      parentController = (MainController) screenParent;
   }
}
