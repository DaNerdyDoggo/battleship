import java.util.Scanner;

public class GameRunner
{
   private GameBoard player1GuessBoard;
   private GameBoard player1PlayerBoard;
   private GameBoard player2GuessBoard;
   private GameBoard player2PlayerBoard;
   private int turn;
   private Scanner inp;

   public GameRunner()
   {
      player1GuessBoard = new GameBoard();
      player1PlayerBoard = new GameBoard();
      player2GuessBoard = new GameBoard();
      player2PlayerBoard = new GameBoard();
      turn = 1;
      inp = new Scanner(System.in);
   }

   //clear text
   public void gamePlay()
   { 
      //intro maybe
      //Game Setup
      System.out.print("Player 1 is now placing their ships! Player 2 should now turn away from the screen.\nPlayer 1 should press ENTER when ready.");
      inp.nextLine();
      
      player1PlayerBoard.placeShips(inp, "Carrier", 5,0);
      player1PlayerBoard.placeShips(inp, "Battleship", 4,1);
      player1PlayerBoard.placeShips(inp, "Submarine 1", 2,2);
      player1PlayerBoard.placeShips(inp, "Submarine 2", 2,3);
      
      //clear screen
      System.out.print("Player 2 is now placing their ships! Player 1 should now turn away from the screen.\nPlayer 2 should press ENTER when ready.");
      inp.nextLine();

      player2PlayerBoard.placeShips(inp, "Carrier", 5,0);
      player2PlayerBoard.placeShips(inp, "Battleship", 4,1);
      player2PlayerBoard.placeShips(inp, "Submarine 1", 2,2);
      player2PlayerBoard.placeShips(inp, "Submarine 2", 2,3);
      
      //clear console
      while(turn>0)
      {
         int pGuess;
         if(turn % 2 ==0)
         {
            //clear console
            System.out.println("Player 2 is now going to make a move! Player 1 should turn away from the screen.\nPress ENTER when ready.");
            inp.nextLine();
            //player 1 guess info
         }
         else
         {
            if(turn > 1)
            {
               //clear console
               System.out.println("Player 1 is now going to make a move! Player 2 should turn away from the screen.\nPress ENTER when ready.");
               inp.nextLine();
               //player 2 guess info
            }
            else
            {
               System.out.println("Player 1 is now going to make the first move! Player 2 should turn away from the screen.\nPress ENTER when ready.");
               inp.nextLine();
            }
            
            viewPlayerBoard(player1GuessBoard);
            
            pGuess = player1GuessBoard.guess(inp, player2PlayerBoard);
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
      }
   }
   
   //method that will return the string with the miss/hit and the info the player will recieve 

 
}
