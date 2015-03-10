/**
 * @author Anish Kunduru
 * 
 * This program is our handler for RegistrationScreen.fxml.
 */

package view;

import java.awt.Button;
import java.awt.TextField;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;

public class RegistrationScreenController implements ControlledScreen
{
   
   // So we can set the screen's parent later on.
   MainController parentController;
   
   @FXML
   private Button registerButton;
   @FXML
   private TextField usernameTextField;
   @FXML
   private TextField emailTextField;
   @FXML
   private PasswordField passwordField;
   @FXML
   private PasswordField checkPasswordField;
   
   private String usernameString;
   private String emailString;
   private String passwordString;
   
   @FXML
   public void initialize()
   {
      
	   registerButton.setOnAction(event ->
	      {
	         //TO DO - Verify PWs match
	    	 //TO DO - Check to see if username already exists
	    	  
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
