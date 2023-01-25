public class Ships
{
   private String name;
   private String symbol;
   //private String origin;
   private String[] coordinates;
   private boolean sunk;
   
   //for the ships 
   public Ships(String n,String s,String[] c)
   {
      name = n;
      symbol = s;
      coordinates = c;
      sunk = false;
   }
   
   //Getters for the symbol, coordinates, and sunk instance variables
   public String getName()
   {
      return name;
   }
   
   public String getSymbol()
   {
      return symbol;
   }
   
   public String[] getCoordinates()
   {
      return coordinates;
   }
   
   public boolean getSunk()
   {
      return sunk;
   }
   
   //setter for sunk
   //used to set true if a ship has been sunk
   public void setSunk(boolean s)
   {
      sunk = s;
   }
   
   //used if ship has been hit
   //will set hit coordinate to x 
   // make return if sunk
   public void markHitCoord(String c)
   {
      for(int i =0; i<coordinates.length; i++)
      {
         if(c.equals(coordinates[i]))
         {
            coordinates[i] = "X";
         }
      }
      
      if(checkSunk())
      {
         sunk = true;
      }
   }
   
   public boolean checkSunk()
   {
      for(String c: coordinates)
      {
         if(!c.equals("X"))
         {
            return false;
         }
      }
      return true;
   }

   

}