package leaderboards;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class LeaderboardRow {

	public SimpleIntegerProperty rank = new SimpleIntegerProperty();
	public SimpleStringProperty username = new SimpleStringProperty();
	public SimpleDoubleProperty totalPoints = new SimpleDoubleProperty();
	public SimpleDoubleProperty avgPoints = new SimpleDoubleProperty();
	public SimpleIntegerProperty gamesPlayed = new SimpleIntegerProperty();
	public SimpleIntegerProperty gamesWon = new SimpleIntegerProperty();
	public SimpleDoubleProperty ratio = new SimpleDoubleProperty();
	public SimpleDoubleProperty karma = new SimpleDoubleProperty();
	
	public int getRank(){
		return rank.get();
	}
	
	public String getUsername(){
		return username.get();
	}
	
	public Double getTotalPoints(){
		return totalPoints.get();
	}
	
	public Double getAvgPoints(){
		return avgPoints.get();
	}
	
	public int getGamesPlayed(){
		return gamesPlayed.get();
	}
	
	public int getGamesWon(){
		return gamesWon.get();
	}
	
	public double getRatio(){
		return ratio.get();
	}
	
	public double getKarma(){
		return karma.get();
	}
}


