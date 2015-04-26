/**
 * @author Anish Kunduru
 * 
 * This program is our handler for ModeratorReports.fxml.
 */

package moderatorReports;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import profile.KarmaRow;
import profile.RecentGameStatsRow;
import userListing.UserRow;
import view.MainController;
import framework.AbstractScreenController;
import framework.ControlledScreen;

public class ModeratorReportsScreenController implements ControlledScreen
{
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
   
   @FXML
   public void initialize(){
	   
   }
   
   private void populateTable(){
	   
	   idColumn.setCellValueFactory(new PropertyValueFactory<UserRow, String>("id"));
	   
	   tableData = FXCollections.observableArrayList();
	   reportTable.setItems(tableData);
	   
	   ReportRow currentRow = new ReportRow();
	   currentRow.id.set(1);
	   
	   tableData.add(currentRow);
   }
   
   /**
    * This method will allow for the injection of each screen's parent.
    */
   @Override
   public void setScreenParent(AbstractScreenController screenParent)
   {
      parentController = (MainController) screenParent;
   }
}
