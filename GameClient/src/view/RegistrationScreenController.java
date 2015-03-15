/**
 * @author Anish Kunduru
 * 
 * This program is our handler for RegistrationScreen.fxml.
 */

package view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.image.ImageView;

public class RegistrationScreenController implements ControlledScreen
{

   // So we can set the screen's parent later on.
   MainController parentController;

   @FXML
   private Button registerButton;
   @FXML
   private Button cancelButton;
   @FXML
   private Button browseButton; // http://docs.oracle.com/javafx/2/ui_controls/file-chooser.htm
   
   @FXML
   private TextField usernameTextField;
   @FXML
   private TextField profilePictureTextField;
   @FXML
   private TextField emailTextField;
   @FXML
   private TextField checkEmailTextField;
   @FXML
   private TextField questionOneTextField;
   @FXML
   private TextField questionTwoTextField;
   
   @FXML
   private ComboBox questionOneComboBox;
   @FXML
   private ComboBox questionTwoComboBox;
   
   @FXML
   private CheckBox over13CheckBox;
   @FXML
   private CheckBox usernameAvailableCheckBox;
   @FXML
   private CheckBox validEmailCheckBox;
   @FXML
   private CheckBox emailMatchCheckBox;
   @FXML
   private CheckBox passwordMatchCheckBox;
   
   @FXML
   private ImageView userImage;
   
   
   @FXML
   private PasswordField passwordField;
   @FXML
   private PasswordField checkPasswordField;
   
   @FXML
   private Label errorLabel;

   private String usernameString;
   private String emailString;
   private String passwordString;

   @FXML
   public void initialize()
   {

      registerButton.setOnAction(event ->
      {
         // TO DO - Verify PWs match
         // TO DO - Check to see if username already exists

         usernameString = usernameTextField.getText();
         emailString = emailTextField.getText();
         passwordString = passwordField.getText();

         // DEBUG
         System.out.println(usernameString);
         System.out.println(emailString);
         System.out.println(passwordString);
      });

   }

   /**
    * This method will allow for the injection of each screen's parent.
    */
   public void setScreenParent(MainController screenParent)
   {
      parentController = screenParent;
   }
}
