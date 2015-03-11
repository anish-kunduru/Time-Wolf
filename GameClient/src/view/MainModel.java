/**
 * @author Anish Kunduru
 *
 * The purpose of this program is to have one central model for all our user's game data.
 * This utilizes a software design pattern called the singleton pattern.
 * We separate each controller's game data into a new submodel that will be implemented in this class.
 */

package view;

public class MainModel
{
   // Singleton.
   private final static MainModel model = new MainModel();

   /**
    * Get the current instance (data model).
    * 
    * @return The current instance of MainModel.
    */
   public static MainModel getModel()
   {
      return model;
   }

   // Instantiate new LoginData to store information.
   private LoginData loginData = new LoginData();

   /**
    * Get the current loginData.
    * 
    * @return The current instance of LoginData.
    */
   public LoginData currentLoginData()
   {
      return loginData;
   }
}
