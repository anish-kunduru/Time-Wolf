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

   @FXML
   public void initialize()
   {
      // Check if username is already taken.
      usernameTextField.setOnKeyReleased(event ->
      {
         // TO-DO: CHECK IF USERNAME HAS BEEN TAKEN.
         /*
          * if (!usernameTaken) usernameAvailableCheckBox.setSelected(true);
          */
      });

      emailTextField.setOnKeyReleased(event ->
      {
         if (isValidEmail(emailTextField.getText()))
            validEmailCheckBox.setSelected(true);
      });

      // Check if both e-mail fields match.
      checkEmailTextField.setOnKeyReleased(event ->
      {
         if (emailTextField.getText().equals(checkEmailTextField.getText()))
            emailMatchCheckBox.setSelected(true);
      });

      // Send the user back to the login screen.
      cancelButton.setOnAction(event ->
      {
         parentController.displayScreen(MainView.LOGIN_SCREEN);
      });

      // Check if all fields valid and register if okay.
      registerButton.setOnAction(event ->
      {
      });

   }

   /**
    * This private helper method validates e-mail in a VERY weak way. If we wanted to strongly authenticate e-mails we would have many more checks, force the
    * user to verify through a confirmation e-mail, and likely use regex for sanity.
    * 
    * @param email The e-mail address you wish to validate.
    * @return true If valid e-mail format, false otherwise.
    */
   private boolean isValidEmail(String email)
   {
      if (email.contains("@") && email.contains("."))
         return true;
      
      // Implied else.
      return false;
   }

   /**
    * This method will allow for the injection of each screen's parent.
    */
   public void setScreenParent(MainController screenParent)
   {
      parentController = screenParent;
   }
}
