/**
 * @author Anish Kunduru
 *
 * This program is our handler for UserListingScreen.fxml.
 */

package userListing;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

import GameServer.Users.User;
import singleton.MainData;
import singleton.MainModel;
import view.ControlledScreen;
import view.MainController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

public class UserListingScreenController implements ControlledScreen
{
   // So we can set the screen's parent later on.
   MainController parentController;
   
   // Table components.
   ArrayList<User> users;
   ObservableList<UserRow> tableData;
   
   @FXML
   TableView<UserRow> userTable;
   @FXML
   TableColumn usernameColumn;
   @FXML
   TableColumn emailColumn;
   @FXML
   TableColumn bannedColumn;
   @FXML
   TableColumn roleColumn;
   
   // User components.
   @FXML
   TextField usernameTextField;
   @FXML
   TextField emailTextField;
   @FXML
   CheckBox bannedCheckBox;
   @FXML
   TextArea bannedReasonTextArea;
   @FXML
   CheckBox administratorRoleCheckBox;
   @FXML
   CheckBox moderatorRoleCheckBox;
   @FXML
   CheckBox userRoleCheckBox;
   @FXML
   Button resetPasswordButton;
   
   @FXML
   ImageView profilePictureImageView;
   @FXML
   Button removePhotoButton;
   
   // Save/Cancel components.
   @FXML
   Label errorLabel;
   
   @FXML
   Button saveChangesButton;
   @FXML
   Button cancelButton;
   
   
   /**
    * Initializes the controller class. Automatically called after the FXML file has been loaded.
    */
   @FXML
   public void initialize()
   {
      // Get list of users.
      // NOTE: THIS CODE ISN'T VERY EFFICIENT AND USES A TON OF MEMORY.
      //       WE KNOW THIS, BUT THIS SHOULD SUFFICE FOR THE PURPOSES OF THIS DEMO.
      try
      {
         users = MainModel.getModel().currentLoginData().getLogInConnection().getUserList();
      }
      catch (RemoteException | SQLException e)
      {
         System.out.println("Error reading users from the database.");
      }
      
      // Bind table elements to their appropriate values.
      usernameColumn.setCellValueFactory(new PropertyValueFactory<UserRow, String>("username"));
      emailColumn.setCellValueFactory(new PropertyValueFactory<UserRow, String>("email"));
      bannedColumn.setCellValueFactory(new PropertyValueFactory<UserRow, String>("banned"));
      roleColumn.setCellValueFactory(new PropertyValueFactory<UserRow, String>("role"));
      
      // Bind the table values.
      tableData = FXCollections.observableArrayList();
      userTable.setItems(tableData);      
      
      // Populate the table.
      for (int i = 0; i < users.size(); i++)
      {
         UserRow currentEntry = new UserRow(); // new row.
         
         User currentUser = users.get(i); // Get index in ArrayList.
         
         currentEntry.username.set(currentUser.getUsername()); // Set username.
         currentEntry.email.set(currentUser.getEmail()); // Set e-mail.
         
         // Set banned.
         if (currentUser.isBanned())
            currentEntry.banned.set("Yes");
         else
            currentEntry.banned.set("No");
         
         // Set role.
         if (currentUser.isAdmin())
            currentEntry.role.set("Administrator");
         else if (currentUser.isModerator())
            currentEntry.role.set("Moderator");
         else
            currentEntry.role.set("User");
         
         // Add to observableArrayList.
         tableData.add(currentEntry);
      }
   }

   /**
    * This method will allow for the injection of each screen's parent.
    */
   public void setScreenParent(MainController screenParent)
   {
      parentController = screenParent;
   }

}