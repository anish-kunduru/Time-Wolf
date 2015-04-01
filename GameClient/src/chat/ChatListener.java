/**
 * @author Anish Kunduru
 *
 * This class listens for an incoming message and sends it to the appropriate location.
 */

package chat;

import gameLobby.GameLobbyScreenController;
import gameTable.GameTableScreenController;

import java.io.IOException;
import java.io.ObjectInputStream;

public class ChatListener extends Thread
{
   private ObjectInputStream input;
   
   private boolean gameLobby; // If true, thread is to output to gameLobby. If false, thread is to output to gameTable.
   private boolean run; // To start and stop thread.

   /**
    * Constructor creates a new listener thread.
    * 
    * @param gameLobby True if the output should go to the gameLobby, false if the output should go to the gameTable.
    * @param input The input stream of the socket the server is connected to.
    */
   public ChatListener(boolean gameLobby, ObjectInputStream input)
   {
      // Initialize private state vars.
      this.gameLobby = gameLobby;
      this.input = input;
   }

   public void run()
   {
      run = true;
      
      while (run)
      {
         try
         {
            String message = (String) input.readObject();
            
            if (gameLobby)
               GameLobbyScreenController.appendChatMessage();
            else
               GameTableScreenController.appendChatMessage();
         }
         catch (IOException | ClassNotFoundException e)
         {
            System.out.println("It appears the chat server has closed the connection: " + e);
         }
      }
   }
   
   public void end()
   {
      run = false;
   }
}
