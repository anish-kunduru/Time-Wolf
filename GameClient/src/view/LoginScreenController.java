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
import java.rmi.RemoteException;
import java.rmi.Naming;
import java.rmi.server.UnicastRemoteObject;
import GameEngine.LogIn;



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
         
         if(usernameString.equals("")){
        	 usernameEmptyLabel.setText("Please enter a username.");
         }
         
         if(passwordString.equals("")){
        	 passwordEmptyLabel.setText("Please enter a password.");
         }
         
         LogIn login = new LogIn();
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
    * @return The username inputted by the player.
    */
   public String getUsername()
   {
      return usernameString;
   }

   /**
    * @return The password inputted by the player.
    */
   public String getPassword()
   {
      return passwordString;
   }
   
   /**
    * This method will allow for the injection of each screen's parent.
    */
   public void setScreenParent(MainController screenParent)
   {
      parentController = screenParent;
   }
}