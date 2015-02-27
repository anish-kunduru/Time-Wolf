package GameEngine;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBHelper {
	
	
	public ResultSet executeQuery(String query)
	{
		try {   
	         // Load the driver (registers itself)
	         Class.forName ("com.mysql.jdbc.Driver");
	         } 
	      catch (Exception E) {
	            System.err.println ("Unable to load driver.");
	            E.printStackTrace ();
	      } 
		
		try {
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://mysql.cs.iastate.edu:3306/db30911", "u30911", "4rv2ucue78");
			

			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			
			return rs;
		} catch (SQLException e) {
			System.err.println("Connection to database failed!");
			e.printStackTrace();
		}
		
		return null;
	}

}
