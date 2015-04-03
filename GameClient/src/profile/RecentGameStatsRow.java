/**
 * @author Anish Kunduru
 * 
 * This class defines a row in the feedback recent games stats table.
 */

package profile;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class RecentGameStatsRow
{
   // Define our entry types.
   public SimpleBooleanProperty win = new SimpleBooleanProperty();
   public SimpleStringProperty gameType = new SimpleStringProperty();
   public SimpleIntegerProperty pointsWon = new SimpleIntegerProperty();

   // Define auto getters to populate table on initialize.

   /**
    * @return true if this game was a win, false if this game was a loss.
    */
   public boolean getWin()
   {
      return win.get();
   }

   /**
    * @return The String type of game.
    */
   public String getGameTye()
   {
      return gameType.get();
   }

   /**
    * @return The number of points won in this game.
    */
   public int getPointsWon()
   {
      return pointsWon.get();
   }
}
