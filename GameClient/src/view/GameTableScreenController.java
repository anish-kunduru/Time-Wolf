/**
 * @author Anish Kunduru
 *
 * This program is our handler for GameTableScreen.fxml.
 */

package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.TextFlow;

public class GameTableScreenController implements ControlledScreen
{
   // Game State Data

   // FXML Components
   @FXML
   private Label cardsInDeckString;
   @FXML
   private Label playerTurnString;

   @FXML
   private ImageView playerHandOne;
   @FXML
   private ImageView playerHandTwo;
   @FXML
   private ImageView playerHandThree;
   @FXML
   private ImageView playerHandFour;
   @FXML
   private ImageView playerHandFive;

   @FXML
   private ImageView lastDiscardImage;

   @FXML
   private TextFlow playLog;

   // So we can set the screen's parent later on.
   MainController parentController;

   /**
    * Initializes the controller class. Automatically called after the FXML file has been loaded.
    */
   @FXML
   public void initialize()
   {
      playerHandOne.setImage(new Image("cards/bite.png"));
      // cardsInDeckString.setText("Cards in Deck: NUMCARDS");

      playerHandOne.setOnMousePressed(event ->
      {
         playerHandOne.setImage(new Image("cards/bury.png"));
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
