/**
 * @author Anish Kunduru
 */
package view;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class LoginScreenController
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
   private TextField passwordTextField;

   // Store input from user.
   private String usernameString;
   private String passwordString;

   /**
    * Constructor
    */
   public LoginScreenController()
   {
   }

   /**
    * Initializes the controller class.
    * 
    * Automatically called after the FXML file has been loaded.
    */
   @FXML
   private void initialize()
   {
      // Event handlers for buttons.
      // The arrow means lambda expression in Java.
      // Lambda expressions allow you to create anonymous methods, which is perfect for eventHandling.

      loginButton.setOnAction((event) ->
      {
         // TO-DO
         // CHECK IF USERNAME/PASSWORD IS VALID BEFORE SETTING...
         usernameString = usernameTextField.getText();
         passwordString = passwordTextField.getText();
      });

      registerButton.setOnAction((event) ->
      {
         // TO-DO
      });

      forgotUsernamePasswordHyperlink.setOnAction((event) ->
      {
         // TO-DO
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
}