/**
 * @author Anish Kunduru
 * 
 * This program is our handler for LoginScreen.fxml.
 */

package view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.rmi.Naming;

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
   private PasswordField passwordTextField;

   @FXML
   private Label errorMessage;

   // So we can set the screen's parent later on.
   MainController parentController;

   /**
    * Initializes the controller class. Automatically called after the FXML file has been loaded.
    */
   @FXML
   public void initialize()
   {
      // Event handlers for buttons.
      // The arrow means lambda expression in Java.
      // Lambda expressions allow you to create anonymous methods, which is perfect for eventHandling.
      loginButton.setOnAction(event ->
      {
         if (usernameTextField.getText().equals(""))
         {
            errorMessage.setText("Username is not filled in.");
         }
         else if (passwordTextField.getText().equals(""))
         {
            errorMessage.setText("Password is not filled in.");
         }

         try
         {
            LogIn login = (LogIn) Naming.lookup("//localhost/auth");
            User userOne = new User();

            try
            {
               userOne = login.logIn(usernameTextField.getText(), passwordTextField.getText());
               // TEST LOGIN: username: ssimmons, password: password

               // This will only be called if an exception isn't thrown by the previous statement, so no need to worry about error handling.
               // Set the login information in our shared model so that we can access it from other controllers.
               MainModel.getModel().currentLoginData().setUsername(usernameTextField.getText());
               MainModel.getModel().currentLoginData().setUserID(userOne.getID());

               // DEBUG
               System.out.println("usernameTextField.getText()");
               System.out.println("passwordTextField.getText()");
               System.out.println(MainModel.getModel().currentLoginData().getUsername());
               System.out.println("Login succesful. User ID: " + userOne.getID());
               
               // Go to the next screen.
               parentController.displayScreen(MainView.GAME_LOBBY_SCREEN);
            }
            catch (Exception e)
            {
               errorMessage.setText(e.getMessage());
            }
         }
         catch (Exception e)
         {
            errorMessage.setText("The server is offline. Please try again later.");
         }
      });

      registerButton.setOnAction(event ->
      {
         parentController.displayScreen(MainView.REGISTRATION_SCREEN);
      });

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