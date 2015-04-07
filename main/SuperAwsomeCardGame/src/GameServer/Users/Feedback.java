package GameServer.Users;

public class Feedback {
	private int ID;
	private int userID;
	private String description;
	private boolean isPositive;
	
	public Feedback()
	{
		ID = 0;
		userID = 0;
		description = "";
		isPositive = false;
	}
	
	public Feedback(int id, int userid, String desc, boolean isPositive)
	{
		this.ID = id;
		this.userID = userid;
		this.description = desc;
		this.isPositive = isPositive;
		
	}
}
