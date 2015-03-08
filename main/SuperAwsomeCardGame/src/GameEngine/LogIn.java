package GameEngine;

import java.sql.ResultSet;

public class LogIn {
		
	public User logIn(String username, String password) throws Exception {
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

		u.setID(0);
		return u;
	}

}
