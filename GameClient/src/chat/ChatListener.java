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

import singleton.MainModel;
import javafx.application.Platform;

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
      
      // The following may or may not be necessary. I think it depends on how heavy the page we are to call is. TODO is there a better way to do this?
      // Put this thread to sleep to make sure we that MainController has enough time to update the current controller.
      try
      {
         Thread.sleep(1000);
      }
      catch (InterruptedException e)
      {
         // DEBUG
         System.out.println("There was an issue putting the thread to sleep.");
      }

      while (run)
      {
         try
         {
            String message = (String) input.readObject();

            if (gameLobby)
            {
               GameLobbyScreenController lobbyController = MainModel.getModel().currentControllerData().getGameLobbyScreenController();
               
               // For JavaFX thread safety:
               Platform.runLater(() ->
               {
                  lobbyController.appendToChatBox(message);
               });
               
               // DEBUG
               System.out.println("Incoming: " + message);
            }
            else
            {
               GameTableScreenController tableController = MainModel.getModel().currentControllerData().getGameTableScreenController();
               
               // For JavaFX thread safety:
               Platform.runLater(() ->
               {
                  tableController.appendToChatBox(message);
               });
               
               // DEBUG
               System.out.println("Incoming: " + message);
            }
         }
         catch (IOException | ClassNotFoundException e)
         {
            System.out.println("The connection has been terminated: " + e.getMessage());
            run = false;
         }
      }
   }

   public void end()
   {
      run = false;
   }
}
