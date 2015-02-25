package GameEngine;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * An object to interface with the database when viewing user statistics This
 * class will help calculate win ratios, karma values, and other helpful
 * statistics based on a users game interaction.
 * 
 * @author Shelbie
 *
 *
 *
 */



public class UserStats {
	private int gamesPlayed;
	private int gamesWon;
	private double totalPoints;
	private int karmaScore;
	private int userID;
	private int ID;
	

	

	public UserStats(int userID) {
		getStats(userID);
	}

	/**
	 * Total number of games played
	 * 
	 * @return gamesPlayed
	 */
	public int getGamesPlayed() {
		return gamesPlayed;
	}

	/**
	 * Returns the win loss ratio
	 * 
	 * @return (# of games won) / ( # of games lost)
	 */
	public double getWinLossRatio() {
		return (double)(gamesWon / (gamesPlayed - gamesWon));
	}

	/**
	 * Returns total points accumulated over all games
	 * 
	 * @return totalPoints
	 */
	public double getTotalPoints() {
		return totalPoints;
	}

	/**
	 * Returns average number of points over all games
	 * 
	 * @return totalPoints/gamesPlayed
	 */
	public double getAveragePoints() {
		return totalPoints / gamesPlayed;
	}

	/**
	 * TBD once we figure out how to use karma Will interface with the Feedback
	 * table in the database
	 * 
	 * @return
	 */
	public int getKarma() {
		return 0;
	}

	/**
	 * Retrieves stats from the database matching the given userID
	 * 
	 * @param userID
	 * @return corresponding user's statistic information
	 * @throws SQLException
	 */
	public void getStats(int userID) {
		try {
			// Developing 2/23 PM
			
			try {   
		         // Load the driver (registers itself)
		         Class.forName ("com.mysql.jdbc.Driver");
		         } 
		      catch (Exception E) {
		            System.err.println ("Unable to load driver.");
		            E.printStackTrace ();
		      } 
			
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://mysql.cs.iastate.edu:3306/db30911", "u30911", "4rv2ucue78");
			String query = "SELECT * FROM Statistics WHERE UserID=" + userID;
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);

			if (rs.first()) // should only be one returned on table is incorrect
			{
				this.gamesPlayed = rs.getInt("TotalGames");
				this.gamesWon = rs.getInt("TotalWins");
				this.totalPoints = rs.getDouble("TotalPoints");
				this.ID = rs.getInt("ID");
				this.userID = userID;
			} else {
				// given userID is not valid
				this.gamesPlayed = 0;
				this.gamesWon = 0;
				this.totalPoints = 0;
				this.ID = 0;
				this.userID = 0;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}


	
}
