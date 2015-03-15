/**
 * @author Anish Kunduru
 *
 * This program is our handler for CreateGameScreen.fxml.
 */

package view;

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
      
   }

   /**
    * This method will allow for the injection of each screen's parent.
    */
   public void setScreenParent(MainController screenParent)
   {
      parentController = screenParent;
   }
}
