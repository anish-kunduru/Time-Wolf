package GameServer.Users;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.Remote;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;

import GameServer.DBHelper;

/**
 * Server side class to handle DB methods involving the User class
 * 
 * @author Shelbie
 *
 */

public class LogIn implements Remote, Serializable {

	/**
	 * Updates a user object in case of new updates
	 */
	private static final long serialVersionUID = 1L;

	public void getLiveUpdate(User u) throws RemoteException, Exception {
		DBHelper dbh = new DBHelper();
		String query = "SELECT * FROM User WHERE Username='" + u.getUsername()
				+ "'";
		ResultSet rs = dbh.executeQuery(query);
		if (rs.first()) {
			u.setBannedStatus(false);
			int bannedBit = rs.getInt("IsBanned");
			if (bannedBit > 0)
				u.setBannedStatus(true);

			if (u.isBanned())
				throw new Exception("This user is banned.");

			u.setID(rs.getInt("ID"));
			u.setUsername("Username");
			u.setEmail(rs.getString("Email"));
			u.setRole(rs.getInt("Role"));
			u.setSecurityQuestion(rs.getString("SecurityQuestion"));
			u.setSecurityAnswer(rs.getString("SecurityAnswer"));
			u.setBannedReason(rs.getString("BannedReason"));
			u.Statistics = initStats(u.getID());
			u.Feedback = getFeedbackList(u.getID());
			Blob blob = rs.getBlob("Avatar");
			if(blob != null)
				u.setImageBytes(blob.getBytes(1, (int) blob.length()));
			u.setLocation(rs.getString("Location"));
			u.setParanoid(rs.getBoolean("Paranoia"));
		} else {
			throw new Exception("There was an error updating the user!");
		}

	}

	/**
	 * Returns the user to be logged in by the given username and password
	 * 
	 * @param username
	 * @param password
	 * @return
	 * @throws Exception
	 */

