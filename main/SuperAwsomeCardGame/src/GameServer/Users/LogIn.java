package GameServer.Users;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.Remote;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import GameServer.DBHelper;

/**
 * Server side class to handle DB methods involving the User class
 * 
 * @author Shelbie
 *
 */

public class LogIn implements Remote, Serializable {

	/**
    * 
    */
	private static final long serialVersionUID = 1L;

	public static User getLiveUpdate(User u) throws RemoteException, Exception
	{
		return logIn(u.getUsername(), u.getPassword());
	}
	
	/**
	 * Returns the user to be logged in by the given username and password
	 * 
	 * @param username
	 * @param password
	 * @return
	 * @throws Exception
	 */

	public static User logIn(String username, String password)
			throws Exception, RemoteException {
		User u = new User();

		DBHelper dbh = new DBHelper();
		String query = "SELECT * FROM User WHERE Username='" + username
				+ "' AND Password='" + password + "'";
		ResultSet rs = dbh.executeQuery(query);
		if (rs.first()) {
			u.setBannedStatus(false);
			int bannedBit = rs.getInt("IsBanned");
			if (bannedBit > 0)
				u.setBannedStatus(true);

			if (u.isBanned())
				throw new Exception("This user is banned.");

			u.setID(rs.getInt("ID"));
			u.setUsername(username);
			u.setEmail(rs.getString("Email"));
			u.setImagePath(rs.getString("ImagePath"));
			u.setRole(rs.getInt("Role"));
			u.setSecurityQuestion(rs.getString("SecurityQuestion"));
			u.setSecurityAnswer(rs.getString("SecurityAnswer"));
			u.Statistics = initStats(u.getID());
			u.Feedback = initFeedbackList(u.getID());

			return u;
		} else {
			throw new Exception("Username or password was incorrect!");
		}
	}

	/**
	 * Static method to initialize the feedback list for the user class
	 * Used when retrieving a user
	 * @param userID
	 * @return
	 * @throws SQLException
	 */
	private static ArrayList<Feedback> initFeedbackList(int userID) throws SQLException
	{
		ArrayList<Feedback> fl = new ArrayList<Feedback>();
		
		DBHelper dbh = new DBHelper();
		String query = "SELECT * FROM Feedback WHERE UserID=" + userID;
		ResultSet rs = dbh.executeQuery(query);
		int ID = 0;
		int uID = 0;
		int byID = 0;
		String desc = "";
		boolean isPos = false;
		
		while(rs.next())
		{
			ID = rs.getInt("ID");
			uID = rs.getInt("UserID");
			byID = rs.getInt("ByUserID");
			desc = rs.getString("Comment");
			isPos = rs.getBoolean("isGood");
			fl.add(new Feedback(ID, uID, desc, isPos, byID));
		}
		
		
		return fl;
	}
	
