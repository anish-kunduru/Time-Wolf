/**
 * @author Anish Kunduru
 * 
 * This class defines a row in the user listing table.
 */

package userListing;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;

public class UserRow
{
   // Define our entry types.
   public SimpleStringProperty username = new SimpleStringProperty();
   public SimpleStringProperty email = new SimpleStringProperty();
   public SimpleBooleanProperty banned = new SimpleBooleanProperty();
   public SimpleStringProperty role = new SimpleStringProperty();
   
   // The following getters are called automatically when the table is loaded.
   
   /**
    * @return The user's username.
    */
   public String getUsername()
   {
      return username.get();
   }
   
   /**
    * @return The e-mail address of a user.
    */
   public String getEmail()
   {
      return email.get();
   }
   
   /**
    * @return The boolean representation of a user's ban status (true for banned, false for not banned).
    */
   public boolean getBanned()
   {
      return banned.get();
   }
   
   /**
    * @return The String representation of a user's role (administrator, moderator, user).
    */
   public String getRole()
   {
      return role.get();
   }
}
