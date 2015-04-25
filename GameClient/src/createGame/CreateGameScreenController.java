/**
 * @author Anish Kunduru
 *
 * This program is our handler for CreateGameScreen.fxml.
 */

package createGame;

import framework.AbstractScreenController;
import framework.ControlledScreen;
import view.MainController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class CreateGameScreenController implements ControlledScreen
{
   // FXML Components.
   @FXML
   private Button createGameButton;

   @FXML
   private TextField lobbyNameTextField;
   @FXML
   private TextField minKarmaScoreTextField;
   @FXML
   private TextField passwordTextField;

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

      createGameButton.setOnAction(event ->
      {
         // TO-DO: CHECK IF EVERYTHING WAS SELECTED PROPERLY. (VERIFY INPUT)
         // TO-DO: CREATE THE GAME.

         parentController.goToGameTableScreen();
      });
   }

   /**
    * This method will allow for the injection of each screen's parent.
    */
   @Override
   public void setScreenParent(AbstractScreenController screenParent)
   {
      parentController = (MainController) screenParent;
   }
}
