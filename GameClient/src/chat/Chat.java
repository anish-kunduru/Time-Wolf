/**
 * @author Anish Kunduru
 *
 * This is the client side chat class that is called by a screen.
 */

package chat;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import GameServer.ChatMessage;

public class Chat
{
   private Socket socket; // What we will listen on.

   // For our ChatMessage objects.
   private ObjectInputStream input;
   private ObjectOutputStream output;
   
   private ChatListener listener; // Listening thread waits for server message.

   // PUBLIC CONSTANTS THAT WILL NEED TO BE UPDATED WHEN SERVER FIELDS CHANGE.
   public final String SERVER_ADDRESS = "localhost";
   public final int SERVER_PORT = 1444;
   
   /**
    * Constructor creates a new Chat object.
    * 
    * @param gameLobby If true, the linked ChatListener will output to the gameLobby. If false, it will output to the gameTable.
    * @param username The username of the player that will be chatting.
    * @param chatroomID The chatroomID (as given to the client by the server as the gameID).
    */
   public Chat(boolean gameLobby, String username, int chatroomID)
   {
      // Try and connect to the server.
      try
      {
         socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
      }
      catch (IOException ioe)
      {
         System.out.println("There was an error connecting to the server: " + ioe);
      }
      
      // Initialize streams.
      try
      {
         input = new ObjectInputStream(socket.getInputStream());
         output = new ObjectOutputStream(socket.getOutputStream());
      }
      catch (IOException ioe)
      {
         System.out.println("Exception creating I/O streams: " + ioe);
      }
      
      // Initialize the thread to listen from the server and start it.
      listener = new ChatListener(gameLobby, input);
      listener.start();
      
      // Initialize server side calls to set username and chatroomID.
      try
      {
         output.writeObject(username);
         output.writeInt(chatroomID);
      }
      catch (IOException ioe)
      {
         System.out.println("Error adding username and chatroomID to server: " + ioe);
         close(); // Shut it down, there is nothing else we can do. This will also shut down the chat listener thread.
      }
   }
   
   /**
    * Closes out the I/O streams and sets the ChatListener run boolean to false by calling its end() method.
    */
   private void close()
   {
      try
      {
         input.close();
         output.close();
      }
      catch (IOException ioe)
      {
         System.out.println("Error closing I/O streams: " + ioe);
      }
      
      listener.end();
   }
   
   /**
    * Write a message to the object buffer (socket stream).
    * 
    * @param message The message that you wish to send to the buffer.
    * @return true if the message was successfully sent, false if something failed.
    */
   public boolean bufferMessage(String message)
   {
      // Check if client is still connected.
      if (!socket.isConnected())
      {
         close();
         return false;
      }

      // Write to buffer.
      try
      {
         ChatMessage chatMessage = new ChatMessage(ChatMessage.Type.MESSAGE, message);
         
         output.writeObject(chatMessage);
      }
      catch (IOException ioe)
      {
         System.out.println("I/O error while attempting to write to the buffer: " + ioe);
      }

      // Implied else.
      return true;
   }
   
   /**
    * Send a logout type ChatMessage to the buffer, close out the streams, and end the listener thread.
    */
   public void end()
   {
      // Send logout message.
      try
      {
         output.writeObject(new ChatMessage(ChatMessage.Type.LOGOUT, ""));
      }
      catch (IOException ioe)
      {
         System.out.println("I/O error while sending logout request to server: " + ioe);
      }
      
      close();
   }
}
