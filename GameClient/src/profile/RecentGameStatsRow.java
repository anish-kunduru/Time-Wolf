/**
 * @author Anish Kunduru
 * 
 * This class defines a row in the feedback recent games stats table.
 */

package profile;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class RecentGameStatsRow
{
   // Define our entry types.
   public SimpleIntegerProperty gamesPlayed = new SimpleIntegerProperty();
   public SimpleIntegerProperty gamesWon = new SimpleIntegerProperty();
   public SimpleDoubleProperty winLossRatio = new SimpleDoubleProperty();
   public SimpleDoubleProperty totalPoints = new SimpleDoubleProperty();
   public SimpleDoubleProperty avgPoints = new SimpleDoubleProperty();
   public SimpleDoubleProperty karma = new SimpleDoubleProperty();

   // Define auto getters to populate table on initialize.

   /**
    * @return number of games played
    */
   public int getGamesPlayed()
   {
      return gamesPlayed.get();
   }

   /**
    * @return number of games won
    */
   public int getGamesWon()
   {
      return gamesWon.get();
   }

   /**
    * @return the win/loss ratio 
    */
   public double getWinLossRatio()
   {
      return winLossRatio.get();
   }
   
   /**
    * @return total points won
    */
   public double getTotalPoints(){
	   return totalPoints.get();
   }
   
   /**
    * @return avg points won per game
    */
   public double getAvgPoints(){
	   return avgPoints.get();
   }
   
   /**
    * @return user's karma score
    */
   public double getKarma(){
	   return karma.get();
   }
}
