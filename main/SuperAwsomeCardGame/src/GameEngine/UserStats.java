package GameEngine;
/**
 * An object to interface with the database when viewing user statistics
 * This class will help calculate win ratios, karma values, and other helpful statistics based on a users game interaction.
 * @author Shelbie
 *
 */
public class UserStats {
	final private int gamesPlayed;
	final private int gamesWon;
	final private int totalPoints;
	final private int karmaScore;
	final private int userID;

	public UserStats(int _userID, int _gamesPlayed, int _gamesWon, int _totalPoints, int _karma)
	{
		userID = _userID;
		gamesPlayed = _gamesPlayed;
		gamesWon = _gamesWon;
		totalPoints = _totalPoints;
		karmaScore = _karma;
	}
	
	
	/**
	 * Total number of games played
	 * @return gamesPlayed
	 */
	public int getGamesPlayed()
	{
		return gamesPlayed;
	}
	
	/**
	 * Returns the win loss ratio
	 * @return (# of games won) / ( # of games lost)
	 */
	public double getWinLossRatio()
	{
		return gamesWon/(gamesPlayed-gamesWon);
	}
	
	/**
	 * Returns total points accumulated over all games
	 * @return totalPoints
	 */
	public int getTotalPoints()
	{
		return totalPoints;
	}
	
	/**
	 * Returns average number of points over all games
	 * @return totalPoints/gamesPlayed
	 */
	public double getAveragePoints()
	{
		return totalPoints/gamesPlayed;
	}
	
	/**
	 * TBD once we figure out how to use karma
	 * Will interface with the Feedback table in the database
	 * @return
	 */
	public int getKarma()
	{
		return 0;
	}
	
	/**
	 * Retrieves stats from the database matching the given userID
	 * @param userID
	 * @return corresponding user's statistic information
	 */
	public UserStats getStats(int userID)
	{
		//Developing 2/23 PM
		
	}
}
