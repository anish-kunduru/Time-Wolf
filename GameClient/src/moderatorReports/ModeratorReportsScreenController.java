/**
 * @author Anish Kunduru
 * 
 * This program is our handler for ModeratorReports.fxml.
 */

package moderatorReports;

import java.sql.SQLException;
import java.util.ArrayList;

import GameServer.Users.LogIn;
import GameServer.Users.Report;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import singleton.MainModel;
import view.MainController;
import framework.AbstractScreenController;
import framework.ControlledScreen;

public class ModeratorReportsScreenController implements ControlledScreen {
	// So we can set the screen's parent later on.
	MainController parentController;

	@FXML
	private ObservableList<ReportRow> tableData;

	@FXML
	private TableView<ReportRow> reportTable;
	@FXML
	private TableColumn idColumn;

	@FXML
	private TextArea chatText;
	@FXML
	private TextArea reasonText;
	@FXML
	private TextField username;
	@FXML
	private Button flagButton;
	@FXML
	private Button deleteButton;

	@FXML
	public void initialize() {
		populateTable();
	}

	private void populateTable() {
		// Bind column to table.
		idColumn.setCellValueFactory(new PropertyValueFactory<ReportRow, Integer>(
				"id"));
		
		// Bind table elements.
		tableData = FXCollections.observableArrayList();
		reportTable.setItems(tableData);

		LogIn login = MainModel.getModel().currentLoginData()
				.getLogInConnection();
		try {
			ArrayList<Report> reports = login.getReports();
			
			for(Report r : reports)
			{
				ReportRow currentRow = new ReportRow();
				
				// DEBUG
				System.out.println(r.getID());
				
				currentRow.id.set(r.getID());
				tableData.add(currentRow);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * This method will allow for the injection of each screen's parent.
	 */
	@Override
	public void setScreenParent(AbstractScreenController screenParent) {
		parentController = (MainController) screenParent;
	}
}
