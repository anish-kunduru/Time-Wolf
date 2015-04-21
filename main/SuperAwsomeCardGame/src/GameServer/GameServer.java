package GameServer;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.SQLException;

import GameServer.Users.LogIn;

public class GameServer
{

   public static void main(String[] args)
   {
	   
		GameManagement gm = null;
		try {
			gm = new GameManagement();
		} catch (RemoteException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//System.out.println(gm.getClass());
      // DEBUG
      System.out.println("Game server launched.");

      LogIn login = new LogIn();
      try
      {
    	  
         Registry r = LocateRegistry.createRegistry(1099);
         Naming.rebind("//localhost/auth", login);
         Naming.rebind("//localhost/game", (IGameManagement)gm);
         
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
      while (true)
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
