/**
 * @author Anish Kunduru
 * This class is the chat server that will connect all the clients together.
 * It calls and starts a new ClientThread for every user that connects to it.
 */

package GameServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ChatServer
{
   private static int clientID; // Iterate to keep list of connected clients.
   private ArrayList<ClientThread> clients;
   private int serverPort; // Port number to listen to.
   private boolean run;
   
   /**
    * Create a new chat room.
    * @param portNumber The port number the server should listen to.
    */
   public ChatServer(int portNumber)
   {
      serverPort = portNumber;
      clients = new ArrayList<ClientThread>();
      run = true;
      clientID = 0;
   }
   
   /**
    * Starts up the server.
    */
   public void start()
   {
      try
      {
         // Create the socket and wait for a request.
         ServerSocket serverSocket = new ServerSocket(serverPort);
         
         while (run)
         {
            // DEBUG
            System.out.println("Waiting for clients on port: " + serverPort + ".");
            
            // If you get a request, add it!
            Socket clientSocket = serverSocket.accept();
            
            // Check to break before adding threads (avoid thread interrupt exceptions or hangs).
            if (!run)
               break;
            
            // Create and start the thread
            ClientThread client = new ClientThread(clientSocket, clientID, this);
            clients.add(client);
            
            // Iterate ID var.
            clientID++;
            
            // Start the previously created thread.
            client.start();
         }
         
         // Broke out of while loop. Shut it down...
         try
         {
            serverSocket.close();
            
            // Shut down the threads.
            for (int i = 0; i < clients.size(); i++)
            {
               ClientThread client = clients.get(i);
               
               client.close();
            }
         }
         catch(IOException ioe)
         {
            System.out.println("Error closing the serverSocket: " + ioe);
         }
      }
      catch (IOException ioe)
      {
         System.out.println("Error creating the serverSocket or creating the clientSocket: " + ioe);
      }
   }
   
   public void stop()
   {
      run = false;
      
      // Break the loop by connecting to the socket.
      try
      {
         new Socket("localhost", serverPort);
      }
      catch (IOException ioe)
      {
         System.out.println("Error trying to stop the server: " + ioe);
      }
   }
   
   /**
    * To broadcast a message to all the clients.
    * This is run as a synchronized method because we want to prevent thread interference since this object will be visible to more than one thread.
    * @param message The message that you wish to broadcast to all the clients part of this chatroom.
    */
   public synchronized void broadcastMessage(String message)
   {
      // Loop in reverse to remove disconnected clients along the way.
      for (int i = clients.size(); --i >= 0;)
      {
         ClientThread client = clients.get(i);
         
         // Buffer messages, dumping a client if it fails.
         if (!client.bufferMessage(message))
         {
            clients.remove(i);
            
            // DEBUG
            System.out.println("Client: " + clientID + " has been disconnected.");
         }
      }
   }
   
   /**
    * To remove a client from the chat server.
    * This is called when a client sends a logout message.
    * @param clientID The unique ID given to the client when he joined this server.
    */
   public synchronized void removeClient(int clientID)
   {
      // Scan the array until you find the clientID.
      for (int i = 0; i < clients.size(); i++)
      {
         ClientThread client = clients.get(i);
         
         if (client.getClientID() == clientID)
         {
            clients.remove(i);
            break; /////////////////////// found it, we can stop looping now ///////////////////
         }
      }
   }
   
   public static void main(String[] args)
   {
      // Start a new ChatServer on port 1444.
      ChatServer server = new ChatServer(1444);
      server.start();
   }
}
