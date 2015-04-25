/**
 * @author Anish Kunduru
 * 
 * This is the controller for LeaderboardsScreen.fxml.
 */

package leaderboards;

import java.sql.SQLException;
import java.util.ArrayList;

import GameServer.Users.User;
import profile.KarmaRow;
import profile.RecentGameStatsRow;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import singleton.MainModel;
import userListing.UserRow;
import view.ControlledScreen;
import view.MainController;

public class LeaderboardsScreenController implements ControlledScreen {

	private ObservableList<LeaderboardRow> tableData;
	@FXML
	private TableView<LeaderboardRow> leaderboard;

	@FXML
	private TableColumn rankColumn;
	@FXML
	private TableColumn usernameColumn;
	@FXML
	private TableColumn totalPointsColumn;
	@FXML
	private TableColumn avgPointsColumn;
	@FXML
	private TableColumn gamesPlayedColumn;
	@FXML
	private TableColumn gamesWonColumn;
	@FXML
	private TableColumn ratioColumn;
	@FXML
	private TableColumn karmaColumn;

	// So we can set the screen's parent later on.
	MainController parentController;

	/**
	 * Initializes the controller class. Automatically called after the FXML
	 * file has been loaded.
	 */
	@FXML
	public void initialize() {
		rankColumn.setCellValueFactory(new PropertyValueFactory<UserRow, String>("rank"));
		usernameColumn.setCellValueFactory(new PropertyValueFactory<UserRow, String>("username"));
		totalPointsColumn.setCellValueFactory(new PropertyValueFactory<UserRow, String>("totalPoints"));
		avgPointsColumn.setCellValueFactory(new PropertyValueFactory<UserRow, String>("avgPoints"));
		gamesPlayedColumn.setCellValueFactory(new PropertyValueFactory<UserRow, String>("gamesPlayed"));
		gamesWonColumn.setCellValueFactory(new PropertyValueFactory<UserRow, String>("gamesWon"));
		ratioColumn.setCellValueFactory(new PropertyValueFactory<UserRow, String>("ratio"));
		karmaColumn.setCellValueFactory(new PropertyValueFactory<UserRow, String>("karma"));

		tableData = FXCollections.observableArrayList();
		leaderboard.setItems(tableData);

		ArrayList<User> users = new ArrayList<User>();
		
		try {
			users = MainModel.getModel().currentLoginData().getLogInConnection().getLeaderboard();
			int count = 0;
			
			for (int i = users.size() - 1; i >= 0; i--) {
				User user = users.get(i);
				LeaderboardRow currentRow = new LeaderboardRow();
				currentRow.rank.set(count++);
				currentRow.username.set(user.getUsername());
				currentRow.gamesPlayed.set(user.Statistics.getGamesPlayed());
				currentRow.gamesWon.set(user.Statistics.getGamesWon());
				currentRow.ratio.set(user.Statistics.getWinLossRatio());
				currentRow.totalPoints.set(user.Statistics.getTotalPoints());
				currentRow.avgPoints.set(user.Statistics.getAveragePoints());
				currentRow.karma.set(user.Statistics.getKarma());

				tableData.add(currentRow);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	}

	/**
	 * This method will allow for the injection of each screen's parent.
	 */
	public void setScreenParent(MainController screenParent) {
		parentController = screenParent;
	}
}
