package GameEngine;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class LogIn {

/**
 * Returns the user to be logged in by the given username and password
 * @param username
 * @param password
 * @return
 * @throws Exception
 */
	public static User logIn(String username, String password) throws Exception {
		User u = new User();
		
		DBHelper dbh = new DBHelper();
		String query = "SELECT * FROM User WHERE Username='" + username + "' AND Password='"
				+ password + "'";
		ResultSet rs = dbh.executeQuery(query);
		if (rs.first()) {
			u.setBannedStatus(false);
			int bannedBit = rs.getInt("IsBanned");
			if (bannedBit > 0)
				u.setBannedStatus(true);
			
			if(u.isBanned())
				throw new Exception("This user is banned.");
			
			u.setID(rs.getInt("ID"));
			u.setUsername(username);
			u.setEmail(rs.getString("Email"));
			u.setImagePath(rs.getString("ImagePath"));
			u.setRole(rs.getInt("Role"));
			u.initStats();

			return u;
		}
		else{
			throw new Exception("Username or password was incorrect!");
		}
	}
	
	/**
	 * Registers the current user by username, email, and password
	 * @param username
	 * @param email
	 * @param password
	 * @return the user object returned by this registration
	 * @throws Exception
	 */
	public static User register(String username, String email, String password) throws Exception
	{
		User u = new User();
		DBHelper dbh = new DBHelper();
		String query = "INSERT INTO User ";
		query += "(Username, Email, Password, ImagePath, IsBanned, Role)";
		query += "VALUES ('" + username + "','" + email + "','" + password + "','','0','0')";

		try{
			dbh.executeUpdate(query);
		}
		catch(Exception ex)
		{
			throw new Exception("This username already exists!");
		}
		
		query = " SELECT * FROM User WHERE Username='" + username + "'";
		ResultSet rs = dbh.executeQuery(query);
		
		if(rs.first())
		{
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
		}
		else
		{
			throw new Exception("Insert of new user failed in saveUser()!");
		}
		
		return u;
		
	}
	
	public static LinkedList<User> List() throws SQLException
	{
		LinkedList<User> users = new LinkedList<User>();
		DBHelper dbh = new DBHelper();
		String query = "SELECT * FROM User";
		ResultSet rs = dbh.executeQuery(query);
		
		while(rs.next())
		{
			User u = new User();
			u.setID(rs.getInt("ID"));
			u.setUsername("Username");
			u.setEmail(rs.getString("Email"));
			u.setImagePath(rs.getString("ImagePath"));
			u.setRole(rs.getInt("Role"));
			u.setPassword(rs.getString("Password"));
			u.setBannedStatus(rs.getBoolean("IsBanned"));
			u.initStats();
			users.add(u);
		}
		
		
		return users;
	}
	
	/**
	 * Saves all properties of the user passed via parameter
	 * @param u - user to be saved
	 */
	public static void save(User u)
	{
		DBHelper dbh = new DBHelper();
		String query = "UPDATE User SET ";
		query += "Username='" + u.getUsername() + "'";
		query += ",Email='" + u.getEmail() + "'";
		query += ",Password='" + u.getPassword() + "'";
		query += ",ImagePath='" + u.getImagePath() + "'";
		int bit = 0;
		if(u.isBanned())
			bit = 1;
		query += ",IsBanned=" + bit;
		query += ",Role=" + u.getRole();
		query += " WHERE ID=" + u.getID();

		dbh.executeUpdate(query);
	}
	
	/**
	 * Saves password parameter in database where ID=id
	 * @param id - user id to be updated
	 * @param newPassword - new password to be saved
	 */
	public static void resetPassword(int id, String newPassword) {
		
			DBHelper dbh = new DBHelper();
			String query = "UPDATE User SET Password='" + newPassword + "' WHERE ID="
					+ id;
			
			dbh.executeUpdate(query);
			
		//if ID == 0 then no user is selected
	}

}
