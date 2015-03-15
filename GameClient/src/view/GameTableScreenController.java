/**
 * @author Anish Kunduru
 *
 * This program is our handler for GameTableScreen.fxml.
 */

package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.TextFlow;

public class GameTableScreenController implements ControlledScreen
{
   // Game State Data

   // FXML Components
   @FXML
   private Label cardsInDeckLabel;
   @FXML
   private Label playerTurnLabel;

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
      // Initial cards in deck state.
      // cardsInDeckString.setText("Cards in Deck: NUMCARDS");
      
      // Initial card states.
      playerHandOne.setImage(new Image("cards/bite.png"));
      playerHandTwo.setImage(new Image("cards/claw.png"));
      playerHandThree.setImage(new Image("cards/edison.png"));
      playerHandFour.setImage(new Image("cards/sacrifice.png"));
      playerHandFive.setImage(new Image("cards/genghisKhan.png"));
      
      // Add effects to cards.
      highlightOnMouseEntered(playerHandOne);
      highlightOnMouseEntered(playerHandTwo);
      highlightOnMouseEntered(playerHandThree);
      highlightOnMouseEntered(playerHandFour);
      highlightOnMouseEntered(playerHandFive);
      
      unhighlightOnMouseExited(playerHandOne);
      unhighlightOnMouseExited(playerHandTwo);
      unhighlightOnMouseExited(playerHandThree);
      unhighlightOnMouseExited(playerHandFour);
      unhighlightOnMouseExited(playerHandFive);
      
      // Act as if card was played.
      playerHandOne.setOnMousePressed(event ->
      {
         playerHandOne.setImage(new Image("cards/bury.png"));
      });
   }
   
   /**
    * Helper method associates a listener to each passed image that adds a highlight effect to an image on mouse hover.
    * 
    * @param imageToHighlight The image that you wish to add the highlight effect to.
    */
   private void highlightOnMouseEntered(ImageView imageToHighlight)
   {
      imageToHighlight.setOnMouseEntered(event ->
      {
         // Create ambient light
         Light.Distant light = new Light.Distant();
         light.setAzimuth(-135.0);

         // Create lighting effect
         Lighting lighting = new Lighting();
         lighting.setLight(light);
         lighting.setSurfaceScale(4.0);

         // Apply lighting.
         imageToHighlight.setEffect(lighting);
      });
   }
   
   /**
    * Helper method that removes any effect added to an image when the mouse is no longer hovering over it.
    * Intended to be used with the highlightOnMouseEntered() method.
    * 
    * @param imageToUnhighlight The image that you wish to remove effects from.
    */
   private void unhighlightOnMouseExited(ImageView imageToUnhighlight)
   {
      imageToUnhighlight.setOnMouseExited(event ->
      {
         imageToUnhighlight.setEffect(null);
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
