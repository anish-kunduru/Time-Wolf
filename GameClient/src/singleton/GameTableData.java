/**
 * @author Anish Kunduru
 *
 * The purpose of this program is to store game table state data.
 */

package singleton;

public class GameTableData
{
   private int gameID; // The unique gameID as given by the game server.
   
   /**
    * Default constructor to use in singleton.
    */
   public GameTableData()
   {
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
}
