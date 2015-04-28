/**
 * @author Anish Kunduru
 *
 * The purpose of this program is to store game lobby state data.
 */

package singleton;

import GameServer.IGameManagement;

public class GameLobbyData
{
   // Declare elements necessary to search for the game.
   // (These will need to be called in game lobby's initialization and the page will need to be reloaded on a search call.)
   private IGameManagement gm;
   private int id;
   private int numPlayers;
   private boolean chatEnabled;

   /**
    * Default constructor to use in singleton.
    */
   public GameLobbyData()
   {
   }

   /**
    * @return The number of players that will be in the new game.
    */
   public int getNumPlayers()
   {
      return this.numPlayers;
   }

   /**
    * @param numPlayers To let the GameTableScreenController know how many players will be in the new game (to set the labels).
    */
   public void setNumPlayers(int numPlayers)
   {
      this.numPlayers = numPlayers;
   }
   
   /**
    * @return The gameID that represents the game the client wishes to join.
    */
   public int getID()
   {
      return this.id;
   }
   
   /**
    * Set the game that the client wishes to join so that the GameTableScreenController knows which game the user wants to join.
    * 
    * @param id The gameID as stored in the game table lobby.
    */
   public void setID(int id)
   {
      this.id = id;
   }
   
   /**
    * The GameManagement interface to interface with server-side methods.
    * @return the IGameManagement object.
    */
   public IGameManagement getGameManager()
   {
      return gm;
   }
   
   /**
    * The GameManager interface to communicate to the sever via RMI (so we don't have to keep creating a new connection).
    * @param gm A valid IGameManagement interface object.
    */
   public void setGameManager(IGameManagement gm)
   {
      if (gm instanceof IGameManagement)
         this.gm = gm;
   }
   
   /**
    * Lets the game table know if chat is enabled.
    * 
    * @param isEnabled True if chat should be enabled, false if it shouldn't be enabled.
    */
   public void setChatEnabled(boolean isEnabled)
   {
      chatEnabled = isEnabled;
   }
   
   /**
    * @return True if GameTableScreenController should have chat enabled; false if it shouldn't.
    */
   public boolean getChatEnabled()
   {
      return chatEnabled;
   }
}
