/**
 * @author Anish Kunduru
 *
 * The purpose of this program is to store game lobby state data (so that the search feature works as intended).
 */

package singleton;

import GameServer.IGameManagement;

public class GameLobbyData
{
   // Declare elements necessary to search for the game.
   // (These will need to be called in game lobby's initialization and the page will need to be reloaded on a search call.)

	private IGameManagement gm;
	private int id;
   /**
    * Default constructor to use in singleton.
    */
   public GameLobbyData()
   {
   }
   
   public int getID(){
	   return this.id;
   }
   
   public void setID(int id){
	   this.id = id;
   }
   
   public IGameManagement getGameManager()
   {
	   return gm;
   }
   
   public void setGameManager(IGameManagement gm)
   {
	   this.gm = gm;
   }
   
}