	/**
	 * Initializes and returns a UserStats object given a userID
	 * @param userID - UserID to initialize the statistics of
	 * @return the UserStats object corresponding to the given UserID
	 */
	private static UserStats initStats(int userID) {
		try {

			DBHelper dbh = new DBHelper();
			String query = "SELECT * FROM Statistics WHERE UserID=" + userID;
			ResultSet rs = dbh.executeQuery(query);
			int gamesPlayed = 0;
			int gamesWon = 0;
			double totalPoints = 0;
			int id = 0;
			int uID = 0;
			double karma = 0;

			if (rs.first()) // should only be one returned or table is incorrect
			{
				gamesPlayed = rs.getInt("TotalGames");
				gamesWon = rs.getInt("TotalWins");
				totalPoints = rs.getDouble("TotalPoints");
				id = rs.getInt("ID");
				uID = userID;
				karma = GetKarma(uID);
			} else {
				// given userID is not valid
				gamesPlayed = 0;
				gamesWon = 0;
				totalPoints = 0;
				id = 0;
				uID = 0;
				karma = 0;
			}
			UserStats us = new UserStats(id, uID, karma, totalPoints, gamesWon,
					gamesPlayed);
			return us;
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return null;
	}

	private static double GetKarma(int uID) {
		double karma = 0;

		try {
			String query = "SELECT * FROM Feedback WHERE UserID='" + uID + "'";
			DBHelper dbh = new DBHelper();
			ResultSet rs = dbh.executeQuery(query);
			double pos = 0;
			double total = 0;
			while (rs.next()) {
				if (rs.getBoolean("isGood"))
					pos++;
				total++;
			}
			if(total > 0)
			{
				karma = pos / total;
			}
			else
				karma = 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return karma;

	}

	/**
	 * Updates stats in database
	 */
	public void UpdateStats(User u) {
		DBHelper dbh = new DBHelper();
		String query = "UPDATE Statistics SET TotalGames="
				+ u.Statistics.getGamesPlayed() + ",TotalWins="
				+ u.Statistics.getGamesWon() + ",TotalPoints="
				+ u.Statistics.getTotalPoints();
		query = query + " WHERE UserID=" + u.getID();
		dbh.executeUpdate(query);
	}

	/**
	 * Checks for username existance in database
	 * 
	 * @param username
	 *            - username to check for
	 * @return true if username exists, false if it is not taken
	 * @throws SQLException
	 */
	public static boolean doesUsernameExist(String username)
			throws SQLException, RemoteException {
		String query = "SELECT 1 FROM User WHERE Username='" + username + "'";
		DBHelper dbh = new DBHelper();
		ResultSet rs = dbh.executeQuery(query);
		if (rs.first())
			return true;
		else
			return false;
	}

	/**
	 * Registers the current user by username, email, and password
	 * 
	 * @param username
	 * @param email
	 * @param password
	 * @return the user object returned by this registration
	 * @throws Exception
	 */
	public static User register(String username, String email, String password,
			String question, String answer) throws RemoteException, Exception {
		User u = new User();
		DBHelper dbh = new DBHelper();
		String query = "INSERT INTO User ";
		query += "(Username, Email, Password, ImagePath, IsBanned, Role, SecurityQuestion, SecurityAnswer)";
		query += "VALUES ('" + username + "','" + email + "','" + password
				+ "','','0','0','" + question + "','" + answer + "')";

		try {
			dbh.executeUpdate(query);
			query = " SELECT * FROM User WHERE Username='" + username + "'";
			ResultSet rs = dbh.executeQuery(query);
			if (rs.first()) {
				u.setID(rs.getInt("ID"));
				u.setUsername(username);
				u.setEmail(rs.getString("Email"));
				u.setImagePath(rs.getString("ImagePath"));
				u.setRole(rs.getInt("Role"));
				u.setPassword(rs.getString("Password"));
				query = "INSERT INTO Statistics ";
				query += "(UserID,TotalGames,TotalWins,TotalPoints)";
				query += "VALUES ('" + u.getID() + "','0','0','0')";
				dbh.executeUpdate(query);
				u.initStats();
			} else {
				throw new Exception("Insert of new user failed in saveUser()!");
			}
		} catch (Exception ex) {
			throw new Exception("Insert of new user failed!");
		}

		return u;

	}
	
	public static void insertFeedback(int userID, int byUserID, String desc, boolean isPositive)
	{
		DBHelper dbh = new DBHelper();
		String query = "INSERT INTO Feedback";
		query += "(UserID, isGood, Comment, ByUserID)";
		query += "VALUES ('" + userID + "','" + isPositive + "','" + desc + "','" + byUserID + "')";
		dbh.executeUpdate(query);
	}

	/**
	 * Returns an ArrayList of all users in the database
	 * 
	 * @return List of all users
	 * @throws SQLException
	 */
	public static ArrayList<User> getUserList() throws SQLException, RemoteException {
		ArrayList<User> users = new ArrayList<User>();
		DBHelper dbh = new DBHelper();
		String query = "SELECT * FROM User";
		ResultSet rs = dbh.executeQuery(query);

		while (rs.next()) {
			User u = new User();
			u.setID(rs.getInt("ID"));
			u.setUsername(rs.getString("Username"));
			u.setEmail(rs.getString("Email"));
			u.setImagePath(rs.getString("ImagePath"));
			u.setRole(rs.getInt("Role"));
			u.setPassword(rs.getString("Password"));
			u.setBannedStatus(rs.getBoolean("IsBanned"));
			u.initStats();
			u.setSecurityQuestion(rs.getString("SecurityQuestion"));
			u.setSecurityAnswer(rs.getString("SecurityAnswer"));
			users.add(u);
		}

		return users;
	}

	/**
	 * Saves all properties of the user passed via parameter
	 * 
	 * @param u
	 *            - user to be saved
	 */
	public static void save(User u) throws RemoteException {
		DBHelper dbh = new DBHelper();
		String query = "UPDATE User SET ";
		query += "Username='" + u.getUsername() + "'";
		query += ",Email='" + u.getEmail() + "'";
		query += ",Password='" + u.getPassword() + "'";
		query += ",ImagePath='" + u.getImagePath() + "'";
		int bit = 0;
		if (u.isBanned())
			bit = 1;
		query += ",IsBanned=" + bit;
		query += ",Role=" + u.getRole();
		query += ",SecurityQuestion='" + u.getSecurityQuestion() + "'";
		query += ",SecurityAnswer='" + u.getSecurityAnswer() + "'";
		query += " WHERE ID=" + u.getID();

		dbh.executeUpdate(query);
		
	}
	

	/**
	 * Saves password parameter in database where ID=id
	 * 
	 * @param id
	 *            - user id to be updated
	 * @param newPassword
	 *            - new password to be saved
	 */
	public static void resetPassword(int id, String newPassword)
			throws RemoteException {

		DBHelper dbh = new DBHelper();
		String query = "UPDATE User SET Password='" + newPassword
				+ "' WHERE ID=" + id;

		dbh.executeUpdate(query);

		// if ID == 0 then no user is selected
	}

	/**
	 * Overload method for forgot password functionality
	 * 
	 * @param username
	 * @param newPassword
	 * @throws RemoteException
	 */
	public static void resetPassword(String username, String newPassword)
			throws RemoteException {

		DBHelper dbh = new DBHelper();
		String query = "UPDATE User SET Password='" + newPassword
				+ "' WHERE Username='" + username + "'";

		dbh.executeUpdate(query);
	}

	/**
	 * Check security question for given username
	 * 
	 * @param username
	 * @param answer
	 * @return true if answer is correct, false if answer is incorrect
	 * @throws SQLException
	 */
	public static boolean checkSecurityQuestionAnswer(String username,
			String answer) throws SQLException {
		DBHelper dbh = new DBHelper();
		String query = "SELECT * FROM User WHERE Username='" + username
				+ "' AND SecurityAnswer='" + answer + "'";
		ResultSet rs = dbh.executeQuery(query);

		if (rs.first())
			return true;
		else
			return false;
	}

	/**
	 * Return username corresponding to given email
	 * @param email
	 * @return Username of the account the email parameter belongs to
	 * @throws SQLException
	 */
	public static String findUsername(String email) throws SQLException {
		String username;
		
		DBHelper dbh = new DBHelper();
		String query = "SELECT * FROM User WHERE Email='" + email + "'";
		ResultSet rs = dbh.executeQuery(query);
		if(rs.first())
			username = rs.getString("Username");
		else
		   throw new SQLException(); // Throw an exception if we are passed an e-mail not in the table.
		
		return username;
	}
	
	/**
	 * Get security question given email
	 * @param email
	 * @return Security question that corresponds to the account the email belongs to
	 * @throws SQLException
	 */
	public static String getSecurityQuestion(String email) throws SQLException{
		String sq = "";
		DBHelper dbh = new DBHelper();
		String query = "SELECT * FROM User WHERE Email='" + email + "'";
		ResultSet rs = dbh.executeQuery(query);
		if(rs.first())
		{
			sq = rs.getString("SecurityQuestion");
		}

		return sq;
		
	}

	/**
	 * Inserts new feedback record into database
	 */
	public static void insertFeedback(int userID, boolean isPositive,
			String comment) {
		String query = "INSERT INTO Feedback ";
		query += "(UserID, isGood, Comment)";
		query += "VALUES ('" + userID + "','" + isPositive + "','" + comment
				+ "')";
		DBHelper dbh = new DBHelper();
		dbh.executeUpdate(query);
	}

}