/**
 * @author Anish Kunduru
 * 
 * This program is our handler for RegistrationScreen.fxml.
 */

package view;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.util.Duration;

public class ForgotPasswordScreenController implements ControlledScreen
{
   // So we can set the screen's parent later on.
   MainController parentController;

   @FXML
   private Button resetButton;
   @FXML
   private Button cancelButton;

   @FXML
   private TextField usernameTextField;
   @FXML
   private TextField emailTextField;
   @FXML
   private TextField passwordResetQuestionTextField;

   @FXML
   private ComboBox passwordResetQuestionComboBox;

   @FXML
   private PasswordField passwordField;
   @FXML
   private PasswordField verifyPasswordField;

   @FXML
   private Label errorLabel;

   // Validity check.
   private boolean validPassword = false;

   @FXML
   public void initialize()
   {
      // Display username and password reset Qs.
      emailTextField.setOnKeyReleased(event ->
      {
         try
         {
            // usernameTextField.setText(MainModel.getModel().currentLoginData().getLogInConnection().findUsername(usernameTextField.getText()));
            // usernameTextField.setVisible(true);

            // TO-DO: PASSWORD RESET Qs.
         }
         catch (Exception e)
         {
            e.printStackTrace();
         }
      });

      // Send the user back to the login screen.
      cancelButton.setOnAction(event ->
      {
         parentController.displayScreen(MainView.LOGIN_SCREEN);
      });

      // Check if all fields valid and reset if okay.
      resetButton.setOnAction(event ->
      {
         // Timeline action event.
         errorLabel.setText("Reset sucessful! Redirecting to the login screen in 5 seconds.");

         Timeline timeline = new Timeline(new KeyFrame(Duration.millis(5000), action ->
         {
            parentController.displayScreen(MainView.LOGIN_SCREEN);
         }));
         timeline.play();
         
         
//         if (!passwordField.getText().equals(verifyPasswordField.getText()))
//            errorLabel.setText("Passwords do not match.");
//         else
//         {
//            try
//            {
//               // MainModel.getModel().currentLoginData().getLogInConnection().resetPassword(id, newPassword);
//
//               // Timeline action event.
//               errorLabel.setText("Reset sucessful! Redirecting to the login screen in 5 seconds.");
//
//               Timeline timeline = new Timeline(new KeyFrame(Duration.millis(5000), action ->
//               {
//                  parentController.displayScreen(MainView.LOGIN_SCREEN);
//               }));
//               timeline.play();
//            }
//            catch (Exception e)
//            {
//               errorLabel.setText("There was an error resetting your account. Please contact support.");
//            }
//         }
         
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
