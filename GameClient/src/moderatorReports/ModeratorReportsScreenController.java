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
	private TextField reasonText;
	@FXML
	private TextField username;
	@FXML
	private Button flagButton;
	@FXML
	private Button deleteButton;
	
	private Report currentReport;
	private ArrayList<Report> reports;

	@FXML
	public void initialize() {
		populateTable();
		
		reportTable.setOnMouseClicked(event ->
		{
			if (reportTable.getSelectionModel().getSelectedIndex() != -1){
				int id = reportTable.getSelectionModel().getSelectedItem().getId();
				
				//find Report object for the ID
				for(Report r : reports)
				{
					if(id == r.getID())
					{
						currentReport = r;
						break;
					}
				}
				
				chatText.setText(currentReport.getLog());
			}
		});
		
		flagButton.setOnMouseClicked(event -> {
			if(currentReport != null)
			{
				String user = username.getText();
				String reason = reasonText.getText();
				MainModel.getModel().currentLoginData().getLogInConnection().controlFlag(user, reason, true);
			}
		});
		
		deleteButton.setOnMouseClicked(event -> {
			if(currentReport != null)
			{
				try {
					MainModel.getModel().currentLoginData().getLogInConnection().deleteReport(currentReport.getID());
				} catch (Exception e) {
					System.out.println("Error log could not be deleted.");
					e.printStackTrace();
				}
			}
		});
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
			reports = login.getReports();
			
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