	public User logIn(String username, String password) throws Exception,
			RemoteException {
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
			u.setRole(rs.getInt("Role"));
			u.setSecurityQuestion(rs.getString("SecurityQuestion"));
			u.setSecurityAnswer(rs.getString("SecurityAnswer"));
			u.setBannedReason(rs.getString("BannedReason"));
			u.Statistics = initStats(u.getID());
			u.Feedback = getFeedbackList(u.getID());
			Blob blob = rs.getBlob("Avatar");
			if(blob != null)
				u.setImageBytes(blob.getBytes(1, (int) blob.length()));
			u.setLocation(rs.getString("Location"));
			u.setParanoid(rs.getBoolean("Paranoia"));

			return u;
		} else {
			throw new Exception("Username or password was incorrect!");
		}
	}

	/**
	 * Returns the user to be logged in by the given username
	 * 
	 * @param username
	 * @return
	 * @throws Exception
	 */

	public User getUser(String username) throws Exception, RemoteException {
		User u = new User();

		DBHelper dbh = new DBHelper();
		String query = "SELECT * FROM User WHERE Username='" + username + "'";
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
			u.setRole(rs.getInt("Role"));
			u.setSecurityQuestion(rs.getString("SecurityQuestion"));
			u.setSecurityAnswer(rs.getString("SecurityAnswer"));
			u.setBannedReason(rs.getString("BannedReason"));
			u.Statistics = initStats(u.getID());
			u.Feedback = getFeedbackList(u.getID());
			Blob blob = rs.getBlob("Avatar");
			if(blob != null)
				u.setImageBytes(blob.getBytes(1, (int) blob.length()));
			u.setLocation(rs.getString("Location"));
			u.setParanoid(rs.getBoolean("Paranoia"));

			return u;
		} else {
			throw new Exception("Username or password was incorrect!");
		}
	}
	
	public String getUsername(int id){
		
		String userName = "";
		DBHelper dbh = new DBHelper();
		String query = "SELECT Username FROM User WHERE ID='" + id + "'";
		ResultSet rs = dbh.executeQuery(query);
		
		try {
			if(rs.first()){
				userName = rs.getString("Username");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return userName;
	}

	/**
	 * Static method to initialize the feedback list for the user class Used
	 * when retrieving a user
	 * 
	 * @param userID
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Feedback> getFeedbackList(int userID) throws SQLException {
		ArrayList<Feedback> fl = new ArrayList<Feedback>();

		DBHelper dbh = new DBHelper();
		String query = "SELECT * FROM Feedback WHERE UserID=" + userID;
		ResultSet rs = dbh.executeQuery(query);
		int ID = 0;
		int uID = 0;
		int byID = 0;
		String desc = "";
		boolean isPos = false;

		while (rs.next()) {
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
	 * 
	 * @param userID
	 *            - UserID to initialize the statistics of
	 * @return the UserStats object corresponding to the given UserID
	 */
	private UserStats initStats(int userID) {
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

	private double GetKarma(int uID) {
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
			if (total > 0) {
				karma = pos / total;
			} else
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
	public boolean doesUsernameExist(String username) throws SQLException,
			RemoteException {
		String query = "SELECT 1 FROM User WHERE Username='" + username + "'";
		DBHelper dbh = new DBHelper();
		ResultSet rs = dbh.executeQuery(query);
		if (rs.first())
			return true;
		else
			return false;
	}

	public User register(String username, String email, String password,
			String question, String answer) throws RemoteException, Exception {
		return register(username, email, password, question, answer, null);
	}

	/**
	 * Update picture
	 * @throws Exception 
	 */
	public void updateImage(String username, File picture) throws Exception
	{
		if (picture != null) {
			try {
				DBHelper dbh = new DBHelper();
				Connection conn = dbh.getConnection();
				conn.setAutoCommit(false);
				String upload_pic = "Update User SET Avatar = ? WHERE Username = ?";
				FileInputStream fis = new FileInputStream(picture);
				java.sql.PreparedStatement ps = conn.prepareStatement(upload_pic);
				ps.setBinaryStream(1, fis, (int)picture.length());
				ps.setString(2, username);
				ps.executeUpdate();
				conn.commit();
				
				
			} catch (Exception ex) {
				throw new Exception("Image save failed!");
			}
		}
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
	public User register(String username, String email, String password,
			String question, String answer, File picture)
			throws RemoteException, Exception {
		User u = new User();
		DBHelper dbh = new DBHelper();

		String query = "INSERT INTO User ";
		query += "(Username, Email, Password, ImagePath, IsBanned, Role, SecurityQuestion, SecurityAnswer, BannedReason)";
		query += "VALUES ('" + username + "','" + email + "','" + password
				+ "','','0','0','" + question + "','" + answer + "','')";

		try {
			dbh.executeUpdate(query);
			query = " SELECT * FROM User WHERE Username='" + username + "'";
			ResultSet rs = dbh.executeQuery(query);
			if (rs.first()) {
				updateImage(username, picture);
				u.setID(rs.getInt("ID"));
				u.setUsername(username);
				u.setEmail(rs.getString("Email"));
				u.setRole(rs.getInt("Role"));
				u.setPassword(rs.getString("Password"));
				u.setBannedReason(rs.getString("BannedReason"));
				Blob blob = rs.getBlob("Avatar");
				if(blob != null)
					u.setImageBytes(blob.getBytes(1, (int) blob.length()));
				u.setLocation(rs.getString("Location"));
				u.setParanoid(rs.getBoolean("Paranoia"));
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

	public void insertFeedback(int userID, int byUserID, String desc,
			boolean isPositive) {
		DBHelper dbh = new DBHelper();
		String query = "INSERT INTO Feedback";
		query += "(UserID, isGood, Comment, ByUserID)";
		query += "VALUES ('" + userID + "','" + isPositive + "','" + desc
				+ "','" + byUserID + "')";
		dbh.executeUpdate(query);
	}

	/**
	 * Returns an ArrayList of all users in the database
	 * 
	 * @return List of all users
	 * @throws SQLException
	 */
	public ArrayList<User> getUserList() throws SQLException, RemoteException {
		ArrayList<User> users = new ArrayList<User>();
		DBHelper dbh = new DBHelper();
		String query = "SELECT * FROM User";
		ResultSet rs = dbh.executeQuery(query);

		while (rs.next()) {
			User u = new User();
			u.setID(rs.getInt("ID"));
			u.setUsername(rs.getString("Username"));
			u.setEmail(rs.getString("Email"));
			u.setRole(rs.getInt("Role"));
			u.setPassword(rs.getString("Password"));
			u.setBannedStatus(rs.getBoolean("IsBanned"));
			u.setBannedReason(rs.getString("BannedReason"));
			u.initStats();
			u.setSecurityQuestion(rs.getString("SecurityQuestion"));
			u.setSecurityAnswer(rs.getString("SecurityAnswer"));
			Blob blob = rs.getBlob("Avatar");
			if(blob != null)
				u.setImageBytes(blob.getBytes(1, (int) blob.length()));
			u.setLocation(rs.getString("Location"));
			u.setParanoid(rs.getBoolean("Paranoia"));
			users.add(u);
		}

		return users;
	}

	public void removeAvatar(String username)
	{
		DBHelper dbh = new DBHelper();
		String query = "UPDATE User SET Avatar=" + null + " WHERE Username='" + username + "'";
		dbh.executeUpdate(query);
	}
	
	/**
	 * Saves all properties of the user passed via parameter
	 * 
	 * @param u
	 *            - user to be saved
	 * @throws Exception 
	 */
	public void save(User u) throws Exception {
		
		DBHelper dbh = new DBHelper();
		String query = "UPDATE User SET ";
		query += "Username='" + u.getUsername() + "'";
		query += ",Email='" + u.getEmail() + "'";
		query += ",ImagePath='" + u.getImagePath() + "'";
		int bit = 0;
		if (u.isBanned())
			bit = 1;
		query += ",IsBanned=" + bit;
		query += ",Role=" + u.getRole();
		query += ",SecurityQuestion='" + u.getSecurityQuestion() + "'";
		query += ",SecurityAnswer='" + u.getSecurityAnswer() + "'";
		query += ",BannedReason='" + u.getBannedReason() + "'";
		query += ",Location='" + u.getLocation() +"'";
		int pBit = 0;
		if(u.isParanoid())
			pBit = 1;
		query+= ",Paranoia=" + pBit;
		query += " WHERE ID=" + u.getID();

		dbh.executeUpdate(query);
		if (u.getImageBytes() != null) {
			try {
				Connection conn = dbh.getConnection();
				conn.setAutoCommit(false);
				String upload_pic = "Update User SET Avatar = ? WHERE Username = ?";
				
				java.sql.PreparedStatement ps = conn.prepareStatement(upload_pic);
				ps.setBinaryStream(1, new ByteArrayInputStream(u.getImageBytes()), u.getImageBytes().length);
				ps.setString(2, u.getUsername());
				ps.executeUpdate();
				conn.commit();
				
				
			} catch (Exception ex) {
				throw new Exception("Image save failed!");
			}
		}
		else{
			query = "UPDATE User SET Avatar=" + null + " WHERE Username='" + u.getUsername() + "'";
			dbh.executeUpdate(query);
		}
		
	}
	
	public void updateSettings(User u) throws Exception {
		DBHelper dbh = new DBHelper();
		String query = "UPDATE User SET ";
		query += "Username='" + u.getUsername() + "'";
		query += ",Email='" + u.getEmail() + "'";
		query += ",ImagePath='" + u.getImagePath() + "'";
		query += ",Location='" + u.getLocation() +"'";
		int pBit = 0;
		if(u.isParanoid())
			pBit = 1;
		query+= ",Paranoia=" + pBit;
		query += " WHERE ID=" + u.getID();

		dbh.executeUpdate(query);
		if (u.getImageBytes() != null) {
			try {
				Connection conn = dbh.getConnection();
				conn.setAutoCommit(false);
				String upload_pic = "Update User SET Avatar = ? WHERE Username = ?";
				
				java.sql.PreparedStatement ps = conn.prepareStatement(upload_pic);
				ps.setBinaryStream(1, new ByteArrayInputStream(u.getImageBytes()), u.getImageBytes().length);
				ps.setString(2, u.getUsername());
				ps.executeUpdate();
				conn.commit();
				
				
			} catch (Exception ex) {
				throw new Exception("Image save failed!");
			}
		}
		else{
			query = "UPDATE User SET Avatar=" + null + " WHERE Username='" + u.getUsername() + "'";
			dbh.executeUpdate(query);
		}
		
	}

	/**
	 * Saves password parameter in database where ID=id
	 * 
	 * @param id
	 *            - user id to be updated
	 * @param newPassword
	 *            - new password to be saved
	 */
	public void resetPassword(int id, String newPassword)
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
	public void resetPassword(String username, String newPassword)
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
	public boolean checkSecurityQuestionAnswer(String username, String answer)
			throws SQLException {
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
	 * 
	 * @param email
	 * @return Username of the account the email parameter belongs to
	 * @throws SQLException
	 */
	public String findUsername(String email) throws SQLException {
		String username;

		DBHelper dbh = new DBHelper();
		String query = "SELECT * FROM User WHERE Email='" + email + "'";
		ResultSet rs = dbh.executeQuery(query);
		if (rs.first())
			username = rs.getString("Username");
		else
			throw new SQLException(); // Throw an exception if we are passed an
										// e-mail not in the table.

		return username;
	}

	/**
	 * Get security question given email
	 * 
	 * @param email
	 * @return Security question that corresponds to the account the email
	 *         belongs to
	 * @throws SQLException
	 */
	public String getSecurityQuestion(String email) throws SQLException {
		String sq = "";
		DBHelper dbh = new DBHelper();
		String query = "SELECT * FROM User WHERE Email='" + email + "'";
		ResultSet rs = dbh.executeQuery(query);
		if (rs.first()) {
			sq = rs.getString("SecurityQuestion");
		}

		return sq;
	}
	
	public ArrayList<User> getLeaderboard() throws SQLException {
		
		ArrayList<User> users = new ArrayList<User>();
		DBHelper dbh = new DBHelper();
		String query = "SELECT UserID FROM Statistics ORDER BY TotalPoints desc";
		ResultSet rs = dbh.executeQuery(query);
		ArrayList<Integer> userIDs = new ArrayList<Integer>();

		while (rs.next()) {
			userIDs.add(rs.getInt("UserID"));
		}
		
		System.out.println(userIDs.get(0));
		System.out.println(userIDs.get(1));
		
		for(int i = 0; i < userIDs.size(); i++){
			String username = getUsername(userIDs.get(i));
			int count = 0;
			try {
				User user = getUser(username);
				users.add(user);
			} catch (Exception e) {
			}
		}
		
		System.out.println(users.get(0).getID());
		System.out.println(users.get(1).getID());

		return users;
		
	}

	/**
	 * Inserts new feedback record into database
	 */
	public void insertFeedback(int userID, int byUserID, boolean isPositive,
			String comment) {
		int bool = 0;
		if (isPositive)
			bool = 1;
		String query = "INSERT INTO Feedback ";
		query += "(UserID, isGood, Comment, ByUserID)";
		query += "VALUES ('" + userID + "','" + bool + "','" + comment + "','"
				+ byUserID + "')";
		DBHelper dbh = new DBHelper();
		dbh.executeUpdate(query);
	}

}