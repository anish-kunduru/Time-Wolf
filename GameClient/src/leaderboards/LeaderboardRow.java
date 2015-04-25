package leaderboards;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class LeaderboardRow {

	public SimpleIntegerProperty rank = new SimpleIntegerProperty();
	public SimpleStringProperty username = new SimpleStringProperty();
	public SimpleIntegerProperty totalPoints = new SimpleIntegerProperty();
	public SimpleIntegerProperty avgPoints = new SimpleIntegerProperty();
	public SimpleIntegerProperty gamesPlayed = new SimpleIntegerProperty();
	public SimpleIntegerProperty gamesWon = new SimpleIntegerProperty();
	public SimpleStringProperty ratio = new SimpleStringProperty();
	public SimpleDoubleProperty karma = new SimpleDoubleProperty();
	
	public int getRank(){
		return rank.get();
	}
	
	public String getUsername(){
		return username.get();
	}
	
	public Integer getTotalPoints(){
		return totalPoints.get();
	}
	
	public Integer getAvgPoints(){
		return avgPoints.get();
	}
	
	public int getGamesPlayed(){
		return gamesPlayed.get();
	}
	
	public int getGamesWon(){
		return gamesWon.get();
	}
	
	public String getRatio(){
		return ratio.get();
	}
	
	public double getKarma(){
		return karma.get();
	}
}


