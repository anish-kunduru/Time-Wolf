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

import GameEngine.LogIn;
import GameEngine.User;

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
   private Label usernameEmptyLabel;

   @FXML
   private Label passwordEmptyLabel;

   // Store input from user.
   private String usernameString;
   private String passwordString;

   // So we can set the screen's parent later on.
   MainController parentController;

   /**
    * Initializes the controller class. Automatically called after the FXML file has been loaded.
    */
   @FXML
   public void initialize()
   {

      passwordString = "";
      usernameString = "";

      // Event handlers for buttons.
      // The arrow means lambda expression in Java.
      // Lambda expressions allow you to create anonymous methods, which is perfect for eventHandling.

      loginButton.setOnAction(event ->
      {
         // TO-DO
         // CHECK IF USERNAME/PASSWORD IS VALID BEFORE SETTING...
         usernameString = usernameTextField.getText();
         passwordString = passwordTextField.getText();

         // DEBUG
         System.out.println(usernameString);
         System.out.println(passwordString);

         if (usernameString.equals(""))
         {
            usernameEmptyLabel.setText("Please enter a username.");
         }

         if (passwordString.equals(""))
         {
            passwordEmptyLabel.setText("Please enter a password.");
         }

         try
         {
            LogIn login = (LogIn) Naming.lookup("//localhost/auth");

            User userOne = login.logIn(usernameString, passwordString);

            System.out.println("Login succesful. User ID: " + userOne.getID());

            // TO-DO: MAKE SURE THESE ARE VALID BEFORE SETTING.
            // Set the login information in our shared model so that we can access it from other controllers.
            MainModel.getModel().currentLoginData().setUsername(usernameString);
            MainModel.getModel().currentLoginData().setPassword(passwordString);
            MainModel.getModel().currentLoginData().setUserID(userOne.getID());

            // Go to the next screen.
            // THE FOLLOWING IS TEMP CODE THAT WE NEED TO REMOVE LATER.
            // Temp code has us go to the registrationScreen, because I want to make sure the model logic works and that screen is already created.
            parentController.displayScreen(MainView.REGISTRATION_SCREEN);

         }
         catch (Exception e)
         {
            // TODO Auto-generated catch block
            e.printStackTrace();
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