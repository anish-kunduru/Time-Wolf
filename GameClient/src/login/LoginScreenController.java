/**
 * @author Anish Kunduru
 * 
 * This program is our handler for LoginScreen.fxml.
 */

package login;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.rmi.Naming;

import framework.ControlledScreen;
import singleton.MainModel;
import view.MainController;
import view.MainView;
import GameServer.Users.LogIn;
import GameServer.Users.User;

public class LoginScreenController implements ControlledScreen
{
   // Functional components.
   @FXML
   private Button loginButton;
   @FXML
   private Button registerButton;
   @FXML
   private Hyperlink forgotUsernamePasswordHyperlink;

   // To get input from user.
   @FXML
   private TextField usernameTextField;
   @FXML
   private PasswordField passwordField;

   @FXML
   private Label errorMessage;

   // So we can set the screen's parent later on.
   MainController parentController;

   // So that we can call it from different event listeners.
   private LogIn login;

   /**
    * Initializes the controller class. Automatically called after the FXML file has been loaded.
    */
   @FXML
   public void initialize()
   {
      // Initialize login and store in singleton.
      try
      {
         login = (LogIn) Naming.lookup("//localhost/auth");

         MainModel.getModel().currentLoginData().setLogInConnection(login);
      }
      catch (Exception e)
      {
         errorMessage.setText("The server is offline. Please try again later.");
      }

      // Event handlers for buttons.
      // The arrow means lambda expression in Java.
      // Lambda expressions allow you to create anonymous methods, which is perfect for eventHandling.
      loginButton.setOnAction(event ->
      {
         User user = new User();

         try
         {
            user = login.logIn(usernameTextField.getText(), passwordField.getText());
            // TEST LOGIN: username: ssimmons, password: password

            // This will only be called if an exception isn't thrown by the previous statement, so no need to worry about error handling.
            // Set the login information in our shared model so that we can access it from other controllers.
            MainModel.getModel().currentLoginData().setUsername(usernameTextField.getText());
            MainModel.getModel().currentLoginData().setUserID(user.getID());
            MainModel.getModel().currentLoginData().setIsAdmin(user.isAdmin());

            // Go to the next screen.
            parentController.displayScreen(MainView.GAME_LOBBY_SCREEN);
         }
         catch (Exception e)
         {
            errorMessage.setText(e.getMessage());
         }
      });

      // Attempt login upon hitting return register in passwordField.
      passwordField.setOnAction(event ->
      {
         loginButton.fire(); // Fire off a loginButton event.
      });

      // Go to registration screen.
      registerButton.setOnAction(event ->
      {
         parentController.displayScreen(MainView.REGISTRATION_SCREEN);
      });

      // Go to forgot password screen.
      forgotUsernamePasswordHyperlink.setOnAction(event ->
      {
         parentController.displayScreen(MainView.FORGOT_PASSWORD_SCREEN);
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