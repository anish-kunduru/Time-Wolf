package GameServer.Users;

public class Feedback {
	private int ID;
	private int userID;
	private String description;
	private boolean isPositive;
	private int byUserID;
	
	public Feedback()
	{
		ID = 0;
		userID = 0;
		description = "";
		isPositive = false;
		byUserID = 0;
	}
	
	public Feedback(int id, int userid, String desc, boolean isPositive, int byUserID)
	{
		this.ID = id;
		this.userID = userid;
		this.description = desc;
		this.isPositive = isPositive;
		this.byUserID = byUserID;
	}
	
	/**
	 * Returns ID of object
	 * @return
	 */
	public int getID()
	{
		return ID;
	}
	
	/**
	 * Returns FK UserID of the user it belongs to
	 * @return
	 */
	public int getUserID()
	{
		return userID;
	}
	
	/**
	 * Returns description of feedback
	 * @return
	 */
	public String getDescription()
	{
		return description;
	}
	
	/**
	 * Returns whether the feedback was positive or negative
	 * @return
	 */
	public boolean isPositive()
	{
		return isPositive;
	}
	
	/**
	 * Returns the ID of the user who left the feedback
	 * @return
	 */
	public int byUserID()
	{
		return byUserID;
	}
}
