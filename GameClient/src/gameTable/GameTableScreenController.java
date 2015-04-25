/**
 * This program is our handler for GameTableScreen.fxml.
 */

package gameTable;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.Random;

import framework.AbstractScreenController;
import framework.ControlledScreen;
import singleton.MainModel;
import view.MainController;
import GameServer.IGameManagement;
import GameServer.GameEngine.Action;
import GameServer.GameEngine.Card;
import GameServer.GameEngine.Client;
import GameServer.GameEngine.Deck;
import GameServer.GameEngine.FacadeClient;
import GameServer.GameEngine.GameEngine;
import GameServer.GameEngine.Hand;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GameTableScreenController implements ControlledScreen, Client
{

   // IMPORTANT NOTE: IF YOU RENAME ANYTHING WITH AN FXML TAG IN FRONT OF IT,
   // YOU WILL NEED TO RE-LINK IT IN THE GAME TABLE SCREEN.
   // OTHERWISE, IT SIMPLY WON'T WORK AND/OR THE MAIN CONTROLLER WILL THROW AN
   // EXCEPTION TO THE CONSOLE...

   // FXML Components

   @FXML
   private Button endTurnButton;

   @FXML
   private Label cardsInGameDeckLabel; // Set text to show num of cards in main
   // deck
   @FXML
   private Label playerTurnLabel; // Set text to reflect whose turn it is.
   @FXML
   private Label playerCardsInDeckLabel; // Set text to show num of cards in
   // player's deck

   // Labels to show the number of VP points each player has

   @FXML
   private Label playerOneVP;
   @FXML
   private Label playerTwoVP;
   @FXML
   private Label playerThreeVP;
   @FXML
   private Label yourVP;

   // Labels to show current values of stealth and attack
   @FXML
   private Label Attack;
   @FXML
   private Label Stealth;

   // Images for the cards currently in player's hand
   @FXML
   private ImageView playerHandOne;
   @FXML
   private ImageView playerHandTwo;
   @FXML
   private ImageView playerHandThree;
   @FXML
   private ImageView playerHandFour;
   @FXML
   private ImageView playerHandFive;
   @FXML
   private ImageView playerHandSix;
   @FXML
   private ImageView playerHandSeven;
   @FXML
   private ImageView playerHandEight;
   @FXML
   private ImageView playerHandNine;
   @FXML
   private ImageView playerHandTen;
   @FXML
   private ImageView playerHandEleven;
   @FXML
   private ImageView playerHandTwelve;
   @FXML
   private ImageView playerHandThirteen;

   // Images that represent the 5 current cards on the table.
   @FXML
   private ImageView gameTableCardOne;
   @FXML
   private ImageView gameTableCardTwo;
   @FXML
   private ImageView gameTableCardThree;
   @FXML
   private ImageView gameTableCardFour;
   @FXML
   private ImageView gameTableCardFive;

   @FXML
   private ImageView playerDeckImage; // Image representing player's deck
   @FXML
   private ImageView lastDiscardImage; // Image representing player's discard
   // deck
   @FXML
   private ImageView biteDeckImage; // Image representing the bite card deck
   @FXML
   private ImageView lurkDeckImage; // Image representing the lurk card deck
   @FXML
   private ImageView notSoImportantHistoricalFigureImage; // Image representing
   // the NSIHF card
   // deck

   @FXML
   private TextArea playLog; // Log for game actions.

   private ImageView[] playerHandImages;

   private ImageView[] gameTableImages;

   private Card cardForAction;
   private Action action;
   private boolean isTurn;
   private boolean isDiscard;
   private boolean isTrash;
   private boolean Update;
   private int stealth;
   private int attack;
   private int counter;

   private String remoteString;
   private GameEngine gameEngine;

   // So we can set the screen's parent later on.
   MainController parentController;

   /**
    * Initializes the controller class. Automatically called after the FXML file has been loaded.
    * 
    * @throws SQLException
    */
   @FXML
   public void initialize() throws SQLException // NOTE FROM ANISH: YOU SHOULD HANDLE THE SQL EX RIGHT HERE, OTHERWISE IT'LL CRASH THE ENTIRE APP IF IT IS THROWN SINCE THIS IS CALLED VIA DI.
   {
      // initRemoteObject();
      /*
       * try { IGameManagement gameManagement = (IGameManagement) Naming.lookup("//localhost/game"); //gameManagement.addUserToGame(1, //
       * MainModel.getModel().currentLoginData().getLogInConnection().getUser("jheinig"), // this.remoteString); System.out.println("GO THROUGH THE JOIN."); }
       * catch (Exception e) { // DEBUG System.out.println("Error initializing remote game management object."); e.printStackTrace(); }
       */
      // Objects used for testing, will be provided by server in the future.
      /*
       * Deck starterDeck = new Deck(); starterDeck = Deck.getStarterDeck();
       * 
       * Deck mainDeck = new Deck(); mainDeck = Deck.getMainDeck();
       * 
       * Hand playerHand = new Hand(5); Hand tableHand = new Hand(5);
       * 
       * starterDeck.draw(playerHand); mainDeck.draw(tableHand); String[] playerNames = new String[] { "Player One", "Player Two", "Player Three" };
       */
      attack = 5;
      stealth = 5;
      isTurn = true;

      // KEEP. Puts imageviews into arrays.

      gameTableImages = new ImageView[] { gameTableCardOne, gameTableCardTwo, gameTableCardThree, gameTableCardFour, gameTableCardFive };

      playerHandImages = new ImageView[] { playerHandOne, playerHandTwo, playerHandThree, playerHandFour, playerHandFive, playerHandSix, playerHandSeven,
                                          playerHandEight, playerHandNine, playerHandTen, playerHandEleven, playerHandTwelve, playerHandThirteen };

      for (int i = 0; i < playerHandImages.length; i++)
      {
         playerHandImages[i].setId(null);
      }

      // Initializes the game table when page is opened. This includes
      // adding effects as well as populating fields.
      // Temp disable due to implementing things
      // initializeTable(playerHand, tableHand, playerNames);

      // Handles ending the turn on button clicked
      endTurn();

      // Handles action when a main table card is clicked
      // onTableCardClicked(mainDeck, stealth, attack);

      // Handles actions when a player's hand card is clicked
      // onPlayerCardClicked();

      // Demo for discard state
      /*
       * Card c = new Card("Overhaul"); Hand h = new Hand(3); h.addCard(starterDeck.draw()); h.addCard(starterDeck.draw()); h.addCard(starterDeck.draw());
       * Action a = new Action(7, 1, c, h); determineAction(a);
       */
   }

   private void initRemoteObject()
   {
      Random rnd = new Random();
      boolean flag = true;
      while (flag)
      {
         int id = rnd.nextInt();
         FacadeClient thing = new FacadeClient();
         thing.c = (Client) this;
         String path = "//localhost/client" + id;
         try
         {
            Naming.rebind(path, thing);
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
      }
   }

   /**
    * Helper method pulls a card to the front of the display. Intended to be used with the hand card objects.
    * 
    * @param imageToShow The image that you wish to pull to the front of the display.
    */
   private void showOnMouseEntered(ImageView imageToShow)
   {
      imageToShow.setOnMouseEntered(event ->
      {
         imageToShow.toFront();
      });
   }

   /**
    * Helper method associates a listener to each passed image that adds a highlight effect to an image on mouse hover.
    * 
    * @param imageToHighlight The image that you wish to add the highlight effect to.
    */
   private void highlightOnMouseEntered(ImageView imageToHighlight)
   {
      imageToHighlight.setOnMouseEntered(event ->
      {
         // Create ambient light
         Light.Distant light = new Light.Distant();
         light.setAzimuth(-135.0);

         // Create lighting effect
         Lighting lighting = new Lighting();
         lighting.setLight(light);
         lighting.setSurfaceScale(4.0);

         // Apply lighting.
         imageToHighlight.setEffect(lighting);
      });
   }

   /**
    * Helper method that removes any effect added to an image when the mouse is no longer hovering over it. Intended to be used with the
    * highlightOnMouseEntered() method.
    * 
    * @param imageToUnhighlight The image that you wish to remove effects from.
    */
   private void unhighlightOnMouseExited(ImageView imageToUnhighlight)
   {
      imageToUnhighlight.setOnMouseExited(event ->
      {
         imageToUnhighlight.setEffect(null);
      });
   }

   /**
    * Helper method that takes care of adding the highlight effect to the appropriate cards.
    */
   private void highlightEffect()
   {

      highlightOnMouseEntered(biteDeckImage);
      highlightOnMouseEntered(lurkDeckImage);
      highlightOnMouseEntered(notSoImportantHistoricalFigureImage);
      highlightOnMouseEntered(playerDeckImage);

      for (int i = 0; i < gameTableImages.length; i++)
      {
         highlightOnMouseEntered(gameTableImages[i]);
      }

      // ///////////////////////////////////////////////////////////////

      unhighlightOnMouseExited(biteDeckImage);
      unhighlightOnMouseExited(lurkDeckImage);
      unhighlightOnMouseExited(notSoImportantHistoricalFigureImage);
      unhighlightOnMouseExited(playerDeckImage);

      for (int i = 0; i < gameTableImages.length; i++)
      {
         unhighlightOnMouseExited(gameTableImages[i]);
      }

   }

   /**
    * Helper method that takes care of the "bring to front" effect on the appropriate cards.
    */

   private void showCard()
   {

      for (int i = 0; i < playerHandImages.length; i++)
      {
         showOnMouseEntered(playerHandImages[i]);
      }

   }

   @Override
   public void initializeTable(Hand playerHand, Hand gameTableHand, String[] playerNames)
   {

      System.out.println("Initialize table happened.");

      // Add highlight effects
      highlightEffect();

      // Add bring to front effects
      showCard();

      // Set the claw, lurk, and notSoImportantHistoricalFigure deck images
      biteDeckImage.setImage(new Image("cards/bite.png"));
      biteDeckImage.setId("Bite");
      lurkDeckImage.setImage(new Image("cards/lurk.png"));
      lurkDeckImage.setId("Lurk");
      notSoImportantHistoricalFigureImage.setImage(new Image("cards/notSoImportantHistoricalFigure.png"));
      notSoImportantHistoricalFigureImage.setId("Not So Important Historical Figure");

      // Set the face down card image.
      playerDeckImage.setImage(new Image("cards/faceDownCard.png"));

      // Set button text
      endTurnButton.setText("End Turn");

      // Set cards in deck label
      cardsInGameDeckLabel.setText("Cards In Deck: 10");

      // Set player VP labels
      if (playerNames.length == 1)
      {
         playerOneVP.setText(playerNames[0] + ": 1,000 BCE");
         yourVP.setText("You are in year 1,000 BCE");
      }

      if (playerNames.length == 2)
      {
         playerOneVP.setText(playerNames[0] + ": 1,000 BCE");
         playerTwoVP.setText(playerNames[1] + ": 1,000 BCE");
         yourVP.setText("You are in year 1,000 BCE");
      }

      if (playerNames.length == 3)
      {
         playerOneVP.setText(playerNames[0] + ": 1,000 BCE");
         playerTwoVP.setText(playerNames[1] + ": 1,000 BCE");
         playerThreeVP.setText(playerNames[2] + ": 1,000 BCE");
         yourVP.setText("You are in year 1,000 BCE");
      }

      // Populate hand image fields for player and main table
      for (int i = 0; i < gameTableHand.size(); i++)
      {
         gameTableImages[i].setImage(new Image(gameTableHand.get(i).getImagePath()));
         gameTableImages[i].setId(gameTableHand.get(i).getName());
      }

      for (int i = 0; i < playerHand.size(); i++)
      {
         playerHandImages[i].setImage(new Image(playerHand.get(i).getImagePath()));
         playerHandImages[i].setId(playerHand.get(i).getName());
      }

   }

   /**
    * Action that covers what happens when a main table card is clicked. This method verifies first whether there is enough attack or stealth to get the card
    * and if true it creates an action to send to the server with the card clicked as well as sends the appropriate message to the play log.
    * 
    * @param image
    * @param deck
    * @param stealth
    * @param attack
    * @return
    */
   private Action onTableCardClickedEvent(ImageView image, Deck deck, int stealth, int attack)
   {
      image.setOnMouseClicked(event ->
      {
         if (isTurn)
         {
            // Check to see if player can afford card first.
            Card oldCard;
            try
            {
               oldCard = new Card(image.getId());
               if (oldCard.getCostAttack() > attack || oldCard.getCostStealth() > stealth)
               {
                  playLog.appendText("Can't afford that card. \n");
                  action = null;
               }

               else
               {

                  // Append action to the play log.

                  // System.out.println(oldCard.getCardType());
                  if (oldCard.getCardType().equals("Action"))
                  {
                     playLog.appendText("You stole card " + oldCard.getName() + ". " + oldCard.getDescription() + "\n");
                  }
                  else
                  {
                     playLog.appendText("You defeated " + oldCard.getName() + ". " + oldCard.getDescription() + "\n");
                  }

                  // Create action with old card
                  try
                  {
                     cardForAction = new Card(image.getId());
                  }
                  catch (Exception e)
                  {
                     e.printStackTrace();
                  }
                  action = new Action(1, cardForAction);
                  // System.out.println(action.getCard().getName());

                  // Change image to a new card's image, reset id to new
                  // card's
                  // name,

                  if (oldCard.getName().equals("Bite") || oldCard.getName().equals("Not So Important Historical Figure") || oldCard.getName().equals("Lurk"))
                  {
                  }
                  else
                  {
                     Card card = deck.draw();
                     image.setImage(new Image(card.getImagePath()));
                     image.setId(card.getName());
                  }
               }
            }
            catch (Exception e1)
            {
               e1.printStackTrace();
            }
         }
         else
         {
            action = null;
         }

      });
      return action;
   }

   /**
    * This method applies the tableCardClickedEvent to all the table cards.
    * 
    * @param deck
    * @param stealth
    * @param attack
    * @throws SQLException
    */

   private void onTableCardClicked(Deck deck, int stealth, int attack) throws SQLException
   {

      onTableCardClickedEvent(biteDeckImage, deck, stealth, attack);
      onTableCardClickedEvent(lurkDeckImage, deck, stealth, attack);
      onTableCardClickedEvent(notSoImportantHistoricalFigureImage, deck, stealth, attack);

      for (int i = 0; i < 5; i++)
      {
         onTableCardClickedEvent(gameTableImages[i], deck, stealth, attack);
      }
   }

   /**
    * This method sets boolean isTurn to false after the end button is clicked.
    */
   private void endTurn()
   {
      endTurnButton.setOnMouseClicked(event ->
      {
         isTurn = false;
         Attack.setText("0");
         Stealth.setText("0");
         attack = 0;
         stealth = 0;
      });
   }

   public void startTurn()
   {
      isTurn = true;
      playerTurnLabel.setText("Your turn");
   }

   /**
    * This method handles the required actions when a player clicks on a card in their hand. It takes the appropriate actions given the game state and prints
    * the appropriate message to the play log.
    * 
    * @param image
    * @return
    */
   private Action onPlayerCardClickedEvent(ImageView image)
   {

      image.setOnMouseClicked(event ->
      {

         if (image.getId() != null)
         {

            if (isTurn && isDiscard && counter > 0)
            {
               try
               {
                  Card c = new Card(image.getId());
                  playLog.appendText("You discarded card " + c.getName() + ". " + c.getDescription() + "\n");

                  lastDiscardImage.setImage(image.getImage());
                  image.setImage(null);
                  image.setId(null);

                  counter--;
                  if (counter == 0)
                     isDiscard = false;

                  action = null;

               }
               catch (Exception e)
               {
                  e.printStackTrace();
               }
            }
            if (isTurn && isTrash && counter > 0)
            {
               try
               {
                  Card c = new Card(image.getId());
                  playLog.appendText("You trashed card " + c.getName() + ". " + c.getDescription() + "\n");

                  image.setImage(null);
                  image.setId(null);

                  counter--;
                  if (counter == 0)
                     isTrash = false;

                  action = null;

               }
               catch (Exception e)
               {
                  e.printStackTrace();
               }
            }
            else if (isTurn)
            {
               try
               {
                  Card oldCard = new Card(image.getId());
                  playLog.appendText("You played card " + oldCard.getName() + ". " + oldCard.getDescription() + "\n");
                  lastDiscardImage.setImage(image.getImage());
                  image.setImage(null);
                  image.setId(null);
               }
               catch (Exception e)
               {
                  e.printStackTrace();
               }
            }

            else
            {
               action = null;
            }
         }
      });

      Update = false;
      return action;

   }

   /**
    * This sets onPlayerCardClickedEvent to each of the player's cards.
    */
   private void onPlayerCardClicked()
   {
      for (int i = 0; i < playerHandImages.length; i++)
      {
         onPlayerCardClickedEvent(playerHandImages[i]);
      }
   }

   @Override
   public void determineAction(Action a)
   {
      if (a.getAction() == action.PLAY_CARD)
      {
         playCard(a);
      }
      else if (a.getAction() == action.AQUIRE_CARD)
      {
         acquireCard(a);
      }
      else if (a.getAction() == action.DISCARD)
      {
         discardCard(a);
      }
      else if (a.getAction() == action.TRASH)
      {
         trashCard(a);
      }
      else if (a.getAction() == action.DRAW)
      {
         drawCards(a);
      }
   }

   /**
    * This method handles action playCard by getting the card from the Action object and building the appropriate play log message from it.
    * 
    * @param a
    */
   private void acquireCard(Action a)
   {
      Card c = a.getCard();
      if (c.getCardType().equals("Action"))
      {
         playLog.appendText(a.getPlayerName() + " stole card " + c.getName() + ". " + c.getDescription() + "\n");
      }
      else
      {
         playLog.appendText("Player defeated " + c.getName() + ". " + c.getDescription() + "\n");
      }

   }

   /**
    * This method handles the action aquireCard by getting the card from the Action object and building the appropriate play log message from it.
    * 
    * @param a
    */
   private void playCard(Action a)
   {
      playLog.appendText(a.getPlayerName() + " played card " + a.getCard().getName() + ". " + a.getCard().getDescription() + "\n");
   }

   /**
    * This method handles the discardCard action.
    * 
    * @param a
    */
   private void discardCard(Action a)
   {
      Card c = a.getCard();
      int discard = 0;

      if (c.getPreturnDiscard() != 0)
         discard = c.getPreturnDiscard();
      else
         discard = c.getPostturnDiscard();
      isDiscard = true;
      counter = discard;
   }

   /**
    * This method handles the trash card state.
    * 
    * @param a
    */
   private void trashCard(Action a)
   {
      Card c = a.getCard();
      int trash = 0;

      if (c.getTrashCardsMandatory() != 0)
         trash = c.getTrashCardsMandatory();
      else
         trash = c.getTrashCardsOptional();
      isTrash = true;
      counter = trash;
   }

   public void drawCards(Action a)
   {
      int j = 0;

      for (int i = 0; i < a.getHand().size(); i++)
      {
         while (playerHandImages[j].getImage() != null)
         {
            j++;
         }
         playerHandImages[j].setImage(new Image(a.getHand().get(i).getImagePath()));
         playerHandImages[j].setId(a.getHand().get(i).getName());
      }

      onPlayerCardClicked();

   }

   public void setGameEngine(String ge)
   {

      // connect to the game engine
      while (true)
      {
         try
         {
            this.gameEngine = (GameEngine) Naming.lookup(ge);
            break;
         }
         catch (MalformedURLException | RemoteException | NotBoundException e)
         {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
      }
   }

   public void addStealth(int st)
   {
      stealth += st;
      Stealth.setText("" + stealth + "");
   }

   public void addAttack(int at)
   {
      attack += at;
      Attack.setText("" + attack + "");
   }
   
   /**
    * This method will allow for the injection of each screen's parent.
    */
   @Override
   public void setScreenParent(AbstractScreenController screenParent)
   {
      parentController = (MainController) screenParent;
   }
}