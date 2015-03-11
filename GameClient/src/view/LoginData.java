/**
 * @author Anish Kunduru
 *
 * The purpose of this program is to store login state data.
 */

package view;

public class LoginData
{
   // Login information.
   private String usernameString;
   private String passwordString; // I'm not sure if this state data is necessary or even safe.

   private int userID;

   /**
    * Default constructor to use in singleton.
    */
   public LoginData()
   {
   }

   /**
    * Sets the userNameString.
    * 
    * @param userNameString A string representing the userName of the current player.
    */
   public void setUsername(String usernameString)
   {
      this.usernameString = usernameString;
   }

   /**
    * Sets the passwordString.
    * 
    * @param passwordString A string representing the password of the current player.
    */
   public void setPassword(String passwordString)
   {
      this.passwordString = passwordString;
   }

   /**
    * Sets the userID.
    * 
    * @param userID An int representing the userID of the current player.
    */
   public void setUserID(int userID)
   {
      this.userID = userID;
   }

   /**
    * @return The valid username of the current player.
    */
   public String getUsername()
   {
      return usernameString;
   }

   /**
    * @return The password of the current player.
    */
   public String getPasswordString()
   {
      return passwordString;
   }

   /**
    * @return The userID of the current player.
    */
   public int getUserID()
   {
      return userID;
   }
}
