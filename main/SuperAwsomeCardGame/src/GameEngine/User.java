package GameEngine;

import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
	private int ID;
	private String username;
	private String email;
	private String password;
	private String imgPath;
	private UserStats stats;
	private boolean isBanned;
	private int role;

	public User() {
		this.ID = 0;
		this.username = "";
		this.email = "";
		this.password = "";
		this.imgPath = "";
		this.stats = null;
		this.isBanned = false;
		this.role = 0;
	}

	public String getEmail() {
		return email;
	}

	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}

	public void LoadImage() {
		// get image from server
	}
	
	public void deleteImage()
	{
		//do server stuff to delete from server
		this.imgPath = "";
	}
	
	public boolean isBanned() {
		return isBanned;
	}
	
	public void Ban()
	{
		this.isBanned = true;
	}
	

	public boolean isAdmin() {
		if (role == 2)
			return true;
		else
			return false;
	}

	public boolean isModerator() {
		if (role > 0)
			return true;
		else
			return false;
	}
	
	/*
	 * Pre-existing users
	 */
	public void saveUser() throws Exception
	{
		if(this.ID > 0)
			saveUser(this.username, this.email, this.password);
		else
			throw new Exception("saveUser() cannot accept a call from a User with ID=0");
	}
	
	/*
	 * Creating a new user case
	 */
	public void saveUser(String username, String email, String password) throws Exception
	{
		if(username.equals("") || email.equals("") || password.equals(""))
		{
			throw new Exception("saveUser() cannot accept empty strings as arguments");
		}
	
		DBHelper dbh = new DBHelper();
		String query = "";
		if(this.ID == 0) //insert query
		{
			query = "INSERT INTO User ";
			query += "('Username', 'Email', 'Password', 'ImagePath', 'IsBanned', 'Role')";
			query += "VALUES (" + username + "," + email + "," + password + ",,0,0)";
			query += " SELECT * FROM User WHERE Username=" + username;

			ResultSet rs = dbh.executeQuery(query);
			if(rs.first())
			{
				this.ID = rs.getInt("ID");
			
				query = "INSERT INTO Statistics ";
				query += "('UserID','TotalGames','TotalWins','TotalPoints')";
				query += "(" + this.ID + ",0,0,0)";
				dbh.executeUpdate(query);
			}
			else
			{
				throw new Exception("Insert of new user failed in saveUser()!");
			}
			
		}
		else     //update query
		{
			query = "UPDATE User SET ";
			query += "Username=" + username;
			query += ",Email=" + email;
			query += ",Password=" + password;
			query += ",ImagePath=" + this.imgPath;
			int bit = 0;
			if(this.isBanned)
				bit = 1;
			query += ",IsBanned=" + this.isBanned;
			query += ",Role=" + this.role;
			query += " WHERE ID=" + this.ID;

			dbh.executeUpdate(query);
			//Stats are currently saved whenever games are incremented so there isn't a need to save here as well
		}
		
		
	}
	
	/*
	 * Resets current user's password and saves new password in database Emails
	 * user new password - if we can get a SMTP setup
	 */
	public String resetPassword() {
		if (this.ID > 0) {
			// newPW is awful right now, can make better later
			String newPW = username + ID;
			DBHelper dbh = new DBHelper();
			String query = "UPDATE User SET Password=" + newPW + " WHERE ID="
					+ this.ID;
			
			dbh.executeQuery(query);
			
			return newPW;
		}
		//if ID == 0 then no user is selected
		return "";
	}

	public boolean logIn(String username, String password) throws SQLException {
		DBHelper dbh = new DBHelper();
		String query = "SELECT * FROM User WHERE Username='" + username + "' AND Password='"
				+ password + "'";
		ResultSet rs = dbh.executeQuery(query);
		if (rs.first()) {
			this.ID = rs.getInt("ID");
			this.username = username;
			this.email = rs.getString("Email");
			this.password = password;
			this.imgPath = rs.getString("ImagePath");
			this.isBanned = false;
			int bannedBit = rs.getInt("IsBanned");
			if (bannedBit > 0)
				this.isBanned = true;
			this.role = rs.getInt("Role");
			this.stats = new UserStats(this.ID);

			return true;
		}

		this.ID = 0;
		return false;
	}
	

	/*
	 * The following are UserStats methods
	 * for ease of access
	 */
	public int getGamesPlayed()
	{
		if(this.stats != null)
			return this.stats.getGamesPlayed();
		else
			return 0;
	}
	
	public int getGamesWon()
	{
		if(this.stats != null)
			return this.stats.getGamesWon();
		else
			return 0;
	}
	
	public double getWinLossRatio()
	{
		if(this.stats != null)
			return this.stats.getWinLossRatio();
		else
			return 0;
	}
	
	public double getTotalPoints()
	{
		if(this.stats != null)
			return this.stats.getTotalPoints();
		else
			return 0;
	}
	
	public double getAveragePoints()
	{
		if(this.stats != null)
			return this.getAveragePoints();
		else
			return 0;
	}
	
	public void incrementGamesPlayed(boolean wonGame, int points)
	{
		if(this.stats != null)
		{
			this.stats.incrementGamesPlayed(wonGame, points);
			this.stats.updateStatsDatabase();
		}
	}
	
	

}
