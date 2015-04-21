/**
 * @author Anish Kunduru
 * 
 * This program is our handler for AfterGameScreen.fxml.
 */

package afterGame;


import GameServer.GameEngine.AfterGameInfo;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import view.ControlledScreen;
import view.MainController;

public class AfterGameScreenController implements ControlledScreen
{
   // Reference to Parent type (to be set by setScreenParent).
   MainController parentController;
   
   @FXML
   private Label rankPlayerOne;
   @FXML
   private Label rankPlayerTwo;
   @FXML
   private Label rankPlayerThree;
   @FXML
   private Label rankPlayerFour;
   
   @FXML
   private Label namePlayerOne;
   @FXML
   private Label namePlayerTwo;
   @FXML
   private Label namePlayerThree;
   @FXML
   private Label namePlayerFour;
   
   @FXML
   private Label vpPlayerOne;
   @FXML
   private Label vpPlayerTwo;
   @FXML
   private Label vpPlayerThree;
   @FXML
   private Label vpPlayerFour;
   
   @FXML
   private Label deckPlayerOne;
   @FXML
   private Label deckPlayerTwo;
   @FXML
   private Label deckPlayerThree;
   @FXML
   private Label deckPlayerFour;
   
   @FXML
   private Label[] rankList;
   @FXML
   private Label[] nameList;
   @FXML
   private Label[] vpList;
   @FXML
   private Label[] deckList;
   
   /**
    * Called automatically by the linked FXML at program launch via dependency injection.
    */
   @FXML
   public void initialize()
   {
      rankList = new Label[] {rankPlayerOne, rankPlayerTwo, rankPlayerThree, rankPlayerFour };
      nameList = new Label[] {namePlayerOne, namePlayerTwo, namePlayerThree, namePlayerFour };
      vpList = new Label[] {vpPlayerOne, vpPlayerTwo, vpPlayerThree, vpPlayerFour };
      deckList = new Label[] {deckPlayerOne, deckPlayerTwo, deckPlayerThree, deckPlayerFour };
      
      //This part is for testing purposes only
      AfterGameInfo playerOne = new AfterGameInfo("Player One Test", 1, 50, 100);
      AfterGameInfo playerTwo = new AfterGameInfo("Player Two Test", 2, 40, 80);
      
      AfterGameInfo[] playerArray = new AfterGameInfo[] {playerOne, playerTwo};
      
      setAfterGameInfo(playerArray);
   }
   
   public void setAfterGameInfo(AfterGameInfo[] afterGameInfo){
	   for(int i = 0; i < afterGameInfo.length; i++){
		   rankList[i].setText("" + afterGameInfo[i].getRank());
		   nameList[i].setText(afterGameInfo[i].getName());
		   vpList[i].setText("" + afterGameInfo[i].getVP());
		   deckList[i].setText("" + afterGameInfo[i].getNumDeck());
	   }
   }
   
   /**
    * This method will allow the injection of the Parent.
    */
   @Override
   public void setScreenParent(MainController screenParent)
   {
      parentController = screenParent;
   }

}
