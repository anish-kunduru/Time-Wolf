/**
 * @author Anish Kunduru
 *
 * This program is our handler for SearchGameScreen.fxml.
 */

package searchGame;

import framework.ControlledScreen;
import singleton.MainData;
import singleton.MainModel;
import view.MainController;
import view.MainView;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class SearchGameScreenController implements ControlledScreen
{
   // FXML Components.
   @FXML
   private Button searchButton;

   @FXML
   private TextField minKarmaScoreTextField;

   @FXML
   private ComboBox gameTypeComboBox;
   @FXML
   private ComboBox numberPlayersComboBox;

   @FXML
   private CheckBox chatCheckBox;

   // So we can set the screen's parent later on.
   MainController parentController;

   /**
    * Initializes the controller class. Automatically called after the FXML file has been loaded.
    */
   @FXML
   public void initialize()
   {
      // Set appropriate text if box is unchecked.
      chatCheckBox.setOnAction(event ->
      {
         if (chatCheckBox.isSelected())
            chatCheckBox.setText("On");
         else
            chatCheckBox.setText("Off");
      });

      searchButton.setOnAction(event ->
      {
         // TO-DO: CHECK IF EVERYTHING WAS SELECTED PROPERLY. (VERIFY INPUT)

         // TO-DO: SET SEARCH CRITERA IN GAME LOBBY SINGLETON.

         // Reload and display the lobby screen.
         MainModel.getModel().currentMainData().getMainController().reloadScreen(MainView.GAME_LOBBY_SCREEN, MainView.GAME_LOBBY_SCREEN_FXML);
         parentController.displayScreen(MainView.GAME_LOBBY_SCREEN);
      });
   }

   /**
    * This method will allow for the injection of each screen's parent.
    */
   public void setScreenParent(MainController screenParent)
   {
      parentController = screenParent;
   }
}
