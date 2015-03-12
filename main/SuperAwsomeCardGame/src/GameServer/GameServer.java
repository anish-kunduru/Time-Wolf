package GameServer;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import GameServer.Users.LogIn;

public class GameServer {
	
	public static void main(String[] args)
	   {
	      System.out.println("TEST");
	      LogIn login = new LogIn();
	      try
	      {
	         Registry r = LocateRegistry.createRegistry(1099);
	         Naming.rebind("//localhost/auth", login);
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
	            Thread.sleep(4000);
	         }
	         catch (InterruptedException e)
	         {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	         }
	   }

}
