package GameServer;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.SQLException;

import GameServer.Users.LogIn;

public class GameServer
{

   public static void main(String[] args)
   {
      System.out.println("TEST");
      LogIn login = new LogIn();
      try
      {
         GameManagement gm = new GameManagement();
         Registry r = LocateRegistry.createRegistry(1099);
         Naming.rebind("//localhost/auth", login);
         Naming.rebind("//localhost/game", gm);
      }
      catch (RemoteException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      catch (MalformedURLException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      catch (SQLException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      while (true)
      {
         try
         {
            Thread.sleep(60000);
         }
         catch (InterruptedException e)
         {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
      }
   }

}
