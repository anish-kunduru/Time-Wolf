package GameServer.Users;

public class Report {

	private int ID;
	private String log;
	
	public Report(int id, String log)
	{
		ID = id;
		this.log = log;
	}
	
	public int getID()
	{
		return ID;
	}
	
	public String getLog()
	{
		return log;
	}
	
}
