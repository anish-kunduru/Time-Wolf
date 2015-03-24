/**
 * @author Anish Kunduru
 *
 * This program is our registration server.
 */

package GameServer;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import GameServer.Users.LogIn;

public class RegistrationServer
{
   public static void main(String[] args)
   {      
      LogIn login = new LogIn();
      
      // DEBUG
      System.out.println("Registration server launched.");
      
      try
      {
         Registry r = LocateRegistry.createRegistry(1098);
         Naming.rebind("//localhost/auth", login);
      }
      catch (RemoteException e)
      {
         e.printStackTrace();
      }
      catch (MalformedURLException e)
      {
         e.printStackTrace();
      }
      while (true)
      {
         try
         {
            Thread.sleep(10000);
         }
         catch (InterruptedException e)
         {
            e.printStackTrace();
         }
      }
   }

}
