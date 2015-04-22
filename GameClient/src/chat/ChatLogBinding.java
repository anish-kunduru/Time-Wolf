/**
 * @author Anish Kunduru
 *
 */

package chat;

import javafx.beans.property.SimpleStringProperty;

public class ChatLogBinding
{
   public SimpleStringProperty chatLog = new SimpleStringProperty();
   
   public String getChatLog()
   {
      return chatLog.get();
   }
}
