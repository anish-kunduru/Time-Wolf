/**
 * @author Anish Kunduru
 *
 * This class listens for an incoming message and sends it to the appropriate location.
 */

package chat;

import java.io.IOException;
import java.io.ObjectInputStream;

import javafx.beans.property.SimpleStringProperty;

public class ChatListener extends Thread
{
   private ObjectInputStream input;
   
   private boolean gameLobby; // If true, thread is to output to gameLobby. If false, thread is to output to gameTable.
   private boolean run; // To start and stop thread.
   
   ChatLogBinding chatLog;
   String chatStr;

   /**
    * Constructor creates a new listener thread.
    * 
    * @param gameLobby True if the output should go to the gameLobby, false if the output should go to the gameTable.
    * @param input The input stream of the socket the server is connected to.
    */
   public ChatListener(boolean gameLobby, ObjectInputStream input, ChatLogBinding chatLog)
   {
      // Initialize private state vars.
      this.gameLobby = gameLobby;
      this.input = input;
      
      this.chatLog = chatLog;
      chatStr = "";
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
            {
               chatStr += ("> " + message + "\n");
               chatLog.chatLog.setValue(chatStr);
               
               // DEBUG
               System.out.println("Incoming: " + message);
            }
               
               
            //else
               //GameTableScreenController.appendChatMessage(message);
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
