import java.util.Scanner;

public class GameBoard
{
   private final String X_COORDINATES = "ABCDEFGHIJ";
   private String[][] grid;
   private Ships[] ships;
   private String Y_COORD_CHECK = "0123456789";
   
   public GameBoard()
   {
      grid = new String[10][10];
      ships = new Ships[4];

      for(int row=0;row<grid.length;row++)
      {
         for(int col=0;col<grid[0].length;col++)
         {
            grid[row][col] = "~";
         }
      }
   }

   //method that prints grid
   // 2 spaces
   public String printGrid()
   {
      String g = "   ";
      

      for(int i =0; i < X_COORDINATES.length(); i++)
      {
         g += X_COORDINATES.substring(i,i+1) + "  ";
      }

      g += "\n";

      for(int row = 0; row < grid.length; row++)
      {
         
         g += (row) + "  ";
         
         for(String s: grid[row])
         {            
            g += s + "  ";
         }
   
         g+= "\n";
         
         
      }
      return g;
   }
   
   //method for getting and checking player guesses
   //returns -1 if miss and 0-4 if hit
   // add player parameter
   public int guess(Scanner input, GameBoard b)
   {
      int isHit = 0;
   
      String[][] otherBoard = b.getGrid();
      
      // are you sure?
      System.out.print(printGrid() + "\nWhat is your guess?: ");
      String coord = input.nextLine().toUpperCase();
      
      if(!isInvalidCoord(coord))
      {
         System.out.println("Invlaid coordinate");
         guess(input, b);
      }
      
      if(otherBoard[getYCoord(coord)][getXCoord(coord)].equals("~"))
      {
         grid[getYCoord(coord)][getXCoord(coord)] = "O";
         b.setGrid(getXCoord(coord), getYCoord(coord), "O");
         isHit = -1;
      }
      else if(otherBoard[getYCoord(coord)][getXCoord(coord)].equals("X") || otherBoard[getYCoord(coord)][getXCoord(coord)].equals("O"))
      {
         System.out.println("You have already guessed this spot! Pick another. (Press ENTER to continue)");
         input.nextLine();
         guess(input, b);
      }
      else
      {
         grid[getYCoord(coord)][getXCoord(coord)] = "X";
         b.setGrid(getXCoord(coord), getYCoord(coord), "X");
         isHit = checkHit(coord);
                     
      }
      
      return isHit;
   }

   //method that places ships on grid and adds them to ship list
   public void placeShips(Scanner input, String name, int len, int shipNum)
   {
      String sym = name.substring(0,1);
      String[] coordArr = new String[len];
   
      System.out.print("Where to you want to place your " + name+ "?: ");
      String coord = input.nextLine().toUpperCase();
      
      if(!isInvalidCoord(coord))
      {
         //clear console and add explaination if needed
         System.out.println("Invlaid coordinate");
         placeShips(input, name, len, shipNum);
      }
      
      System.out.println("Do you want to place you ship downwards or to the right? (Press 1 to place downwards or Press 2 to place to the right): ");
      String orin = input.nextLine();
      
      while(!orin.equals("1") || !orin.equals("2"))
      {
         if(orin.equals("1") || orin.equals("2"))
         {
            break;
         }
      
         //clear console
         System.out.println("Invalid input. Only 1 or 2 can be entered...\n(press ENTER to continue)");
         input.nextLine();
         
         System.out.println("Do you want to place you ship downwards or to the right? (Press 1 to place downwards or Press 2 to place to the right): ");
         orin = input.nextLine();
         
      }
      
      if(orin.equals("1"))
      {
         //vertical placement
         for(int col= 0; col < len; col++)
         {
            String c = X_COORDINATES.substring(getXCoord(coord), getXCoord(coord)+1) + (getYCoord(coord)+col);
            System.out.println(c);
            if(isInvalidCoord(c) && grid[col + getYCoord(coord)][getXCoord(coord)].equals("~"))
            {
            
                  grid[col + getYCoord(coord)][getXCoord(coord)] = sym;
                  coordArr[col] = c;
            }
            else
            {
               //invalid
               System.out.println("Ship is out of bounds or is overlapping with another ship! Choose another place for your " + name + "...\n(press ENTER to continue)");
               input.nextLine();
               
               placeShips(input, name, len, shipNum);
            }            
         }
         
      }
      else
      {
         //horiz placement 
         for(int row =0; row < len; row++)
         {
            String c = X_COORDINATES.substring((getXCoord(coord) + row),(getXCoord(coord) + row)+1) + getYCoord(coord);
            if(isInvalidCoord(c) && grid[getYCoord(coord)][getXCoord(coord) + row].equals("~"))
            {
               grid[getYCoord(coord)][getXCoord(coord) + row] = sym;
               coordArr[row] = c;
            }
            else
            {
               //invalid
               System.out.println("Ship is out of bounds or is overlapping with another ship! Choose another place for your " + name + "...\n(press ENTER to continue)");
               input.nextLine();
               
               placeShips(input, name, len, shipNum);

            }
         }
      }
            
      System.out.println(printGrid() + "\nComfirm placement?(y/n): ");
      String place = input.nextLine().toLowerCase();
      
      while(!place.equals("y") || !place.equals("n"))
      {
         if(place.equals("y") || place.equals("n"))
         {
            break;
         }
         
         System.out.println("invalid input");
         System.out.println(printGrid() + "\nComfirm placement?(y/n): ");
         place = input.nextLine();
      }
      
      if(place.equals("n"))
      {
         for(String c: coordArr)
         {
            grid[getYCoord(c)][getXCoord(c)] = "~";
         }
         placeShips(input, name, len, shipNum);
      }
      else
      {
         ships[shipNum] = new Ships(name, sym, coordArr);
      }
      
   }
      
   public boolean isInvalidCoord(String c)
   {
      if(c.length() != 2)
      {
         return false;
      }
      else if(getXCoord(c) == -1)
      {
         return false;
      }
      else if(getYCoord(c) == -1)
      {
         //need to check if y is not a int
         return false;
      }
      else
      {
         return true;
      }
   }
   
   public int getXCoord(String c)
   {
      String x = c.substring(0,1);
      return X_COORDINATES.indexOf(x);
   }
   
   public int getYCoord(String c)
   {
      String y = c.substring(1,2);
      return Y_COORD_CHECK.indexOf(y);
   }
   
   //accessor method for grid
   public String[][] getGrid()
   {
      return grid;
   }
   
   public void setGrid(int x, int y, String val)
   {
      grid[y][x] = val;
   }
   
   //accesses a ship in the object's ships array
   public Ships getShip(int index)
   {
      return ships[index];
   }
   
   //method that gives the index of which ship has been hit 
   public int checkHit(String coord)
   {
      int shipHit =0;
      for(int i=0; i<ships.length;i++)
      {
         String[] c = ships[i].getCoordinates();
         for(int j=0; j<c.length; j++)
         {
            if(c[j].equals(coord))
            {
               shipHit = i;
            }
         }
      }
      ships[shipHit].markHitCoord(coord);
      return shipHit;
   }
}