/**
 * @author Anish Kunduru
 * 
 * This class defines a row in the user listing table.
 */

package userListing;

import javafx.beans.property.SimpleStringProperty;

public class UserRow
{
   // Define our entry types.
   public SimpleStringProperty username = new SimpleStringProperty();
   public SimpleStringProperty email = new SimpleStringProperty();
   public SimpleStringProperty banned = new SimpleStringProperty();
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
    * @return The String representation of a user's ban status (yes for banned, no for not banned).
    */
   public String getBanned()
   {
      return banned.get();
   }
   
   /**
    * @return The String representation of a user's role (admin, mod, user).
    */
   public String getRole()
   {
      return role.get();
   }
}
