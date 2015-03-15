/**
 * @author Anish Kunduru
 * 
 * This program is our handler for GameLobbyScreen.fxml.
 */

package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class GameLobbyScreenController implements ControlledScreen
{
   // So we can set the screen's parent later on.
   MainController parentController;
   
   @FXML
   ImageView handOne;
   
   @FXML 
   ImageView handTwo;

   /**
    * Initializes the controller class. Automatically called after the FXML file has been loaded.
    */
   @FXML
   public void initialize()
   {
      
      handOne.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

           @Override
           public void handle(MouseEvent event) {
               handOne.toFront();
               event.consume();
           }
      });
      
      handTwo.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

           @Override
           public void handle(MouseEvent event) {
               handTwo.toFront();
               event.consume();
           }
      });
   }
   
   public void bringToFront(ActionEvent event){
      handTwo.toFront();
   }

   /**
    * This method will allow for the injection of each screen's parent.
    */
   public void setScreenParent(MainController screenParent)
   {
      parentController = screenParent;
   }
}
