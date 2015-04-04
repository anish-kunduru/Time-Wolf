/**
 * @author Anish Kunduru
 *
 * This class defines a row in the game lobby listing table.
 */

package gameLobby;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class LobbyRow
{
   // Define entry types.
   public SimpleStringProperty name = new SimpleStringProperty(); // Lobby name.
   public SimpleStringProperty type = new SimpleStringProperty(); // Game type.
   public SimpleIntegerProperty numberPlayers = new SimpleIntegerProperty(); // Number allowed players in game.
   public SimpleBooleanProperty chat = new SimpleBooleanProperty(); // Chat enabled in game?
   public SimpleBooleanProperty privateLobby = new SimpleBooleanProperty(); // Public lobby?
   
   // Auto load getters for "automagic" initialization of rows.
   
   /**
    * @return The name of the game as declared by the user at game creation.
    */
   public String getName()
   {
      return name.get();
   }
   
   /**
    * @return The String description of the type of game that it is.
    */
   public String getType()
   {
      return type.get();
   }
   
   /**
    * @return Get the maximum number of allowed players for this game.
    */
   public int getNumberPlayers()
   {
      return numberPlayers.get();
   }
   
   /**
    * @return true if chat is enabled for this game, false if it has been disabled.
    */
   public boolean getChat()
   {
      return chat.get();
   }
   
   /**
    * @return true if a private lobby, false if a public lobby.
    */
   public boolean getPrivateLobby()
   {
      return privateLobby.get();
   }
}
