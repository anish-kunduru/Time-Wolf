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
import javafx.scene.layout.AnchorPane;
import GameServer.Users.LogIn;
import GameServer.Users.User;

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
   
   // Validity checks.
   private boolean validUsername = false;
   private boolean validEmail = false;
   private boolean validPassword = false;
   
   @FXML
   public void initialize()
   {
      // Check if username is already taken.
      usernameTextField.setOnKeyReleased(event ->
      {
         try
         {
            if (MainModel.getModel().currentLoginData().getLogInConnection().doesUsernameExist(usernameTextField.getText()))
            {
               usernameAvailableCheckBox.setSelected(false);
               validUsername = false;
            }
            else
            {
               usernameAvailableCheckBox.setSelected(true);
               validUsername = true;
            }
         }
         catch (Exception e)
         {
            e.printStackTrace();
         }
      });

      // Check if e-mail address is valid.
      emailTextField.setOnKeyReleased(event ->
      {
         if (isValidEmail(emailTextField.getText()))
            validEmailCheckBox.setSelected(true);
         else
            validEmailCheckBox.setSelected(false);
      });

      // Check if both e-mail fields match.
      checkEmailTextField.setOnKeyReleased(event ->
      {
         if (emailTextField.getText().equals(checkEmailTextField.getText()))
         {
            emailMatchCheckBox.setSelected(true);
            validEmail = true;
         }
         else
         {
            emailMatchCheckBox.setSelected(false);
            validEmail = false;
         }
      });
      
      // Check if passwords match.
      checkPasswordField.setOnKeyReleased(event ->
      {
         if (passwordField.getText().equals(checkPasswordField.getText()))
         {
            passwordMatchCheckBox.setSelected(true);
            validPassword = true;
         }
         else
         {
            passwordMatchCheckBox.setSelected(false);
            validPassword = false;
         }
      });

      // Send the user back to the login screen.
      cancelButton.setOnAction(event ->
      {
         parentController.displayScreen(MainView.LOGIN_SCREEN);
      });

      // Check if all fields valid and register if okay.
      registerButton.setOnAction(event ->
      {
         if (!validUsername)
            errorLabel.setText("That username is not valid.");
         else if (!over13CheckBox.isSelected())
            errorLabel.setText("You are not over 13.");
         else if (!validEmail)
            errorLabel.setText("That e-mail address is not valid.");
         else if (!validPassword)
            errorLabel.setText("Passwords do not match.");
         else
         {
            try
            {
               MainModel.getModel().currentLoginData().getLogInConnection().register(usernameTextField.getText(), checkEmailTextField.getText(), checkPasswordField.getText());
            }
            catch (Exception e)
            {
               errorLabel.setText("There was an error registering your account. Please contact support.");
            }
         }
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
