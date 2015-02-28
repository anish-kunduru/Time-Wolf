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
	
	public User()
	{
		this.ID = 0;
		this.username = "";
		this.email = "";
		this.password = "";
		this.imgPath = "";
		this.stats = null;
		this.isBanned = false;
		this.role = 0;
	}
	
	public String getEmail()
	{
		return email;
	}
	
	public String getUsername()
	{
		return username;
	}
	
	public void LoadImage()
	{
		//get image from server
	}
	
	public boolean isBanned()
	{
		return isBanned;
	}
	
	public boolean isAdmin()
	{
		if(role == 2)
			return true;
		else
			return false;
	}
	
	public boolean isModerator()
	{
		if(role > 0)
			return true;
		else
			return false;
	}
	
	public boolean logIn(String username, String password) throws SQLException
	{
		DBHelper dbh = new DBHelper();
		String query = "SELECT * WHERE Username=" + username + " AND Password=" + password;
		ResultSet rs = dbh.executeQuery(query);
		if(rs.first())
		{
			this.ID = rs.getInt("ID");
			this.username = username;
			this.email = rs.getString("Email");
			this.password = password;
			this.imgPath = rs.getString("ImagePath");
			this.isBanned = false;
			int bannedBit = rs.getInt("IsBanned");
			if(bannedBit > 0)
				this.isBanned = true;
			this.role = rs.getInt("Role");
			this.stats = new UserStats(this.ID);
			
			return true;
		}
		
		this.ID = 0;
		return false;
	}
	

}
