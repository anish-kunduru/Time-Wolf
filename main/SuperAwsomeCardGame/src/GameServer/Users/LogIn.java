package GameServer.Users;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import GameServer.DBHelper;

/**
 * Server side class to handle DB methods involving the User class
 * 
 * @author Shelbie
 *
 */

public class LogIn implements Remote, Serializable
{

   

   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

/**
    * Returns the user to be logged in by the given username and password
    * 
    * @param username
    * @param password
    * @return
    * @throws Exception
    */

   public static User logIn(String username, String password) throws Exception, RemoteException
   {
      User u = new User();

      DBHelper dbh = new DBHelper();
      String query = "SELECT * FROM User WHERE Username='" + username + "' AND Password='" + password + "'";
      ResultSet rs = dbh.executeQuery(query);
      if (rs.first())
      {
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
         u.initStats();

         return u;
      }
      else
      {
         throw new Exception("Username or password was incorrect!");
      }
   }

   private static UserStats initStats(int id)
   {
		   int gamesPlayed = 0;
		   int gamesWon = 0;
		   double totalPoints = 0;
		   int ID = 0;
		   int userID = 0;
		   int karmaScore = 0;
	   try {

		   
			DBHelper dbh = new DBHelper();
			String query = "SELECT * FROM Statistics WHERE UserID=" + userID;
			ResultSet rs = dbh.executeQuery(query);
			

			if (rs.first()) // should only be one returned on table is incorrect
			{
				gamesPlayed = rs.getInt("TotalGames");
				gamesWon = rs.getInt("TotalWins");
				totalPoints = rs.getDouble("TotalPoints");
				ID = rs.getInt("ID");
				userID = id;
			} else {
				// given userID is not valid
				gamesPlayed = 0;
				gamesWon = 0;
				totalPoints = 0;
				ID = 0;
				userID = 0;
			}
			
			
			if(ID > 0) //valid user
			{
				int total = 0;
				int totalGood = 0;
				query = "SELECT * FROM Feedback WHERE UserID=" + userID;
				rs = dbh.executeQuery(query);
				
				
				while(rs.next())
				{
					total++;
					if(rs.getBoolean("isGood"))
						totalGood++;
				}
				
				karmaScore = totalGood/total;
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	   UserStats us = new UserStats(userID, gamesWon, gamesPlayed, totalPoints, karmaScore, ID);
	   return us;
   }
   
   /**
    * Checks for username existance in database
    * 
    * @param username - username to check for
    * @return true if username exists, false if it is not taken
    * @throws SQLException
    */
   public static boolean doesUsernameExist(String username) throws SQLException, RemoteException
   {
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
   public static User register(String username, String email, String password) throws RemoteException, Exception
   {
      User u = new User();
      DBHelper dbh = new DBHelper();
      String query = "INSERT INTO User ";
      query += "(Username, Email, Password, ImagePath, IsBanned, Role)";
      query += "VALUES ('" + username + "','" + email + "','" + password + "','','0','0')";

      try
      {
         dbh.executeUpdate(query);
         query = " SELECT * FROM User WHERE Username='" + username + "'";
         ResultSet rs = dbh.executeQuery(query);
         if (rs.first())
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
      }
      catch (Exception ex)
      {
         throw new Exception("Insert of new user failed!");
      }

      return u;

   }

   /**
    * Returns a list of all users in the database
    * 
    * @return List of all users
    * @throws SQLException
    */
   public static LinkedList<User> List() throws SQLException, RemoteException
   {
      LinkedList<User> users = new LinkedList<User>();
      DBHelper dbh = new DBHelper();
      String query = "SELECT * FROM User";
      ResultSet rs = dbh.executeQuery(query);

      while (rs.next())
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
    * 
    * @param u - user to be saved
    */
   public static void save(User u) throws RemoteException
   {
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
      query += " WHERE ID=" + u.getID();

      dbh.executeUpdate(query);
   }

   /**
    * Saves password parameter in database where ID=id
    * 
    * @param id - user id to be updated
    * @param newPassword - new password to be saved
    */
   public static void resetPassword(int id, String newPassword) throws RemoteException
   {

      DBHelper dbh = new DBHelper();
      String query = "UPDATE User SET Password='" + newPassword + "' WHERE ID=" + id;

      dbh.executeUpdate(query);

      // if ID == 0 then no user is selected
   }

}
