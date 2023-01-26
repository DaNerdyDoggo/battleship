import java.util.Scanner;

public class GameRunner
{
   private GameBoard player1GuessBoard;
   private GameBoard player1PlayerBoard;
   private GameBoard player2GuessBoard;
   private GameBoard player2PlayerBoard;
   private int turn, p1ShipsLeft, p2ShipsLeft;
   private Scanner inp;
   //private int p2ShipsLeft;
   //private int p1ShipsLeft;

   public GameRunner()
   {
      player1GuessBoard = new GameBoard();
      player1PlayerBoard = new GameBoard();
      player2GuessBoard = new GameBoard();
      player2PlayerBoard = new GameBoard();
      turn = 1;
      inp = new Scanner(System.in);

      p1ShipsLeft = 4;
      p2ShipsLeft = 4;
   }

   //clear text
   public void gamePlay()
   { 
      //intro maybe
      //Game Setup
      System.out.print("Player 1 is now placing their ships! Player 2 should now turn away from the screen.\nPlayer 1 should press ENTER when ready.");
      inp.nextLine();
      
      clearConsole();
      System.out.println(player1PlayerBoard.printGrid());
      player1PlayerBoard.placeShips(inp, "Carrier", 5,0);
      player1PlayerBoard.placeShips(inp, "Battleship", 4,1);
      player1PlayerBoard.placeShips(inp, "Submarine 1", 2,2);
      player1PlayerBoard.placeShips(inp, "Submarine 2", 2,3);
      
      clearConsole();
      System.out.print("Player 2 is now placing their ships! Player 1 should now turn away from the screen.\nPlayer 2 should press ENTER when ready.");
      inp.nextLine();

      clearConsole();
      System.out.println(player2PlayerBoard.printGrid());
      player2PlayerBoard.placeShips(inp, "Carrier", 5,0);
      player2PlayerBoard.placeShips(inp, "Battleship", 4,1);
      player2PlayerBoard.placeShips(inp, "Submarine 1", 2,2);
      player2PlayerBoard.placeShips(inp, "Submarine 2", 2,3);
      
      clearConsole();
      
      while(true)
      {
         int pGuess =0;
         if(turn % 2 ==0)
         {
            clearConsole();
            System.out.println("Player 2 is now going to make a move! Player 1 should turn away from the screen.\nPress ENTER when ready.");
            inp.nextLine();
            //player 1 guess info
            System.out.println(guessOutput(pGuess, "Player 2", false, player1PlayerBoard));
            viewPlayerBoard(player2GuessBoard);
            
            pGuess = player2GuessBoard.guess(inp, player1PlayerBoard);

            System.out.println(guessOutput(pGuess, "Player 2", true, player1PlayerBoard));
            p1ShipsLeft = player1PlayerBoard.whichLeft();
            System.out.println("Press ENTER to end turn...");
            inp.nextLine();
         }
         else
         {
            if(turn > 1)
            {
               clearConsole();
               System.out.println("Player 1 is now going to make a move! Player 2 should turn away from the screen.\nPress ENTER when ready.");
               inp.nextLine();
               //player 2 guess info
               System.out.println(guessOutput(pGuess, "Player 1", false, player2PlayerBoard));
               System.out.println("Press ENTER to continue...");
               inp.nextLine();
            }
            else
            {
               clearConsole();
               System.out.println("Player 1 is now going to make the first move! Player 2 should turn away from the screen.\nPress ENTER when ready.");
               inp.nextLine();

            }
            
            viewPlayerBoard(player1GuessBoard);
            
            pGuess = player1GuessBoard.guess(inp, player2PlayerBoard);

            System.out.println(guessOutput(pGuess, "Player 1", true, player2PlayerBoard));
            p2ShipsLeft = player2PlayerBoard.whichLeft();

            System.out.println("Press ENTER to end turn...");
            inp.nextLine();
         }
         
         if(p1ShipsLeft == 0)
         {
            System.out.println("Player 2 Wins!");
            break;
         }
         else if(p2ShipsLeft == 0)
         {
            System.out.println("Player 1 Wins!");
            break;
         }
         else
         {
            turn++;
            continue;
         }
      
      }
      
      //close input when done
      inp.close();
   }
   
   public void viewPlayerBoard(GameBoard board)
   {
      System.out.println("Do you want to look at your board?(y/n): ");
      String ans = inp.nextLine().toLowerCase();
      
      while(!ans.equals("y") || !ans.equals("n"))
      {
         if(ans.equals("y") || ans.equals("n"))
         {
            break;
         }
      
         System.out.println("Invalid input. Only y or n can be entered...\n(press ENTER to continue)");
         inp.nextLine();
         
         System.out.println("Do you want to look at your board?(y/n): ");
         ans = inp.nextLine().toLowerCase();
      }
      
      if(ans.equals("y"))
      {
         System.out.println(board.printGrid());
         System.out.println("Press ENTER to continue...");
         inp.nextLine();
      }
   }
   
   //method that will return the string with the miss/hit and the info the player will recieve 
   //true for player that guess false for opposing player

   public String guessOutput(int ind, String player, boolean p, GameBoard board)
   {

      String s ="";

      if(p)
      {
         if(ind == -1)
         {
            s = "You missed";
         }
         else
         {
            s = "You hit a ship";
         }
      }
      else
      {
         if(ind == -1)
         {
            s = player + " has missed in their turn";
         }
         else
         {
            s = player + " hit your " + board.getShip(ind).getName();
         }
      }

      return s;
   }

   public void clearConsole()
   {
      String clr = "";

      for(int i =0; i< 100; i++)
      {
         clr += "\n";
      }
      System.out.print(clr);
   }

 
}
