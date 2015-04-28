/**
 * @author Anish Kunduru
 *
 * The purpose of this program is to store game table state data.
 */

package singleton;

public class GameTableData
{
   private int gameID; // The unique gameID as given by the game server.
   private boolean chatEnabled; // Should chat be enabled?
   private int VP[];
   private int cardsInDeck[];
   private String playerNames[];
   
   /**
    * Default constructor to use in singleton.
    */
   public GameTableData()
   {
   }
   
   public void setVP(int vp[]){
	   this.VP = vp;
   }
   
   public int[] getVP(){
	   return this.VP;
   }
   
   public void setCardsInDeck(int[] cards){
	   this.cardsInDeck = cards;
   }
   
   public int[] getCardsInDeck(){
	   return this.cardsInDeck;
   }
   
   public void setPlayerNames(String[] players){
	   this.playerNames = players;
   }
   
   public String[] getPlayerNames(){
	   return this.playerNames;
   }
   
   /**
    * Set the unique gameID as passed by the server so that it can be used in the after game screen.
    * 
    * @param gameID The unique gameID that was given to the client by the server.
    */
   public void setGameID(int gameID)
   {
      this.gameID = gameID;
   }
   
   /**
    * @return The unique gameID that was given to the client when the game was created.
    */
   public int getGameID()
   {
      return gameID;
   }
   
   /**
    * Lets the after game screen know if chat is enabled.
    * 
    * @param isEnabled True if chat should be enabled, false if it shouldn't be enabled.
    */
   public void setChatEnabled(boolean isEnabled)
   {
      chatEnabled = isEnabled;
   }
   
   /**
    * @return True if AfterGameScreenController should have chat enabled; false if it shouldn't.
    */
   public boolean getChatEnabled()
   {
      return chatEnabled;
   }
}
